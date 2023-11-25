package com.e_sun_test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_sun_test.Repo.EmployeeRepository;
import com.e_sun_test.Repo.SeatingChartRepository;
import com.e_sun_test.model.Employee;
import com.e_sun_test.model.SeatingChart;

import jakarta.transaction.Transactional;

@Service
public class SelectChart {

	@Autowired
	private EmployeeRepository EmpRepo;
	
	@Autowired
	private SeatingChartRepository SeatRepo;
	
	//取得坐位
	@Transactional
	public List<SeatingChart> showAllCharts() {
		List<Object[]> result= SeatRepo.findAllChart();
		List<SeatingChart> Charts = new ArrayList<>();

	    for (Object[] objArray : result) {
	        SeatingChart seatingChart=new SeatingChart();
	        seatingChart.setFloorSeatSeq((Integer) objArray[0]);
	        seatingChart.setFloorNo((Integer) objArray[1]);
	        seatingChart.setSeatNo((Integer) objArray[2]);
	        Charts.add(seatingChart);
	    }
	    return Charts;
	}
	
	//取得人員
	@Transactional
	public List<Employee> showAllEmp() {
	    List<Object[]> result = EmpRepo.findAllEmp();
	    List<Employee> employees = new ArrayList<>();

	    for (Object[] objArray : result) {
	        Employee employee = new Employee();
	        employee.setEmpId((Integer) objArray[0]);
	        employee.setName((String) objArray[1]);
	        employee.setEmail((String) objArray[2]);
   
	        if (objArray[3] != null) {
	            int intValue = (int) objArray[3]; 
	            String stringValue = String.valueOf(intValue);
	            employee.setFloorSeatSeq(stringValue);
	        } else {
	            employee.setFloorSeatSeq(null); 
	        }
	        
	        employees.add(employee);
	    }
	    
	    return employees;
	}

	//更新坐位
	@Transactional
	public boolean updateSeat(String seatSeq, Integer empId) {
	    try {
	        EmpRepo.updateEmployee(seatSeq, empId);
	        return true; 
	    } catch (Exception ex) {
	        return false;
	    }
	}

}
