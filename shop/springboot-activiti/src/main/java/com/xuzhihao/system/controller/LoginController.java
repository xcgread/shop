package com.xuzhihao.system.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;

import com.xuzhihao.common.api.CaptchaUtil;
import com.xuzhihao.common.api.ResultBean;
import com.xuzhihao.common.exception.CaptchaIncorrectException;
import com.xuzhihao.common.shiro.ShiroActionProperties;
import com.xuzhihao.system.model.User;
import com.xuzhihao.system.service.UserService;

@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private ShiroActionProperties shiroActionProperties;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginVerify", shiroActionProperties.getLoginVerify());
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultBean login(User user, @RequestParam(value = "captcha", required = false) String captcha) {
        Subject subject = SecurityUtils.getSubject();

        // 如果开启了登录校验
        if (shiroActionProperties.getLoginVerify()) {
            String realCaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute("captcha");
            // session 中的验证码过期了
            if (realCaptcha == null || !realCaptcha.equals(captcha.toLowerCase())) {
                throw new CaptchaIncorrectException();
            }
        }

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        subject.login(token);
        userService.updateLastLoginTimeByUsername(user.getUsername());
        return ResultBean.success("登录成功");
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:login";
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletResponse response) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CaptchaUtil.Captcha captcha = CaptchaUtil.createCaptcha(140, 38, 4, 10, 30);
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("captcha", captcha.getCode());

        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(captcha.getImage(), "png", os);
    }

}
