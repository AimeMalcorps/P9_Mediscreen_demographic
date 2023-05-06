package com.mediscreen.demographic.entity;

import com.mediscreen.demographic.dto.PatientDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String familly;
	private String given;
	private String dob;
	private Character sex; // M, F, U (unknown)
	private String address;
	private String phone;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFamilly() {
		return familly;
	}
	public void setFamilly(String familly) {
		this.familly = familly;
	}
	public String getGiven() {
		return given;
	}
	public void setGiven(String given) {
		this.given = given;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public Character getSex() {
		return sex;
	}
	public void setSex(Character sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	
	public Patient() {
		
	}
	
	public Patient(PatientDTO patientDTO) {
		this.setFamilly(patientDTO.getFamilly());
		this.setGiven(patientDTO.getGiven());
		this.setDob(patientDTO.getDob());
		this.setSex(patientDTO.getSex());
		this.setAddress(patientDTO.getAddress());
		this.setPhone(patientDTO.getPhone());
	}
	
	
	

}
