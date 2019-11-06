package com.qdc.usual.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qdc.sys.bean.Employee;
import com.qdc.usual.bean.Baoxiao;
import com.qdc.usual.service.BaoXiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/bx")
public class BaoXiaoController {
    @Autowired
    private BaoXiaoService baoXiaoService;

   //查询报销
    @RequestMapping(value = "/list",method = RequestMethod.GET)
      public ModelAndView getMyBaoxiaoList(HttpSession session,@RequestParam(value="pageNum",defaultValue = "1") Integer pageNum){
        Employee loginUser =(Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
       PageInfo<Baoxiao> page= baoXiaoService.getMyBaoXiaoList(eid,pageNum);
       ModelAndView mv=new ModelAndView("mybaoxiao-base");
       mv.addObject("page",page);
       return mv;

    }
        //添加报销
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public  String saveInfo(Baoxiao baoxiao , HttpSession session){
        Employee loginUser =(Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        baoxiao.setEmpFk(eid);
        baoXiaoService.saveInfo(baoxiao);
        return "redirect:/mybaoxiao-base.jsp";
    }
}
