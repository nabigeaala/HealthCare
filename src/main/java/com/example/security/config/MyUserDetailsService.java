package com.example.security.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.exception.UserNotFoundException;
import com.example.security.model.User;
import com.example.security.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    
	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//extract user from database by username
		Optional<User> user = userRepo.findByUsername(username);
		
		//if user not fond throw an exception
		if(user==null) {
			throw new UserNotFoundException();
		}
		return new MyUserDetails(user.get());
	}

}
