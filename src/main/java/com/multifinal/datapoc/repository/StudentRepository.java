package com.multifinal.datapoc.repository;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.multifinal.datapoc.model.Student;


@Repository
@Mapper
public interface StudentRepository {

	@Select("Select * from student")
	@Results(id = "stdMap",value = {
			@Result(property = "id",column = "id"),
			@Result(property = "name", column = "name")
	})
	public List<Student> findAllStudents();
	
}