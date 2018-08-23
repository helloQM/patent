package com.qimou.sb.web.controller;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qimou.sb.web.entity.User;
import com.qimou.sb.web.service.UserService;
import com.qimou.sb.web.tool.JsonUtil;

@Controller
public class UserServlet {
	private static final Logger logger = LoggerFactory.getLogger(UserServlet.class);
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private JsonUtil jsonUtil;
	
	@RequestMapping("singleUser")
//	@RequestMapping(value = "singleUser",method = RequestMethod.POST)
    @ResponseBody
    public String singleUser(@RequestBody String userID,HttpServletRequest request){
		logger.info(userID);
    	User user = userService.singleUser(userID);
    	return JsonUtil.bean2JsonStr(user, null);
    }
	
	@RequestMapping("listUser")
	@ResponseBody
	public String listUser(@RequestBody String jsonStr,HttpServletRequest request){
		Map<Object, Object> conditionMap = new HashMap<Object, Object>();
		List<Map<Object, Object>> list = null;
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
			
			list = userService.listUser(conditionMap);
			returnMap.put("roleTotalNum", userService.userNum(conditionMap));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		return JsonUtil.generalMixJson(list,returnMap,"listUser");
	}
    
    /**
     * 
     * @Author:HaoMing(郝明)
     * @Project_name:patent
     * @Full_path:com.qimou.sb.web.controller.UserServlet.java
     * @Date:@2018 2018-7-26 上午10:25:52
     * @Return_type:String
     * @Desc :添加user实体
     * jsonStr : {"userID":"***","pwd":"***",...}
     */
    @RequestMapping("addUser")
    @ResponseBody
    public String addUser(@RequestBody String jsonStr,HttpServletRequest request){
    	logger.info("jsonStr: "+jsonStr);
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
    		if(userService.addUser(conditionMap)>=1){
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
     * @Author:HaoMing(郝明)
     * @Project_name:patent
     * @Full_path:com.qimou.sb.web.controller.UserServlet.java
     * @Date:@2018 2018-7-26 上午10:25:52
     * @Return_type:String
     * @Desc :修改user实体
     * jsonStr : {"userID":"***","pwd":"***",...}
     */
    @RequestMapping("updateUser")
    @ResponseBody
    public String updateUser(@RequestBody String jsonStr,HttpServletRequest request){
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
    		if(userService.updateUser(conditionMap)>=1){
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
    
   /**
    * 
    * @Author:HaoMing(郝明)
    * @Project_name:patent
    * @Full_path:com.qimou.sb.web.controller.UserServlet.java
    * @Date:@2018 2018-7-26 上午10:30:48
    * @Return_type:String
    * @Desc :删除user实体【由于外键关联，此方法暂时不用】
    * 
    */
    @RequestMapping("delUser")
    @ResponseBody
    public String delUser(String userID,HttpServletRequest request){
    	Map<String, String> returnMap = new HashMap<String, String>();
    	String returnStr="";
    	try {
    		if(userService.delUser(userID) == 1){
    			returnMap.put("code", "0");
    			returnMap.put("msg", "删除成功");
    		}
    	} catch (Exception e) {
    		returnMap.put("code", "1");
			returnMap.put("msg", "删除失败");
    		logger.error(e.toString());
    	} finally{
			returnStr = JsonUtil.map2JsonStr(returnMap);
    	}
    	return returnStr;
    }
    
    
}
