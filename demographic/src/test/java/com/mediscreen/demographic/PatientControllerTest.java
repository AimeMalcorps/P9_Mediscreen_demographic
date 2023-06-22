package com.mediscreen.demographic;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mediscreen.demographic.controller.PatientController;
import com.mediscreen.demographic.dto.PatientDTO;
import com.mediscreen.demographic.entity.Patient;
import com.mediscreen.demographic.repository.PatientRepository;
import com.mediscreen.demographic.service.PatientService;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

	@Autowired
	public MockMvc mockMvc;

	@Autowired
	private PatientController patientController;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	public PatientRepository patientRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
		Patient patient = new Patient();
		patient.setFamilly("MAYER");
		patient.setGiven("John");
		patient.setDob("1968-02-21");
		List<Patient> patientList = new ArrayList<>();
		patientList.add(patient);
		patientRepository.save(patient);
	}
	
	@AfterEach
	public void reset() throws Exception {
		patientRepository.deleteAll();
	}

	@Test
	public void getAllPatient() throws Exception {
		this.mockMvc.perform(get("/patient/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].familly", is("MAYER"))).andReturn();
	}
	
	@Test
	public void searchPatient() throws Exception {
		this.mockMvc.perform(post("/patient/search").content("MAYER"))
		.andExpect(status().isOk())
				.andExpect(jsonPath("$.given", is("John"))).andReturn();
	}
	
	@Test
	public void addPatient() throws Exception {
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setFamilly("LAURENT");
		patientDTO.setGiven("Barre");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(patientDTO);

		this.mockMvc.perform(post("/patient/add").contentType(MediaType.APPLICATION_JSON).content(requestJson))
		.andExpect(status().isOk());
		
		this.mockMvc.perform(post("/patient/search").content("LAURENT"))
		.andExpect(status().isOk())
				.andExpect(jsonPath("$.given", is("Barre"))).andReturn();
	}
	
	@Test
	public void updatePatient() throws Exception {
		
		PatientDTO patientDTO = patientService.getPatientByName("MAYER");
		patientDTO.setGiven("Phillipe");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(patientDTO);

		this.mockMvc.perform(post("/patient/update/" + patientDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(requestJson))
		.andExpect(status().isOk());
		
		this.mockMvc.perform(post("/patient/search").content("MAYER"))
		.andExpect(status().isOk())
				.andExpect(jsonPath("$.given", is("Phillipe"))).andReturn();
	}
	
	@Test
	public void deletePatient() throws Exception {
		
		PatientDTO patientDTO = patientService.getPatientByName("MAYER");

		this.mockMvc.perform(get("/patient/delete/" + patientDTO.getId()))
		.andExpect(status().isOk());
	}

}
