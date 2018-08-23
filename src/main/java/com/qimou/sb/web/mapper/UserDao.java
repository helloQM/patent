package com.qimou.sb.web.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qimou.sb.web.entity.User;

public interface UserDao {
	
	
//	@Select("<script>"+
//			"SELECT userID,userName,pwd,userRole,userEmail,userCellPhone,userStat,userDepartment,userGender,createTime "+
//			" from bs_patent_user_t where 1=1 "+
//			"<if test='userStat!=\"\"'>"+
//			"and userStat=#{userStat} "+
//			"</if>"+
//			"<if test='userID!=\"\"'>"+
//			"and userID=#{userID} "+
//			"</if>"+
//			"<if test='userName!=\"\"'>"+
//			"and userName=#{userName} "+
//			"</if>"+
//			"order by createTime desc "+
//			"limit #{startNum} , #{pageSize}"+
//			"</script>")
	
	/*SELECT userID,userName,userEmail,userCellPhone,userDepartment,
	userStat ,
	  CASE
	     WHEN userStat=1 THEN '正常'
	     WHEN userStat=2 THEN '禁用'
	     ELSE ''
	  END userStatDESC,
	  userGender,
	  CASE
	     WHEN userGender=1 THEN '男'
	     WHEN userGender=0 THEN '女'
	     ELSE ''
	  END userGenderDESC,
	  userRole,
	  CASE
	     WHEN userRole=-1 THEN ''
	--      ELSE (select roleName  from bs_patent_roledict_t  where bs_patent_roledict_t.roleID = bs_patent_user_t.userRole limit 0,1)
	--      ELSE (SELECT GROUP_CONCAT(roleName SEPARATOR '，') roleName  FROM bs_patent_roledict_t  WHERE bs_patent_roledict_t.roleID IN (0,1))
	  ELSE (SELECT GROUP_CONCAT(roleName SEPARATOR '，') roleName  FROM bs_patent_roledict_t  
		WHERE bs_patent_roledict_t.roleID IN (
			SUBSTRING_INDEX(SUBSTRING_INDEX(userRole,'~', 1), '~', -1),
			SUBSTRING_INDEX(SUBSTRING_INDEX(userRole,'~', 2), '~', -1)
			)
		)
	  END userRoleDESC
	FROM bs_patent_user_t
	WHERE 1=1*/
	@Select({"<script>",
			"SELECT userID,userName,userEmail,userCellPhone,userDepartment,",
			" userStat,",
			"	  CASE"+
			"	     WHEN userStat=1 THEN '正常'",
			"	     WHEN userStat=2 THEN '禁用'",
			"	     ELSE ''",
			"	  END userStatShow,",
			" userGender,",
			"	  CASE",
			"	     WHEN userGender=1 THEN '男'",
			"	     WHEN userGender=0 THEN '女'",
			"	     ELSE ''",
			"	  END userGenderShow,",
			" userRole,",
			"	  CASE",
			"	     WHEN userRole='' THEN ''",
			"	  ELSE (SELECT GROUP_CONCAT(roleName SEPARATOR '，') roleName  FROM bs_patent_roledict_t  ",
			"		WHERE bs_patent_roledict_t.roleID IN (",
			
			"<foreach item='item' index='index' collection='roleNumSet' open='' separator=',' close=''>",
			"	SUBSTRING_INDEX(SUBSTRING_INDEX(userRole,'~', #{item}), '~', -1)",
            "</foreach>",
			
			"			)",
			"		)",
			"	  END userRoleShow",
			" from bs_patent_user_t where 1=1 ",
			"<if test='userStat!=\"\"'>",
			"and userStat=#{userStat} ",
			"</if>",
			"<if test='userID!=\"\"'>",
			"and userID=#{userID} ",
			"</if>",
			"<if test='userName!=\"\"'>",
			"and userName=#{userName} ",
			"</if>",
			"order by createTime desc ",
			"limit #{startNum} , #{pageSize}",
			"</script>"})
    public List<Map<Object, Object>> listUser(Map<Object, Object> conditionMap);
	
	@Select("<script>"+
	    "SELECT count(*) from bs_patent_user_t where 1=1 "+
	    "<if test='userStat!=\"\"'>"+
		"and userStat=#{userStat} "+
		"</if>"+
		"<if test='userID!=\"\"'>"+
		"and userID=#{userID} "+
		"</if>"+
		"<if test='userName!=\"\"'>"+
		"and userName=#{userName} "+
		"</if>"+
	    "</script>")
	public int userNum (Map<Object, Object> conditionMap);
	
	@Select("select * from bs_patent_user_t where userID=#{userID}")
	public User singleUser(@Param("userID")String userID);
	
	@Select("select * from bs_patent_user_t where userID=#{userID} and pwd=#{pwd} ")
	public User userLogin(@Param("userID")String userID,@Param("pwd")String pwd);
	
	@Insert("INSERT INTO bs_patent_user_t ( userID,userName,pwd,userRole, userEmail,userCellPhone,userStat,userDepartment,userGender )  "+
          "values(#{userID},#{userName},#{pwd},#{userRole},#{userEmail},#{userCellPhone},#{userStat},#{userDepartment},#{userGender})")
	public int addUser(Map<Object, Object> conditionMap);
	
	@Delete("DELETE FROM bs_patent_user_t WHERE userID =#{userID}")
	public int delUser(String userID) ;
	
//	@Update("UPDATE  bs_patent_user_t set userName = #{userName},pwd = #{pwd},userRole = #{userRole},userEmail = #{userEmail},userCellPhone = #{userCellPhone},userStat = #{userStat} where userID = #{userID}")
	@Update("<script>"+
			"UPDATE  bs_patent_user_t set "+
			"<if test='userName!=null and userName!=\"\"'> userName = #{userName},</if>"+
			"<if test='pwd!=null and pwd!=\"\"'> pwd = #{pwd},</if>"+
			"<if test='userGender!=null and pwd!=\"\"'> userGender = #{userGender},</if>"+
			"<if test='userRole!=null'> userRole = #{userRole},</if>"+
			"<if test='userEmail!=null and userEmail!=\"\"'> userEmail = #{userEmail},</if>"+
			"<if test='userCellPhone!=null and userCellPhone!=\"\"'> userCellPhone = #{userCellPhone},</if>"+
			"<if test='userDepartment!=null and userDepartment!=\"\"'> userDepartment = #{userDepartment},</if>"+
			"<if test='userStat!=null and userStat!=\"\"'> userStat = #{userStat},</if>"+
			" userID = #{userID} "+
			" where userID = #{userID} "+
			"</script>")
	public int updateUser(Map<Object, Object> conditionMap) ;
    
	/*@Select("SELECT "+
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
	public List<Map<String,Object>> complexSelect(String parkID);*/

}
