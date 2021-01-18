package com.clinic.models.common_models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Billing {
	
	@Id
	private long id;
	private String patientId;
	private String physicianId;
	private Date billedDatetime;

}
