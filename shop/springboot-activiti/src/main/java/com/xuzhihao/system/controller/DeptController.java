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

import com.xuzhihao.common.api.ResultBean;
import com.xuzhihao.system.model.Dept;
import com.xuzhihao.system.service.DeptService;

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @GetMapping("/index")
    public String index() {
        return "dept/dept-list";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResultBean getList(@RequestParam(required = false) Integer parentId) {
        List<Dept> deptList = deptService.selectByParentId(parentId);
        return ResultBean.success(deptList);
    }

    @GetMapping("/tree/root")
    @ResponseBody
    public ResultBean treeAndRoot() {
        return ResultBean.success(deptService.selectAllDeptTreeAndRoot());
    }

    @GetMapping("/tree")
    @ResponseBody
    public ResultBean tree() {
        return ResultBean.success(deptService.selectAllDeptTree());
    }

    @GetMapping
    public String add() {
        return "dept/dept-add";
    }

    @PostMapping
    @ResponseBody
    public ResultBean add(Dept dept) {
        return ResultBean.success(deptService.insert(dept));
    }

    @DeleteMapping("/{deptId}")
    @ResponseBody
    public ResultBean delete(@PathVariable("deptId") Integer deptId) {
        deptService.deleteCascadeByID(deptId);
        return ResultBean.success();
    }

    @PutMapping
    @ResponseBody
    public ResultBean update(Dept dept) {
        deptService.updateByPrimaryKey(dept);
        return ResultBean.success();
    }

    @GetMapping("/{deptId}")
    public String update(@PathVariable("deptId") Integer deptId, Model model) {
        Dept dept = deptService.selectByPrimaryKey(deptId);
        model.addAttribute("dept", dept);
        return "dept/dept-add";
    }

    @PostMapping("/swap")
    @ResponseBody
    public ResultBean swapSort(Integer currentId, Integer swapId) {
        deptService.swapSort(currentId, swapId);
        return ResultBean.success();
    }

}
