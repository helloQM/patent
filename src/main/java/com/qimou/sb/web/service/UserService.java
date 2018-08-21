package com.qimou.sb.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qimou.sb.web.entity.User;
import com.qimou.sb.web.mapper.UserDao;


@Service
//@Transactional
public class UserService {
	 
	@Autowired 
	 private UserDao userDao;
	 
	public List<User> listUser(int userStat,int pageNum,int pageSize){
		return userDao.listUser(userStat, pageNum-1, pageSize);
	}
	
	public int userNum (int userStat){
		return userDao.userNum(userStat);
	}
	
	public User userLogin(String userID,String pwd){
		return userDao.userLogin(userID, pwd);
	}
	
	//可用于查看用户明细和检测用户id是否存在。
	public User singleUser(String userID){
		return userDao.singleUser(userID);
	}
	
	@Transactional
	public int addUser(User user){
		return userDao.addUser(user);
	}
	
	@Transactional
	public int delUser(String userID) {
		return userDao.delUser(userID);
	}
	
	@Transactional
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}
}
