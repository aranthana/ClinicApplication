package com.clinic.pm.dao;

import java.util.List;
import com.clinic.models.common_models.Visit;

public interface VisitDao {
	
	public Visit saveVisit(Visit visit);
	public List<Visit> findAll();
	public Visit findById(String id);
	public void deleteById(String id);

}
