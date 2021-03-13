package com.example.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.model.Ambulance;
import com.example.security.repository.AmbulanceRepository;

@Service
public class AmbulanceService {
	
	@Autowired
	AmbulanceRepository ambulanceRepo;
	

     public List<Ambulance> ambulanceList;
     public AmbulanceService(){
    	 ambulanceList= new ArrayList<Ambulance>();
     }

	public void addAmbulances(Ambulance ambulance) {
		ambulanceList.add(ambulance);
	}


	public Map<Integer, Ambulance> getAmbulanceListFromDatabase() {
		int Sn=1;
		Map<Integer, Ambulance> ambulanceMap= new HashMap<>();
		List<Ambulance> ambulances= ambulanceRepo.findAll();
		for(Ambulance ambulance : ambulances) {
			ambulanceMap.put(Sn++, ambulance);
		}
		return ambulanceMap;
	}
    
	public void addAmbulanceToDatabase(Ambulance ambulance) {
		ambulanceRepo.save(ambulance);
		ambulanceList.removeIf(amb->amb.getPlateNo().equals(ambulance.getPlateNo()));
	}

	public void deleteAmbulanceFromRequestList(Ambulance ambulance) {
		
		ambulanceList.removeIf(amb-> amb.getPlateNo().equals(ambulance.getPlateNo()));
	}
     
     
}
