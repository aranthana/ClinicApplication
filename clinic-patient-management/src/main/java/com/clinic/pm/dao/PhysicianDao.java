package com.clinic.pm.dao;

import java.util.List;
import com.clinic.models.common_models.Physician;

public interface PhysicianDao {
	
	public Physician savePhysician(Physician physician) ;
	public List<Physician> findAll();
	public Physician findById(String id);
	public void deleteById(String id) ;

}
