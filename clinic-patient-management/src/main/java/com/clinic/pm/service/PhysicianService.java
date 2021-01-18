package com.clinic.pm.service;

import java.util.List;

import com.clinic.models.common_models.Physician;

public interface PhysicianService {

	public Physician creatPhysician (Physician physician) ;
	public List<Physician> getAllPhysicians ();
	public Physician getPhysicianById(String id);
	public Physician updatePhysician (Physician physician);
	public void deletePhysician(String id);
}
