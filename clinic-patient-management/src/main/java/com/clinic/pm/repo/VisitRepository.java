package com.clinic.pm.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinic.models.common_models.Visit;


@Repository
public interface VisitRepository extends CrudRepository<Visit,String> {

}
