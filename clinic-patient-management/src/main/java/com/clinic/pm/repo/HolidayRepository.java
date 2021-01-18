package com.clinic.pm.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinic.models.common_models.Holiday;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday,String>{
	
	
	@Query(value = "SELECT a FROM Holiday  a WHERE  a.holidayDate = :date ")
	public Holiday getHolidayBydate(String date);
	

}
