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

import com.qimou.sb.web.entity.Contract;
import com.qimou.sb.web.service.ContractService;
import com.qimou.sb.web.tool.JsonUtil;

@Controller
public class ContractServlet {
	private static final Logger logger = LoggerFactory.getLogger(ContractServlet.class);
	
	@Autowired
	private ContractService contractService;
	
//	@Autowired
//	private JsonUtil jsonUtil;
	
	
	/**
	 * 
	 * @Author:HaoMing(郝明)
	 * @Project_name:patent
	 * @Full_path:com.qimou.sb.web.controller.ContractServlet.java
	 * @Date:@2018 2018-7-27 下午2:03:39
	 * @Return_type:String
	 * @Desc :获得一个任务（合同）实体
	 * jsonStr : {"taskID":"***"}
	 */
	@RequestMapping("singleTask")
    @ResponseBody
    public String singleTask(@RequestBody String jsonStr,HttpServletRequest request) throws Exception{
		jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
    	Contract singleTask = contractService.singleTask(JsonUtil.jsonStr2Map(jsonStr).get("taskID").toString());
    	return JsonUtil.bean2JsonStr(singleTask, null);
    }
	
	/**
	 * 
	 * @throws Exception 
	 * @Author:HaoMing(郝明)
	 * @Project_name:patent
	 * @Full_path:com.qimou.sb.web.controller.ContractServlet.java
	 * @Date:@2018 2018-7-27 下午2:03:12
	 * @Return_type:String
	 * @Desc :获得同一合同下的所有任务
	 * jsonStr : {"contractID":"***"}
	 */
	@RequestMapping("listTask")
	@ResponseBody
	public String listTask(@RequestBody String jsonStr,HttpServletRequest request) throws Exception{
		jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
		List<Contract> listTask = contractService.listTask(JsonUtil.jsonStr2Map(jsonStr).get("contractID").toString());
		return JsonUtil.list2JsonStr(listTask);
	}
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年7月31日下午1:55:01
	 * @patent com.qimou.sb.web.controller.ContractServlet.java.listContract
	 * @returnType : String
	 * @desc :获得合同列表
	 * jsonStr : {"stat":"***","pageNum":"***"}
	 */
	@RequestMapping("listContract")
	@ResponseBody
	public String listContract(@RequestBody String jsonStr,HttpServletRequest request){
		List<Contract> listCont = null;
		Map<Object, Object> returnMap = new HashMap<Object, Object>();
		try {
			jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
			Map<Object, Object> jsonStr2Map = JsonUtil.jsonStr2Map(jsonStr);
			String stat = jsonStr2Map.get("stat").toString();
			String pageNum = jsonStr2Map.get("pageNum").toString();
			listCont = contractService.listContract(stat==null?-1:Integer.parseInt(stat), Integer.parseInt(pageNum), 10);
			returnMap.put("userTotalNum", contractService.contractNum(stat==null?-1:Integer.parseInt(stat)));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return JsonUtil.generalMixJson(listCont,returnMap,"userList");
	}
    
    /**
     * 
     * @Author:HaoMing(郝明)
     * @Project_name:patent
     * @Full_path:com.qimou.sb.web.controller.UserServlet.java
     * @Date:@2018 2018-7-26 上午10:25:52
     * @Return_type:String
     * @Desc :添加实体
     * jsonStr : [{"$$$":"***","###":"***",...},{"$$$":"***","###":"***",...},...]
     */
    @RequestMapping("addContract")
    @ResponseBody
    public String addContract(@RequestBody String jsonStr,HttpServletRequest request){
//    	logger.info("jsonStr: "+jsonStr);
    	Map<String, String> returnMap = new HashMap<String, String>();
    	String returnStr = "";
    	try {
    		jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
    		List<Object> contractList = JsonUtil.jsonStr2Beans(jsonStr, Contract.class);
    		if(contractService.addContract(contractList)>=1){
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
  
    
    @RequestMapping("distributeTask")
    @ResponseBody
    public String distributeTask(@RequestBody String jsonStr,HttpServletRequest request){
//    	logger.info("jsonStr: "+jsonStr);
    	Map<String, String> returnMap = new HashMap<String, String>();
    	String returnStr = "";
    	try {
    		jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
    		List<Object> contractUserList = JsonUtil.jsonStr2Beans(jsonStr, Map.class);
    		if(contractService.distributeTask(contractUserList)>=1){
    			returnMap.put("code", "0");
    			returnMap.put("msg", "分配成功");
    		}
		} catch (Exception e) {
			returnMap.put("code", "1");
			returnMap.put("msg", "分配失败");
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
     * @Full_path:com.qimou.sb.web.controller.ContractServlet.java
     * @Date:@2018 2018-7-27 下午12:02:02
     * @Return_type:String
     * @Desc :修改实体
     * jsonStr : {"$$$":"***","###":"***",...}
     */
    @RequestMapping("updateTask")
    @ResponseBody
    public String updateTask(@RequestBody String jsonStr,HttpServletRequest request){
    	Map<String, String> returnMap = new HashMap<String, String>();
    	String returnStr = "";
    	try {
    		jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
    		Contract contract = (Contract)JsonUtil.jsonStr2Bean(jsonStr, Contract.class);
    		if(contractService.updateTask(contract)>=1){
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
    * @Full_path:com.qimou.sb.web.controller.ContractServlet.java
    * @Date:@2018 2018-7-27 上午11:58:29
    * @Return_type:String
    * @Desc :删除实体  【注意 content type 要设置成 application/json，否则会在请求体后多一个=】
    * jsonStr : {"culName":"***","id":"***"}
    */
    @RequestMapping("delContract")
    @ResponseBody
    public String delContract(@RequestBody String jsonStr,HttpServletRequest request){
    	Map<String, String> returnMap = new HashMap<String, String>();
    	String returnStr="";
    	try {
    		jsonStr = URLDecoder.decode(jsonStr, "UTF-8");
    		Map<Object, Object> map = JsonUtil.jsonStr2Map(jsonStr);
    		String culName = map.get("culName").toString();
    		String id = map.get("id").toString();
    		
    		if(contractService.delContract(culName,id) >= 1){
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
