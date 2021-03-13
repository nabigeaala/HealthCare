package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
