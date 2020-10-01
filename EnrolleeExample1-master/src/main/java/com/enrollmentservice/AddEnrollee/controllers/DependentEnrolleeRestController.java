package com.enrollmentservice.AddEnrollee.controllers;

import com.enrollmentservice.AddEnrollee.models.DependentEnrollee;
import com.enrollmentservice.AddEnrollee.services.DependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DependentEnrolleeRestController {

	@Autowired
	private DependentService dependentService;

	public void DependentEnrollee(DependentEnrollee dependentEnrollee)
	{
		this.dependentService = dependentService;
	}

	@PutMapping("/api/dependent/modify")
	public boolean modifyEnrollee(long id, String name, String birth_Day)
	{
		try {
			DependentEnrollee dependentEnrollee = new DependentEnrollee(id, name, birth_Day);
			dependentService.modifyDependentEnrollee(dependentEnrollee);
			return true;
		}catch (Exception e)
		{
			return false;
		}
	}

	@GetMapping("/api/dependent/get")
	public List<DependentEnrollee> getDependents()
	{
		return dependentService.getDependents();
	}
}

