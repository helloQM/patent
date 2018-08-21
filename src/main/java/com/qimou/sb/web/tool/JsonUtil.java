package com.qimou.sb.web.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.google.gson.Gson;
import com.qimou.sb.web.entity.Policy;

//@Component
public class JsonUtil {

	
	public static void main(String[] args) {
		
		
		/*String reqBody="{\"parkID\":\"shicheng1\",\"title\":\"title0.13708906058025083\"}";
		Policy policy = (Policy) js.jsonStr2Bean(reqBody, Policy.class);
		System.out.println(policy);
		
		reqBody="[{\"parkID\":\"shicheng1\",\"title\":\"title0.13708906058025083\"},{\"parkID\":\"shicheng2\",\"title\":\"title0.13708906058025083\"}]";
		Object o = js.jsonStr2Beans(reqBody,Policy.class);
		System.out.println("s:"+o.toString());
		
		reqBody="[{\"taskID\": \"101\",\"userID\": \"haom\"}]";
		o = js.jsonStr2Beans(reqBody,Map.class);
		System.out.println("s:"+o.toString());*/
		
		
		/*policy = new Policy();
    	policy.setParkID("shicheng1");
    	policy.setTitle("title"+Math.random());
    	policy.setPolicyStr("policyStr"+Math.random());
    	policy.setPolicyID(UUID.randomUUID().toString());
    	
    	List<Object> filterList = new ArrayList<Object>();
    	filterList.add("parkID");
    	System.out.println(js.bean2JsonStr(policy, filterList));
    	System.out.println(js.bean2JsonStr(policy, null));
    	
    	JSONObject jsonObject = js.bean2JsonObj(policy);
    	System.out.println(jsonObject.get("parkID"));
    	
    	Map<Object, Object> map = js.jsonStr2Map(reqBody);
		System.out.println(map.entrySet().toString());
		
		System.out.println(js.map2JsonStr(map));
		
		
		
		Set<Object> set = new HashSet<Object>();
		set.add(policy);
		policy = new Policy();
    	policy.setParkID("shicheng1");
    	policy.setTitle("title"+Math.random());
    	policy.setPolicyStr("policyStr"+Math.random());
    	policy.setPolicyID(UUID.randomUUID().toString());
    	set.add(policy);
		System.out.println(js.set2JsonStr(set));
		
		List<Policy> list = new ArrayList<Policy>();
		list.add(policy);
		list.add(policy);
		System.out.println(js.list2JsonStr(list));
		
		System.out.println("generalMixJson : "+js.generalMixJson(list,map,"listStr"));*/
		
		
		/*Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("serviceID", "1");
		map.put("cityCode","1");
		map.put("serviceCode","1");
		map.put("serviceName","服务名称");
		map.put("serviceBak","递交专利");
		map.put("serviceType", "12");
		map.put("priceNumMin",123);
		map.put("priceNumMax",456);
		System.out.println(js.map2JsonStr(map));*/
		
		/*String  s= "serviceBak=";
		String[] split = s.split("=");
		System.out.println(split.length);
		System.out.println(split[0]);
		System.out.println(split.length==1?"hhh":split[1]);*/
    	
		
		String str = "{check:{enable: true},data:{simpleData:{enable:true}},callback:{onCheck:\"onCheck\"}}";
		System.out.println(str);
		System.out.println(JSONObject.fromObject(str));
	}
	
	
	public static String generalMixJson(List<?> list,Map<Object,Object> map,String listName){
		
		String json = map2JsonStr(map);
		json = json.replace("}", ",");
		json += "\""+listName+"\":"+list2JsonStr(list);
		json += "}";
		return json;
	}

	/**
	 * 
	 * @Author:HaoMing(郝明)
	 * @Project_name:mavenWebProj
	 * @Full_path:com.qimou.sb.web.tool.JsonUtil.java
	 * @Date:@2018 2018-7-20 下午1:46:26
	 * @Return_type:Object
	 * @Desc :json字符串转javabean
	 */
	public static Object jsonStr2Bean(String jsonStr,Class<?> beanClass) {
		/*if (jsonStr.indexOf("[") != -1) {
			jsonStr = jsonStr.replace("[", "");
		}
		if (jsonStr.indexOf("]") != -1) {
			jsonStr = jsonStr.replace("]", "");
		}*/
		JSONObject obj = JSONObject.fromObject(jsonStr);
		return JSONObject.toBean(obj,beanClass);
	}
	
