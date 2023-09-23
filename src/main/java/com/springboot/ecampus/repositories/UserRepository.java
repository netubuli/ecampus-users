package com.springboot.ecampus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.ecampus.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> /*<the tablename, primary key dtype>*/{

	User findByUsername(String username);
		
}
