package com.example.security.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
   
	@Id
	private int id;
	private String role;
	
	@ManyToMany(mappedBy= "roles")
	List<User> users= new ArrayList<>();
	
	public Role() {
		
	}
	
	public Role(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
