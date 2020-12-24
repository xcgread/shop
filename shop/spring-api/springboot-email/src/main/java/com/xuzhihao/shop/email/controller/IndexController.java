package com.xuzhihao.shop.email.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import lombok.extern.slf4j.Slf4j;

/**
 * 发送邮件
 * 
 * @author Administrator
 *
 */
@RestController
@Slf4j
public class IndexController {
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	TemplateEngine templateEngine;

	@Value("${spring.mail.fromAddr}")
	private String from;

	@Value("${spring.mail.nickName}")
	private String nickName;

	private String to = "xcg992224@163.com";

	@RequestMapping(value = "/sendTextMail")
	public void sendTextMail() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(nickName + "<" + from + ">");
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject("测试邮件题目");
		simpleMailMessage.setText("测试邮件内容<a>看这里<a>");
		try {
			javaMailSender.send(simpleMailMessage);
			log.info("简易邮件发送成功");
		} catch (Exception e) {
			log.error("简易邮件发送异常", e);
		}
	}

	@RequestMapping(value = "/sendHtmlEmail")
	public void sendHtmlEmail() {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setFrom(new InternetAddress(from, nickName, "UTF-8"));
			messageHelper.setTo(to);
			messageHelper.setSubject("测试邮件题目");
			messageHelper.setText("测试邮件内容 这是一个html邮件<a>看这里<a>", true);
			javaMailSender.send(message);

			log.info("HTML 模版邮件发送成功");
		} catch (MessagingException e) {
			log.error("HTML 模版邮件发送失败", e);
		} catch (UnsupportedEncodingException e) {
			log.error("收件地址编码异常", e);
		}
	}

	@RequestMapping(value = "/sendHtmlemplateMail")
	public void sendHtmlemplateMail() {
		Context context = new Context();
		context.setVariable("code", "123456");
		String emailHTMLContent = templateEngine.process("email", context);
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setFrom(new InternetAddress(from, nickName, "UTF-8"));
			messageHelper.setTo(to);
			messageHelper.setSubject("测试 HTML 模版邮件");
			messageHelper.setText(emailHTMLContent, true);
			javaMailSender.send(message);
			log.info("HTML 模版邮件发送成功");
		} catch (MessagingException e) {
			log.error("HTML 模版邮件发送失败", e);
		} catch (UnsupportedEncodingException e) {
			log.error("收件地址编码异常", e);
		}
	}

	@RequestMapping(value = "/sendAttachmentsMail")
	public void sendAttachmentsMail() {
		String fileName = "图片.jpg";
		String filePath = "D:\\aa.jpg";
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setFrom(new InternetAddress(from, nickName, "UTF-8"));
			messageHelper.setTo(to);
			messageHelper.setSubject("测试带附件的邮件");
			messageHelper.setText("详细请查阅附件", true);
			FileSystemResource file = new FileSystemResource(new File(filePath));
			messageHelper.addAttachment(fileName, file);
			javaMailSender.send(message);
			log.info("带附件邮件发送成功");
		} catch (MessagingException e) {
			log.error("带附件邮件发送失败", e);
		} catch (UnsupportedEncodingException e) {
			log.error("收件地址编码异常", e);
		}
	}
}