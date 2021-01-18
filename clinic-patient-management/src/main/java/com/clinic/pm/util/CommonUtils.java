package com.clinic.pm.util;

import java.util.concurrent.atomic.AtomicBoolean;

public class CommonUtils {
	
	public static AtomicBoolean isHolidayToday=new AtomicBoolean(false);
	
	
	public static boolean isHoliday() {
		return isHolidayToday.get();
	}

}
