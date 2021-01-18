package com.clinic.billing.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clinic.billing.dao.BillingDao;
import com.clinic.billing.repo.BillingRepository;
import com.clinic.models.common_models.Billing;

@Component
public class BillingDaoImpl implements  BillingDao{
	
	@Autowired
	BillingRepository billingRepo;

	@Override
	public Billing createBilling(Billing billing) {
		return billingRepo.save(billing);
	}

}
