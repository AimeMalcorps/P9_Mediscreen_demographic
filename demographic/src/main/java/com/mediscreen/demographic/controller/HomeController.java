package com.mediscreen.demographic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mediscreen.demographic.dto.PatientDTO;

@RestController
public class HomeController {
	
	@RequestMapping("/")
	public ModelAndView home(Model model) {
		PatientDTO patientDTO = new PatientDTO();
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("patientDTO", patientDTO);
		mav.setViewName("/home");
		mav.setStatus(HttpStatus.OK);
		return mav;
	}

}
