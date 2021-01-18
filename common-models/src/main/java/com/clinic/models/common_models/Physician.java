package com.clinic.models.common_models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data 
@Entity
public class Physician {
	
	@Id 
	private String id;
	private String name;
	private Date createdDatetime;
	private Date modifiedDatetime;
	private String createdBy;
	private String modifiedBy;
	
}
