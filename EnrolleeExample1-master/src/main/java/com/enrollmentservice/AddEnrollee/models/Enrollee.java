package com.enrollmentservice.AddEnrollee.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "tbl_enrollee")
public class Enrollee {

	@Id
	private long id;
	private String name;
	private boolean activation_Status;
	private String birth_Day;
	private String phone_Number;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<DependentEnrollee> dependentEnrolleeList;

	public Enrollee ()
	{

	}

	public Enrollee(long id, String name, boolean activation_Status, String birth_Day, String phone_Number) {
		this.id = id;
		this.name = name;
		this.activation_Status = activation_Status;
		this.birth_Day = birth_Day;
		this.phone_Number = phone_Number;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActivation_Status() {
		return activation_Status;
	}

	public void setActivation_Status(boolean activation_status) {
		this.activation_Status = activation_status;
	}

	public boolean getActivation_Status() {
		return activation_Status;
	}

	public String getBirth_Day() {
		return birth_Day;
	}

	public void setBirth_Day(String birth_Day) {
		this.birth_Day = birth_Day;
	}

	public String getPhone_Number() {
		return phone_Number;
	}

	public void setPhone_Number(String phone_Number) {
		this.phone_Number = phone_Number;
	}

	public List<DependentEnrollee> getDependentEnrolleeList(){return dependentEnrolleeList;}

	public void setDependentEnrolleeList(List<DependentEnrollee> dependentEnrolleeList){this.dependentEnrolleeList = dependentEnrolleeList ;}

}
