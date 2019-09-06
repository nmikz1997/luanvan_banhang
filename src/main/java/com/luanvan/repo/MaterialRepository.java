package com.luanvan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long>{

}
