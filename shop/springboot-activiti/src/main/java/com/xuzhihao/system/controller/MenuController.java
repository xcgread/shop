package com.xuzhihao.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuzhihao.common.annotation.RefreshFilterChain;
import com.xuzhihao.common.api.ResultBean;
import com.xuzhihao.system.model.Menu;
import com.xuzhihao.system.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/index")
    public String index() {
        return "menu/menu-list";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResultBean getList(@RequestParam(required = false) Integer parentId) {
        List<Menu> menuList = menuService.selectByParentId(parentId);
        return ResultBean.success(menuList);
    }

    @GetMapping
    public String add(Model model) {
        return "menu/menu-add";
    }

    @GetMapping("/tree")
    @ResponseBody
    public ResultBean tree() {
        return ResultBean.success(menuService.getALLTree());
    }

    @GetMapping("/tree/root")
    @ResponseBody
    public ResultBean treeAndRoot() {
        return ResultBean.success(menuService.getALLMenuTreeAndRoot());
    }

    @GetMapping("/tree/root/operator")
    @ResponseBody
    public ResultBean menuAndCountOperatorTreeAndRoot() {
        return ResultBean.success(menuService.getALLMenuAndCountOperatorTreeAndRoot());
    }

    @RefreshFilterChain
    @PostMapping
    @ResponseBody
    public ResultBean add(Menu menu) {
        menuService.insert(menu);
        return ResultBean.success();
    }

    @RefreshFilterChain
    @DeleteMapping("/{menuId}")
    @ResponseBody
    public ResultBean delete(@PathVariable("menuId") Integer menuId) {
        menuService.deleteByIDAndChildren(menuId);
        return ResultBean.success();
    }

    @GetMapping("/{menuId}")
    public String updateMenu(@PathVariable("menuId") Integer menuId, Model model) {
        Menu menu = menuService.selectByPrimaryKey(menuId);
        model.addAttribute("menu", menu);
        return "menu/menu-add";
    }

    @RefreshFilterChain
    @PutMapping
    @ResponseBody
    public ResultBean update(Menu menu) {
        menuService.updateByPrimaryKey(menu);
        return ResultBean.success();
    }

    @PostMapping("/swap")
    @ResponseBody
    public ResultBean swapSort(Integer currentId, Integer swapId) {
        menuService.swapSort(currentId, swapId);
        return ResultBean.success();
    }
}