package com.luanvan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Member;
import com.luanvan.repo.MemberRepository;
import com.luanvan.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Override
	public List<Member> findByStore(Long storeId) {
		return memberRepository.findByStoreId(storeId);
	}

	@Override
	public List<Member> findByMemberType(Long memberTypeId) {
		return memberRepository.findByMemberTypeId(memberTypeId);
	}

	@Override
	public Member findById(Long id) {
		return memberRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public Member save(Member member) {
		return memberRepository.save(member);
	}

}
