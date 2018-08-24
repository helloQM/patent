package com.qimou.sb.web.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qimou.sb.web.entity.Customer;
import com.qimou.sb.web.service.CustomerService;
import com.qimou.sb.web.tool.JsonUtil;

@Controller
public class CustomerServlet {
	private static final Logger logger = LoggerFactory.getLogger(CustomerServlet.class);
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年7月31日下午3:02:29
	 * @patent com.qimou.sb.web.controller.CustomerServlet.java.listContract
	 * @returnType : String
	 * @desc :获得列表
	 */
	@RequestMapping("listCustomer")
	@ResponseBody
	public String listCustomer(@RequestBody String jsonStr,HttpServletRequest request){
		Map<Object, Object> conditionMap = new HashMap<Object, Object>();
		List<Customer> list = null;
		Map<Object, Object> returnMap = new HashMap<Object, Object>();
		try {
			jsonStr = URLDecoder.decode(jsonStr, "UTF-8");//解决中文乱码
			System.out.println(jsonStr);
			if(jsonStr.contains("&")){
				conditionMap = JsonUtil.url2Map(jsonStr, "&");
			}else{
				conditionMap = JsonUtil.jsonStr2Map(jsonStr);
			}
			int pageSize = Integer.parseInt(conditionMap.get("pageSize").toString());
			conditionMap.put("pageSize", pageSize);
			conditionMap.put("startNum", pageSize*(Integer.parseInt(conditionMap.get("currentPage").toString())-1));
			
			list = customerService.listCustomer(conditionMap);
			returnMap.put("customerNum", customerService.customerNum(conditionMap));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		return JsonUtil.generalMixJson(list,returnMap,"listCustomer");
	}
    
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年7月31日下午3:00:12
	 * @patent com.qimou.sb.web.controller.CustomerServlet.java.addCustomer
	 * @returnType : String
	 * @desc :添加实体
	 */
    @RequestMapping("addCustomer")
    @ResponseBody
    public String addCustomer(@RequestBody String jsonStr,HttpServletRequest request){
    	Map<String, String> returnMap = new HashMap<String, String>();
    	Map<Object, Object> conditionMap = new HashMap<Object, Object>();
    	String returnStr = "";
    	try {
    		jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
    		if(jsonStr.contains("&")){
				conditionMap = JsonUtil.url2Map(jsonStr, "&");
			}else{
				conditionMap = JsonUtil.jsonStr2Map(jsonStr);
			}
    		conditionMap.put("userID", request.getSession().getAttribute("userID"));
    		conditionMap.put("userName", request.getSession().getAttribute("userName"));
    		if(customerService.addCustomer(conditionMap)>=1){
    			returnMap.put("code", "0");
				returnMap.put("msg", "新增成功");
    		}
    	} catch (Exception e) {
    		returnMap.put("code", "1");
			returnMap.put("msg", "新增失败");
    		logger.error(e.toString());
    	} finally{
    		returnStr = JsonUtil.map2JsonStr(returnMap);
    	}
    	
    	return returnStr;
    }
  
    /**
     * 
     * @author : haoming
     * @date : 2018年7月31日下午2:58:30
     * @patent com.qimou.sb.web.controller.CustomerServlet.java.updateCustomer
     * @returnType : String
     * @Desc :修改实体
     */
    @RequestMapping("updateCustomer")
    @ResponseBody
    public String updateCustomer(@RequestBody String jsonStr,HttpServletRequest request){
    	Map<String, String> returnMap = new HashMap<String, String>();
    	Map<Object, Object> conditionMap = new HashMap<Object, Object>();
    	String returnStr = "";
    	try {
    		jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
    		System.out.println(jsonStr);
    		if(jsonStr.contains("&")){
				conditionMap = JsonUtil.url2Map(jsonStr, "&");
			}else{
				conditionMap = JsonUtil.jsonStr2Map(jsonStr);
			}
    		if(customerService.updateCustomer(conditionMap)>=1){
    			returnMap.put("code", "0");
    			returnMap.put("msg", "修改成功");
    		}
    	} catch (Exception e) {
    		returnMap.put("code", "1");
			returnMap.put("msg", "修改失败");
    		logger.error(e.toString());
    	} finally{
    		returnStr = JsonUtil.map2JsonStr(returnMap);
    	}
    	
    	return returnStr;
    }
}
