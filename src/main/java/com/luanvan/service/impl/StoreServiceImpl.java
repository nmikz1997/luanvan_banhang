package com.luanvan.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.dto.request.CreateStoreDTO;
import com.luanvan.dto.response.StoreDTO;
import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Store;
import com.luanvan.repo.StoreRepository;
import com.luanvan.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{
	
	private StoreRepository StoreRepository;
	private SendGridEmailService sendGridEmailService;
	
	@Autowired
	public StoreServiceImpl(StoreRepository StoreRepository,
			SendGridEmailService sendGridEmailService) {
		this.StoreRepository = StoreRepository;
		this.sendGridEmailService 	= sendGridEmailService;
	}
	
	@Override
	public List<StoreDTO> findAll() {
		List<Store> stores = StoreRepository.findAll();
		ModelMapper mapper = new ModelMapper();
		List<StoreDTO> dto = mapper.map(stores,new TypeToken<List<StoreDTO>>(){}.getType());
		return dto;
	}

	@Override
	public List<Store> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store findById(Long id) {
		return StoreRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	@Transactional
	public void create(CreateStoreDTO storeDTO) {
		ModelMapper mapper = new ModelMapper();
		Store store = mapper.map(storeDTO, Store.class);
		StoreRepository.save(store);
	}

	@Override
	public Store update(Store Store, Long id) {
		StoreRepository.findById(id)
			.orElseThrow(NotFoundException::new);
		return StoreRepository.save(Store);
	}
	
	@Override
	public void updateStatus(Store status, Long id) {
		Store store = StoreRepository.findById(id)
				.orElseThrow(NotFoundException::new);
		store.setStatus(status.getStatus());
		StoreRepository.save(store);
		
		StringBuilder  string = new StringBuilder("GreenLife xin kính chào!");
		if(status.getStatus() == 1) {
			string.append("<p>Yêu câu bán hàng của bạn đã được duyệt.</p>");
		}else if(status.getStatus() == 3){
			string.append("<p>Yêu câu bán hàng của bạn đã bị từ chối.</p>");
		}else if(status.getStatus() == 2) {
			string.append("<p>Tài khoản của bạn tạm khóa.</p>");
		}
		
		string.append("<p>Mọi thắc mắc và góp ý vui lòng liên hệ với GreenLife Support qua email: <a href=\"mailto:trinhthenguyen123@gmail.vn\" style=\"color:#099202;text-decoration:none\" target=\"_blank\"> <strong>hotro@greenlife.vn</strong> </a> hoặc gọi số điện thoại 1900-6035 (8-21h cả T7,CN). Đội ngũ GreenLife Support luôn sẵn sàng hỗ trợ bạn bất kì lúc nào.</p>");
		string.append("<strong>GreenLife</strong><p> Xin cảm ơn!</p>");
		
		sendGridEmailService.sendHTML(store.getUser().getEmail(), "Xác nhận bán hàng cùng GreenLife", string.toString());

	}

	@Override
	public void delete(Long id) {
		StoreRepository.deleteById(id);
	}

	@Override
	public List<StoreDTO> findAllActive() {
		List<Store> stores = StoreRepository.findByStatus(1);
		ModelMapper mapper = new ModelMapper();
		List<StoreDTO> dto = mapper.map(stores,new TypeToken<List<StoreDTO>>(){}.getType());
		return dto;
	}
	

}
