package com.clinic.pm.daoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clinic.models.common_models.Visit;
import com.clinic.pm.dao.VisitDao;
import com.clinic.pm.repo.VisitRepository;

@Component
public class VisitDaoImpl implements VisitDao{
	
	@Autowired
	VisitRepository visitRepo;

	public Visit saveVisit(Visit visit) {
		return visitRepo.save(visit);
	}

	public List<Visit> findAll() {
		return (List<Visit>) visitRepo.findAll();
	}

	public Visit findById(String id) {
		Optional<Visit> visit = visitRepo.findById(id);
		if(visit !=null && visit.isPresent())
			return visit.get();
		return null;
	}

	public void deleteById(String id) {
		visitRepo.deleteById(id);	
	}

}
