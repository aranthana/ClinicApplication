package com.clinic.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.clinic.*")
public class ClinicPatientBillingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicPatientBillingApplication.class, args);
	}

}
