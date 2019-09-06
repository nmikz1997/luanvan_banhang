package com.luanvan.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.dto.request.SaveDetailProductDTO;
import com.luanvan.dto.response.PictureDTO;
import com.luanvan.dto.response.ProductDTO;
import com.luanvan.model.Picture;
import com.luanvan.model.Price;
import com.luanvan.model.Product;
import com.luanvan.repo.PictureRepository;
import com.luanvan.repo.PriceRepository;
import com.luanvan.repo.ProductRepository;
import com.luanvan.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	private ProductRepository productRepository;
	private PictureRepository pictureRepository;
	private PriceRepository priceRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository,
			PictureRepository pictureRepository,
			PriceRepository priceRepository) {
		this.productRepository 	= productRepository;
		this.pictureRepository 	= pictureRepository;
		this.priceRepository	= priceRepository;
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

	@Override
	@Transactional
	public void save(SaveDetailProductDTO productDetail) {
		//System.out.println(productDetail.getProduct());
		Product product = new Product();
		
		product = productDetail.getProduct();
		//product.setAttributeValues(productDetail.getAttributeValues());
		System.out.println(productDetail.getAttributeValues());
		productRepository.save(product);
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
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public ProductDTO findDTOById(Long id) {
		Product product = productRepository.findById(id).get();
		Picture avatar = pictureRepository.findByProductIdAndAvatar(id, true);
		Price price = priceRepository.findTopPriceByProductIdOrderByIdDesc(id);
		
//		Set<PictureDTO> listPictures = new HashSet<>();
		
//		product.getPictures().forEach(pic->{
//			listPictures.add(new PictureDTO(pic.getId(),pic.getPath()));
//		});
		
		ProductDTO productDTO = new ProductDTO(
				product.getId(),
				product.getName(),
				avatar.getPath(),
				price.getUnitPrice(),
				price.getUnitPrice()
				);
		
		return productDTO;
	}

	@Override
	public Set<ProductDTO> selectAll() {
		List<Product> products = productRepository.findAll();
		
		Set<ProductDTO> data = new HashSet<>();

		products.forEach(prod->{
			System.out.println(prod.getId());
			Picture avatar = pictureRepository.findByProductIdAndAvatar(prod.getId(), true);
			Price price = priceRepository.findTopPriceByProductIdOrderByIdDesc(prod.getId());
			data.add(
					new ProductDTO(
							prod.getId(),
							prod.getName(),
							avatar.getPath(),
							price.getUnitPrice(),
							price.getUnitPrice()
							)
					);
			});
		return data;
	}

}
