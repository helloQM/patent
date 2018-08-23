package com.qimou.sb.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author : haoming
 * @date : 2018年8月23日上午9:18:42
 * @returnType : String
 * @desc : 导航调度控制器   【跳转到 templates 目录下的 ***.html】
 * 不能用@RestController
	   或@ResponseBody
 */
@Controller
public class CommNavServlet {
	
	
	@GetMapping(value="gotoLogoutPage")
    public String gotoLogoutPage(HttpServletRequest request) {
        //跳转到 templates 目录下的 logout.html
//		System.out.println("SessionID : "+request.getSession().getId());
		request.getSession().removeAttribute("userID");
		request.getSession().invalidate();//将session设置为失效，在退出时使用.
//        return "view/system/editDemo";
        return "logout";
//        return "uploadimg";
    }
	
	@GetMapping(value="gotoLoginPage")
	public String gotoLoginPage(HttpServletRequest request) {
		//跳转到 templates 目录下的 logout.html
//		System.out.println("SessionID : "+request.getSession().getId());
//        return "view/system/editDemo";
		return "login";
	}
	@GetMapping(value="/")
	public String gotoHomePage(HttpServletRequest request) {
		//跳转到 templates 目录下的 logout.html
//		System.out.println("SessionID : "+request.getSession().getId());
//        return "view/system/editDemo";
		HttpSession session = request.getSession();
		System.out.println("session userid : "+session.getId()+" : "+session.getAttribute("userID"));
//		return "home";
		return "navHome";
	}
	@GetMapping(value="/navHome")
	public String nav(HttpServletRequest request) {
		return "navHome";
	}
	@GetMapping(value="/tree")
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
