package com.qdc.sys.controller;

import com.qdc.common.ResultEntity;
import com.qdc.sys.bean.Role;
import com.qdc.sys.service.RoleService;
import com.qdc.sys.service.RoleSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleSourceService  roleSourceService;

    //查询角色列表
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getJsonList(){
        return roleService.getJsonlist();
    }

    //添加角色信息
   @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
   @ResponseBody
    public ResultEntity saveInfo(Role role,String ids){
       int roleid=roleService.saveInfo(role);
       roleSourceService.saveInfo(roleid,ids);
    return ResultEntity.success();
    }
}
