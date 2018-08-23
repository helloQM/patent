package com.qimou.sb.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qimou.sb.web.entity.User;
import com.qimou.sb.web.mapper.UserDao;
import com.qimou.sb.web.tool.CommUtil;


@Service
//@Transactional
public class UserService {
	 
	@Autowired 
	 private UserDao userDao;
	 
	public List<Map<Object, Object>> listUser(Map<Object, Object> conditionMap){
		conditionMap.put("roleNumSet", CommUtil.roleNumSet);
		return userDao.listUser(conditionMap);
	}
	
	public int userNum (Map<Object, Object> conditionMap){
		return userDao.userNum(conditionMap);
	}
	
	public User userLogin(String userID,String pwd){
		return userDao.userLogin(userID, pwd);
	}
	
	//可用于查看用户明细和检测用户id是否存在。
	public User singleUser(String userID){
		return userDao.singleUser(userID);
	}
	
	@Transactional
	public int addUser(Map<Object, Object> conditionMap){
		return userDao.addUser(conditionMap);
	}
	
	@Transactional
	public int delUser(String userID) {
		return userDao.delUser(userID);
	}
	
	@Transactional
	public int updateUser(Map<Object, Object> conditionMap) {
		return userDao.updateUser(conditionMap);
	}
}
