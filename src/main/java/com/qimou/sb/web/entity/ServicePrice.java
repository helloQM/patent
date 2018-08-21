package com.qimou.sb.web.entity;

import java.io.Serializable;

public class ServicePrice implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String serviceID = null ;//服务ID
	private String cityCode = null ;//城市编码【六位一体】
	private String serviceCode = null ;//服务编码
	private String serviceName = null ;//服务名称
	private String serviceBak = null ;//服务介绍
	private String serviceType = null ;//服务类型
	private int priceNumMin = 0 ;//价格下限数值【单位：元】
	private int priceNumMax = 0 ;//价格上限数值【单位：元】
	public String getServiceID() {
		return serviceID;
	}
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceBak() {
		return serviceBak;
	}
	public void setServiceBak(String serviceBak) {
		this.serviceBak = serviceBak;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public int getPriceNumMin() {
		return priceNumMin;
	}
	public void setPriceNumMin(int priceNumMin) {
		this.priceNumMin = priceNumMin;
	}
	public int getPriceNumMax() {
		return priceNumMax;
	}
	public void setPriceNumMax(int priceNumMax) {
		this.priceNumMax = priceNumMax;
	}
	
	
}
