package com.enrollmentservice.AddEnrollee.services;

import com.enrollmentservice.AddEnrollee.models.DependentEnrollee;
import com.enrollmentservice.AddEnrollee.repositories.DependentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DependentServiceImpl implements DependentService {

    @Autowired
    private DependentRepository dependentRepository;

    @Override
    public boolean modifyDependentEnrollee(DependentEnrollee dependentEnrollee) {
        try
        {
            Optional<DependentEnrollee> record = dependentRepository.findById(dependentEnrollee.getId());
            DependentEnrollee newRecord = new DependentEnrollee();

            newRecord.setId(dependentEnrollee.getId());

            if(dependentEnrollee.getBirth_Day()!= null)
                newRecord.setBirth_Day(dependentEnrollee.getBirth_Day());
            else
                record.ifPresentOrElse( value -> newRecord.setBirth_Day(value.getBirth_Day()), () -> System.out.println("Not found"));

            if(dependentEnrollee.getName() != null)
                newRecord.setName(dependentEnrollee.getName());
            else
                record.ifPresentOrElse( value -> newRecord.setName(value.getName()), () -> System.out.println("Not found"));

            dependentRepository.deleteById(dependentEnrollee.getId());
            dependentRepository.save(newRecord);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public List<DependentEnrollee> getDependents()
    {
        return dependentRepository.findAll();
    }
}
