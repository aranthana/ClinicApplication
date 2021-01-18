package com.clinic.pm.dao;

import java.util.List;
import com.clinic.models.common_models.Patient;


public interface PatientDao {
	
	public Patient savePatient(Patient patient);
	public List<Patient> getAllPatients();
	public Patient findById(String id);
	public void deleteById(String id) ;

}
