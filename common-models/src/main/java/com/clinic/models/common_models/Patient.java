package com.clinic.models.common_models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Patient {
	
	@Id 
	private String id;
	private String name;
	private int age;
	private String gender;
	private Date createdDatetime;
	private Date modifiedDatetime;
	private String createdBy;
	
}
