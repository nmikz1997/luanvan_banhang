package com.luanvan.service.impl;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.exception.NotFoundException;
import com.luanvan.model.Member;
import com.luanvan.model.MemberType;
import com.luanvan.model.Store;
import com.luanvan.repo.MemberRepository;
import com.luanvan.repo.MemberTypeRepository;
import com.luanvan.repo.StoreRepository;
import com.luanvan.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberRepository memberRepository;
	private StoreRepository storeRepository;
	private MemberTypeRepository membertypeRepository;
	
	@Autowired
	private MemberServiceImpl(MemberRepository memberRepository,
			StoreRepository storeRepository,
			MemberTypeRepository membertypeRepository) {
		this.memberRepository = memberRepository;
		this.storeRepository = storeRepository;
		this.membertypeRepository = membertypeRepository;
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
	public void save(Member member, Long storeId) {
		Store store = storeRepository.getOne(storeId);
		Optional<Member> lastMember = memberRepository.findFirstByStoreIdAndDateEndAfterOrderByIdDesc(storeId, new Date());
		
		MemberType memberType = membertypeRepository.getOne(member.getMemberType().getId());
		Date dateStart = new Date();
		if(lastMember.isPresent()) {
			dateStart = lastMember.get().getDateEnd();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateStart);
		int amount = memberType.getDuration();
		cal.add(Calendar.MONTH,amount);//cộng thêm tháng
		Date dateEnd = cal.getTime();
		
		member.setDateStart(dateStart);
		member.setDateEnd(dateEnd);
		member.setStore(store);
		
		memberRepository.save(member);
	}

}
