package com.qimou.sb.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qimou.sb.web.entity.Customer;
import com.qimou.sb.web.mapper.CustomerDao;

@Service
// @Transactional
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	//列出 客户列表
	public List<Customer> listCustomer(Map<Object, Object> conditionMap) {
		return customerDao.listCustomer(conditionMap);
	}
	
	// 用于可编辑的下拉框，做模糊查询。
	public List<Map<Object, Object>> listSimpleCustomer(Map<Object, Object> conditionMap){
		return customerDao.listSimpleCustomer(conditionMap);
	}
	
	//客户列表的总数量
	public int customerNum(Map<Object, Object> conditionMap) {
		return customerDao.customerNum(conditionMap);
	}
		
	@Transactional
	public int addCustomer(Map<Object, Object> conditionMap) {
		
		return customerDao.addCustomer(conditionMap);
	}

	@Transactional
	public int updateCustomer(Map<Object, Object> conditionMap) {
		return customerDao.updateCustomer(conditionMap);
	}

}
