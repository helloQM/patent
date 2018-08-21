package com.qimou.sb.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qimou.sb.web.entity.User;

public interface UserDao {
	
	
	@Select("<script>"+
			"select * from bs_patent_user_t "+
			"<if test='userStat!=-1'>"+
			"where userStat=#{userStat} "+
			"</if>"+
			"order by userID "+
			"limit #{startNum} , #{pageSize}"+
			"</script>")
    public List<User> listUser(@Param("userStat")int userStat,@Param("startNum")int startNum,@Param("pageSize")int pageSize);
	
//	@Select("select count(*) from bs_patent_user_t where userStat=#{userStat}")
	@Select("<script>"+
	    "SELECT count(*) from bs_patent_user_t "+
	    "<if test='userStat!=-1'>"+
	    " where userStat=#{userStat} "+
	    "</if>"+
	    "</script>")
	public int userNum (@Param("userStat")int userStat);
	
	@Select("select * from bs_patent_user_t where userID=#{userID}")
//	@Select("<script>"+
//		    "select * from bs_patent_user_t where userID=#{userID}"+
//		    "</script>"
//			)
	public User singleUser(String userID);
	
	@Select("select * from bs_patent_user_t where userID=#{userID} and pwd=#{pwd} ")
	public User userLogin(@Param("userID")String userID,@Param("pwd")String pwd);
	
	@Insert("INSERT INTO bs_patent_user_t ( userID,userName,pwd,userRole, userEmail,userCellPhone,userStat )  "+
          "values(#{userID},#{userName},#{pwd},#{userRole},#{userEmail},#{userCellPhone},#{userStat})")
	public int addUser(User user);
	
	@Delete("DELETE FROM bs_patent_user_t WHERE userID =#{userID}")
	public int delUser(String userID) ;
	
	@Update("UPDATE  bs_patent_user_t set userName = #{userName},pwd = #{pwd},userRole = #{userRole},userEmail = #{userEmail},userCellPhone = #{userCellPhone},userStat = #{userStat} where userID = #{userID}")
	public int updateUser(User user) ;
    
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
