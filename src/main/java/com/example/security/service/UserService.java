package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.model.User;
import com.example.security.repository.UserRepository;

@Service
public class UserService {
    
	@Autowired
	UserRepository userRepo;

	public void saveUser(User user) {
		
			userRepo.save(user);
	}
	
	
	
}
