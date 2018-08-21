package com.qimou.sb.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qimou.sb.web.tool.FileUtil;

@Controller
public class Init {
	
    @RequestMapping("init_list")
    @ResponseBody
    public String list(){
        return "https://blog.csdn.net/guolong1983811/article/details/78349682"+System.currentTimeMillis();
    }
    
  //跳转到上传文件的页面
    @RequestMapping(value="/gouploadimg", method = RequestMethod.GET)
    public String goUploadImg() {
        //跳转到 templates 目录下的 uploadimg.html
        return "uploadimg";
    }

    //处理文件上传
    @RequestMapping(value="/testuploadimg", method = RequestMethod.POST)
//    @RequestMapping(value="/testuploadimg")
    @ResponseBody  //返回值当值一般字符串，否则会在templates目录下找相应的文件
    public  String uploadImg(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
        String parameter = request.getParameter("foo") == null?"nil":request.getParameter("foo");
        System.out.println(parameter);
        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
        System.out.println(filePath);
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
        }
        //返回json
        return "uploadimg success";
    }
    
    /**
     * 
     * @Author:HaoMing(郝明)
     * @Project_name:mavenWebProj
     * @Full_path:com.qimou.sb.web.controller.Init.java
     * @Date:@2018 2018-7-20 下午4:36:01
     * @Return_type:String
     * @Desc :session 测试
     */
    public  String sessionFunc(HttpServletRequest request) {
       HttpSession session = request.getSession();
       session.setAttribute("key", "value");
       session.setMaxInactiveInterval(30 * 60); 
        return "uploadimg success";
    }
}
