package com.luanvan.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luanvan.dto.request.CreateProduct;
import com.luanvan.dto.response.ProductDTO;
import com.luanvan.dto.response.ProductDetailDTO;
import com.luanvan.model.Product;
import com.luanvan.repo.PictureRepository;
import com.luanvan.repo.PriceRepository;
import com.luanvan.repo.ProductRepository;
import com.luanvan.repo.PromotionRepository;
import com.luanvan.repo.StoreRepository;
import com.luanvan.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	private ProductRepository productRepository;
	private PictureRepository pictureRepository;
	private PriceRepository priceRepository;
	private StoreRepository storeRepository;
	private PromotionRepository promotionRepository;
	private List<Long> ids;
	
	@Autowired
	public ProductServiceImpl(
			ProductRepository productRepository,
			PictureRepository pictureRepository,
			PriceRepository priceRepository,
			StoreRepository storeRepository,
			PromotionRepository promotionRepository
			) {
		this.productRepository 	= productRepository;
		this.pictureRepository 	= pictureRepository;
		this.priceRepository	= priceRepository;
		this.storeRepository	= storeRepository;
		this.promotionRepository= promotionRepository;
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

//	@Override
//	@Transactional
//	public void save(CreateProduct productDetail) {
//		
//		//lay id store theo user login
//		Long id = (long) 1;
//		
//		Product product = new Product();
//		product = productDetail.getProduct();
//		product.setAttributeValues(productDetail.getAttributeValues());
//		product.setStore(storeRepository.getOne(id));
//		productRepository.save(product);
//	}
	
	@Transactional
	@Override
	public String save(String productReq,MultipartFile fileupload) throws IOException {
		//Ví dụ đây là id cửa hàng
		Long Storeid = (long) 1;
		//ánh xạ dữ liệu
		ObjectMapper mapper = new ObjectMapper();
		CreateProduct productDTO = mapper.readValue(productReq, CreateProduct.class);
		//new đối tượng product
		Product product = new Product();
		product = productDTO.getProduct();
		product.setAttributeValues(productDTO.getAttributeValues());
		product.setStore(storeRepository.getOne(Storeid));

		
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
			String fileName = Storeid+"-"+System.currentTimeMillis()+"-"+fileupload.getOriginalFilename();
			//nếu không cập nhật ảnh thì không set lại avatar
			product.setAvatar(fileName);
			
			//lưu ảnh moi
			file = new File(uploadingDir + fileName);
			fileupload.transferTo(file);
		};
		
		Product productnew = productRepository.save(product);
		
		productDTO.getProduct().getPrices().forEach(price ->{
			price.setProduct(productnew);
			priceRepository.save(price);
		});
		
		return "thêm và upload thành công";
	
	}
	@Override
	public List<Product> findByCategory(Long categoryId) {
		return productRepository.findByCategory(categoryId);
	}

	@Override
	public void deleteByCategoryId(Long categoryId) {
		productRepository.deleteByCategoryId(categoryId);
	}

//	@Override
//	public List<Product> findByStore() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public void update(Product product) {
		productRepository.save(product);
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
		List<Product> products = productRepository.findAllByOrderByIdDesc();
		ModelMapper mapper = new ModelMapper();
		List<ProductDTO> productDTO = mapper.map(products,new TypeToken<List<ProductDTO>>(){}.getType());
		return productDTO;//danh sách sản phẩm mới
	}

	@Override
	public List<ProductDTO> listSPKM() {
		List<Product> products = findAll();
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
	
	private ProductDTO convertToDto(Product product) {
		ModelMapper modelMapper = new ModelMapper();
 		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

	@Override
	public List<ProductDTO> productsInIds(List<Long> ids) {
		ModelMapper mapper = new ModelMapper();
		List<ProductDTO> productDTO = mapper.map(productRepository.findByIdIn(ids),new TypeToken<List<ProductDTO>>(){}.getType());
		return productDTO;
	}

}
