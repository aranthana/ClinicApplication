package com.clinic.pm.serviceImpl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.models.common_models.Physician;
import com.clinic.pm.dao.PhysicianDao;
import com.clinic.pm.exception.DuplicateException;
import com.clinic.pm.exception.HolidayException;
import com.clinic.pm.service.PhysicianService;
import com.clinic.pm.util.CommonUtils;
import com.clinic.pm.util.Constants;

@Service
public class PhysicianServiceImpl implements PhysicianService{
	
	@Autowired
	PhysicianDao physicianDao;
	

	@Override
	public Physician creatPhysician(Physician physician)  {
		if(CommonUtils.isHoliday())
			throw new HolidayException("Today is Holiday");
		if(physician!=null && getPhysicianById(physician.getId())!=null)
			throw new DuplicateException("Physician Already Exist");
		physician.setCreatedDatetime(new DateTime().toDateTimeISO().toDate());
		physician.setModifiedDatetime(new DateTime().toDateTimeISO().toDate());
		physician.setCreatedBy(Constants.SYSTEM_ADMIN);
		physician.setModifiedBy(Constants.SYSTEM_ADMIN);
		return physicianDao.savePhysician(physician);
			
	
	}

	@Override
	public List<Physician> getAllPhysicians() {
		return physicianDao.findAll();
	}

	@Override
	public Physician getPhysicianById(String id) {
		return  physicianDao.findById(id);
	}

	@Override
	public Physician updatePhysician(Physician physician) {
		if(CommonUtils.isHoliday())
			throw new HolidayException("Holiday Today");
		Physician physicianToUpdate =getPhysicianById (physician.getId());
		if(physicianToUpdate!=null) {
			physicianToUpdate.setName(physician.getName());
			physicianToUpdate.setModifiedBy(physician.getModifiedBy());
			physicianToUpdate.setModifiedDatetime(new DateTime().toDateTimeISO().toDate());
			physicianToUpdate.setModifiedBy(Constants.SYSTEM_ADMIN);
			return physicianDao.savePhysician(physicianToUpdate);
		}
		return null;
		
	}

	@Override
	public void deletePhysician(String id) {
		if(CommonUtils.isHoliday())
			throw new HolidayException("Holiday Today");
		physicianDao.deleteById(id);
	}

}
