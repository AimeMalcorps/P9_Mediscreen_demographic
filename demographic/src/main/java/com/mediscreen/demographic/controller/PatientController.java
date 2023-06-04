package com.mediscreen.demographic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.demographic.dto.PatientDTO;
import com.mediscreen.demographic.service.PatientService;

@RestController
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@GetMapping("/patient/all")
	public List<PatientDTO> getAllPatients() {
		return patientService.getAllPatients();
	}
	
	@GetMapping("/patient/search")
	public PatientDTO getPatientByName(@RequestParam String familly) {
		return patientService.getPatientByName(familly);
	}
	
	@PostMapping("/patient/add")
	public boolean addPatient(@Valid PatientDTO patientDTO) {
		return patientService.addPatient(patientDTO);
	}
	
	@GetMapping("/patient/update/{name}")
	public PatientDTO getUpdateTemplate(@PathVariable String name) {
		return patientService.getPatientByName(name);
	}
	
	@PostMapping("/patient/update/{id}")
	public boolean modifyPatient(@PathVariable Integer id, @Valid PatientDTO patientDTO) {
		return patientService.modifyPatient(id, patientDTO);
	}
	
	@GetMapping("/patient/delete/{id}")
	public boolean modifyPatient(@PathVariable Integer id) {
		return patientService.deletePatient(id);
	}

}
