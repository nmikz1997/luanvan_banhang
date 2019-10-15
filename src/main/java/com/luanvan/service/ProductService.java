package com.luanvan.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.luanvan.dto.response.ProductDTO;
import com.luanvan.dto.response.ProductDetailDTO;
import com.luanvan.dto.response.ProductSearchDTO;
import com.luanvan.model.Product;

public interface ProductService {
	
	List<Product> findAll();
	
	Set<ProductDTO> selectAll();
	
	//List<Product> findByStore();
	
	List<Product> findByName(String name);
	
	Map<String,Object> findById(Long id);
	
	ProductDTO findDTOById(Long id);
	
	String save(String productReq, MultipartFile fileupload, Authentication auth) throws IOException;
	
	List<ProductDTO> findByCategory(Long categoryId);
	
	void delete(Long id);
	
	void deleteByCategoryId(Long categoryId);

	void update(Product product);
	
	//san pham dang khuyen mai
	List<Product> findProductPromotion(Date dayStart,Date dayEnd);

	ProductDTO findProducts(Long id);

	List<ProductDTO> listSPKM();

	List<ProductDTO> listSPMoi();
	
	ProductDetailDTO chiTietSanPham(Long id);

	List<ProductDTO> productsInIds(List<Long> ids);

	List<Product> findByStore(Authentication auth);

	List<ProductDTO> searchBy(String name, Long categoryId);

	//Page<ProductDTO> findbyPlugName(String name, String cate, String material, String origin, String producer, int minPrice, int maxPrice, int page);

	Page<ProductDTO> findbyPlugName(
			String name, 
			String cate, 
			String material,
			String origin, 
			String producer, 
			float ratting,
			int minPrice, 
			int maxPrice, 
			int page);

	Page<Product> testSearch();
}
