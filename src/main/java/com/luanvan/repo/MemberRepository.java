package com.luanvan.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	List<Member> findByMemberTypeId(Long memberTypeId);
	
	List<Member> findByStoreId(Long storeId);
	
	Optional<Member> findFirstByStoreIdAndDateEndAfterOrderByIdDesc(Long storeId, Date today);
}
