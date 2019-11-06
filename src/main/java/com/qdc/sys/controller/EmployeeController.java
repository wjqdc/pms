package com.qdc.sys.controller;

import com.qdc.sys.bean.Employee;
import com.qdc.sys.service.EmpRoleService;
import com.qdc.sys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
     private EmployeeService employeeService;
     @Autowired
     private EmpRoleService empRoleService;
    //权限管理之添加用户信息
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public  String saveInfo(Employee emp,String[] roleids){
       int id=  employeeService.saveInfo(emp);
        empRoleService.insert(id,roleids);
        return "redirect:/user.jsp";
    }
    //发件箱的查询
    @RequestMapping(value = "/others",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getOthers(HttpSession session){
        Employee loginUser = (Employee)session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
       List<Employee> emp= employeeService.getOthers(eid);
       return  emp;
    }

    //返回到登录界面
    @RequestMapping(value = "/loginout",method = RequestMethod.GET)
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
    //登录提交
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Employee employee, String code, HttpSession session, RedirectAttributes attributes){
        String validateCode =(String) session.getAttribute("validateCode");
          if (code.equalsIgnoreCase(validateCode)){
              session.removeAttribute("validateCode");
              employee=employeeService.login(employee);
              if(employee!=null) {
                  session.setAttribute("loginUser", employee);
                  return "redirect:/index.jsp";
              }else{
                  attributes.addFlashAttribute("errorMsg","用户名或密码错误");
                  return "redirect:/login";
              }
          }
        attributes.addFlashAttribute("errorMsg","验证码错误");

        return "redirect:/login";

    }

    @RequestMapping(value = "/managers",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getManagers(){

        return  employeeService.getManagers();
    }

}
