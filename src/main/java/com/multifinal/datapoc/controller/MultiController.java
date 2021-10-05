package com.multifinal.datapoc.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multifinal.datapoc.model.Employee;
import com.multifinal.datapoc.model.Retailer;
import com.multifinal.datapoc.model.Student;
import com.multifinal.datapoc.service.MultipleDaoService;


@RestController
@RequestMapping("/")
public class MultiController {
	
	@Autowired
	MultipleDaoService multipleDaoService;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()throws Exception {
		return multipleDaoService.getAllEmployees("Primary");
	}
	
	@GetMapping("/students")
	public List<Student> getAllStudents()throws Exception {
		return multipleDaoService.getAllStudents("Secondary");
	}
	
	@GetMapping("/retailers")
	public List<Retailer> getAllRetailers()throws Exception {
		return multipleDaoService.getAllRetailers("Tertiary");
	}
	
}