package com.clinic.billing.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clinic.billing.service.BillingService;
import com.clinic.models.common_models.Billing;
import com.clinic.models.common_models.Visit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class VisitReceiver {
	
	@Autowired
	BillingService billingService;
	
	public void messageReceived(Visit visit) {
		log.info("Message recived : visit id = "+visit.getId());
		Billing billing =new Billing();
		billing.setPatientId(visit.getPatientId());
		billing.setPhysicianId(visit.getPhysicianId());
		billingService.createNewBilling(billing);
	}

}
