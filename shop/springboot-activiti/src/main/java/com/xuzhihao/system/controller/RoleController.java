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

import com.github.pagehelper.PageInfo;
import com.xuzhihao.common.api.PageResultBean;
import com.xuzhihao.common.api.ResultBean;
import com.xuzhihao.system.model.Role;
import com.xuzhihao.system.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/index")
    public String index() {
        return "role/role-list";
    }

    @GetMapping("/list")
    @ResponseBody
    public PageResultBean<Role> getList(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "limit", defaultValue = "10")int limit,
                                        Role roleQuery) {
        List<Role> roles = roleService.selectAll(page, limit, roleQuery);
        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        return new PageResultBean<>(rolePageInfo.getTotal(), rolePageInfo.getList());
    }

    @GetMapping
    public String add() {
        return "role/role-add";
    }

    @PostMapping
    @ResponseBody
    public ResultBean add(Role role) {
        roleService.add(role);
        return ResultBean.success();
    }

    @GetMapping("/{roleId}")
    public String update(@PathVariable("roleId") Integer roleId, Model model) {
        Role role = roleService.selectOne(roleId);
        model.addAttribute("role", role);
        return "role/role-add";
    }

    @PutMapping
    @ResponseBody
    public ResultBean update(Role role) {
        roleService.update(role);
        return ResultBean.success();
    }


    @DeleteMapping("/{roleId}")
    @ResponseBody
    public ResultBean delete(@PathVariable("roleId") Integer roleId) {
        roleService.delete(roleId);
        return ResultBean.success();
    }

    @PostMapping("/{roleId}/grant/menu")
    @ResponseBody
    public ResultBean grantMenu(@PathVariable("roleId") Integer roleId, @RequestParam(value = "menuIds[]", required = false) Integer[] menuIds) {
        roleService.grantMenu(roleId, menuIds);
        return ResultBean.success();
    }


    @PostMapping("/{roleId}/grant/operator")
    @ResponseBody
    public ResultBean grantOperator(@PathVariable("roleId") Integer roleId, @RequestParam(value = "operatorIds[]", required = false) Integer[] operatorIds) {
        roleService.grantOperator(roleId, operatorIds);
        return ResultBean.success();
    }

    /**
     * 获取角色拥有的菜单
     */
    @GetMapping("/{roleId}/own/menu")
    @ResponseBody
    public ResultBean getRoleOwnMenu(@PathVariable("roleId") Integer roleId) {
        return ResultBean.success(roleService.getMenusByRoleId(roleId));
    }

    /**
     * 获取角色拥有的操作权限
     */
    @GetMapping("/{roleId}/own/operator")
    @ResponseBody
    public ResultBean getRoleOwnOperator(@PathVariable("roleId") Integer roleId) {
        Integer[] operatorIds = roleService.getOperatorsByRoleId(roleId);
        for (int i = 0; i < operatorIds.length; i++) {
            operatorIds[i] = operatorIds[i] + 10000;
        }
        return ResultBean.success(operatorIds);
    }
}
