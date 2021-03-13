package com.example.security;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.security.model.Role;
import com.example.security.model.User;
import com.example.security.repository.RoleRepository;
import com.example.security.service.UserService;

@SpringBootApplication
@EnableJpaRepositories
public class SecurityApplication implements CommandLineRunner {
    
	@Autowired
	UserService userService;
	
	@Autowired
	RoleRepository roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//add 3 role in database
		roleRepo.save(new Role(1111, "ROLE_ADMIN"));
		roleRepo.save(new Role(2222, "ROLE_HOSPITALADMIN"));
		roleRepo.save(new Role(3333, "ROLE_USER"));
		
		//create 1st admin
		List<Role> roles1= new ArrayList<>();
		roles1.add(roleRepo.findById(1111).get());
		roles1.add(roleRepo.findById(3333).get());
		User user1= new User("admin1", "pass", roles1);
		
		userService.saveUser(user1);
		
		//create 2st admin
	    List<Role> roles2= new ArrayList<>();
		roles2.add(roleRepo.findById(1111).get());
		roles2.add(roleRepo.findById(3333).get());
		User user2= new User("admin2", "pass", roles2);
				
		userService.saveUser(user2);
		
		/*//create 1 hospital admin
		List<Role> roles3= new ArrayList<>();
		roles3.add(roleRepo.findById(2222).get());
		roles3.add(roleRepo.findById(3333).get());
		User user3= new User("admin3", "pass", roles3);
				
		userService.saveUser(user3);*/
		
		
	}

}
