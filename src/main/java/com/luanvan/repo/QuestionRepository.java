package com.luanvan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
	List<Question> findQuestionByProductIdAndStatus(Long productId,boolean status);

	List<Question> findQuestionByProductStoreId(Long storeId);

	Long countByProductStoreId(Long storeId);
}
