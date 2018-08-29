package com.qimou.sb.web.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qimou.sb.web.entity.CustApplicationMan;
import com.qimou.sb.web.entity.CustInventMan;
import com.qimou.sb.web.entity.CustLinkMan;
import com.qimou.sb.web.service.CustomerService;
import com.qimou.sb.web.tool.JsonUtil;

@RestController
public class CustomerMainServlet {
	private static final Logger logger = LoggerFactory.getLogger(CustomerMainServlet.class);
	
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
	@RequestMapping("listSimpleCustomer")
	public String listSimpleCustomer(@RequestBody String jsonStr,HttpServletRequest request){
		Map<Object, Object> conditionMap = new HashMap<Object, Object>();
		List<Map<Object, Object>> list = null;
		try {
//			jsonStr = URLDecoder.decode(jsonStr, "UTF-8");//解决中文乱码
//			System.out.println(jsonStr);
//			if(jsonStr.contains("&")){
//				conditionMap = JsonUtil.url2Map(jsonStr, "&");
//			}else{
//				conditionMap = JsonUtil.jsonStr2Map(jsonStr);
//			}
//			System.out.println("userID : "+request.getSession().getAttribute("userID"));
			conditionMap.put("userID", request.getSession().getAttribute("userID"));
			list = customerService.listSimpleCustomer(conditionMap);
//			System.out.println("userID : "+list);
			
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		return JsonUtil.list2JsonStr(list);
	}
	
///////////////
	
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年8月27日下午3:35:15
	 * @patent com.qimou.sb.web.controller.CustomerMainServlet.java.listCustApplicationman
	 * @returnType : String
	 * @desc :获得列表
	 */
	@RequestMapping("listCustApplicationman")
	public String listCustApplicationman(@RequestBody String jsonStr,HttpServletRequest request){
		Map<Object, Object> conditionMap = new HashMap<Object, Object>();
		List<CustApplicationMan> list = null;
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
			list = customerService.listCustApplicationman(conditionMap);
			
			returnMap.put("custApplicationmanNum", customerService.custApplicationmanNum(conditionMap));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		return JsonUtil.generalMixJson(list,returnMap,"listCustApplicationman");
	}
	
	
	 /**
	  * 
	  * @author : haoming
	  * @date : 2018年8月27日下午3:35:28
	  * @patent com.qimou.sb.web.controller.CustomerMainServlet.java.addCustApplicationman
	  * @returnType : String
	  * @desc :新加实体
	  */
	 @RequestMapping("addCustApplicationman")
	    public String addCustApplicationman(@RequestBody String jsonStr,HttpServletRequest request){
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
	    		if(customerService.addCustApplicationman(conditionMap)>=1){
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
	    * @date : 2018年8月27日下午3:35:36
	    * @patent com.qimou.sb.web.controller.CustomerMainServlet.java.updateCustApplicationman
	    * @returnType : String
	    * @desc :修改实体
	    */
	    @RequestMapping("updateCustApplicationman")
	    public String updateCustApplicationman(@RequestBody String jsonStr,HttpServletRequest request){
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
	    		if(customerService.updateCustApplicationman(conditionMap)>=1){
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
	
	///////////////
			
			
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年8月27日下午3:38:17
	 * @patent com.qimou.sb.web.controller.CustomerMainServlet.java.listCustInventman
	 * @returnType : String
	 * @desc :获得列表
	 */
	@RequestMapping("listCustInventman")
	public String listCustInventman(@RequestBody String jsonStr,HttpServletRequest request){
		Map<Object, Object> conditionMap = new HashMap<Object, Object>();
		List<CustInventMan> list = null;
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
			
			list = customerService.listCustInventman(conditionMap);
			returnMap.put("custInventmanNum", customerService.custInventmanNum(conditionMap));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		return JsonUtil.generalMixJson(list,returnMap,"listCustInventman");
	}
	
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年8月27日下午3:38:09
	 * @patent com.qimou.sb.web.controller.CustomerMainServlet.java.addCustInventman
	 * @returnType : String
	 * @desc :新加实体
	 */
	@RequestMapping("addCustInventman")
	  public String addCustInventman(@RequestBody String jsonStr,HttpServletRequest request){
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
	  		if(customerService.addCustInventman(conditionMap)>=1){
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
	  * @date : 2018年8月27日下午3:37:59
	  * @patent com.qimou.sb.web.controller.CustomerMainServlet.java.updateCustInventman
	  * @returnType : String
	  * @desc :修改实体
	  */
	  @RequestMapping("updateCustInventman")
	  public String updateCustInventman(@RequestBody String jsonStr,HttpServletRequest request){
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
	  		if(customerService.updateCustInventman(conditionMap)>=1){
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
	
	  
	///////////////
			
			
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年8月27日下午3:40:54
	 * @patent com.qimou.sb.web.controller.CustomerMainServlet.java.listCustLinkman
	 * @returnType : String
	 * @desc :获得列表
	 */
	@RequestMapping("listCustLinkman")
	public String listCustLinkman(@RequestBody String jsonStr, HttpServletRequest request) {
		Map<Object, Object> conditionMap = new HashMap<Object, Object>();
		List<CustLinkMan> list = null;
		Map<Object, Object> returnMap = new HashMap<Object, Object>();
		try {
			jsonStr = URLDecoder.decode(jsonStr, "UTF-8");// 解决中文乱码
			System.out.println(jsonStr);
			if (jsonStr.contains("&")) {
				conditionMap = JsonUtil.url2Map(jsonStr, "&");
			} else {
				conditionMap = JsonUtil.jsonStr2Map(jsonStr);
			}
			int pageSize = Integer.parseInt(conditionMap.get("pageSize").toString());
			conditionMap.put("pageSize", pageSize);
			conditionMap.put("startNum", pageSize * (Integer.parseInt(conditionMap.get("currentPage").toString()) - 1));
			list = customerService.listCustLinkman(conditionMap);
//			System.out.println("listaaa : "+list);
			returnMap.put("custLinkmanNum", customerService.custLinkmanNum(conditionMap));
		} catch (Exception e) {
			logger.error(e.toString());
		}

		return JsonUtil.generalMixJson(list, returnMap, "listCustLinkman");
	}
	
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年8月27日下午3:40:46
	 * @patent com.qimou.sb.web.controller.CustomerMainServlet.java.addCustLinkman
	 * @returnType : String
	 * @desc :新加实体
	 */
	@RequestMapping("addCustLinkman")
	public String addCustLinkman(@RequestBody String jsonStr, HttpServletRequest request) {
		Map<String, String> returnMap = new HashMap<String, String>();
		Map<Object, Object> conditionMap = new HashMap<Object, Object>();
		String returnStr = "";
		try {
			jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
			if (jsonStr.contains("&")) {
				conditionMap = JsonUtil.url2Map(jsonStr, "&");
			} else {
				conditionMap = JsonUtil.jsonStr2Map(jsonStr);
			}
			if (customerService.addCustLinkman(conditionMap) >= 1) {
				returnMap.put("code", "0");
				returnMap.put("msg", "新增成功");
			}
		} catch (Exception e) {
			returnMap.put("code", "1");
			returnMap.put("msg", "新增失败");
			logger.error(e.toString());
		} finally {
			returnStr = JsonUtil.map2JsonStr(returnMap);
		}

		return returnStr;
	}
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年8月27日下午3:40:38
	 * @patent com.qimou.sb.web.controller.CustomerMainServlet.java.updateCustLinkman
	 * @returnType : String
	 * @desc :修改实体
	 */
	@RequestMapping("updateCustLinkman")
	public String updateCustLinkman(@RequestBody String jsonStr, HttpServletRequest request) {
		Map<String, String> returnMap = new HashMap<String, String>();
		Map<Object, Object> conditionMap = new HashMap<Object, Object>();
		String returnStr = "";
		try {
			jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
			System.out.println(jsonStr);
			if (jsonStr.contains("&")) {
				conditionMap = JsonUtil.url2Map(jsonStr, "&");
			} else {
				conditionMap = JsonUtil.jsonStr2Map(jsonStr);
			}
			if (customerService.updateCustLinkman(conditionMap) >= 1) {
				returnMap.put("code", "0");
				returnMap.put("msg", "修改成功");
			}
		} catch (Exception e) {
			returnMap.put("code", "1");
			returnMap.put("msg", "修改失败");
			logger.error(e.toString());
		} finally {
			returnStr = JsonUtil.map2JsonStr(returnMap);
		}

		return returnStr;
	}
	
}
