package com.qdc.pre.controller;

import com.qdc.pre.bean.Project;
import com.qdc.pre.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/pro")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    //基本信息查询的选择查询

    @RequestMapping(value = "/list",method = RequestMethod.GET)
     public ModelAndView getProjectList(Integer cid,String keyword, Integer orderby){

        ModelAndView mv=new ModelAndView("project-base");
        List<Project> list =projectService.getProjectList(cid,keyword,orderby);
        mv.addObject("list",list);
        return mv;

    }
    @RequestMapping(value = "/select",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> select(){
       return projectService.select();
   }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Project project){
       projectService.saveInfo(project);
        return "redirect:/project-base.jsp";
    }

    //日常办公中创建任务的参考位置
    //带需求分析的查询
    @RequestMapping(value = "/withAnalyseJsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> getProjectWithAnalyseJsonList(){
    return  projectService.getProjectWithAnalyseJsonList();
    }

}
