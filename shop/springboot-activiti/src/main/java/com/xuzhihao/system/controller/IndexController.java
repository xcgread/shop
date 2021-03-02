package com.xuzhihao.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.xuzhihao.system.model.Menu;
import com.xuzhihao.system.service.MenuService;
import com.xuzhihao.system.service.RoleService;
import com.xuzhihao.system.service.UserService;

@Controller
public class IndexController {

	@Resource
	private MenuService menuService;

	@Resource
	private UserService userService;

	@Resource
	private RoleService roleService;

	@GetMapping(value = { "/", "/main" })
	public String index(Model model) {
		List<Menu> menuTreeVOS = menuService.selectCurrentUserMenuTree();
		model.addAttribute("menus", menuTreeVOS);
		return "index";
	}

	@GetMapping("/welcome")
	public String welcome(Model model) {
		int userCount = userService.count();
		int roleCount = roleService.count();
		int menuCount = menuService.count();

		model.addAttribute("userCount", userCount);
		model.addAttribute("roleCount", roleCount);
		model.addAttribute("menuCount", menuCount);
		return "welcome";
	}

}
