package com.clinic.pm.sheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.clinic.pm.service.HolidayService;
import com.clinic.pm.util.CommonUtils;

@Configuration
@EnableScheduling
public class HolidayScheduler {
	
	@Autowired
	HolidayService holidayServ;
	
	@Scheduled(cron="0 0 0 * * ? ")
	public void fetchHolidayList() {
		CommonUtils.isHolidayToday.set(holidayServ.isHolidayToday());  
	}

}
