package com.luanvan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luanvan.dto.response.CartItemsDTO;
import com.luanvan.dto.response.ProductDTO;
import com.luanvan.dto.response.ProductDetailDTO;
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
	
	@PostMapping("/gio-hang")
	public List<ProductDTO> gioHang(@RequestBody List<CartItemsDTO> req) {
		List<Long> ids = new ArrayList<Long>();
		req.forEach(product ->{
			ids.add(product.getId());
		});
		
		List<ProductDTO> dataCart = productService.productsInIds(ids);
		
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
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody Product product) {
		productService.update(product);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		productService.delete(id);
	}
	


}
