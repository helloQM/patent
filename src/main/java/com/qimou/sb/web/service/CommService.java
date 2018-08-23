package com.qimou.sb.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimou.sb.web.mapper.CommDao;

@Service
// @Transactional
public class CommService {

	@Autowired
	private CommDao commDao;
	
	//列出 客户列表
	public List<Map<Object, Object>> listCityCode(Map<Object, Object> conditionMap) {
		return commDao.listCityCode(conditionMap);
	}

	public List<Map<Object, Object>> listAllRoles(){
		return commDao.listAllRoles();
	}
}
