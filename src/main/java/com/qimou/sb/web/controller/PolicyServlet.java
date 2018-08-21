package com.qimou.sb.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qimou.sb.web.entity.Policy;
import com.qimou.sb.web.service.PolicyService;
import com.qimou.sb.web.tool.EMailTool;
import com.qimou.sb.web.tool.JsonUtil;
import com.qimou.sb.web.tool.PingyinUtil1;

//@Log4j2
@Controller
public class PolicyServlet {
	private static final Logger logger = LoggerFactory.getLogger(PolicyServlet.class);
	@Autowired
	private PolicyService policyService;
	
	@Autowired
	private EMailTool eMailTool;
	
//	@Autowired
//	private JsonUtil jsonUtil;
	
    @RequestMapping("policy_list")
    @ResponseBody
    public String find(){
    	String resultStr = "";
    	List<Policy> find = policyService.find();
    	System.out.println("find : "+find.size());
    	logger.info("13231");
    	Iterator<Policy> it = find.iterator();
    	while(it.hasNext()){
    		Policy next = it.next();
    		resultStr+=next.getPolicyStr()+"~";
    	}
    	PingyinUtil1 o = new PingyinUtil1();
    	String stringPinYin = o.getStringPinYin("中安山东123e阿凡达");
    	
    	String simpleCcontent="尊敬的用户您好，	您申请的【**专利】已经收到”授权通知书” http:www.baidu.com 验证码为： 5945 ，请尽查看。";
    	String htmlContent="<html>\n" +
    			"<body>\n" +
    			"    <h3>尊敬的用户您好，	您申请的【**专利】已经收到”授权通知书” ,验证码为： 5945 ，请尽查看。</h3>\n" +
    			"    <a href='http:www.baidu.com'>点击查看</a>\n" +System.currentTimeMillis()+
//    			"<a href=\"http://www.baidu.com\" rel=\"noopener\" target=\"_blank\">123</a>"+  //qq邮箱接收邮件需要这样写
    			"</body>\n" +
    			"</html>";
    	
//    	logger.info(eMailTool.sendSimpleMail("zyhaoming@126.com", "【奇牟科技专利检索及分析系统】进度提示", simpleCcontent));
//    	logger.info(eMailTool.sendHtmlMail("zyhaoming@126.com", "【奇牟科技专利检索及分析系统】进度提示", htmlContent));
//    	logger.info(eMailTool.sendHtmlMail("357677486@qq.com", "【奇牟科技专利检索及分析系统】进度提示", htmlContent));
//    	logger.info(eMailTool.sendAttachmentsMail("zyhaoming@126.com", "【奇牟科技专利检索及分析系统】进度提示", htmlContent,"E:\\workbanch\\MyEclipse 10\\mavenWebProj\\src\\main\\resources\\banner.txt"));
//    	logger.info(eMailTool.sendInlineResourceMail("zyhaoming@126.com", "【奇牟科技专利检索及分析系统】进度提示", htmlContent,"E:\\workbanch\\MyEclipse 10\\mavenWebProj\\src\\main\\resources\\yzm.png","验证码"));
        return stringPinYin+resultStr+"\nhttps://blog.csdn.net/guolong1983811/article/details/78349682"+System.currentTimeMillis();
    }
    
    @RequestMapping("policy_justReturn1")
    @ResponseBody
    public String justReturnGetP(String p){
    	return p+"   qm";
    }
    
    @RequestMapping("policy_justReturn2")
    @ResponseBody
    public String justReturnGetP2(HttpServletRequest request){
    	String p = request.getParameter("p");
    	return p+"   qm";
    }
    
    /**
     * 
     * @Author:HaoMing(郝明)
     * @Project_name:mavenWebProj
     * @Full_path:com.qimou.sb.web.controller.PolicyServlet.java
     * @Date:@2018 2018-7-20 下午2:09:17
     * @Return_type:String
     * @Desc :注意 content-type 要设置成 application/json，否则会在请求体后多一个=
     */
    @RequestMapping("policy_justReturn3")
    @ResponseBody
    public String justReturnPost(@RequestBody String jsonStr){
    	String decode = jsonStr;
    	try {
//        	String account =json;//.get("account");//req.getParameter("account");
//        	logger.info(json.values().toString());
        	decode = URLDecoder.decode(jsonStr, "UTF-8");
        	logger.info(decode);
        	
        	System.out.println(JsonUtil.list2JsonStr((List<Object>)JsonUtil.jsonStr2Map(decode).get("getList")));
		} catch (Exception e) {
			// TODO: handle exception
		}

    	return decode+"   qm中汉";
    }
    
    @RequestMapping("policy_add")
    @ResponseBody
    public String add(){
    	Policy policy = new Policy();
    	policy.setParkID("shicheng1");
    	policy.setTitle("title"+Math.random());
    	policy.setPolicyStr("policyStr"+Math.random());
    	policy.setPolicyID(UUID.randomUUID().toString());
    	return policyService.add(policy)+"";
    }
    
    @RequestMapping("policy_del")
    @ResponseBody
    public String del(){
    	String policyID="a5b9e58c-ba63-424e-b9e9-b3388b69ae24";
    	return policyService.del(policyID)+"";
    }
    
    @RequestMapping("policy_update")
    @ResponseBody
    public String update(){
    	String id = "e90114b0-0c7e-4218-b945-40036c7b2a7c";
    	Policy policy = new Policy();
    	policy.setParkID("shicheng1");
    	policy.setTitle("title");
    	policy.setPolicyStr("policyStr");
    	policy.setPolicyID(id);
    	return policyService.update(policy)+"";
    }
    
    @RequestMapping("complexSelect")
    @ResponseBody
    public String complexSelect(@RequestBody String jsonStr) throws Exception{
    	String resultStr = "";
    	
    	String decode = URLDecoder.decode(jsonStr, "UTF-8");
    	Object object = JsonUtil.jsonStr2Map(decode).get("parkID");
//    	logger.info("parkID:"+object.toString());
    	List<Map<String, Object>> result = policyService.complexSelect(object.toString());
    	
    	Iterator<Map<String, Object>> it = result.iterator();
    	while(it.hasNext()){
    		Map<String, Object> next = (Map<String, Object>)it.next();
    		resultStr+=next.get("wfID")+"~";
    		resultStr+=next.get("num")+"~";
    		resultStr+=next.get("departmentID")+"~";
    		resultStr+=next.get("parkID")+"#";
    	}
    	
        return resultStr+System.currentTimeMillis();
    }
    
    
}
