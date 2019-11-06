package com.qdc.compare.controller;

import com.qdc.compare.bean.Benchmarking;
import com.qdc.compare.service.BenchmarkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/Benchmarking")
public class BenchmarkingController {

    @Autowired
    private BenchmarkingService benchmarkingService;

    //对标结果查询
    @RequestMapping(value = "/list/{year}",method = RequestMethod.GET)
    @ResponseBody
    public List<Benchmarking> getAmountList(@PathVariable("year") Integer year){
       return benchmarkingService.getAmountList(year);
    }

    //添加指标信息
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Benchmarking benchmarking){
        benchmarkingService.saveInfo(benchmarking);
        return "redirect:/indexvalue-base.jsp";
    }
}
