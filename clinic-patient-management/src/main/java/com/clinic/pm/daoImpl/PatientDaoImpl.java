package com.clinic.pm.daoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clinic.models.common_models.Patient;
import com.clinic.pm.dao.PatientDao;
import com.clinic.pm.repo.PatientRepository;

@Component
public class PatientDaoImpl implements  PatientDao{
	
	@Autowired
	PatientRepository patientRepo;
	
	@Override
	public Patient savePatient(Patient patient) {
		return patientRepo.save(patient);		
	}
	
	@Override
	public List<Patient> getAllPatients(){
		return (List<Patient>) patientRepo.findAll();
	}
	
	@Override
	public Patient findById(String id) {
		Optional<Patient> patient = patientRepo.findById(id);
		if(patient !=null && patient.isPresent())
			return patient.get();
		return null;	
	}

	@Override
	public void deleteById(String id) {
		patientRepo.deleteById(id);
		
	}



}
