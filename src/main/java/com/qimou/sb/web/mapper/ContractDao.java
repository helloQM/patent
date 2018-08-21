package com.qimou.sb.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qimou.sb.web.entity.Contract;

public interface ContractDao {
	
	
	@Select("<script>"+
			"select distinct contractID,contractName,createTime from bs_patent_contract_t "+
			"<if test='stat!=-1'>"+
			"where stat=#{stat} "+
			"</if>"+
			"order by createTime desc "+
			"limit #{startNum} , #{pageSize}"+
			"</script>")
    public List<Contract> listContract(@Param("stat")int stat,@Param("startNum")int startNum,@Param("pageSize")int pageSize);
	
	@Select("select count(distinct contractID) from bs_patent_contract_t where stat=#{stat}")
	public int contractNum (@Param("stat")int stat);
	
	@Select("select * from bs_patent_contract_t where taskID=#{taskID}")
	public Contract singleTask(String taskID);
	
	@Select("select * from bs_patent_contract_t where contractID=#{contractID}")
	public List<Contract> listTask(String contractID);
	
	@Insert("<script>"
            + "INSERT INTO bs_patent_contract_t  (contractID,contractName,taskID,taskName,userID,stat,pathJDS,customerID) values "
            + "<foreach item='item' index='index' collection='list' open='' separator=',' close=''>"
            + "(#{item.contractID},#{item.contractName},#{item.taskID},#{item.taskName},#{item.userID},#{item.stat},#{item.pathJDS},#{item.customerID})"
            + "</foreach>"
            + "</script>")
	public int addContract (@Param("list") List<Object> list);
	
	@Update("UPDATE  bs_patent_contract_t set stat = #{stat},pathJDS = #{pathJDS} where taskID = #{taskID}")
	public int updateTask(Contract contract);
    
	@Insert("<script>"
            + "INSERT INTO bs_patent_usertask_t  (taskID,userID) values "
            + "<foreach item='item' index='index' collection='userContractList' open='' separator=',' close=''>"
            + "(#{item.taskID},#{item.userID})"
            + "</foreach>"
            + "</script>")
	public int distributeTask(@Param("userContractList") List<Object> userContractList);
	
	@Delete("DELETE FROM bs_*_t WHERE #{culName} =#{id}")
	public int delContract(String culName, String id) ;

}
