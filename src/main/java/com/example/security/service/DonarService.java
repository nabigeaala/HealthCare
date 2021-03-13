package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.example.security.model.Register;
import com.example.security.repository.DonarRepository;

@Service
public class DonarService {
    
	@Autowired
	public DonarRepository donarRepo;
	
	public void saveDonarDetails(Register register) {
		donarRepo.save(register);	
	}

	public List<Register> findBloodDetailsForAcceptor(String bg) {
		List<Register> listBloodDetails= donarRepo.findAll();
		
		return listBloodDetails.stream().filter(detail-> detail.getBloodGroup().equals(bg)).collect(Collectors.toList());
	}
}
