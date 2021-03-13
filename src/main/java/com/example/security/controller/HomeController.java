package com.example.security.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.security.model.Ambulance;
import com.example.security.model.Register;
import com.example.security.service.AmbulanceService;
import com.example.security.service.DonarService;

@Controller
public class HomeController implements WebMvcConfigurer {
	
	Logger logger= LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	AmbulanceService ambulanceService;
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/bloodBanks").setViewName("List_blood_bank");
		registry.addViewController("/doctor").setViewName("doctor");
		registry.addViewController("/donar").setViewName("donar");
		registry.addViewController("/user").setViewName("user");
		registry.addViewController("/admin").setViewName("admin");
		registry.addViewController("/login").setViewName("login");
		
	}

	@Autowired
	DonarService donarService;
	
	@GetMapping("/ambulanceRegistration")
	public String getAmbulanceForm() {
		return "ambulance_registration_form";
	}
	
	@PostMapping("/ambulanceRegistration")
	public String addAmbulance(@ModelAttribute Ambulance ambulance, Model model) {
		logger.info("A request for ambulance Registration");
		ambulanceService.addAmbulances(ambulance);
		model.addAttribute("message", "Your request Successfully saved ");
        logger.info("request successfully saved in request queue");		
		return "ambulance_registration_form";
	}
	
	@GetMapping("/admin/viewAmbulanceRequest")
	public String ambulanceRequestList(Model model) {
		List<Ambulance> ambulances= ambulanceService.ambulanceList;
		if(ambulances.isEmpty()) {
			model.addAttribute("message", "Ambulance Request is Empty");
		}
		else
			model.addAttribute("ambulances", ambulances);
		return "Ambulance_Request_List";
	}
	
	@PostMapping("/admin/ambulanceRequest")
	public String addAmbulances(@ModelAttribute Ambulance ambulance, Model model) {
		ambulanceService.addAmbulanceToDatabase(ambulance);
		model.addAttribute("message1", "Ambulance with plate number " + ambulance.getPlateNo() + " saved successfully");
		List<Ambulance> ambulances= ambulanceService.ambulanceList;
		if(ambulances.isEmpty()) {
			model.addAttribute("message2", "Ambulance Request Queue is Empty");
		}
		else
			model.addAttribute("ambulances", ambulances);
		return "Ambulance_Request_List";
	}
	
	@GetMapping("/admin/ambulanceRequest")
	public String deleteAmbulance(@ModelAttribute Ambulance ambulance, Model model) {
		
		ambulanceService.deleteAmbulanceFromRequestList(ambulance);
		model.addAttribute("message1", "Successfully deleted from request Queue");
		List<Ambulance> ambulances= ambulanceService.ambulanceList;
		if(ambulances.isEmpty()) {
			model.addAttribute("message2", "Ambulance Request Queue is Empty");
		}
		else
			model.addAttribute("ambulances", ambulances);
		return "Ambulance_Request_List";
	}
	
	@PostMapping("/register")
	public String addDonarDetails(@ModelAttribute Register register, Model model) {
		donarService.saveDonarDetails(register);
		model.addAttribute("message", "Successfully saved !");
		
		return "home";
	}
	
	
	@RequestMapping("/ambulance")
	public String ambulance(Model model){
		Map<Integer, Ambulance> ambulances = ambulanceService.getAmbulanceListFromDatabase();
		model.addAttribute("ambulances", ambulances);
		return "ambulance";
	}
	
	@GetMapping("/acceptor/{bg}")
	public String acceptor(@PathVariable("bg") String bg, Model model){
		List<Register> bloodDetails = donarService.findBloodDetailsForAcceptor(bg);
		model.addAttribute("bloodDetails", bloodDetails);
		return "acceptor_list";
	}
	
}
