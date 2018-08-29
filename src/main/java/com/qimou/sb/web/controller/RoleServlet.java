package com.qimou.sb.web.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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

import com.qimou.sb.web.service.RoleService;
import com.qimou.sb.web.tool.JsonUtil;

@Controller
public class RoleServlet {
	private static final Logger logger = LoggerFactory.getLogger(RoleServlet.class);
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年8月22日上午9:40:41
	 * @patent com.qimou.sb.web.controller.RoleServlet.java.listRole
	 * @returnType : String
	 * @desc :获取权限列表
	 */
	@RequestMapping("listRole")
	@ResponseBody
	public String listRole(@RequestBody String jsonStr,HttpServletRequest request){
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
			
			list = roleService.listRole(conditionMap);
			returnMap.put("roleTotalNum", roleService.roleNum());
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		return JsonUtil.generalMixJson(list,returnMap,"listRole");
	}
	
	@RequestMapping("listRoleAuthority")
	@ResponseBody
	public String listRoleAuthority(@RequestBody String jsonStr,HttpServletRequest request){
		Map<Object, Object> conditionMap = new HashMap<Object, Object>();
		List<Object> list = null;
		List<String> c = new ArrayList<String>();
		String returnStr = "";
		try {
			jsonStr = URLDecoder.decode(jsonStr, "UTF-8");//解决中文乱码
			System.out.println(jsonStr);
			if(jsonStr.contains("&")){
				conditionMap = JsonUtil.url2Map(jsonStr, "&");
			}else{
				conditionMap = JsonUtil.jsonStr2Map(jsonStr);
			}
			list = roleService.listRoleAuthority(conditionMap.get("roleID").toString());
			for(Object str : list) {
				if(str.toString().length()==1){
					c.add(str.toString());
					continue;
				}
				if(str.toString().length()==3){
					c.add(str.toString().subSequence(0, str.toString().length()-1).toString());
				}
			}
//	        list.removeAll(c);
	        Iterator<Object> it = list.iterator();
	        while(it.hasNext()){
	        	String s = it.next().toString();
	        	if(c.contains(s)){
	        		it.remove();
	        	}
	        }
			for(Object str : list) {
				returnStr += str+"~";
			}
			if(returnStr.length()>0){
				returnStr = returnStr.substring(0, returnStr.length()-1);
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		return returnStr;
	}
    
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年8月22日上午9:40:12
	 * @patent com.qimou.sb.web.controller.RoleServlet.java.delRole
	 * @returnType : String
	 * @desc :删除角色【咱时没有此业务】
	 */
	@RequestMapping("delRole")
    @ResponseBody
    public String delRole(@RequestBody String jsonStr,HttpServletRequest request){
//		System.out.println(jsonStr);
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
    		if(roleService.delRole(conditionMap.get("roleID").toString())>=1){
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
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年8月22日上午9:40:00
	 * @patent com.qimou.sb.web.controller.RoleServlet.java.addRole
	 * @returnType : String
	 * @desc :新增角色
	 */
	@RequestMapping("addRole")
    @ResponseBody
    public String addRole(@RequestBody String jsonStr,HttpServletRequest request){
//		System.out.println(jsonStr);
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
    		if(roleService.addRole(conditionMap)>=1){
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
	 * @date : 2018年8月22日上午9:39:43
	 * @patent com.qimou.sb.web.controller.RoleServlet.java.updateRole
	 * @returnType : String
	 * @desc :修改角色属性
	 */
    @RequestMapping("updateRole")
    @ResponseBody
    public String updateRole(@RequestBody String jsonStr,HttpServletRequest request){
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
    		if(roleService.updateRole(conditionMap)>=1){
    			if(conditionMap.get("checkedNodes")!=null&&conditionMap.get("checkedNodes").toString().length()!=0){
    				Set<Object> conditionSet = new HashSet<Object>(Arrays.asList(conditionMap.get("checkedNodes").toString().split("~")));
    				if(roleService.modifyRoleAuthority(conditionMap.get("roleID").toString(),conditionSet)){
    					returnMap.put("code", "0");
    	    			returnMap.put("msg", "修改成功");
    				}else{
    					
    				}
    			}else{
    				returnMap.put("code", "0");
        			returnMap.put("msg", "修改成功");
    			}
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
     * @author : haoming
     * @date : 2018年8月22日上午9:39:24
     * @patent com.qimou.sb.web.controller.RoleServlet.java.modifyRoleAuthority
     * @returnType : String
     * @desc :变更角色的权限
     */
    @RequestMapping("modifyRoleAuthority")
    @ResponseBody
    public String modifyRoleAuthority(@RequestBody String jsonStr,HttpServletRequest request){
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
    		Set<Object> conditionSet = new HashSet<Object>();
    		String[] split = conditionMap.get("ids").toString().split("~");
    		for (int i = 0; i < split.length; i++) {
    			conditionSet.add(split[i]);
			}
    		if(roleService.modifyRoleAuthority(conditionMap.get("roleID").toString(),conditionSet)){
    			returnMap.put("code", "0");
    			returnMap.put("msg", "变更成功");
    		}else{
    			returnMap.put("code", "1");
    			returnMap.put("msg", "变更失败");
    		}
    	} catch (Exception e) {
    		returnMap.put("code", "1");
			returnMap.put("msg", "变更失败");
    		logger.error(e.toString());
    	} finally{
    		returnStr = JsonUtil.map2JsonStr(returnMap);
    	}
    	
    	return returnStr;
    }
    
    
}
