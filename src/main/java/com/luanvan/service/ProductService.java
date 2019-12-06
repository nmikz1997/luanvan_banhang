package com.luanvan.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.multipart.MultipartFile;

import com.luanvan.dto.response.InventoryProductDTO;
import com.luanvan.dto.response.ProductDTO;
import com.luanvan.dto.response.ProductDetailDTO;
import com.luanvan.dto.response.TopSeller;
import com.luanvan.model.CustomUserDetails;
import com.luanvan.model.Image360;
import com.luanvan.model.Inventory;
import com.luanvan.model.Product;

public interface ProductService {
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	List<Product> findAll();
	
	Set<ProductDTO> selectAll();
	
	InventoryProductDTO findInventory(Long productId);
	
	List<Product> findByName(String name);
	
	Map<String,Object> findById(Long id);
	
	ProductDTO findDTOById(Long id);
	
	String save(String productReq, MultipartFile fileupload, Authentication auth) throws IOException;
	
	List<ProductDTO> findByCategory(Long categoryId);
	
	void delete(Long id);
	
	void deleteByCategoryId(Long categoryId);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	void update(Product product);
	
	@PreAuthorize("hasRole('ROLE_STORE')")
	void updateByStore(Product product, Long storeId);
	
	//san pham dang khuyen mai
	List<Product> findProductPromotion(Date dayStart,Date dayEnd);

	ProductDTO findProducts(Long id);

	List<ProductDTO> listSPKM();

	List<ProductDTO> listSPMoi();
	
	ProductDetailDTO chiTietSanPham(Long id);

	List<ProductDTO> productsInIds(List<Long> ids);

	List<Product> findByStore(Authentication auth);

	List<ProductDTO> searchBy(String name, Long categoryId);

	Page<Product> testSearch();

	Page<ProductDTO> findbyPlugName(String name, Long cate, String material, String origin, String producer,
			String store, float ratting, String filter, int page);

	ResponseEntity<?> uploadImage(Long storeId,int productId, MultipartFile[] uploadfiles);

	void doiGia(Product product);
	
	List<TopSeller> bestSeller(int limit);

	void createInventory(Inventory inventory, Long productId);

	List<Image360> allImage360Product(Long productid);

	void updateSttImage360(List<Image360> image360s, Long productid);

	Map<String, String> deleteImage360(List<Long> imageIds);

	ResponseEntity<?> uploadImage360(int product, MultipartFile[] uploadfiles, CustomUserDetails user);

	List<Product> findByStoreId(Long storeId);
}
