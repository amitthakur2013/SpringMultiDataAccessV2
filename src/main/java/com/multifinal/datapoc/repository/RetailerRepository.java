package com.multifinal.datapoc.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.multifinal.datapoc.model.Retailer;

@Repository
@Mapper
public interface RetailerRepository {

	@Select("Select * from retailer")
	@Results(id = "retMap",value = {
		@Result(property = "id",column = "id"),
		@Result(property = "retailerName",column = "retailer_name")
	})
	public List<Retailer> findAllRetailers();
}