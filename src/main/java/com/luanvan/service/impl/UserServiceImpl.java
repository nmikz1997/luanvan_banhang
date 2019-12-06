package com.luanvan.service.impl;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.luanvan.dto.request.CreateRegisterStoreDTO;
import com.luanvan.dto.request.RegisterDTO;
import com.luanvan.exception.RollbackException;
import com.luanvan.model.Customer;
import com.luanvan.model.Role;
import com.luanvan.model.Store;
import com.luanvan.model.User;
import com.luanvan.repo.CustomerRepository;
import com.luanvan.repo.RoleRepository;
import com.luanvan.repo.StoreRepository;
import com.luanvan.repo.UserRepository;
import com.luanvan.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private CustomerRepository customerRepository;
	private StoreRepository storeRepository;
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(CustomerRepository customerRepository,
			StoreRepository storeRepository,
			UserRepository userRepo,
			RoleRepository roleRepo,
			PasswordEncoder passwordEncoder) {
		this.customerRepository = customerRepository;
		this.storeRepository = storeRepository;
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@Override
	@Transactional
	public void RegisterStore(CreateRegisterStoreDTO req) { //Đăng ký cửa hàng
		// check email
		if(findByEmail(req.getUser().getEmail()) != null) {
			throw new RollbackException("Email đã tồn tại");
		}
		
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(req.getUser(),User.class);
		Store store = mapper.map(req.getStore(),Store.class);
		Customer customer = mapper.map(req.getCustomer(),Customer.class);
		
		System.out.println(user);
		System.out.println(store);
		System.out.println(customer);
		
		HashSet<Role> roles = new HashSet<>();
		roles.add( roleRepo.findByName("ROLE_STORE") );
		roles.add( roleRepo.findByName("ROLE_USER") );
		user.setPassword( passwordEncoder.encode(req.getUser().getPassword() ));
		user.setRoles( roles );
		
		User newUser = userRepo.save(user);
		
		store.setUser(newUser);
		storeRepository.save(store);
		
		customer.setUser(newUser);
		customerRepository.save(customer);
	}
	
	@Override
	@Transactional
	public void save(RegisterDTO req) { //Đăng ký khách hàng
		// check email
		if(findByEmail(req.getUser().getEmail()) != null) {
			throw new RollbackException("Email đã tồn tại");
		}
		
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(req.getUser(),User.class);
		Customer customer = mapper.map(req.getCustomer(),Customer.class);
		
		HashSet<Role> roles = new HashSet<>();
		roles.add( roleRepo.findByName("ROLE_USER") );
		user.setPassword( passwordEncoder.encode(req.getUser().getPassword() ));
		user.setRoles(roles);
		
		customer.setUser(userRepo.save(user));
		customerRepository.save(customer);
	}
	

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}


	@Override
	public Customer getCustomer(Authentication auth) {
		return customerRepository.findByUserEmail(auth.getName());
	}


	@Override
	public Store getStore(Authentication auth) {
		return storeRepository.findByUserEmail(auth.getName());
	}
	
	
	
}
