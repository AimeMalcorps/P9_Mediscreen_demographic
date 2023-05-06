package com.mediscreen.demographic.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.servlet.ModelAndView;

import com.mediscreen.demographic.dto.PatientDTO;
import com.mediscreen.demographic.service.PatientService;

@RestController
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	
	@GetMapping("/patient/all")
	public ModelAndView getAllPatients() {
//		return patientService.getAllPatients();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("patientDTOList", patientService.getAllPatients());
		mav.setViewName("/patient/list");
		mav.setStatus(HttpStatus.OK);
		return mav;
	}
	
	@GetMapping("/patient/{name}")
	public PatientDTO getPatientByName(@PathVariable String name) {
		return patientService.getPatientByName(name);
	}
	
	@PostMapping("/patient/add")
	public HttpStatus addPatient(@Valid PatientDTO patientDTO) {
		if (patientService.addPatient(patientDTO)) {
			return HttpStatus.OK;
		} else {
			return HttpStatus.BAD_REQUEST;
		}
	}
	
	@PostMapping("/patient/update")
	public HttpStatus modifyPatient(@RequestBody PatientDTO patientDTO) {
		if (patientService.modifyPatient(patientDTO)) {
			return HttpStatus.OK;
		} else {
			return HttpStatus.BAD_REQUEST;
		}
	}
	
	@GetMapping("/patient/delete/{id}")
	public ModelAndView modifyPatient(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView();
		HttpStatus status = null;
		if (patientService.deletePatient(id)) {
			status =  HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		mav.addObject("patientDTOList", patientService.getAllPatients());
		mav.setViewName("/patient/list");
		mav.setStatus(status);
		return mav;
	}

}
