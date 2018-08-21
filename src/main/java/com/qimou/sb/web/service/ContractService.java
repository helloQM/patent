package com.qimou.sb.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qimou.sb.web.entity.Contract;
import com.qimou.sb.web.mapper.ContractDao;

@Service
// @Transactional
public class ContractService {

	@Autowired
	private ContractDao contractDao;
	
	//列出 合同列表
	public List<Contract> listContract(int userStat, int pageNum, int pageSize) {
		return contractDao.listContract(userStat, pageNum-1, pageSize);
	}
	
	//合同列表的总数量
	public int contractNum(int stat) {
		return contractDao.contractNum(stat);
	}

	//列出指定合同的所有任务列表
	public List<Contract> listTask(String contractID) {
		return contractDao.listTask(contractID);
	}
		
	//单个任务的详情
	public Contract singleTask(String taskID) {
		return contractDao.singleTask(taskID);
	}

	@Transactional
	public int addContract(List<Object> contractList) {
		
//		Iterator<Contract> it = contractList.iterator();
//		while(it.hasNext()){
//			it.next();
//		}
		return contractDao.addContract(contractList);
	}

	@Transactional
	public int updateTask(Contract contract) {
		return contractDao.updateTask(contract);
	}

	//分发任务
	@Transactional
	public int distributeTask(List<Object> userContractList) {
		return contractDao.distributeTask(userContractList);
	}
	
	//culName表示合同ID或任务ID
	@Transactional
	public int delContract(String culName, String id) {
		return contractDao.delContract(culName,id);
	}
}
