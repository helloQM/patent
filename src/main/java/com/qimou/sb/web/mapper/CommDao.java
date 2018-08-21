package com.qimou.sb.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface CommDao {
	
	@Select("SELECT * FROM bs_patent_cityCodeDict_t WHERE pid = #{pid} ")
	public List<Map<Object, Object>> listCityCode(Map<Object, Object> conditionMap);
	
    
}
