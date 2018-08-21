package com.qimou.sb.web.controller;

import java.net.URLDecoder;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qimou.sb.web.entity.Authority;
import com.qimou.sb.web.service.AuthorityService;
import com.qimou.sb.web.tool.JsonUtil;

@Controller
public class AuthorityServlet {
	private static final Logger logger = LoggerFactory.getLogger(AuthorityServlet.class);
	
	@Autowired
	private AuthorityService authorityService;
	
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年8月16日下午4:41:40
	 * @patent com.qimou.sb.web.controller.AuthorityServlet.java.listAllAuthority
	 * @returnType : Object
	 * @desc :获得全部的权限列表
	 */
	@RequestMapping("listAllAuthority")
	@ResponseBody
	public String listAllAuthority(HttpServletRequest request){
		List<Authority> listAllAuthority = null;
		try {
			listAllAuthority = authorityService.listAllAuthority();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		return JsonUtil.list2JsonStr(listAllAuthority);
	}
	
	/**
	 * 一级，二级，三级导航的模板
	 * <li><a >建立网站</a>
	     <ul id="subMgm" class="second-menu">
	    	<li><a class="arrow">域名空名空间空间</a>
		        <ul class="third-menu">
		          <li><a href="#">fatcow</a></li>
		          <li><a href="#">justhost</a></li>
		        </ul>
	        </li>
	       <li><a href="#">CSS 模版</a></li>
	      </ul>
	  </li>
	 */
	@RequestMapping("navDel")
	@ResponseBody
	public String navDel(HttpServletRequest request){
		
		List<Map<Object, Object>> list = authorityService.listTemp();
		Map<Object, Object> returnMap = new HashMap<Object, Object>();
		returnMap.put("userName", request.getSession().getAttribute("userName").toString());
//		returnMap.put("navList", stringBuilder.toString());
//		return JsonUtil.map2JsonStr(returnMap);
		return JsonUtil.generalMixJson(list, returnMap, "navList");
	}
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年8月16日下午4:42:03
	 * @patent com.qimou.sb.web.controller.AuthorityServlet.java.listAuthorityByRole
	 * @returnType : String
	 * @desc :根据user角色获得相应的权限列表
	 */
	@RequestMapping("listAuthorityByRole")
	@ResponseBody
	public String listAuthorityByRole(HttpServletRequest request){
		List<Authority> listAllAuthority = null;
		try {
			String jsonStr = request.getSession().getAttribute("roleIDs").toString();
			Set<Object> conditionSet = new HashSet<Object>();
			jsonStr = URLDecoder.decode(jsonStr, "UTF-8");//解决中文乱码
			if(jsonStr.contains(",")){
				String[] split = jsonStr.split(",");
				for (int i = 0; i < split.length; i++) {
					conditionSet.add(split[i]);
				}
			}else{
				conditionSet.add(jsonStr);
			}
			System.out.println("conditionSet : "+conditionSet.toString());
//			listAllAuthority = authorityService.listAuthorityByRole(conditionSet);
			listAllAuthority = authorityService.listAllAuthority();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		return JsonUtil.list2JsonStr(listAllAuthority);
	}
    
}
