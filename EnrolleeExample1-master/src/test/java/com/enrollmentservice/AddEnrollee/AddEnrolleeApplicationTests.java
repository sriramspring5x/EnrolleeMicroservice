package com.enrollmentservice.AddEnrollee;

import com.enrollmentservice.AddEnrollee.models.DependentEnrollee;
import com.enrollmentservice.AddEnrollee.models.Enrollee;
import com.enrollmentservice.AddEnrollee.repositories.DependentRepository;
import com.enrollmentservice.AddEnrollee.repositories.EnrolleeRepository;
import com.enrollmentservice.AddEnrollee.services.DependentService;
import com.enrollmentservice.AddEnrollee.services.EnrolleeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddEnrolleeApplicationTests {

    @Autowired
    private EnrolleeService enrolleeService;
    @Autowired
    private EnrolleeRepository enrolleeRepository;
    @Autowired
    private DependentService dependentService;
    @Autowired
    private DependentRepository dependentRepository;

    @Test
    public void createEnrollee() {
        Enrollee enrollee = new Enrollee(1, "ABC", true, "11/01/1901", "500-222-11-11");
        Assert.assertTrue(enrolleeService.createEnrollee(enrollee));
        Optional<Enrollee> result = enrolleeRepository.findById((long) 1);
        Assert.assertEquals(enrollee.getId(), result.get().getId());
        Assert.assertEquals(enrollee.getName(), result.get().getName());
        Assert.assertEquals(enrollee.getPhone_Number(), result.get().getPhone_Number());
        Assert.assertEquals(enrollee.getBirth_Day(), result.get().getBirth_Day());
    }

    @Test
    public void modifyEnrollee() {
        Enrollee enrollee = new Enrollee(1, "ABC", true, "11/01/1901", "500-222-11-11");
        enrolleeService.createEnrollee(enrollee);
        Enrollee enrollee2 = new Enrollee(1, "KDR", false,"11/01/1902", "500-222-11-12");
        Assert.assertTrue(enrolleeService.modifyEnrollee(enrollee2));
        Optional<Enrollee> result = enrolleeRepository.findById((long) 1);
        Assert.assertEquals(enrollee2.getId(), result.get().getId());
        Assert.assertEquals(enrollee2.getName(), result.get().getName());
        Assert.assertEquals(enrollee2.getPhone_Number(), result.get().getPhone_Number());
        Assert.assertEquals(enrollee2.getBirth_Day(), result.get().getBirth_Day());
    }

    @Test
    public void removeEnrollee()
    {
        Enrollee enrollee = new Enrollee(1, "ABC", true, "11/01/1901", "500-222-11-11");
        enrolleeService.createEnrollee(enrollee);
        Assert.assertTrue(enrolleeService.removeEnrollee(1));
    }

    @Test
    public void addDependent()
    {
        Enrollee enrollee = new Enrollee(1, "ABC", true, "11/01/1901", "500-222-11-11");
        enrolleeService.createEnrollee(enrollee);
        DependentEnrollee dependentEnrollee = new DependentEnrollee(2, "CZT", "12/08/1999");
        Assert.assertFalse(enrolleeService.addDependent(1,dependentEnrollee));
    }

    @Test
    public void removeDependent()
    {
        Enrollee enrollee = new Enrollee(1, "ABC", true, "11/01/1901", "500-222-11-11");
        enrolleeService.createEnrollee(enrollee);
        DependentEnrollee dependentEnrollee = new DependentEnrollee(2, "CZT", "12/08/1999");
        enrolleeService.addDependent(1,dependentEnrollee);
        Assert.assertFalse(enrolleeService.removeDependent(1,2));
    }

    @Test
    public void modifyDependent()
    {
        Enrollee enrollee = new Enrollee(1, "ABC", true, "11/01/1901", "500-222-11-11");
        enrolleeService.createEnrollee(enrollee);
        DependentEnrollee dependentEnrollee = new DependentEnrollee(2, "CZT", "12/08/1999");
        enrolleeService.addDependent(1,dependentEnrollee);
        DependentEnrollee dependentEnrollee2 = new DependentEnrollee(2, "CZTK", "13/08/1999");
        Assert.assertFalse(dependentService.modifyDependentEnrollee(dependentEnrollee2));
    }
}
