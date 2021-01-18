package com.clinic.pm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.models.common_models.Patient;
import com.clinic.pm.service.PatientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@CrossOrigin(origins = "*") 
@RestController
@RequestMapping(value = "/patients")
public class PatientController {
	
	
	@Autowired
	PatientService patientService;
		
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Patient addNewPatient(@RequestBody Patient patient) {
		log.info("Patient Creation Workflow");
		return patientService.creatPatient(patient);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	  public List<Patient> getAllPatients() {
	    return patientService.getAllPatients();
	 }
	
	@RequestMapping(value="/patient/patientId/{patientId}", method=RequestMethod.GET)
	public Patient getPatient(@PathVariable String patientId) {
		return patientService.getPatientById(patientId);
	}
	
	@RequestMapping(value="/update",method = RequestMethod.PUT)
	public Patient updatePatient(@RequestBody Patient patient) {
		log.info("Patient Updating Workflow");
		return patientService.updatePatient(patient);
		
	}
	
	@RequestMapping(value="/delete/patientId/{patientId}", method=RequestMethod.DELETE)
	public void deletePatient(@PathVariable String patientId) {
		patientService.deletePatient(patientId);
	}

}
