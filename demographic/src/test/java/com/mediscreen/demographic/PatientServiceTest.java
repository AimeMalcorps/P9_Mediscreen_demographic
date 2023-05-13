package com.mediscreen.demographic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mediscreen.demographic.dto.PatientDTO;
import com.mediscreen.demographic.entity.Patient;
import com.mediscreen.demographic.repository.PatientRepository;
import com.mediscreen.demographic.service.PatientService;

@SpringBootTest
public class PatientServiceTest {
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	private PatientService patientService;

	@Test
	public void testGetAllPatients() {
		
		List<Patient> patientInDatabase = patientRepository.findAll();
		
		List<PatientDTO> patientDTOList = patientService.getAllPatients();
		
		assertEquals(patientInDatabase.size(), patientDTOList.size());
	}
	
	@Test
	public void testGetPatientByName() {
		Patient patientToSearch = new Patient();
		patientToSearch.setFamilly("Bilbon");
		patientRepository.save(patientToSearch);
		
		PatientDTO patientDTO = patientService.getPatientByName("Bilbon");
		
		assertNotNull(patientDTO);
		
		patientRepository.deleteById(patientDTO.getId());
	}
	
	@Test
	public void testAddPatient() {
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setFamilly("Gandalf");
		patientDTO.setGiven("Legris");
		patientDTO.setDob("03081996");
		patientDTO.setAddress("18 chemin du Mordor");
		patientDTO.setSex('M');
		patientDTO.setPhone("0606060606");
		
		patientService.addPatient(patientDTO);
		
		patientDTO = patientService.getPatientByName("Gandalf");
		
		assertNotNull(patientDTO);
		
		patientRepository.deleteById(patientDTO.getId());
	}
	
	@Test
	public void testModifyPatient() {
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setFamilly("Gandalf");
		patientDTO.setGiven("Legris");
		patientDTO.setDob("03081996");
		patientDTO.setAddress("18 chemin du Mordor");
		patientDTO.setSex('M');
		patientDTO.setPhone("0606060606");
		
		patientService.addPatient(patientDTO);
		
		patientDTO = patientService.getPatientByName("Gandalf");
		
		patientDTO.setFamilly("Tolkien");
		
		patientService.modifyPatient(patientDTO.getId(), patientDTO);
		
		assertNotNull(patientService.getPatientByName("Tolkien"));
		
		patientRepository.deleteById(patientDTO.getId());
	}
	
	@Test
	public void testDeletePatient() {
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setFamilly("Gandalf");
		patientDTO.setGiven("Legris");
		patientDTO.setDob("03081996");
		patientDTO.setAddress("18 chemin du Mordor");
		patientDTO.setSex('M');
		patientDTO.setPhone("0606060606");
		
		patientService.addPatient(patientDTO);
		
		patientDTO = patientService.getPatientByName("Gandalf");
		
		patientService.deletePatient(patientDTO.getId());
		
		assertNull(patientService.getPatientByName("Gandalf"));
	}

}
