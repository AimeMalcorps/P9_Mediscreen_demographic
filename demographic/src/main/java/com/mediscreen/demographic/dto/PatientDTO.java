package com.mediscreen.demographic.dto;

import com.mediscreen.demographic.entity.Patient;

public class PatientDTO {
	
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
	
	public PatientDTO() {
		
	}
	
	public PatientDTO(Patient patient) {
		this.id = patient.getId();
		this.familly = patient.getFamilly();
		this.given = patient.getGiven();
		this.dob = patient.getDob();
		this.sex = patient.getSex();
		this.address = patient.getAddress();
		this.phone = patient.getPhone();
	}
	
	

}
