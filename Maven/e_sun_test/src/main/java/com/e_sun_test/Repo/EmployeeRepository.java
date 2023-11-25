package com.e_sun_test.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.e_sun_test.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Procedure(name="showall")
	public List<Object[]> findAllEmp();
	
	@Procedure(name="UpdateFloorSeatSeq")
	void updateEmployee(@Param("seatSeq")String seatSeq,@Param("empId")Integer empId);
	
}
