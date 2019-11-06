package com.luanvan.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luanvan.model.CustomUserDetails;
import com.luanvan.model.Member;
import com.luanvan.model.Role;
import com.luanvan.model.User;
import com.luanvan.repo.MemberRepository;
import com.luanvan.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	private UserRepository userRepository;
	private MemberRepository memberRepository;
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository,MemberRepository memberRepository) {
		this.userRepository = userRepository;
		this.memberRepository = memberRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
		Date hethan = new Date();
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		if( grantedAuthorities.contains( new SimpleGrantedAuthority("ROLE_STORE")) &&
			!grantedAuthorities.contains( new SimpleGrantedAuthority("ROLE_ADMIN")) ) 
		{
			Optional<Member> member = memberRepository.findFirstByStoreIdAndDateEndAfterOrderByIdDesc(getStoreId(user), new Date());
			if(!member.isPresent()) {
				grantedAuthorities.remove(new SimpleGrantedAuthority("ROLE_STORE"));
				grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_HETHAN"));
			}else {
        		hethan = member.get().getDateEnd();
        	}
		}
		
		return new CustomUserDetails(user,grantedAuthorities,getStoreId(user),getCustomerId(user),hethan);		

	}
	
	private Long getStoreId(User user) {
		if(user.getStore() != null) return user.getStore().getId();
		return null;
	}
	
	private Long getCustomerId(User user) {
		if(user.getCustomer() != null) return user.getCustomer().getId();
		return null;
	}

}
