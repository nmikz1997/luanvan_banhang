package com.luanvan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.MemberType;

@Repository
public interface MemberTypeRepository extends JpaRepository<MemberType, Long>{

}
