package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.security.model.Ambulance;

@Repository
public interface AmbulanceRepository extends JpaRepository<Ambulance, String> {
     
}
