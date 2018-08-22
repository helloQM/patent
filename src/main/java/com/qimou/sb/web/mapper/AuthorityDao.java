package com.qimou.sb.web.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Select;

import com.qimou.sb.web.entity.Authority;

public interface AuthorityDao {
	
	@Select("select id,pid,CONCAT(NAME,' -- 【 ',description,' 】') NAME,path,description,OPEN,NAME AVALUE from bs_patent_authority_t ")
	public List<Authority> listAllAuthority();
	
//	@Select("select id,pid,name,path from bs_patent_authority_t WHERE id IN (3,31,311)")
	@Select("select id,pid,name,path from bs_patent_authority_t ")
	public List<Map<Object, Object>> listTemp();
	
	
	@Select("SELECT * FROM bs_patent_authority_t WHERE id IN (SELECT id FROM bs_patent_roleauthority_t WHERE roleID IN ('','')) ")
//	@Select("<script>"+
//		    "SELECT *,(SELECT cityName FROM bs_patent_citycodedict_t WHERE  citycode = bs_patent_servicePriceDict_t.cityCode) cityName from bs_patent_servicePriceDict_t where 1=1 "+
//		    "<if test=\"serviceCode!='-1'.toString()\">"+
//		    " and serviceCode=#{serviceCode} "+
//		    "</if>"+
////		    "<if test=\"serviceName!='-1'.toString()\">"+
//		    "<if test='serviceName!=\"-1\"'>"+
//		    " and serviceName=#{serviceName} "+
//		    "</if>"+
//		    "<if test=\"serviceType!='-1'.toString()\">"+
//		    " and serviceType=#{serviceType} "+
//		    "</if>"+
//		    "<if test='priceNumMin!=-1'>"+
//		    " and priceNumMin >= #{priceNumMin} "+
//		    "</if>"+
//		    "<if test='priceNumMax!=-1'>"+
//		    "<![CDATA[ and priceNumMax <= #{priceNumMax}]]>"+
//		    "</if>"+
//		    "order by serviceID "+
//		    "limit #{startNum} , #{pageSize}"+
//		    "</script>")
	public List<Authority> listAuthorityByRole(Set<Object> conditionSet);
	
    
}
