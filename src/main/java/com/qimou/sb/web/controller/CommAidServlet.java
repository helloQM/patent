package com.qimou.sb.web.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qimou.sb.web.entity.User;
import com.qimou.sb.web.service.CommService;
import com.qimou.sb.web.service.UserService;
import com.qimou.sb.web.tool.JsonUtil;

//在controller层请求处理完了返回时，没有使用@RestController或@ResponseBody而返回了非json格式,将@Controller换成@RestController.
@RestController
//@Controller
//@ResponseBody
public class CommAidServlet {
	private static final Logger logger = LoggerFactory.getLogger(CommAidServlet.class);
	
	@Autowired
	private CommService commService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年8月14日下午1:48:11
	 * @patent com.qimou.sb.web.controller.CommServlet.java.listCityCode
	 * @returnType : String
	 * @desc :按级获得城市列表
	 */
	@RequestMapping("listCityCode")
	public String listCityCode(@RequestBody String jsonStr,HttpServletRequest request){
		List<Map<Object, Object>> listCityCode = null;
		try {
			jsonStr = URLDecoder.decode(jsonStr, "UTF-8");//解决中文乱码
			System.out.println(jsonStr);
			Map<Object, Object> conditionMap = new HashMap<Object, Object>();
			if(jsonStr.contains("&")){
				conditionMap = JsonUtil.url2Map(jsonStr, "&");
			}else{
				conditionMap = JsonUtil.jsonStr2Map(jsonStr);
			}
			listCityCode = commService.listCityCode(conditionMap);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		return JsonUtil.list2JsonStr(listCityCode);
	}
	
	@RequestMapping("listAllRoles")
	public String listAllRoles(HttpServletRequest request){
		return JsonUtil.list2JsonStr(commService.listAllRoles());
	}
	
	@RequestMapping(value="doLogin")
	public String doLogin(@RequestBody String jsonStr,HttpServletRequest request) {
		System.out.println("doLogin....");
		Map<Object, Object> conditionMap = new HashMap<Object, Object>();
		Map<String, String> returnMap = new HashMap<String, String>();
		String returnStr = "";
		try {
    		jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
    		System.out.println("jsonStr : "+jsonStr);
    		if(jsonStr.contains("&")){
				conditionMap = JsonUtil.url2Map(jsonStr, "&");
			}else{
				conditionMap = JsonUtil.jsonStr2Map(jsonStr);
			}
    		String userID = conditionMap.get("userID").toString();
    		String pwd = conditionMap.get("pwd").toString();
    		User user = userService.userLogin(userID, pwd);
    		if(user.getUserStat() == 1){// 登录成功,且是正常用户
    			HttpSession session = request.getSession();
    	        session.setMaxInactiveInterval(30 * 60);
    			System.out.println("SessionID : "+request.getSession().getId());
    			session.setAttribute("userID", user.getUserID());
    			session.setAttribute("userName", user.getUserName());
    			session.setAttribute("roleIDs", user.getUserRole());
    			returnMap.put("code", "0");
    			returnMap.put("msg", "登录成功");
    		} else if(user.getUserStat() == 2){
    			returnMap.put("code", "1");
    			returnMap.put("msg", "用户已经被注销!");
    		} else{
    			returnMap.put("code", "2");
    			returnMap.put("msg", "用户名密码不正确");
    		}
    	} catch (Exception e) {
    		returnMap.put("code", "3");
			returnMap.put("msg", "登录失败");
    		logger.error(e.toString());
    	} finally{
    		returnStr = JsonUtil.map2JsonStr(returnMap);
    	}
		
		return returnStr;
	}
	
}
