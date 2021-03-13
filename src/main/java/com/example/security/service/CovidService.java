package com.example.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.model.Hospital;
import com.example.security.repository.HospitalRepository;

@Service
public class CovidService {
    
	@Autowired
	HospitalRepository hospitalRepo;
	
	public void registerHospital(Hospital hospital) {
		hospitalRepo.save(hospital);
	}

	public List<Hospital> getAllHospital() {
		return hospitalRepo.findAll();
	}

	public Hospital getHospitalById(String username) {
		Long id= Long.parseLong(username);
		
	   return   hospitalRepo.findById(id).get();
		
	}

	public void updateHospital(Long id, int seatAvailable, int seatCapacity) {
		Hospital hospital= hospitalRepo.getOne(id);
		hospital.setAvailableSeat(seatAvailable);
		hospital.setSeatCapacity(seatCapacity);
		
		hospitalRepo.save(hospital);
		
	}

	public Hospital getHospitalById(Long id) {
		
		return hospitalRepo.findById(id).get();
	}
	
}
