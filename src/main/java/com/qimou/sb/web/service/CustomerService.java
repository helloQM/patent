package com.qimou.sb.web.service;

import java.util.List;

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
	public List<Customer> listCustomer(int pageNum, int pageSize) {
		return customerDao.listCustomer(pageNum-1, pageSize);
	}
	
	//客户列表的总数量
	public int customerNum() {
		return customerDao.customerNum();
	}
		
	//单个客户的详情
	public Customer singleCustomer(String customerID) {
		return customerDao.singleCustomer(customerID);
	}

	@Transactional
	public int addCustomer(Customer customer) {
		
		return customerDao.addCustomer(customer);
	}

	@Transactional
	public int updateCustomer(Customer customer) {
		return customerDao.updateCustomer(customer);
	}

}
