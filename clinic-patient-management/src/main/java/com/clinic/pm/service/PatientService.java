package com.clinic.pm.service;

import java.util.List;

import com.clinic.models.common_models.Patient;

public interface PatientService {
	
	public Patient creatPatient (Patient patient) ;
	public List<Patient> getAllPatients ();
	public Patient getPatientById(String id);
	public Patient updatePatient (Patient patient);
	public void deletePatient (String id);

}
