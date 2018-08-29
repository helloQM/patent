package com.qimou.sb.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qimou.sb.web.entity.CustApplicationMan;
import com.qimou.sb.web.entity.CustInventMan;
import com.qimou.sb.web.entity.CustLinkMan;
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
	
//////////////////////////////////////////////////////////////////////////

	// 用于可编辑的下拉框，做模糊查询。
	public List<Map<Object, Object>> listSimpleCustomer(Map<Object, Object> conditionMap){
		return customerDao.listSimpleCustomer(conditionMap);
	}
	
	
    public List<CustApplicationMan> listCustApplicationman(Map<Object, Object> conditionMap){
    	return customerDao.listCustApplicationman(conditionMap);
    }
	
	public int custApplicationmanNum (Map<Object, Object> conditionMap){
		return customerDao.custApplicationmanNum(conditionMap);
	}
	
	@Transactional
	public int addCustApplicationman (Map<Object, Object> conditionMap){
		return customerDao.addCustApplicationman(conditionMap);
	}
	
	@Transactional
	public int updateCustApplicationman(Map<Object, Object> conditionMap){
		return customerDao.updateCustApplicationman(conditionMap);
	}
	
	
	///////////////
	
    public List<CustInventMan> listCustInventman(Map<Object, Object> conditionMap){
    	return customerDao.listCustInventman(conditionMap);
    }
	
	public int custInventmanNum (Map<Object, Object> conditionMap){
		return customerDao.custInventmanNum(conditionMap);
	}
	
	@Transactional
	public int addCustInventman (Map<Object, Object> conditionMap){
		return customerDao.addCustInventman(conditionMap);
	}
	
	@Transactional
	public int updateCustInventman(Map<Object, Object> conditionMap){
		return customerDao.updateCustInventman(conditionMap);
	}
	
///////////////
	
	public List<CustLinkMan> listCustLinkman(Map<Object, Object> conditionMap){
		return customerDao.listCustLinkman(conditionMap);
	}
	
	public int custLinkmanNum (Map<Object, Object> conditionMap){
		return customerDao.custLinkmanNum(conditionMap);
	}
	
	@Transactional
	public int addCustLinkman (Map<Object, Object> conditionMap){
		return customerDao.addCustLinkman(conditionMap);
	}
	
	@Transactional
	public int updateCustLinkman(Map<Object, Object> conditionMap){
		return customerDao.updateCustLinkman(conditionMap);
	}
}
