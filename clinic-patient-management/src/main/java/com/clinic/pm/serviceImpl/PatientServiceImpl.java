package com.clinic.pm.serviceImpl;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.models.common_models.Patient;
import com.clinic.pm.dao.PatientDao;
import com.clinic.pm.exception.DuplicateException;
import com.clinic.pm.exception.HolidayException;
import com.clinic.pm.service.PatientService;
import com.clinic.pm.util.CommonUtils;
import com.clinic.pm.util.Constants;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	PatientDao patientDao;


	@Override
	public Patient creatPatient(Patient patient) {
		if(CommonUtils.isHoliday())
			throw new HolidayException("Today is Holiday");
		if(patient!=null && getPatientById(patient.getId())!=null)
			throw new DuplicateException("Patient Already Exist");
		patient.setCreatedDatetime(new DateTime().toDateTimeISO().toDate());
		patient.setModifiedDatetime(new DateTime().toDateTimeISO().toDate());
		patient.setCreatedBy(Constants.SYSTEM_ADMIN);
		return patientDao.savePatient(patient);
			
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientDao.getAllPatients();
	}

	@Override
	public Patient getPatientById(String id) {
		return patientDao.findById(id);
	}

	@Override
	public Patient updatePatient(Patient patient) {
		if(CommonUtils.isHoliday())
			throw new HolidayException("Holiday Today");
		Patient patientToUpdate = getPatientById(patient.getId());
		if(patientToUpdate!=null) {
			patientToUpdate.setName(patient.getName());
			patientToUpdate.setAge(patient.getAge());
			patientToUpdate.setGender(patient.getGender());
			patientToUpdate.setModifiedDatetime(new DateTime().toDateTimeISO().toDate()); 
			return patientDao.savePatient(patientToUpdate);
		}
		
		return null;
		
	}

	@Override
	public void deletePatient(String id) {
		if(CommonUtils.isHoliday())
			throw new HolidayException("Holiday Today");
		patientDao.deleteById(id);
		
	}
	
	

}
