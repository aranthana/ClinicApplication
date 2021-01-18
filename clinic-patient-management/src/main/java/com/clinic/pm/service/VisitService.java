package com.clinic.pm.service;

import java.util.List;

import com.clinic.models.common_models.Visit;

public interface VisitService {

	public Visit creatVisit (Visit visit) ;
	public List<Visit> getAllVisits ();
	public Visit getVisitTxById(String id);
	public Visit updateVisit (Visit visit);
	public void deleteVisit (String id);

}
