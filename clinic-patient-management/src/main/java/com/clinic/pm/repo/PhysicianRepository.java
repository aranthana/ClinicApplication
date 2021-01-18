package com.clinic.pm.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinic.models.common_models.Physician;


@Repository
public interface PhysicianRepository extends CrudRepository<Physician, String>{

}
