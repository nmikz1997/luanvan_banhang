package com.luanvan.service;

import java.util.List;

import com.luanvan.model.Store;
public interface StoreService {
	List<Store> findAllStore();
	Store findStoreById(Long id);
}
