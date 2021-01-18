package com.clinic.models.common_models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Holiday {
	
	@Id
	private String name;
	private String holidayDate;
	private Date createdDatetime;
	private Date modifiedDatetime;
	private Long createdBy;
	private Long modifiedBy;

}
