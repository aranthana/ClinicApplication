package com.clinic.pm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.clinic.models.common_models.ExceptionResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(DuplicateException.class)
	    public ResponseEntity<ExceptionResponse> resourceAlreadyExist(DuplicateException ex) {
		 	log.error(ex.getMessage());
	        ExceptionResponse response = new ExceptionResponse();
	        response.setReasonCode("CONFLICT");
	        response.setDescription(ex.getMessage());
	        
	        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
	    }

	    @ExceptionHandler(HolidayException.class)
	    public ResponseEntity<ExceptionResponse> resourceAlreadyExists(HolidayException ex) {
	     	log.error(ex.getMessage());
	        ExceptionResponse response=new ExceptionResponse();
	        response.setReasonCode("NOT_ACCEPTABLE");
	        response.setDescription(ex.getMessage());

	        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_ACCEPTABLE);
	    }
}
