package com.qimou.sb.web;

import java.nio.charset.Charset;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
@Configuration
//@EnableScheduling //开启定时器开关
@MapperScan({"com.qimou.sb.web.mapper"})  
public class EntranceApplication extends SpringBootServletInitializer{
    
	@RequestMapping("init")
    @ResponseBody
    public String hello(){
        return "hello 进来哦！";
    }
    
    @RequestMapping("list")
    @ResponseBody
    public String list(){
        return "{\"name\": \"BeJson\",\"url\": \"http://www.bejson.com\", \"page\": 88,\"isNonProfit\": true,\"address\": {\"street\": \"科技园路.\",\"city\": \"江苏苏州\",\"country\": \"中国\"}}";
    }
    
    //启动类，运行入口
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(EntranceApplication.class);
	}
    
    
    //自定义消息转化器
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
//        StringHttpMessageConverter converter  = new StringHttpMessageConverter(Charset.forName("iso-8859-1"));
        StringHttpMessageConverter converter  = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }
    
	public static void main(String[] args) {
//        SpringApplication.run(EntranceApplication.class, args);
    	SpringApplication app = new SpringApplication(EntranceApplication.class);
//    	app.setBannerMode(Mode.OFF);
    	app.run(args);
    	
    }

}