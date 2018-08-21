package com.qimou.sb.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qimou.sb.web.entity.Policy;

public interface PolicyDAO {
	
	
	@Select("select * from t_policy;")
    public List<Policy> find();
	
	@Insert("INSERT INTO t_policy ( policyID, parkID,title,policyStr)  "+
          "values(#{policyID},#{parkID},#{title},#{policyStr})")
	public int add(Policy policy);
	
	@Delete("DELETE FROM t_policy WHERE policyID =#{policyID}")
	public int del(String policyID) ;
	
	@Update("UPDATE  t_policy set parkID = #{parkID},title = #{title},policyStr = #{policyStr} where policyID = #{policyID}")
	public int update(Policy policy) ;
    
	@Select("SELECT "+
		  "wfID wfID,"+
		  "(SELECT COUNT(*) FROM t_workflow_file WHERE wfID=t_workflow_info.wfID) num,"+
		  "departmentID departmentID,"+
		  "parkID parkID,"+
		  "sbsx sbsx,"+
		  "sbzl sbzl,"+
		  "blcx blcx,"+
		  "blbm blbm,"+
		  "blsj blsj,"+
		  "blsx blsx,"+
		  "sfyj sfyj,"+
		  "bldh bldh "+
		"FROM t_workflow_info "+
		"WHERE parkID=#{parkID} "+
		"ORDER BY newTime DESC "+
		"LIMIT 0, 5")
	public List<Map<String,Object>> complexSelect(String parkID);

}
