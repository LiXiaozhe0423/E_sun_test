package com.e_sun_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.e_sun_test.model.Employee;
import com.e_sun_test.model.SeatingChart;
import com.e_sun_test.service.SelectChart;



@RestController
@CrossOrigin
public class SelectChartController {

	@Autowired
	private SelectChart selectService;
	
	//取得座位資訊
	@GetMapping("/ShowChart")
	public ResponseEntity<?> showAllChart(){
		List<SeatingChart> chartResult = selectService.showAllCharts();
		if(chartResult==null) {
			return new ResponseEntity<String>("沒有此資料",HttpStatus.NOT_FOUND);
		}	
		return new ResponseEntity<>(chartResult, HttpStatus.OK);
	}
	
	//取得人員資訊
	@GetMapping("/ShowEmp")
	public ResponseEntity<?> showAllEmp(){
		List<Employee> empResult = selectService.showAllEmp();
		if(empResult==null) {
			return new ResponseEntity<String>("沒有此資料",HttpStatus.NOT_FOUND);
		}	
		return new ResponseEntity<>(empResult, HttpStatus.OK);
	}
	
	//變更座位 當帶的值為0則移除該員工坐位
	@PutMapping("/selectChart/{empId}")
	public ResponseEntity<?> chooseChart(@PathVariable("empId")Integer empId,
										 @RequestParam String seatSeq){
		boolean updateSeat = selectService.updateSeat(seatSeq, empId);
		if(updateSeat) {
			return new ResponseEntity<String>("變更成功",HttpStatus.OK);
		}
		return new ResponseEntity<String>("變更失敗",HttpStatus.NOT_FOUND);
	}
	
	
}
