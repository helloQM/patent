package com.qimou.sb.web.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qimou.sb.web.entity.Authority;
import com.qimou.sb.web.mapper.AuthorityDao;

@Service
// @Transactional
public class AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;
	
	//获得全部的权限列表
	public List<Authority> listAllAuthority() {
		return authorityDao.listAllAuthority();
	}
	
	public List<Map<Object, Object>> listTemp(){
		return authorityDao.listTemp();
	}
	
	//根据用户获得 权限列表
	public List<Authority> listAuthorityByRole(Set<Object> conditionSet) {
		return authorityDao.listAuthorityByRole(conditionSet);
	}
	
	

}
