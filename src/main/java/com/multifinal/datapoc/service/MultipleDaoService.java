package com.multifinal.datapoc.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.multifinal.datapoc.config.DatasourceConfig;
import com.multifinal.datapoc.model.Employee;
import com.multifinal.datapoc.model.Retailer;
import com.multifinal.datapoc.model.Student;
import com.multifinal.datapoc.repository.EmployeeRepository;
import com.multifinal.datapoc.repository.RetailerRepository;
import com.multifinal.datapoc.repository.StudentRepository;


@Service
public class MultipleDaoService {
	
	@Autowired
	private DatasourceConfig datasourceConfig;
	
	private SqlSessionTemplate getSqlSessionTemplate(String datasourceName)throws Exception {
		if(datasourceName.equals("Primary"))
			return (SqlSessionTemplate) datasourceConfig.sessionTemplatePrimary();
		else if(datasourceName.equals("Secondary"))
			return (SqlSessionTemplate) datasourceConfig.sessionTemplateSecondary();
		else if(datasourceName.equals("Tertiary"))
			return (SqlSessionTemplate) datasourceConfig.sessionTemplateTertiary();
		else
			throw new Exception("Invalid datasource name");
	}
	
	public List<Employee> getAllEmployees(String datasourceName)throws Exception {
		EmployeeRepository mapper=getSqlSessionTemplate(datasourceName).getMapper(EmployeeRepository.class);
		return mapper.findAll();
	}
	
	public List<Student> getAllStudents(String datasourceName)throws Exception {
		StudentRepository mapper=getSqlSessionTemplate(datasourceName).getMapper(StudentRepository.class);
		return mapper.findAllStudents();
	}
	
	public List<Retailer> getAllRetailers(String datasourceName)throws Exception {
		RetailerRepository mapper = getSqlSessionTemplate(datasourceName).getMapper(RetailerRepository.class);
		return mapper.findAllRetailers();
	}
	

}