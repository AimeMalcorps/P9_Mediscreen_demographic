package com.mediscreen.demographic.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.demographic.dto.PatientDTO;
import com.mediscreen.demographic.entity.Patient;
import com.mediscreen.demographic.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	private Logger logger = LoggerFactory.getLogger(PatientService.class);
	
	public List<PatientDTO> getAllPatients() {
		
		List<PatientDTO> patientDTOList = new ArrayList<PatientDTO>();
		
		try {
			
			for (Patient patient : patientRepository.findAll()) {
				patientDTOList.add(new PatientDTO(patient));
			}
			
		} catch (Exception e) {
			logger.error("Error getAllPatients : " + e);
			return null;
		}
		
		return patientDTOList;
	}
	
	public PatientDTO getPatientByName(String name) {
		PatientDTO patientDTO = null;
		try {
			Patient patient = patientRepository.findByFamilly(name);
			if (patient != null) {
				patientDTO = new PatientDTO(patient);
			}
		} catch(Exception e) {
			logger.error("Error addPatient : " + e);
			return patientDTO;
		}
		
		return patientDTO;
	}
	
	public boolean addPatient(PatientDTO patientDTO) {
		try {
			Patient patient = new Patient(patientDTO);
			patientRepository.save(patient);
			
		} catch(Exception e) {
			logger.error("Error addPatient : " + e);
			return false;
		}
		
		return true;
	}
	
	public boolean modifyPatient(Integer id, PatientDTO patientDTO) {
		try {
			Patient patient = patientRepository.findById(id).get();
			if (patient != null) {
				patient.setFamilly(patientDTO.getFamilly());
				patient.setGiven(patientDTO.getGiven());
				patient.setDob(patientDTO.getDob());
				patient.setSex(patientDTO.getSex());
				patient.setAddress(patientDTO.getAddress());
				patient.setPhone(patientDTO.getPhone());
				patientRepository.save(patient);
			}
			
		} catch(Exception e) {
			logger.error("Error addPatient : " + e);
			return false;
		}
		
		return true;
	}
	
	public boolean deletePatient(Integer id) {
		try {
			patientRepository.deleteById(id);
		} catch(Exception e) {
			logger.error("Error addPatient : " + e);
			return false;
		}
		return true;
	}

}
