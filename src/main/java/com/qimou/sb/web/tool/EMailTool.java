package com.qimou.sb.web.tool;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EMailTool {

//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final Logger logger = LoggerFactory.getLogger(EMailTool.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * 
     * @Author:HaoMing(郝明)
     * @Project_name:mavenWebProj
     * @Full_path:com.qimou.sb.web.tool.EMailTool.java
     * @Date:@2018 2018-7-19 上午11:05:28
     * @Return_type:String
     * @Desc :发送简单邮件
     */
    public String sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
            return "email ok";
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！"+ e);
        } 
        return "email error !";
    }
    
    /**
     * 
     * @Author:HaoMing(郝明)
     * @Project_name:mavenWebProj
     * @Full_path:com.qimou.sb.web.tool.EMailTool.java
     * @Date:@2018 2018-7-19 上午11:57:55
     * @Return_type:void
     * @Desc :发送html格式邮件
     */
    public String sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            logger.info("html邮件发送成功");
            return "email ok";
	      } catch (MessagingException e) {
	         logger.error("发送html邮件时发生异常！"+e);
	      }
        return "email error !";
    }
    
    /**
     * 
     * @Author:HaoMing(郝明)
     * @Project_name:mavenWebProj
     * @Full_path:com.qimou.sb.web.tool.EMailTool.java
     * @Date:@2018 2018-7-19 下午12:04:20
     * @Return_type:String
     * @Desc :发送带附件的邮件
     * 添加多个附件可以使用多条 helper.addAttachment(fileName, file)
     */
    public String sendAttachmentsMail(String to, String subject, String content, String filePath){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            mailSender.send(message);
            logger.info("带附件的邮件已经发送。");
            return "email ok";
        } catch (MessagingException e) {
            logger.error("发送带附件的邮件时发生异常！"+e);
        }
        return "email error !";
    }
    
    /**
     * 
     * @Author:HaoMing(郝明)
     * @Project_name:mavenWebProj
     * @Full_path:com.qimou.sb.web.tool.EMailTool.java
     * @Date:@2018 2018-7-19 下午12:05:30
     * @Return_type:String
     * @Desc :发送带静态资源的邮件:一般就是指图片
     * 添加多个图片可以使用多条 <img src='cid:" + rscId + "' > 和 helper.addInline(rscId, res) 来实现
     */
    public String sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            mailSender.send(message);
            logger.info("嵌入静态资源的邮件已经发送。");
            return "email ok";
        } catch (MessagingException e) {
            logger.error("发送嵌入静态资源的邮件时发生异常！"+e);
        }
        return "email error !";
    }
}