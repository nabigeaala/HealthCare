package com.example.security.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ambulance {
   
   @GeneratedValue(strategy= GenerationType.SEQUENCE)
   private int Sn;
   private String hospitalName;
   @Id
   private String plateNo;
   private String contactNumber;
   private String address;
      
   public Ambulance() {
	   
   }
   
   public Ambulance(int Sn,String hospitalName, String plateNo, String contactNumber, String address) {
	//this.Sn= Sn; 
	this.hospitalName = hospitalName;
	this.plateNo = plateNo;
	this.contactNumber = contactNumber;
	this.address= address;
}

public String getHospitalName() {
	  return hospitalName;
   }
   
   public void setHospitalName(String hospitalName) {
	  this.hospitalName = hospitalName;
   }
   
   public String getPlateNo() {
	  return plateNo;
   }
   
   public void setPlateNo(String plateNo) {
	  this.plateNo = plateNo;
   }
   
   public String getContactNumber() {
	  return contactNumber;
   }
   
   public void setContactNumber(String contactNumber) {
	  this.contactNumber = contactNumber;
   }


  public String getAddress() {
	 return address;
  } 

  public void setAddress(String address) {
	 this.address = address;
  }
   
}
