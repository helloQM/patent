package com.qimou.sb.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qimou.sb.web.entity.Policy;
import com.qimou.sb.web.mapper.PolicyDAO;


@Service
//@Transactional
public class PolicyService {
	 
	@Autowired 
	 private PolicyDAO policyDAO;
	 
	 public List<Policy> find() {
	        return policyDAO.find();
	 }
	 
	 @Transactional
	 public int add(Policy policy) {
	        return policyDAO.add(policy);
	 }
	 @Transactional
	 public int del(String policyID) {
		 return policyDAO.del(policyID);
	 }
	 @Transactional
	 public int update(Policy policy) {
		 return policyDAO.update(policy);
	 }
	 public List<Map<String,Object>> complexSelect(String parkID){
		 return policyDAO.complexSelect(parkID);
	 }
}
