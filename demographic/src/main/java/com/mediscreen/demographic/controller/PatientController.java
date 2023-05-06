package com.mediscreen.demographic.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.demographic.dto.PatientDTO;
import com.mediscreen.demographic.service.PatientService;

@RestController
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	
	@GetMapping("/patient/all")
	public List<PatientDTO> getAllPatients() {
		return patientService.getAllPatients();
	}
	
	@GetMapping("/patient/{name}")
	public PatientDTO getPatientByName(@PathVariable String name) {
		return patientService.getPatientByName(name);
	}
	
	@PostMapping("/patient/add")
	public HttpStatus addPatient(@RequestBody PatientDTO patientDTO) {
		if (patientService.addPatient(patientDTO)) {
			return HttpStatus.OK;
		} else {
			return HttpStatus.BAD_REQUEST;
		}
	}
	
	@PostMapping("/patient/modify")
	public HttpStatus modifyPatient(@RequestBody PatientDTO patientDTO) {
		if (patientService.modifyPatient(patientDTO)) {
			return HttpStatus.OK;
		} else {
			return HttpStatus.BAD_REQUEST;
		}
	}
	
	@DeleteMapping("/patient/delete/{id}")
	public HttpStatus modifyPatient(@PathVariable Integer id) {
		if (patientService.deletePatient(id)) {
			return HttpStatus.OK;
		} else {
			return HttpStatus.BAD_REQUEST;
		}
	}

}
