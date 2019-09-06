package com.luanvan.service;

import java.util.List;

import com.luanvan.model.Member;

public interface MemberService {
	
	//tim goi dich vu theo cua hang
	List<Member> findByStore(Long id);
	
	//tim goi dich vu theo loai
	List<Member> findByMemberType(Long id);
	
	//tim theo id
	Member findById(Long id);
	
	//save
	Member save(Member member);
}
