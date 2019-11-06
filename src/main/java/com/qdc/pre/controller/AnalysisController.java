package com.qdc.pre.controller;

import com.qdc.pre.bean.Analysis;
import com.qdc.pre.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("anal")
public class AnalysisController {
   @Autowired
    private AnalysisService analysisService;
   @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
   public  String saveInfo(Analysis analysis){
       analysisService.saveInfo(analysis);
       return "redirect:/project-need.jsp";
   }

    //日常办公中创建任务的参考位置
    //通过项目得到需求分析
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public List<Analysis> info(){
        return  analysisService.info();
    }



}
