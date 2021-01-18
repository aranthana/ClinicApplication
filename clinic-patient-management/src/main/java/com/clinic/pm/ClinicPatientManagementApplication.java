package com.clinic.pm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.clinic.*")
public class ClinicPatientManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicPatientManagementApplication.class, args);
	}

}
