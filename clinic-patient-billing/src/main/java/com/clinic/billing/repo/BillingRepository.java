package com.clinic.billing.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinic.models.common_models.Billing;


@Repository
public interface BillingRepository extends CrudRepository<Billing,Long>{

}
