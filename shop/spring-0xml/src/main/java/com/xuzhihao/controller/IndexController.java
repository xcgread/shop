package com.xuzhihao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xuzhihao.domain.UserEntity;

@Controller
public class IndexController {
	@RequestMapping("/test")
	@ResponseBody
	public UserEntity getUser() {
		return new UserEntity("xcg", "18");
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("/index");
		modelAndView.addObject("name", "admin");
		return modelAndView;
	}
}