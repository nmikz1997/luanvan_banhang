package com.luanvan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luanvan.dto.response.CartItemsDTO;
import com.luanvan.dto.response.InventoryProductDTO;
import com.luanvan.dto.response.ProductDTO;
import com.luanvan.dto.response.ProductDetailDTO;
import com.luanvan.dto.response.TopSeller;
import com.luanvan.model.CustomUserDetails;
import com.luanvan.model.Image360;
import com.luanvan.model.Inventory;
import com.luanvan.model.Product;
import com.luanvan.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	public ProductController (ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public List<Product> findByStore(Authentication auth) {
		return productService.findByStore(auth);
	}
	
	@GetMapping("/getByStore/{storeId}")
	public List<Product> findByStore(@PathVariable Long storeId) {
		return productService.findByStoreId(storeId);
	}
	
	@GetMapping("test")
	public Set<ProductDTO> selectAll() {
		return productService.selectAll();
	}
	
	@GetMapping("{id}")
	public Map<String,Object> findById(@PathVariable("id") Long id) {
		return productService.findById(id);
	}
	
	@GetMapping("test/{id}")
	public ProductDTO findDTOById(@PathVariable("id") Long id) {
		return productService.findDTOById(id);
	}
	
	@GetMapping("/category/{id}")
	public List<ProductDTO> findByCategory(@PathVariable("id") Long categoryId) {
		return productService.findByCategory(categoryId);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void save(
			@RequestParam(value = "model") String productReq,
			@RequestParam(value = "file", required = false) MultipartFile fileupload,
			Authentication auth) throws IOException {
		productService.save(productReq, fileupload, auth);
	}
	
	
	@GetMapping("/san-pham-dang-khuyen-mai/{id}")
	public ProductDTO findSanPhamKhuyenMai(@PathVariable("id") Long id) {
		return productService.findProducts(id);
	}
	
	@GetMapping("/chi-tiet-san-pham/{id}")
	public ProductDetailDTO chiTietSanPham(@PathVariable("id") Long id) {
		return productService.chiTietSanPham(id);
	}
	
	@GetMapping("/kiem-tra-status/{id}")
	public Boolean statusCheck(@PathVariable("id") Long id) {
		return productService.chiTietSanPham(id).getStatus() == 1;
	}
	
	@GetMapping("inventory/{productId}")
	public InventoryProductDTO getInventory(@PathVariable Long productId) {
		return productService.findInventory(productId);
	}
	
	@PostMapping("/gio-hang")
	public List<ProductDTO> gioHang(@RequestBody List<CartItemsDTO> req) {
		List<ProductDTO> dataCart = new ArrayList<ProductDTO>();
		if(req.toArray().length == 0) return dataCart;
		List<Long> ids = new ArrayList<Long>();
		req.forEach(product ->{
			ids.add(product.getId());
		});
		
		dataCart = productService.productsInIds(ids);
		
		dataCart.forEach(product ->{
			req.forEach(r -> {
				if(r.getId() == product.getId()) {
					product.setSoLuongMua(r.getQuantity());
				};
			});
		});
		
		return dataCart;
	}
	
	@GetMapping("/san-pham-dang-khuyen-mai")
	public List<ProductDTO> listSPKM(){
		return productService.listSPKM();
	}
	
	@GetMapping("/san-pham-moi")
	public List<ProductDTO> listSPMoi(){
		return productService.listSPMoi();
	}
	
	@GetMapping("/san-pham-danh-gia-cao")
	public List<ProductDTO> listTopStar(){
		return null;
	}
	
	@GetMapping("/san-pham-trong-khoang-gia")
	public List<ProductDTO> listPriceBetween(){
		return null;
	}
	
	@GetMapping("top-ban-chay/{limit}")
	public List<TopSeller> bestSeller(@PathVariable int limit){
		return productService.bestSeller(limit);
	}
	
	@PostMapping("san-pham-da-xem")
	public List<ProductDTO> listProductSeen(@RequestBody List<CartItemsDTO> req){
		List<Long> ids = new ArrayList<Long>();
		req.forEach(product ->{
			ids.add(product.getId());
		});
		
		List<ProductDTO> dataCart = productService.productsInIds(ids);
		return dataCart;
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody Product product) {
		productService.update(product);
	}
	
	@PutMapping("/update-by-store")
	@ResponseStatus(HttpStatus.OK)
	public void updateByStore(@RequestBody Product product,@AuthenticationPrincipal CustomUserDetails store) {
		productService.updateByStore(product, store.getStoreId());
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		productService.delete(id);
	}
	
	@PostMapping("/mutiple-image")
	@ResponseBody
	@Transactional
	public ResponseEntity<?> uploadFile(
			@AuthenticationPrincipal CustomUserDetails auth,
			@RequestParam(value = "productId") int productId,
			@RequestParam(value = "imgs") MultipartFile[] uploadfiles) throws IOException {
		Long storeId = auth.getStoreId();
		return productService.uploadImage(storeId,productId,uploadfiles);
	}
	
	@PostMapping("thay-doi-gia")
	public void thayDoiGia(@RequestBody Product product) {
		productService.doiGia(product);
	}
	
	@PutMapping("inventory/{productId}")
	public void createInventory(@RequestBody Inventory inventory, @PathVariable Long productId) {
		productService.createInventory(inventory,productId);
	}
	
	@PostMapping("/mutiple-image-360")
	@ResponseBody
	@Transactional
	public ResponseEntity<?> uploadImage360(
			@RequestParam(value = "product") int product,
			@RequestParam(value = "imgview360") MultipartFile[] uploadfiles,
			@AuthenticationPrincipal CustomUserDetails auth) throws IOException {
		return productService.uploadImage360(product, uploadfiles, auth);
	}
	
	@GetMapping("/hinh-cua-san-pham-360/{productid}")
	public List<Image360> imag360eOfPro(@PathVariable Long productid){
		return productService.allImage360Product(productid);
	}
	
	@PostMapping("/update-stt-image-360/{productid}")
	public void updateSttImage360(@RequestBody List<Image360> image360s, @PathVariable Long productid) {
		productService.updateSttImage360(image360s, productid);
	}
	
	@PostMapping("/image/deleteSelect-360/{productid}")
	public Map<String, String> deleteImage360Select(@RequestBody List<Long> imageIds, @PathVariable Long productid) {
		return productService.deleteImage360(imageIds);
	}
	
}
