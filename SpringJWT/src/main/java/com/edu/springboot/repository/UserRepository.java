package com.edu.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.springboot.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	Boolean existsByUsername(String username);
	
	UserEntity findByUsername(String username);
}
