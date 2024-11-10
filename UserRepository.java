package com.example.demo;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserRegister, Long>{
	Optional<UserRegister> findByEmail(String email);
    Optional<UserRegister> findByUsername(String username);
	
}