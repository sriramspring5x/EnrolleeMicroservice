package com.enrollmentservice.AddEnrollee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.enrollmentservice.AddEnrollee.models.Enrollee;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolleeRepository extends  JpaRepository<Enrollee, Long> {
	 
}

