package com.qimou.sb.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qimou.sb.web.entity.ServicePrice;
import com.qimou.sb.web.mapper.ServicePriceDao;

@Service
// @Transactional
public class ServicePriceService {

	@Autowired
	private ServicePriceDao servicePriceDao;
	
	//列出 客户列表
	public List<Map<Object, Object>> listServicePrice(Map<Object, Object> conditionMap) {
		return servicePriceDao.listServicePrice(conditionMap);
	}
	
	public int servicePricerNum(Map<Object, Object> conditionMap) {
		return servicePriceDao.servicePricerNum(conditionMap);
	}
	
	
	@Transactional
	public int addServicePrice(Map<Object, Object> conditionMap) {
		return servicePriceDao.addServicePrice(conditionMap);
	}
	
	@Transactional
	public int updateServicePrice(Map<Object, Object> conditionMap) {
		return servicePriceDao.updateServicePrice(conditionMap);
	}

}
