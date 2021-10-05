package com.multifinal.datapoc.model;

public class Retailer {
	private long id;
	private String retailerName;
	
	public Retailer() {
	}
	public Retailer(long id, String retailerName) {
		super();
		this.id = id;
		this.retailerName = retailerName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	@Override
	public String toString() {
		return "Retailer [id=" + id + ", retailerName=" + retailerName + "]";
	}
	
	
}