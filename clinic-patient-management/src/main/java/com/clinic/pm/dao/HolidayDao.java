package com.clinic.pm.dao;

import com.clinic.models.common_models.Holiday;

public interface HolidayDao {
	public Holiday getHolidayBydate(String date);

}
