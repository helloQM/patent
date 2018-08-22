package com.qimou.sb.web.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qimou.sb.web.entity.Role;

public interface RoleDao {
	
	
	@Select("<script>"+
		    "SELECT * from bs_patent_roledict_t "+
		    "order by roleID "+
		    "limit #{startNum} , #{pageSize}"+
		    "</script>")
	public List<Map<Object, Object>> listRole(Map<Object, Object> conditionMap);
	
	@Select("<script>"+
		    "SELECT count(*) from bs_patent_roledict_t"+
		    "</script>")
	public int roleNum ();
	
	@Select("select * from bs_patent_roledict_t where roleID=#{roleID}")
	public Role singleRole(@Param("roleID")String roleID);
	
	@Delete("DELETE FROM bs_patent_roledict_t WHERE roleID =#{roleID}")
	public int delRole(@Param("roleID")String roleID) ;
	
	@Update("UPDATE  bs_patent_roledict_t set roleName = #{roleName},bak = #{bak} where roleID = #{roleID}")
	public int updateRole(Map<Object, Object> conditionMap);

	@Insert("INSERT INTO bs_patent_roledict_t (roleName,bak)  VALUES (  #{roleName},#{bak} ) ")
	public int addRole(Map<Object, Object> conditionMap);
    
	
//	@Select({"<script>",
//        "SELECT * FROM user ", 
//        "WHERE id IN ", 
//          "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>",
//            "#{item}",
//          "</foreach>",
//        "</script>"})
	@Insert({"<script>",
        "INSERT INTO bs_patent_roleauthority_t ( roleID,id)  VALUES ", 
          "<foreach item='item' index='index' collection='conditionSet' open='' separator=',' close=''>",
            "(#{roleID},#{item})",
          "</foreach>",
        "</script>"})
//	public int addRoleAuthority(@Param("roleID")String roleID,Set<Object> conditionSet);
	public int addRoleAuthority(@Param("roleID")String roleID,@Param("conditionSet") Set<Object> conditionSet);
	
	@Delete("DELETE FROM bs_patent_roleauthority_t WHERE roleID =#{roleID}")
	public int delRoleAuthority(@Param("roleID")String roleID) ;
	
	@Select("SELECT id FROM bs_patent_roleauthority_t WHERE roleID =#{roleID}")
	public List<Object> listRoleAuthority(@Param("roleID")String roleID) ;
}
