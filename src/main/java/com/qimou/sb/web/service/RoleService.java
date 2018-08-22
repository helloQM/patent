package com.qimou.sb.web.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qimou.sb.web.entity.Role;
import com.qimou.sb.web.mapper.RoleDao;

@Service
// @Transactional
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	public List<Map<Object, Object>> listRole(Map<Object, Object> conditionMap){
		return roleDao.listRole(conditionMap);
	}
	
	public List<Object> listRoleAuthority(String roleID){
		return roleDao.listRoleAuthority(roleID);
	}
	
	public int roleNum (){
		return roleDao.roleNum();
	}
	
	public Role singleRole(String roleID){
		return roleDao.singleRole(roleID);
	}
	
	@Transactional
	public int delRole(String roleID){
		return roleDao.delRole(roleID);
	}
	
	@Transactional
	public int updateRole(Map<Object, Object> conditionMap){
		return roleDao.updateRole(conditionMap);
	}

	@Transactional
	public int addRole(Map<Object, Object> conditionMap){
		return roleDao.addRole(conditionMap);
	}
	
	@Transactional
	public boolean modifyRoleAuthority(String roleID,Set<Object> conditionSet){
		int delRoleAuthority = roleDao.delRoleAuthority(roleID);//先删除
		int addRoleAuthority = roleDao.addRoleAuthority(roleID, conditionSet);//再添加
		if (delRoleAuthority > 0 && addRoleAuthority > 0) {
			return true;
		}else{
			return false;
		}
	}
	
	
}
