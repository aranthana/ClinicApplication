package com.clinic.pm.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinic.models.common_models.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, String> {

}
