package com.qdc.sys.controller;

import com.qdc.sys.bean.Position;
import com.qdc.sys.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pos")
public class PositionController {
    @Autowired
    private PositionService positionService;
    //查询职位
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Position> getJosnList(){
        return positionService.getJosnList();
    }
}
