package com.clinic.pm.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.models.common_models.Holiday;
import com.clinic.pm.dao.HolidayDao;
import com.clinic.pm.service.HolidayService;

@Service
public class HolidayServiceImpl implements HolidayService{
	
	
	@Autowired
	HolidayDao holidayDao;

	@Override
	public boolean isHolidayToday() {
		Date date = new Date();
		String formatedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		Holiday holiday =holidayDao.getHolidayBydate(formatedDate);
		if(holiday!=null)
			return true;
		return false;
	}

	

}
