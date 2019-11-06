package com.qdc.usual.controller;

import com.github.pagehelper.PageInfo;
import com.qdc.common.ResultEntity;
import com.qdc.common.StringUtils;
import com.qdc.sys.bean.Employee;
import com.qdc.usual.bean.Baoxiao;
import com.qdc.usual.bean.Notice;
import com.qdc.usual.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notic")
public class NoticController {

    @Autowired
    private NoticeService noticeService;

     //显示内容到弹窗
     @RequestMapping(value = "/info{id}",method = RequestMethod.GET)
     @ResponseBody
      public Notice getNoticeInfo(@PathVariable("id") Integer nid ){

        return noticeService.getNoticeInfo(nid);
     }



    //显示遮罩和弹窗
    @RequestMapping(value = "/latestnoticeList",method = RequestMethod.GET)
    @ResponseBody
    public  ResultEntity latestnoticeList(){
       List<Notice> notices= noticeService.latestnoticeList();
       return ResultEntity.success().put("notices",notices);
    }
    //分页请求
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
        public ResultEntity getNoticejsonList(HttpServletRequest request, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum) {
        Map<String, Object> parameterMap = WebUtils.getParametersStartingWith(request,"search_");
        String queryStr = StringUtils.ParseparameterMapToString(parameterMap);

        String requestURI = request.getRequestURI();

        PageInfo<Notice> page= noticeService.getNoticejsonList(pageNum,parameterMap);
        return ResultEntity.success().put("page",page).put("queryStr",queryStr).put("requestURI",requestURI);


    }

  /*  //通知列表异步查询
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public  ResultEntity getNoticejsonList(){
        List<Notice> list=noticeService.getNoticejsonList();
        return ResultEntity.success().put("list",list);

    }*/
  // 添加通告
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
     @ResponseBody
    public ResultEntity saveInfo(Notice notice){
        noticeService.saveInfo(notice);
        return  ResultEntity.success();

    }
}
