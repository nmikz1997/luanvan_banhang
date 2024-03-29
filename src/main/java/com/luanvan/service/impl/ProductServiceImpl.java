package com.luanvan.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luanvan.dto.request.CreateProduct;
import com.luanvan.dto.response.InventoryProductDTO;
import com.luanvan.dto.response.ProductDTO;
import com.luanvan.dto.response.ProductDetailDTO;
import com.luanvan.dto.response.TopSeller;
import com.luanvan.exception.RollbackException;
import com.luanvan.model.Category;
import com.luanvan.model.CustomUserDetails;
import com.luanvan.model.Image360;
import com.luanvan.model.Inventory;
import com.luanvan.model.Picture;
import com.luanvan.model.Product;
import com.luanvan.model.Store;
import com.luanvan.repo.CategoryRepository;
import com.luanvan.repo.InventoryRepository;
import com.luanvan.repo.PictureRepository;
import com.luanvan.repo.PriceRepository;
import com.luanvan.repo.ProductRepository;
import com.luanvan.repo.RealPictureRepository;
import com.luanvan.repo.StoreRepository;
import com.luanvan.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	
	private ProductRepository productRepository;
	private PriceRepository priceRepository;
	private StoreRepository storeRepository;
	private CategoryRepository categoryRepository;
	private PictureRepository pictureRepository;
	private InventoryRepository inventoryRepository;
	private RealPictureRepository image360Repository;
	
	@Autowired
	public ProductServiceImpl(
			ProductRepository productRepository,
			PriceRepository priceRepository,
			StoreRepository storeRepository,
			CategoryRepository categoryRepository,
			PictureRepository pictureRepository,
			InventoryRepository inventoryRepository,
			RealPictureRepository image360Repository
			) {
		this.productRepository 	= productRepository;
		this.priceRepository	= priceRepository;
		this.storeRepository	= storeRepository;
		this.categoryRepository	= categoryRepository;
		this.pictureRepository	= pictureRepository;
		this.inventoryRepository= inventoryRepository;
		this.image360Repository = image360Repository;
	}
	
	@Override
	public List<Product> findByStore(Authentication auth) {
		Store store = storeRepository.findByUserEmail(auth.getName());
		return productRepository.findByStoreId(store.getId());
	}
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> findByName(String name) {
		return productRepository.findByNameIgnoreCaseContaining(name);
	}

	@Override
	@Transactional
	public Map<String,Object> findById(Long id) {
		Product product = productRepository.findById(id).get();
		
		Map<String, Object> res = new HashMap<>();
		
		res.put("product", product);
		res.put("picture", product.getPictures());
		
		return res;
	}
	
	@Transactional
	@Override
	public String save(String productReq,MultipartFile fileupload, Authentication auth) throws IOException {
		
		//ánh xạ dữ liệu
		ObjectMapper mapper = new ObjectMapper();
		CreateProduct productDTO = mapper.readValue(productReq, CreateProduct.class);
		
		//new đối tượng product
		Product product = new Product();
		product = productDTO.getProduct();
		product.setAttributeValues(productDTO.getAttributeValues());
		product.setPlug(covertToString(product.getName()));
		
		Store store = storeRepository.findByUserEmail(auth.getName());
		
		if(product.getId() != null && productDTO.getProduct().getStore().getId() != store.getId()) {
			throw new RollbackException("Bạn không phải chủ món hàng này!!");
		}else if(product.getId() == null){//tao moi
			product.setStore(store);
			product.setStatus(0);
		}
		
		if(fileupload != null) {
			//đường dẫn của ảnh
			String uploadingDir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\picture\\";
			
			//xóa ảnh cũ
			File file = new File(uploadingDir + product.getAvatar());
			Path path = Paths.get(uploadingDir + product.getAvatar());
			//check ton tai anh
			if(Files.exists(path)) {
				file.delete();
			}
			
			//lấy tên ảnh mới
			String fileName = store.getId()+"-"+System.currentTimeMillis()+"-"+fileupload.getOriginalFilename();
			//nếu không cập nhật ảnh thì không set lại avatar
			product.setAvatar(fileName);
			
			//lưu ảnh moi
			file = new File(uploadingDir + fileName);
			fileupload.transferTo(file);
		};
		
		Product productnew = productRepository.save(product);
		
		if(product.getStatus() == 0){//tao moi
			Inventory inventory = new Inventory();
			inventory.setProduct(productnew);
			inventory.setQuantity(productnew.getQuantity());
			inventory.setImportDate(new Date());
			inventoryRepository.save(inventory);
		}
		
		productDTO.getProduct().getPrices().forEach(price ->{
			price.setProduct(productnew);
			priceRepository.save(price);
		});
		
		return "thêm và upload thành công";
	
	}
	
	@Override
	public List<ProductDTO> findByCategory(Long categoryId) {
		List<Category> categories = categoryRepository.findByParentId(categoryId);		
		List<Product> products = productRepository.findByCategoryIn(categories);
		
		ModelMapper mapper = new ModelMapper();
		List<ProductDTO> productDTO = mapper.map(products,new TypeToken<List<ProductDTO>>(){}.getType());
		return productDTO.stream()
						.limit(12)
						.collect(Collectors.toList());
	}

	@Override
	public void deleteByCategoryId(Long categoryId) {
		productRepository.deleteByCategoryId(categoryId);
	}
	
	@Override
	public void update(Product product) {
		Product pro = productRepository.getOne(product.getId());
		pro.setStatus(product.getStatus());
		productRepository.save(pro);
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public ProductDTO findDTOById(Long id) {	
		return null;
	}

	@Override
	public Set<ProductDTO> selectAll() {
		return null;
	}

	@Override
	public List<Product> findProductPromotion(Date dayStart,Date dayEnd) {		
		//List<Product> products = productRepository.findByPromotionsDayStartBeforeAndPromotionsDayEndAfter(dayStart, dayEnd);
		return null;
	}

	@Override
	public ProductDTO findProducts(Long id) {
		Product product = productRepository.findById(id).get();
		ModelMapper modelMapper = new ModelMapper();
 		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
		return productDTO;
	}
	
	@Override
	public List<ProductDTO> listSPMoi() {
		List<Product> products = productRepository.findFirst12ByStatusOrderByIdDesc(1, PageRequest.of(0, 12, Sort.by("id").descending()));
		ModelMapper mapper = new ModelMapper();
		List<ProductDTO> productDTO = mapper.map(products,new TypeToken<List<ProductDTO>>(){}.getType());
		return productDTO;
	}

	@Override
	public List<ProductDTO> listSPKM() {
		List<Product> products = productRepository.findFirst12ByStatusOrderByIdDesc(1, PageRequest.of(0, Integer.MAX_VALUE));
		ModelMapper mapper = new ModelMapper();
		List<ProductDTO> productDTO = mapper.map(products,new TypeToken<List<ProductDTO>>(){}.getType());
		return productDTO.stream()
						.filter(product -> product.getMaxPromotion().getId() != null)
						.limit(6)
						.collect(Collectors.toList());
	}
	
	@Override
	public ProductDetailDTO chiTietSanPham(Long id) {
		Product product = productRepository.findById(id).get();
		ModelMapper modelMapper = new ModelMapper();
 		ProductDetailDTO productDetailDTO = modelMapper.map(product, ProductDetailDTO.class);
		return productDetailDTO;
	}

	@Override
	public List<ProductDTO> productsInIds(List<Long> ids) {
		ModelMapper mapper = new ModelMapper();
		List<ProductDTO> productDTO = mapper.map(productRepository.findByIdInAndStatus(ids,1),new TypeToken<List<ProductDTO>>(){}.getType());
		return productDTO;
	}
	
	
	public String covertToString(String value) {
	      try {
	            String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
	            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	            return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
	      } catch (Exception ex) {
	            ex.printStackTrace();
	      }
	      return null;
	}
	
	//tìm tất cả sản phẩm hợp lệ
	//sản phẩm mà cửa hàng còn thời hạn member

	@Override
	public List<ProductDTO> searchBy(String name, Long categoryId) {
		List<Product> products = productRepository.findAll();
		ModelMapper mapper = new ModelMapper();
		List<ProductDTO> productdto = mapper.map(products, new TypeToken<List<ProductDTO>>(){}.getType());
		return productdto;
	}

	@Override
	public Page<ProductDTO>findbyPlugName(
			String name, 
			Long cate, 
			String material,
			String origin, 
			String producer,
			String store,
			float ratting,
			String filter,
			int page)
	{
		name = covertToString("%"+name+"%");
		
		Pageable pageable = PageRequest.of(page,8);
		
		if(filter.equalsIgnoreCase("priceAsc")) {
			Sort sort = new Sort(Sort.Direction.ASC, "pri.unitPrice");
			pageable = PageRequest.of(page,8, sort);
		}else if(filter.equalsIgnoreCase("priceDesc")) {
			Sort sort = new Sort(Sort.Direction.DESC, "pri.unitPrice");
			pageable = PageRequest.of(page,8, sort);
		}else if(filter.equalsIgnoreCase("newest")) {
			Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
			pageable = PageRequest.of(page,8, sort);
		}else if(filter.equalsIgnoreCase("bestStar")) {
			Sort sort = new Sort(Sort.Direction.DESC, "avgStar");
			pageable = PageRequest.of(page,8, sort);
		}else if(filter.equalsIgnoreCase("bestSeller")) {
			Sort sort = JpaSort.unsafe(Sort.Direction.DESC, "count(dt.product.id)");
			pageable = PageRequest.of(page,8, sort);
		}
		
		Page<Product> products;
		if(cate != null) {
			products = productRepository.pageProducts(
					name,
					material,
					origin, 
					producer,
					store,
					ratting,
					cate,
					pageable
					);
		}else {
			products = productRepository.pageProducts(
					name,
					material,
					origin, 
					producer,
					store,
					ratting,
					pageable
					);
		}
		
		
		
		Page<ProductDTO> productdto = products
			.map(new Function<Product, ProductDTO>() {
				@Override
			    public ProductDTO apply(Product entity) {
					ModelMapper mapper = new ModelMapper();
			    	ProductDTO productDTO = mapper.map(entity,ProductDTO.class);
			        return productDTO;
			    }
		});
		
		return productdto;
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
	{
	    Map<Object, Boolean> map = new ConcurrentHashMap<>();
	    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
	@Override
	public Page<Product> testSearch(){
		Sort sort = Sort.by("createdAt").descending();
		Page<Product> products = productRepository.findAll(PageRequest.of(1,4,sort));
		return products;
	}

	@Override
	public ResponseEntity<?> uploadImage(Long storeId,int productId, MultipartFile[] uploadfiles) {
		try {
			Product productmap = productRepository.getOne((long) productId);
			for(MultipartFile uploadedFile : uploadfiles) {
				String file = uploadedFile.getOriginalFilename();

			    String filename = storeId +"_"+System.currentTimeMillis()+"_"+file;
			    String directory = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\picture\\";
			    String filepath = directory+filename;

			    Picture image = new Picture();
			    image.setPath(filename);
			    image.setProduct(productmap);
			    pictureRepository.save(image);
			 
			    BufferedOutputStream stream =
			        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			    stream.write(uploadedFile.getBytes());
			    stream.close();
	        }
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception e) {
		    System.out.println(e.getMessage());
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	

	@Override
	public void doiGia(Product product) {
		Product capnhat = productRepository.getOne(product.getId());
		capnhat.setQuantity(product.getQuantity());
		
		productRepository.save(capnhat);
	}

	@Override
	public List<TopSeller> bestSeller(int limit) {
		return productRepository.bestSeller(limit);
	}

	@Override
	public InventoryProductDTO findInventory(Long productId) {
		Product product = productRepository.findById(productId).get();
		ModelMapper mapper = new ModelMapper();
		InventoryProductDTO dto = mapper.map(product, InventoryProductDTO.class);
		
		return dto;
	}
	
	
	@Override
	@Transactional
	public void createInventory(Inventory inventory,Long productId) {
		Product product = productRepository.getOne(productId);
		product.setQuantity( product.getQuantity() + inventory.getQuantity() );
		productRepository.save(product);
		inventoryRepository.save(inventory);
	}

	@Override
	public ResponseEntity<?> uploadImage360(int product, MultipartFile[] uploadfiles,@AuthenticationPrincipal CustomUserDetails user) {
		try {
			Product productmap = productRepository.getOne((long)product);
			String directory = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\picture\\";
			int i = 0;
			for(MultipartFile uploadedFile : uploadfiles) {
					String file = uploadedFile.getOriginalFilename();
					Long storeid = user.getStoreId();
					String filename = storeid+"_"+System.currentTimeMillis()+"_"+file;
					String filepath = directory+filename;

				    Image360 image360 = new Image360();
				    image360.setPath(filename);
				    image360.setProduct(productmap);
				    image360.setStatus(++i);
				    image360Repository.save(image360);
				 
				    BufferedOutputStream stream =
				        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
				    stream.write(uploadedFile.getBytes());
				    stream.close();
				 
	        }
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception e) {
		    System.out.println(e.getMessage());
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public List<Image360> allImage360Product(Long productid) {
		return image360Repository.image360Product(productid);
	}

	@Override
	public void updateSttImage360(List<Image360> image360s, Long productid) {
		Product product = productRepository.getOne(productid);
		for(Image360 image360 : image360s) {
			image360.setProduct(product);;
			image360Repository.save(image360);
		}
	}

	@Override
	public Map<String, String> deleteImage360(List<Long> imagesID) {
		Map<String, String> map = new HashMap<String, String>();
		imagesID.forEach(id->{
			Image360 image360 = image360Repository.getOne(id);
			image360Repository.delete(image360);
			String directory = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\uploads\\";
		  	// Delete image old
		  	String deleteFilePath = directory + image360.getPath() ;
		  	File filePath = new File(deleteFilePath);
		  	Path path =  Paths.get(deleteFilePath);
		  	//check file path exits
		  	if(Files.exists(path)) {
		  		filePath.delete();
		  	}
			
		});
		map.put("success", "Xóa thành công");
		return map;
	}

	@Override
	public List<Product> findByStoreId(Long storeId) {
		return productRepository.findByStoreId(storeId);	
	}

	@Override
	@Transactional
	public void updateByStore(Product product, Long storeId) {
		Product pro = productRepository.getOne(product.getId());

		if(pro.getStore().getId() != storeId) {
			throw new RollbackException("Bạn không phải chủ món hàng này!!");
		}else if(pro.getStatus() == 1 || pro.getStatus() == 2){
			pro.setStatus(product.getStatus());
			productRepository.save(pro);
		}
		
		
	}

}
