package com.luanvan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luanvan.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
