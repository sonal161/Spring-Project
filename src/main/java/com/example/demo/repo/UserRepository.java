package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User,String> {

	


}