	/**
	 * 
	 * @Author:HaoMing(郝明)
	 * @Project_name:patent
	 * @Full_path:com.qimou.sb.web.tool.JsonUtil.java
	 * @Date:@2018 2018-7-27 下午12:15:00
	 * @Return_type:Object
	 * @Desc :
	 */
	public static List<Object> jsonStr2Beans(String jsonStr,Class<?> beanClass) {
		
		if (jsonStr.indexOf("[") == -1 || jsonStr.indexOf("]") == -1) {
			return null;
		}
		List<Object> returnList = new ArrayList<Object>();
		JSONArray jsonAarry = JSONArray.fromObject(jsonStr);
		
		for (int i = 0; i < jsonAarry.size(); i++) {
			JSONObject obj = JSONObject.fromObject(jsonAarry.get(i));
			returnList.add(JSONObject.toBean(obj,beanClass));
		}
		return returnList;
	}
	
	 public static JSONObject bean2JsonObj(Object vo){
         return JSONObject.fromObject(vo);
     }
	 
	/**
	 * 
	 * @Author:HaoMing(郝明)
	 * @Project_name:mavenWebProj
	 * @Full_path:com.qimou.sb.web.tool.JsonUtil.java
	 * @Date:@2018 2018-7-20 下午1:46:52
	 * @Return_type:String
	 * @Desc :带过滤字段的beanToJson方法:javabean转json字符串
	 */
	public static String bean2JsonStr(Object vo,final List<?> filterList) {
		String returnStr="";
		if(filterList != null){
    		JsonConfig jsonConfig=new JsonConfig();
        	PropertyFilter filter = new PropertyFilter() {
    			
    			public boolean apply(Object arg0, String arg1, Object arg2) {
    				// Object o  String fieldName  Stirng fieldValue
    				if(filterList.contains(arg1)){
    					return true;
    				}else{
    					return false;
    				}
    			}
    		};
    		jsonConfig.setJsonPropertyFilter(filter);
    		returnStr = JSONObject.fromObject(vo, jsonConfig).toString();
    	}else{
//    		returnStr = JSONObject.fromObject(vo).toString();
    		JSONObject json = JSONObject.fromObject(vo);//将java对象转换为json对象
    		returnStr = json.toString();//将json对象转换为字符串
    	}
    	
    	return returnStr;
	}
	
	/**
	 * 
	 * @Author:HaoMing(郝明)
	 * @Project_name:mavenWebProj
	 * @Full_path:com.qimou.sb.web.tool.JsonUtil.java
	 * @Date:@2018 2018-7-20 下午1:47:15
	 * @Return_type:String
	 * @Desc :map对象转json字符串
	 */
	public static String map2JsonStr(Map<?,?> map){
		if(map.isEmpty()){
			return "";
		}
		
		return JSONObject.fromObject(map).toString();
	}

	/**
	 * 
	 * @Author:HaoMing(郝明)
	 * @Project_name:mavenWebProj
	 * @Full_path:com.qimou.sb.web.tool.JsonUtil.java
	 * @Date:@2018 2018-7-20 下午1:47:37
	 * @Return_type:Map<Object,Object>
	 * @Desc :json字符串转map对象
	 */
	public static Map<Object,Object> jsonStr2Map(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Iterator<?> keyIter = jsonObject.keys();
		String key;
		Object value;
		Map<Object,Object> returnMap = new HashMap<Object, Object>();
		while (keyIter.hasNext()) {
			key = (String) keyIter.next();
			value = jsonObject.get(key);
			returnMap.put(key, value);
		}
		return returnMap;
	}
	
	/**
	 * 
	 * @author : haoming
	 * @date : 2018年8月14日上午8:58:36
	 * @patent com.qimou.sb.web.tool.JsonUtil.java.url2Map
	 * @returnType : Map<Object,Object>
	 * @desc :currentPage=1&serviceType=-1&serviceCode=   转 map
	 */
	public static Map<Object,Object> url2Map(String urlString,String regex) {
		Map<Object,Object> returnMap = new HashMap<Object, Object>();
		String[] split = urlString.split(regex);
		for (String item:split) {
			String[] it = item.split("=");
			returnMap.put(it[0], it.length==1?"-1":it[1]);
		}
		return returnMap;
	}
	
	
	
	/**
	 * 
	 * @Author:HaoMing(郝明)
	 * @Project_name:mavenWebProj
	 * @Full_path:com.qimou.sb.web.tool.JsonUtil.java
	 * @Date:@2018 2018-7-20 下午1:47:51
	 * @Return_type:String
	 * @Desc :list对象转json字符串
	 */
	public static String list2JsonStr(List<?> list){
		return new Gson().toJson(list);
	}
	
	/**
	 * 	
	 * @Author:HaoMing(郝明)
	 * @Project_name:mavenWebProj
	 * @Full_path:com.qimou.sb.web.tool.JsonUtil.java
	 * @Date:@2018 2018-7-20 下午1:48:14
	 * @Return_type:String
	 * @Desc :set对象转json字符串
	 */
	public static String set2JsonStr(Set<?> set){
		return new Gson().toJson(set);
	}

}
