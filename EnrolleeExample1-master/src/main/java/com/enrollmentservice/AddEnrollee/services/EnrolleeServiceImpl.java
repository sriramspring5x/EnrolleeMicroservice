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
            Enrollee newRecord = new Enrollee();
            newRecord.setId(enrollee.getId());

            if(enrollee.getBirth_Day()!= null)
                newRecord.setBirth_Day(enrollee.getBirth_Day());
            else
                record.ifPresentOrElse( value -> newRecord.setBirth_Day(value.getBirth_Day()), () -> System.out.println("Not found"));

            if(enrollee.getName() != null)
                newRecord.setName(enrollee.getName());
            else
                record.ifPresentOrElse( value -> newRecord.setName(value.getName()), () -> System.out.println("Not found"));

            if(enrollee.getPhone_Number() != null)
                newRecord.setPhone_Number(enrollee.getPhone_Number());
            else
                record.ifPresentOrElse( value -> newRecord.setPhone_Number(value.getPhone_Number()), () -> System.out.println("Not found"));

            newRecord.setDependentEnrolleeList(record.get().getDependentEnrolleeList());

            newRecord.setActivation_Status(enrollee.getActivation_Status());

            enrolleeRepository.deleteById(enrollee.getId());
            enrolleeRepository.save(newRecord);
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
