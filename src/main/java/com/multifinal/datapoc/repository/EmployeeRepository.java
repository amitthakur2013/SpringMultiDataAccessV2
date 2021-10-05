package com.multifinal.datapoc.repository;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.multifinal.datapoc.model.Employee;


@Repository
@Mapper
public interface EmployeeRepository {
	
	@Results(id = "empMap",value = {
			@Result(property = "emailId",column = "email_address"),
			@Result(property = "firstName",column="first_name"),
			@Result(property = "lastName",column="last_name")
	})
	@Select("Select * from employees")
	public List<Employee> findAll();
	
	@ResultMap("empMap")
	@Select("Select * from employees where id=#{id}")
	public Employee findById(long id);
	
	@Delete("Delete from employees where id=#{id}")
	public int deleteById(long id);
	
	@Insert("Insert into employees(id,first_name,last_name,email_address) values(#{id},#{firstName},#{lastName},#{emailId})")
	public int insert(Employee employee);
	
	@Update("Update employees set first_name=#{firstName},last_name=#{lastName},email_address=#{emailId} where id=#{id}")
	public int update(Employee employee);
	
}