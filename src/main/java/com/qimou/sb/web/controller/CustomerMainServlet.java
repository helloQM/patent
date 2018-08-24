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
import com.qimou.sb.web.service.CustomerService;
import com.qimou.sb.web.tool.JsonUtil;

@Controller
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
	@ResponseBody
	public String listSimpleCustomer(@RequestBody String jsonStr,HttpServletRequest request){
		Map<Object, Object> conditionMap = new HashMap<Object, Object>();
		List<Map<Object, Object>> list = null;
		try {
			jsonStr = URLDecoder.decode(jsonStr, "UTF-8");//解决中文乱码
			System.out.println(jsonStr);
			if(jsonStr.contains("&")){
				conditionMap = JsonUtil.url2Map(jsonStr, "&");
			}else{
				conditionMap = JsonUtil.jsonStr2Map(jsonStr);
			}
			
			list = customerService.listSimpleCustomer(conditionMap);
			
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		return JsonUtil.list2JsonStr(list);
	}
    
}
