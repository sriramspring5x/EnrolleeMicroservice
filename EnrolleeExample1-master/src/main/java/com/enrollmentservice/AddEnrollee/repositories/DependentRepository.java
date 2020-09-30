package com.enrollmentservice.AddEnrollee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.enrollmentservice.AddEnrollee.models.DependentEnrollee;
import org.springframework.stereotype.Repository;

@Repository
public interface DependentRepository  extends JpaRepository<DependentEnrollee, Long>{

}