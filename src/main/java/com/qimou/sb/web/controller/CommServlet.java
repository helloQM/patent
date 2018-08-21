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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qimou.sb.web.entity.User;
import com.qimou.sb.web.service.CommService;
import com.qimou.sb.web.service.UserService;
import com.qimou.sb.web.tool.JsonUtil;

@Controller
public class CommServlet {
	private static final Logger logger = LoggerFactory.getLogger(CommServlet.class);
	
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
//	@ResponseBody
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
    
	
	@RequestMapping(value="gotoLogoutPage")
    public String gotoLogoutPage(HttpServletRequest request) {
        //跳转到 templates 目录下的 logout.html
//		System.out.println("SessionID : "+request.getSession().getId());
		request.getSession().removeAttribute("userID");
		request.getSession().invalidate();//将session设置为失效，在退出时使用.
//        return "view/system/editDemo";
        return "logout";
//        return "uploadimg";
    }
	
	@RequestMapping(value="gotoLoginPage")
	public String gotoLoginPage(HttpServletRequest request) {
		//跳转到 templates 目录下的 logout.html
//		System.out.println("SessionID : "+request.getSession().getId());
//        return "view/system/editDemo";
		return "login";
	}
	@RequestMapping(value="/")
	public String gotoHomePage(HttpServletRequest request) {
		//跳转到 templates 目录下的 logout.html
//		System.out.println("SessionID : "+request.getSession().getId());
//        return "view/system/editDemo";
		HttpSession session = request.getSession();
		System.out.println("session userid : "+session.getId()+" : "+session.getAttribute("userID"));
//		return "home";
		return "navHome";
	}
	@RequestMapping(value="/navHome")
	public String nav(HttpServletRequest request) {
		return "navHome";
	}
	@RequestMapping(value="/tree")
	public String tree(HttpServletRequest request) {
		return "tree";
	}
	
	/**
     * 
     * @Author:HaoMing(郝明)
     * @Project_name:patent
     * @Full_path:com.qimou.sb.web.controller.UserServlet.java
     * @Date:@2018 2018-7-25 下午5:50:20
     * @Return_type:String
     * @Desc :用户登录 
     * jsonStr : {"userID":"***","pwd":"***"}
     * 注意 content type 要设置成 application/json，否则会在请求体后多一个=
     */
	@RequestMapping(value="doLogin")
	@ResponseBody
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
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年8月15日上午9:56:33
	 * @patent com.qimou.sb.web.controller.CommServlet.java.checkLogin
	 * @returnType : String
	 * @desc :校验此次请求是否登录过，若登录 过则返回其权限列表。
	 *//*
	@RequestMapping(value="checkLogin")
	@ResponseBody
	public String checkLogin(HttpServletRequest request) {
	
		HttpSession session = request.getSession();
		Object userID = session.getAttribute("userID");
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("code", "1");
		returnMap.put("msg", "无此用户，重新登录");
		if(userID==null){
			return JsonUtil.map2JsonStr(returnMap);
		}
		
		Map<Object, Object> conditionMap = new HashMap<Object, Object>();
		conditionMap.put("userID" , userID);
		String returnStr = "";
		try {
//			userService.authorityList(userID);
			returnMap.put("code", "0");
			returnMap.put("pageIDs", "");
    	} catch (Exception e) {
    		
    		logger.error(e.toString());
    	} finally{
    		returnStr = JsonUtil.map2JsonStr(returnMap);
    	}
		
		return returnStr;
	}*/
	
}
