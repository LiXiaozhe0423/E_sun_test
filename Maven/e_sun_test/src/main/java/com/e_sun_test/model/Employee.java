package com.e_sun_test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;

@Entity
@Table(name="employee")
@NamedStoredProcedureQuery(name = "showall",procedureName = "showall")

@NamedStoredProcedureQuery(name="UpdateFloorSeatSeq",procedureName = "UpdateFloorSeatSeq",parameters = {
							@StoredProcedureParameter(mode = ParameterMode.IN,name = "seatSeq",type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN,name = "empId",type = Integer.class)} )
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;
	
	private String name;
	
	private String email;
	
	private String floorSeatSeq;
	
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFloorSeatSeq() {
		return floorSeatSeq;
	}

	public void setFloorSeatSeq(String floorSeatSeq) {
		this.floorSeatSeq = floorSeatSeq;
	}

}
