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

import com.qimou.sb.web.entity.ServicePrice;
import com.qimou.sb.web.service.ServicePriceService;
import com.qimou.sb.web.tool.JsonUtil;

@Controller
public class ServicePriceServlet {
	private static final Logger logger = LoggerFactory.getLogger(ServicePriceServlet.class);
	
	@Autowired
	private ServicePriceService servicePriceService;
	
//	@Autowired
//	private JsonUtil jsonUtil;
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年7月31日下午3:23:43
	 * @patent com.qimou.sb.web.controller.ServicePriceServlet.java.listServicePrice
	 * @returnType : String
	 * @desc :获得列表 
	 * @jsonStr:{"$$$":"***","###":"***",...}  或  currentPage=1&serviceType=-1&serviceCode=
	 */
	@RequestMapping("listServicePrice")
	@ResponseBody
	public String listServicePrice(@RequestBody String jsonStr,HttpServletRequest request){
		jsonStr = URLDecoder.decode(jsonStr);//解决中文乱码
		System.out.println(jsonStr);
//		if(jsonStr.contains("&")){
//			jsonStr = jsonStr.replace("&", ",");
//			jsonStr = "{"+jsonStr+"}";
//		}
//		System.out.println(jsonStr);
//		return "ook";
//		return "{\"serviceType\":\"12\",\"cityName\":\"西安市\",\"serviceCode\":\"1\",\"cityCode\":\"610100\",\"serviceBak\":\"递交专利\",\"serviceID\":\"1\",\"serviceName\":\"服务名称\",\"priceNumMax\":4567,\"priceNumMin\":123}";
//		return "{\"servicePriceTotalNum\": 5,	\"listServicePrice\": [{\"serviceType\":\"12\",\"cityName\":\"西安市\",\"serviceCode\":\"1\",\"cityCode\":\"610100\",\"serviceBak\":\"递交专利\",\"serviceID\":\"1\",\"serviceName\":\"服务名称\",\"priceNumMax\":4567,\"priceNumMin\":123},{\"serviceType\":\"12\",\"cityName\":\"西安市\",\"serviceCode\":\"1\",\"cityCode\":\"610100\",\"serviceBak\":\"递交专利\",\"serviceID\":\"2\",\"serviceName\":\"服务名称\",\"priceNumMax\":456,\"priceNumMin\":123},{\"serviceType\":\"12\",\"cityName\":\"西安市\",\"serviceCode\":\"1\",\"cityCode\":\"610100\",\"serviceBak\":\"递交专利\",\"serviceID\":\"3\",\"serviceName\":\"服务名称\",\"priceNumMax\":456,\"priceNumMin\":123},{\"serviceType\":\"12\",\"cityName\":\"西安市\",\"serviceCode\":\"1\",\"cityCode\":\"610100\",\"serviceBak\":\"递交专利\",\"serviceID\":\"4\",\"serviceName\":\"服务名称\",\"priceNumMax\":456,\"priceNumMin\":123},{\"serviceType\":\"12\",\"cityName\":\"西安市\",\"serviceCode\":\"1\",\"cityCode\":\"610100\",\"serviceBak\":\"递交专利\",\"serviceID\":\"5\",\"serviceName\":\"服务名称\",\"priceNumMax\":456,\"priceNumMin\":123}]}";
		Map<Object, Object> conditionMap = new HashMap<Object, Object>();
		List<Map<Object, Object>> listServicePrice = null;
		Map<Object, Object> returnMap = new HashMap<Object, Object>();
		try {
			if(jsonStr.contains("&")){
				conditionMap = JsonUtil.url2Map(jsonStr, "&");
			}else{
				conditionMap = JsonUtil.jsonStr2Map(jsonStr);
			}
			conditionMap.put("pageSize", 10);
			conditionMap.put("startNum", 10*(Integer.parseInt(conditionMap.get("currentPage").toString())-1));
			jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
			listServicePrice = servicePriceService.listServicePrice(conditionMap);
			returnMap.put("servicePriceTotalNum", servicePriceService.servicePricerNum(conditionMap));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		return JsonUtil.generalMixJson(listServicePrice,returnMap,"listServicePrice");
	}
    
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年8月9日下午4:51:46
	 * @patent com.qimou.sb.web.controller.ServicePriceServlet.java.addServicePrice
	 * @returnType : String
	 * @desc :新增实体
	 * jsonStr : {"$$$":"***","###":"***",...}  或  currentPage=1&serviceType=-1&serviceCode=
	 */
	@RequestMapping("addServicePrice")
    @ResponseBody
    public String addServicePrice(@RequestBody String jsonStr,HttpServletRequest request){
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
//    		ServicePrice servicePrice = (ServicePrice)jsonUtil.jsonStr2Bean(jsonStr, ServicePrice.class);
    		if(servicePriceService.addServicePrice(conditionMap)>=1){
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
	 * @date : 2018年7月31日下午3:25:10
	 * @patent com.qimou.sb.web.controller.ServicePriceServlet.java.updateServicePrice
	 * @returnType : String
	 * @Desc :修改实体
     * jsonStr : {"$$$":"***","###":"***",...}  或  currentPage=1&serviceType=-1&serviceCode=
	 */
    @RequestMapping("updateServicePrice")
    @ResponseBody
    public String updateServicePrice(@RequestBody String jsonStr,HttpServletRequest request){
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
//    		ServicePrice servicePrice = (ServicePrice)jsonUtil.jsonStr2Bean(jsonStr, ServicePrice.class);
    		if(servicePriceService.updateServicePrice(conditionMap)>=1){
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
