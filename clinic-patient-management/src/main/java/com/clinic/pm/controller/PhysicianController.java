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

import com.clinic.models.common_models.Physician;
import com.clinic.pm.service.PhysicianService;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@CrossOrigin(origins = "*") 
@RestController
@RequestMapping(value = "/physicians")
public class PhysicianController {
	
	@Autowired
	PhysicianService physicianService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Physician addNewPhysician(@RequestBody Physician physician) {
		log.info("Physician Creation Workflow");
		return physicianService.creatPhysician(physician);
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	  public List<Physician> getAllPhysicians() {
	    return physicianService.getAllPhysicians();
	 }
	
	@RequestMapping(value="/physician/physicianId/{physicianId}", method=RequestMethod.GET)
	public Physician getPhysician(@PathVariable String physicianId) {
		return physicianService.getPhysicianById(physicianId);
	}
	
	@RequestMapping(value="/update",method = RequestMethod.PUT)
	public Physician updatePhysician(@RequestBody Physician physician) {
		log.info("Physician Updating Workflow");
		return physicianService.updatePhysician(physician);
		
	}
	
	@RequestMapping(value="/delete/physicianId/{physicianId}", method=RequestMethod.DELETE)
	public void deletePhysician(@PathVariable String physicianId) {
		physicianService.deletePhysician(physicianId);
	}


}
