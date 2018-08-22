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

import com.qimou.sb.web.service.CommService;
import com.qimou.sb.web.tool.JsonUtil;

//在controller层请求处理完了返回时，没有使用@RestController或@ResponseBody而返回了非json格式,将@Controller换成@RestController.
@RestController
public class CommAidServlet {
	private static final Logger logger = LoggerFactory.getLogger(CommAidServlet.class);
	
	@Autowired
	private CommService commService;
	
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
	
}
