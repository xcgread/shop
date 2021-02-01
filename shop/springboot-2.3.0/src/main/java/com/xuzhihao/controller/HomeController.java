package com.xuzhihao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xuzhihao.domain.MenuItem;
import com.xuzhihao.domain.SessionInfo;

@Controller
public class HomeController {

	/**
	 * 系统登录
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login")
	@ResponseBody()
	public HashMap<String, Object> login(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		SessionInfo si = new SessionInfo();
		si.setId(1L);
		si.setLoginname("admin");
		si.setSkinid("2");
		request.getSession().setAttribute("sessionInfo", si);
		session.setAttribute("userMenus", userMenus());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rtn", "1");
		return map;
	}

	/**
	 * 跳转系统主页
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/dashboard")
	public ModelAndView main(HttpSession session, ModelAndView modelAndView) {
		modelAndView.setViewName("view/home/dashboard");
		return modelAndView;
	}

	/**
	 * 测试
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/table")
	public ModelAndView table(HttpSession session, ModelAndView modelAndView) {
		modelAndView.setViewName("view/home/table");
		return modelAndView;
	}

	@RequestMapping("/layout")
	public ModelAndView layout(HttpSession session, ModelAndView modelAndView) {
		modelAndView.setViewName("view/home/layout");
		return modelAndView;
	}

	@RequestMapping("/cog")
	public ModelAndView cog(HttpSession session, ModelAndView modelAndView) {
		modelAndView.setViewName("view/home/cog");
		return modelAndView;
	}

	/**
	 * 系统退出
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return "1";
	}

	public List<MenuItem> userMenus() {
		List<MenuItem> userMenus = new ArrayList<MenuItem>();
		MenuItem m = new MenuItem();
		m.setId("57");
		m.setPid("0");
		m.setUrl("/dashboard");
		m.setName("我的桌面");
		m.setIcon("mws-i-24 i-home");
		userMenus.add(m);

		MenuItem m5 = new MenuItem();
		m5.setId("571");
		m5.setPid("0");
		m5.setUrl("/table");
		m5.setName("表格");
		m5.setIcon("mws-i-24 i-table-1");
		userMenus.add(m5);

		// 1组
		List<MenuItem> menusc = new ArrayList<MenuItem>();
		MenuItem m3 = new MenuItem();
		m3.setId("65");
		m3.setPid("11");
		m3.setUrl("/layout");
		m3.setIcon("mws-i-24 i-table-1");
		m3.setName("布局");
		menusc.add(m3);
		MenuItem m4 = new MenuItem();
		m4.setId("65");
		m4.setPid("11");
		m4.setUrl("/cog");
		m4.setIcon("mws-i-24 i-cog");
		m4.setName("配置");
		menusc.add(m4);

		MenuItem m2 = new MenuItem();
		m2.setId("11");
		m2.setPid("0");
		m2.setName("模板管理");
		m2.setIcon("mws-i-24 i-list");
		m2.setUrl("/");
		m2.setChildren(menusc);
		userMenus.add(m2);
		return userMenus;
	}

}