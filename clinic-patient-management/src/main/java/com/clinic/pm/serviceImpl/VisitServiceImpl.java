package com.clinic.pm.serviceImpl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.models.common_models.Visit;
import com.clinic.pm.dao.VisitDao;
import com.clinic.pm.exception.DuplicateException;
import com.clinic.pm.exception.HolidayException;
import com.clinic.pm.producer.MQSenderService;
import com.clinic.pm.service.VisitService;
import com.clinic.pm.util.CommonUtils;
import com.clinic.pm.util.Constants;

@Service
public class VisitServiceImpl implements VisitService{
	
	@Autowired
	VisitDao visitDao;

	@Autowired
	MQSenderService mQSenderService;
	
	@Override
	public Visit creatVisit(Visit visit) {
		if(CommonUtils.isHoliday())
			throw new HolidayException("Today is Holiday");
		if(visit!=null && getVisitTxById(visit.getId())!=null)
			throw new DuplicateException("Visit Already Exist");
		visit.setCreatedDatetime(new DateTime().toDateTimeISO().toDate());
		visit.setModifiedDatetime(new DateTime().toDateTimeISO().toDate());
		visit.setCreatedBy(Constants.SYSTEM_ADMIN);
		visit.setModifiedBy(Constants.SYSTEM_ADMIN);
		Visit createdVisit = visitDao.saveVisit(visit);
		if(createdVisit !=null) {
			mQSenderService.send(createdVisit);
		}
		return createdVisit;
				
	}

	@Override
	public List<Visit> getAllVisits() {
		return visitDao.findAll();
	}

	@Override
	public Visit getVisitTxById(String id) {
		return visitDao.findById(id);
	}

	@Override
	public Visit updateVisit(Visit visit) {
		if(CommonUtils.isHoliday())
			throw new HolidayException("Holiday Today");
		Visit visitToUpdate = getVisitTxById(visit.getId());
		if(visitToUpdate !=null) {
			visitToUpdate.setPhysicianId(visit.getPhysicianId());
			visitToUpdate.setReason(visit.getReason());
			visitToUpdate.setVisitDatetime(visit.getVisitDatetime());
			visitToUpdate.setModifiedBy(visit.getModifiedBy());
			visitToUpdate.setModifiedDatetime(new DateTime().toDateTimeISO().toDate());
			visitToUpdate.setModifiedBy(Constants.SYSTEM_ADMIN);
			return visitDao.saveVisit(visitToUpdate);
		}
		
		return null;
		
	}

	@Override
	public void deleteVisit(String id) {
		if(CommonUtils.isHoliday())
			throw new HolidayException("Holiday Today");
		visitDao.deleteById(id);
		
	}

}
