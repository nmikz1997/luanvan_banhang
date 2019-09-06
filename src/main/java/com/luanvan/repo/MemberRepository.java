package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	List<Member> findByMemberTypeId(Long memberTypeId);
	
	List<Member> findByStoreId(Long storeId);
}
