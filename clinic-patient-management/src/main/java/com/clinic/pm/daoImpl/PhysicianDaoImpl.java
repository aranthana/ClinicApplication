package com.clinic.pm.daoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clinic.models.common_models.Physician;
import com.clinic.pm.dao.PhysicianDao;
import com.clinic.pm.repo.PhysicianRepository;

@Component
public class PhysicianDaoImpl implements PhysicianDao{
	
	@Autowired
	PhysicianRepository physicianRepo;

	@Override
	public Physician savePhysician(Physician physician) {
		return physicianRepo.save(physician);
	}
	
	@Override
	public List<Physician> findAll() {
		return (List<Physician>) physicianRepo.findAll();
	}

	@Override
	public Physician findById(String id) {
		Optional<Physician> phycisian = physicianRepo.findById(id);
		if(phycisian !=null && phycisian.isPresent())
			return phycisian.get();
		return null;
	}

	@Override
	public void deleteById(String id) {
		physicianRepo.deleteById(id);	
	}


}
