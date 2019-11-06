package com.luanvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.model.CustomUserDetails;
import com.luanvan.model.Member;
import com.luanvan.service.MemberService;

@RestController
@RequestMapping("members")
public class MemberController {
	
	private MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("{id}")
	public Member findById(@PathVariable Long id) {
		return memberService.findById(id);
	}
	
	@GetMapping("history-member")
	public List<Member> findByStore(@AuthenticationPrincipal CustomUserDetails store){
		return memberService.findByStore(store.getStoreId());
	}
	
	@GetMapping("member-type/{memberTypeId}")
	public List<Member> findByMemberType(@PathVariable Long memberTypeId){
		return memberService.findByMemberType(memberTypeId);
	}
	
	@PostMapping
	public void save(@RequestBody Member member, @AuthenticationPrincipal CustomUserDetails user) {
		memberService.save(member,user.getStoreId());
	}
}
