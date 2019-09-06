package com.luanvan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long>{
}
