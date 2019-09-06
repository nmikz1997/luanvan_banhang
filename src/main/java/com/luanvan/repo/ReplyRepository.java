package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
	
	List<Reply> findByQuestionId(Long productId);
	
	List<Reply> findByStoreId(Long customerId);
}
