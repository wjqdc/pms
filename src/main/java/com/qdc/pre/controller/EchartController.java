package com.qdc.pre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/echart")
public class EchartController {
    @RequestMapping(value = "/data",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getDatas(){
        Map<String,Object> map=new HashMap<>();
        map.put("IOS",100000);
        map.put("Android",180000);
        map.put("Windows",6000);
        map.put("SamBian",90000);
        map.put("HongMeng",400000);
        return map;
    }
}
