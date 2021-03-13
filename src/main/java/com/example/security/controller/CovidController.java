package com.example.security.controller;

import java.util.ArrayList;
import java.util.List;

//import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.security.model.Hospital;
import com.example.security.model.Role;
import com.example.security.model.User;
import com.example.security.repository.RoleRepository;
//import com.example.security.repository.UserRepository;
import com.example.security.service.CovidService;
import com.example.security.service.UserService;

@Controller
@RequestMapping("/covid")
public class CovidController {
    
	private Logger logger = org.slf4j.LoggerFactory.getLogger(CovidController.class);
	
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CovidService covidService;
	
	@GetMapping("/register")
	public String getHospitalRegistrationPage() {
		logger.info("....request for Hospital Admin registration page....");
		return "covid19 signup";
	}
	
	@GetMapping("/hospitalAdmin/updateSeat")
	public String updateSeatPage(Authentication authentication, Model model) {
		String username= authentication.getName();
		
		//find the details of hospital
		logger.info("...fething details of user....");
		Hospital hospital= covidService.getHospitalById(username);
		model.addAttribute("hospital", hospital);
		return "addmine";
	}
	
	@PostMapping("/hospitalAdmin/updateSeat")
	public String updateSeat(@RequestParam("availableSeats") int seatAvailable,  Model model,
			@RequestParam("numberOfSeats") int seatCapacity, Authentication authentication) {
		
		logger.info(".....request for update available seat");
		
		Long id= Long.parseLong(authentication.getName());
		
		covidService.updateHospital(id, seatAvailable, seatCapacity);
		model.addAttribute("message", "Updated Successfully !");
		Hospital hospital= covidService.getHospitalById(id);
		model.addAttribute("hospital", hospital);
		return "addmine";
	}
	
	@GetMapping("/availableSeat")
	public String getAvailableSeat(Model model) {
		
		List<Hospital> hospitals= covidService.getAllHospital();
		if(hospitals == null) {
			model.addAttribute("message" , "Sorry No hospitals is Available");
		}
		else {
			model.addAttribute("hospitals", hospitals);	
		}
		
		return "covid19";
	}
	
	@PostMapping("/register")
	public String registerHospital(@ModelAttribute Hospital hospital, Model model) {
		
		logger.info(".....new registration request for hospital admin....");
		
		User user = new User();
		logger.info("...creating new user as Hospital Admin....");
		user.setUsername(hospital.getRegistrationNumber().toString());
		user.setPassword(hospital.getPassword());
		List<Role> roles= new ArrayList<>();
		roles.add(roleRepo.findById(2222).get());
		roles.add(roleRepo.findById(3333).get());
		
		user.setRoles(roles);
		// save user
		userService.saveUser(user);
		
		logger.info("....new hospital admin created....");
		
		covidService.registerHospital(hospital);
		model.addAttribute("message", "Hospital Registered Successfully ! your username "
				+ "is your Registration No " + hospital.getRegistrationNumber());
		return "login";
	}
	
	@PostMapping("/user")
	public void addUser(@ModelAttribute User user) {
		
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepo.findById(1111).get());
		//roles.add(roleRepo.findById(2222).get());
		roles.add(roleRepo.findById(3333).get());
		
		user.setRoles(roles);
		
		 userService.saveUser(user);
		
	}
}
