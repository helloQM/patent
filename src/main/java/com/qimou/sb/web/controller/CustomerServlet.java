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
	
//	@Autowired
//	private JsonUtil jsonUtil;
	
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年7月31日下午3:04:18
	 * @patent com.qimou.sb.web.controller.CustomerServlet.java.singleCustomer
	 * @returnType : String
	 * @Desc :获得一个实体
	 * jsonStr : {"customerID":"***"}
	 */
	@RequestMapping("singleCustomer")
    @ResponseBody
    public String singleCustomer(@RequestBody String jsonStr,HttpServletRequest request) throws Exception{
		jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
		Customer singleCustomer = customerService.singleCustomer(JsonUtil.jsonStr2Map(jsonStr).get("customerID").toString());
    	return JsonUtil.bean2JsonStr(singleCustomer, null);
    }
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年7月31日下午3:02:29
	 * @patent com.qimou.sb.web.controller.CustomerServlet.java.listContract
	 * @returnType : String
	 * @desc :获得列表
	 * jsonStr : {"pageNum":"***"}
	 */
	@RequestMapping("listCustomer")
	@ResponseBody
	public String listCustomer(@RequestBody String jsonStr,HttpServletRequest request){
		List<Customer> list = null;
		Map<Object, Object> returnMap = new HashMap<Object, Object>();
		try {
			jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
			Map<Object, Object> jsonStr2Map = JsonUtil.jsonStr2Map(jsonStr);
			String pageNum = jsonStr2Map.get("pageNum").toString();
			list = customerService.listCustomer(Integer.parseInt(pageNum), 10);
			returnMap.put("userTotalNum", customerService.customerNum());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return JsonUtil.generalMixJson(list,returnMap,"customerList");
	}
    
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年7月31日下午3:00:12
	 * @patent com.qimou.sb.web.controller.CustomerServlet.java.addCustomer
	 * @returnType : String
	 * @desc :添加实体
	 * jsonStr : {"$$$":"***","###":"***",...}
	 */
    @RequestMapping("addCustomer")
    @ResponseBody
    public String addCustomer(@RequestBody String jsonStr,HttpServletRequest request){
//    	logger.info("jsonStr: "+jsonStr);
    	Map<String, String> returnMap = new HashMap<String, String>();
    	String returnStr = "";
    	try {
    		jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
    		Customer customer = (Customer)JsonUtil.jsonStr2Bean(jsonStr, Customer.class);
    		if(customerService.addCustomer(customer)>=1){
    			returnMap.put("code", "0");
    			returnMap.put("msg", "添加成功");
    		}
		} catch (Exception e) {
			returnMap.put("code", "1");
			returnMap.put("msg", "添加失败");
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
     * jsonStr : {"$$$":"***","###":"***",...}
     */
    @RequestMapping("updateCustomer")
    @ResponseBody
    public String updateCustomer(@RequestBody String jsonStr,HttpServletRequest request){
    	Map<String, String> returnMap = new HashMap<String, String>();
    	String returnStr = "";
    	try {
    		jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
    		Customer customer = (Customer)JsonUtil.jsonStr2Bean(jsonStr, Customer.class);
    		if(customerService.updateCustomer(customer)>=1){
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
