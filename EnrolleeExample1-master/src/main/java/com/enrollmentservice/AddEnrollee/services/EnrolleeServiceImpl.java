package com.enrollmentservice.AddEnrollee.services;

import com.enrollmentservice.AddEnrollee.models.DependentEnrollee;
import com.enrollmentservice.AddEnrollee.models.Enrollee;
import com.enrollmentservice.AddEnrollee.repositories.DependentRepository;
import com.enrollmentservice.AddEnrollee.repositories.EnrolleeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrolleeServiceImpl implements EnrolleeService {

    @Autowired
    private EnrolleeRepository enrolleeRepository;
    @Autowired
    private DependentRepository dependentRepository;

    public void setEnrolleeRepository(EnrolleeRepository enrolleeRepository)
    {
        this.enrolleeRepository = enrolleeRepository;
    }

    @Override
    public boolean createEnrollee(Enrollee enrollee) {
        try
        {
            Enrollee person = new Enrollee(enrollee.getId(), enrollee.getName(), enrollee.isActivation_Status(),enrollee.getBirth_Day(), enrollee.getPhone_Number());
            enrolleeRepository.save(person);
            return true;
        }catch (Exception e)
        {
            return false;
        }

    }
    @Override
    public List<Enrollee> getEnrollees()
    {
        return enrolleeRepository.findAll();
    }

    @Override
    public boolean modifyEnrollee(Enrollee enrollee) {
        try
        {
            Optional<Enrollee> record = enrolleeRepository.findById(enrollee.getId());

            if(enrollee.getBirth_Day()!= null)
                record.get().setBirth_Day(enrollee.getBirth_Day());

            if(enrollee.getName() != null)
                record.get().setName(enrollee.getName());

            if(enrollee.getPhone_Number() != null)
                record.get().setPhone_Number(enrollee.getPhone_Number());

            record.get().setActivation_Status(enrollee.getActivation_Status());

            enrolleeRepository.save(record.get());
            return true;
        }catch (Exception e)
        {
            return false;
        }

    }

    @Override
    public boolean removeEnrollee(long id) {
        try{

            enrolleeRepository.deleteById(id);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean addDependent(long enrollee_ID, DependentEnrollee dependentEnrollee) {

        try{
            Optional<Enrollee> record = enrolleeRepository.findById(enrollee_ID);
            record.get().getDependentEnrolleeList().add(dependentEnrollee);
            dependentEnrollee.setEnrollee(record.get());

            dependentRepository.save(dependentEnrollee);
            return true;
        }catch (Exception e)
        {
            return false;
        }

    }

    @Override
    public boolean removeDependent(long enrollee_ID, long dependent_ID) {
        try
        {
            Optional<DependentEnrollee> dependentEnrollee = dependentRepository.findById(dependent_ID);
            dependentRepository.delete(dependentEnrollee.get());

            Optional<Enrollee> record = enrolleeRepository.findById(enrollee_ID);
            record.get().getDependentEnrolleeList().remove(dependentEnrollee);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }
}
