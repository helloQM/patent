package com.qimou.sb.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ServicePriceDao {
	
//	@Select("select * from bs_patent_servicePriceDict_t ")
	@Select("<script>"+
		    "SELECT *,(SELECT cityName FROM bs_patent_citycodedict_t WHERE  citycode = bs_patent_servicePriceDict_t.cityCode) cityName from bs_patent_servicePriceDict_t where 1=1 "+
		    "<if test='serviceCode!=\"\"'>"+
		    " and serviceCode=#{serviceCode} "+
		    "</if>"+
//		    "<if test=\"serviceName!='-1'.toString()\">"+
		    "<if test='serviceName!=\"\"'>"+
		    " and serviceName=#{serviceName} "+
		    "</if>"+
		    "<if test='serviceType!=\"-1\"'>"+
		    " and serviceType=#{serviceType} "+
		    "</if>"+
		    "<if test='priceNumMin!=\"\"'>"+
		    " and priceNumMin >= #{priceNumMin} "+
		    "</if>"+
		    "<if test='priceNumMax!=\"\"'>"+
		    "<![CDATA[ and priceNumMax <= #{priceNumMax}]]>"+
		    "</if>"+
		    "order by serviceID "+
		    "limit #{startNum} , #{pageSize}"+
		    "</script>")
	public List<Map<Object, Object>> listServicePrice(Map<Object, Object> conditionMap);
	
	@Select("<script>"+
		    "SELECT count(*) from bs_patent_servicePriceDict_t where 1=1 "+
		    "<if test='serviceCode!=\"\"'>"+
		    " and serviceCode=#{serviceCode} "+
		    "</if>"+
//		    "<if test=\"serviceName!='-1'.toString()\">"+
			"<if test='serviceName!=\"\"'>"+
		    " and serviceName=#{serviceName} "+
		    "</if>"+
		    "<if test='serviceType!=\"-1\"'>"+
		    " and serviceType=#{serviceType} "+
		    "</if>"+
		    "<if test='priceNumMin!=\"\"'>"+
		    " and priceNumMin>=#{priceNumMin} "+
		    "</if>"+
		    "<if test='priceNumMax!=\"\"'>"+
		    "<![CDATA[ and priceNumMax <= #{priceNumMax}]]>"+
		    "</if>"+
		    "</script>")
	public int servicePricerNum (Map<Object, Object> conditionMap);
	
	@Update("UPDATE  bs_patent_servicePriceDict_t set priceNumMin = #{priceNumMin},priceNumMax = #{priceNumMax},serviceName = #{serviceName},serviceBak = #{serviceBak} where serviceID = #{serviceID}")
	public int updateServicePrice(Map<Object, Object> conditionMap);

	@Insert("INSERT INTO bs_patent_servicepricedict_t (  serviceID,  cityCode,  serviceCode,  serviceName,  serviceBak,  serviceType,  priceNumMin,  priceNumMax)   "+
	          "VALUES  ( #{serviceID}, #{cityCode},#{serviceCode}, #{serviceName}, #{serviceBak}, #{serviceType}, #{priceNumMin}, #{priceNumMax} )")
	public int addServicePrice(Map<Object, Object> conditionMap);
    
}
