package com.clinic.billing.serviceImpl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.billing.dao.BillingDao;
import com.clinic.billing.service.BillingService;
import com.clinic.models.common_models.Billing;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BillingServiceImpl implements BillingService{
	
	@Autowired
	BillingDao billingDao;

	@Override
	public Billing createNewBilling(Billing billing) {
		billing.setBilledDatetime(new DateTime().toDateTimeISO().toDate());
		log.info("Billing Creation Workflow");
		return billingDao.createBilling(billing);
	}

}
