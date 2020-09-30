package com.enrollmentservice.AddEnrollee.services;

import com.enrollmentservice.AddEnrollee.models.DependentEnrollee;
import com.enrollmentservice.AddEnrollee.models.Enrollee;

import java.util.List;

public interface EnrolleeService {
    public boolean createEnrollee(Enrollee enrollee);
    public boolean modifyEnrollee(Enrollee enrollee);
    public boolean removeEnrollee(long id);
    public boolean addDependent(long enrollee_ID, DependentEnrollee dependentEnrollee);
    public boolean removeDependent(long enrollee_ID, long dependent_ID);
    public List<Enrollee> getEnrollees();
}
