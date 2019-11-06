package com.luanvan.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.model.Inventory;
import com.luanvan.model.Product;
import com.luanvan.repo.InventoryRepository;
import com.luanvan.repo.ProductRepository;
import com.luanvan.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService{

	private InventoryRepository inventoryRepository;
	private ProductRepository productRepository;
	
	@Autowired
	public InventoryServiceImpl(InventoryRepository inventoryRepository,ProductRepository productRepository) {
		this.inventoryRepository = inventoryRepository;
		this.productRepository = productRepository;
	}

	@Override
	@Transactional
	public void create(Inventory inventory) {
		Product product = productRepository.getOne(inventory.getId());
		product.setQuantity( product.getQuantity() + inventory.getQuantity() );
		productRepository.save(product);
		inventoryRepository.save(inventory);
	}

}
