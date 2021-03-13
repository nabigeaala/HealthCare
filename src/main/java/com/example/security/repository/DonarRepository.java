package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.model.Register;

public interface DonarRepository extends JpaRepository<Register, String>{

}
