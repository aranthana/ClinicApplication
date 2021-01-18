package com.clinic.pm.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clinic.models.common_models.Holiday;
import com.clinic.pm.dao.HolidayDao;
import com.clinic.pm.repo.HolidayRepository;

@Component
public class HolidayDaoImpl implements HolidayDao {
	
	@Autowired
	HolidayRepository holidayRepo;

	@Override
	public Holiday getHolidayBydate(String date) {
		return holidayRepo.getHolidayBydate(date);
	}


}
