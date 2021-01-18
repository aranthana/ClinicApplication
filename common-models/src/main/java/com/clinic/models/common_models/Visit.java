package com.clinic.models.common_models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Visit implements Serializable {
	
	@Id 
	private String id;
	private Date visitDatetime;
	private String patientId;
	private String physicianId;
	private String reason;
	private Date createdDatetime;
	private Date modifiedDatetime;
	private String createdBy;
	private String modifiedBy;
	
}
