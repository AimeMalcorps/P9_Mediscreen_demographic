package com.mediscreen.demographic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mediscreen.demographic.dto.PatientDTO;
import com.mediscreen.demographic.service.PatientService;

@RestController
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@GetMapping("/patient/all")
	public ModelAndView getAllPatients() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("patientDTOList", patientService.getAllPatients());
		mav.setViewName("/patient/list");
		mav.setStatus(HttpStatus.OK);
		return mav;
	}
	
	@GetMapping("/patient/search")
	public ModelAndView getPatientByName(@RequestParam String familly) {
		ModelAndView mav = new ModelAndView();
		PatientDTO patientDTO = patientService.getPatientByName(familly);
		if (patientDTO != null) {
			List<PatientDTO> patientDTOList = new ArrayList<PatientDTO>();
			patientDTOList.add(patientDTO);
			mav.addObject("patientDTOList", patientDTOList);
			mav.setViewName("/patient/list");
			mav.setStatus(HttpStatus.OK);
		} else {
			mav.addObject("patientDTO", new PatientDTO());
			mav.addObject("message", "error");
			mav.setViewName("/home");
			mav.setStatus(HttpStatus.BAD_REQUEST);
		}
		return mav;
	}
	
	@PostMapping("/patient/add")
	public ModelAndView addPatient(@Valid PatientDTO patientDTO) {
		ModelAndView mav = new ModelAndView();
		HttpStatus status = null;
		if (patientService.addPatient(patientDTO)) {
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		mav.setViewName("/home");
		mav.addObject("patientDTO", new PatientDTO());
		mav.setStatus(status);
		return mav;
	}
	
	@GetMapping("/patient/update/{name}")
	public ModelAndView getUpdateTemplate(@PathVariable String name) {
		ModelAndView mav = new ModelAndView();
		PatientDTO patientDTO = patientService.getPatientByName(name);
		if (patientDTO != null) {
			mav.addObject("patientDTO", patientDTO);
			mav.setViewName("patient/update");
			mav.setStatus(HttpStatus.OK);
		} else {
			mav.addObject("patientDTOList", patientService.getAllPatients());
			mav.setViewName("/patient/list");
			mav.setStatus(HttpStatus.BAD_REQUEST);
		}
		return mav;
	}
	
	@PostMapping("/patient/update/{id}")
	public ModelAndView modifyPatient(@PathVariable Integer id, @Valid PatientDTO patientDTO) {
		ModelAndView mav = new ModelAndView();
		HttpStatus status = null;
		if (patientService.modifyPatient(id, patientDTO)) {
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		mav.addObject("patientDTOList", patientService.getAllPatients());
		mav.setViewName("/patient/list");
		mav.setStatus(status);
		return mav;
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
