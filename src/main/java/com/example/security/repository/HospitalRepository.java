package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
