package com.clinic.models.common_models;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class ExceptionResponse<T> {
	
	
	private T data;
	private String reasonCode;
	private String description;
	
	public ExceptionResponse(@Nullable T data, String reasonCode,String description) {
		this.data = data;
		this.reasonCode = reasonCode;
		this.description =description;
	}
	
	public ExceptionResponse() {
		super();
	}
	
	

}
