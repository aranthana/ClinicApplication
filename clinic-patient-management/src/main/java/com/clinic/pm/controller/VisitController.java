package com.clinic.pm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.models.common_models.Visit;
import com.clinic.pm.service.VisitService;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@CrossOrigin(origins = "*") 
@RestController
@RequestMapping(value = "/visit")
public class VisitController {
	
	@Autowired
	VisitService visitService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Visit addNewVisit(@RequestBody Visit visit) {
		log.info("Visit Creation Workflow");
		return visitService.creatVisit(visit);
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	  public List<Visit> getAllVisits() {
	    return visitService.getAllVisits();
	 }
	
	@RequestMapping(value="/visitId/{visitId}", method=RequestMethod.GET)
	public Visit getVisit(@PathVariable String visitId) {
		return visitService.getVisitTxById(visitId);
	}
	
	@RequestMapping(value="/update",method = RequestMethod.PUT)
	public Visit updateVisit(@RequestBody Visit visit) {
		log.info("Visit Updating Workflow");
		return visitService.updateVisit(visit);
		
	}
	
	@RequestMapping(value="/delete/visitId/{visitId}", method=RequestMethod.DELETE)
	public void deleteVisit(@PathVariable String visitId) {
		visitService.deleteVisit(visitId);
	}


}
