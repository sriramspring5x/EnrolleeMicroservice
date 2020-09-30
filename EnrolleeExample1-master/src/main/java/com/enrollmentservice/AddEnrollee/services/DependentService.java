package com.enrollmentservice.AddEnrollee.services;

import com.enrollmentservice.AddEnrollee.models.DependentEnrollee;
import com.enrollmentservice.AddEnrollee.models.Enrollee;

import java.util.List;

public interface DependentService {

    public boolean modifyDependentEnrollee(DependentEnrollee dependentEnrollee);
    public List<DependentEnrollee> getDependents();
}
