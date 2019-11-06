package com.qdc.sys.controller;

import com.qdc.common.ResultEntity;
import com.qdc.sys.bean.Sources;
import com.qdc.sys.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sources")
public class SourcesController {

    @Autowired
    private SourcesService sourcesService;
    //删除
    @RequestMapping(value="/delete",method = RequestMethod.DELETE)
    public ResultEntity delete(Integer id){
        sourcesService.delete(id);
      return  ResultEntity.success();
    }
    //修改
    @RequestMapping(value="/updateInfo",method = RequestMethod.PUT)
    public  String updateInfo(Sources sources){
        sourcesService.updateInfo(sources);
        return "redirect:/pm.jsp";
    }

  // 编辑菜单资源
  @RequestMapping(value="/info/{id}",method = RequestMethod.GET)
  public ModelAndView getSourcesInfo(@PathVariable("id") Integer sid ){
     ModelAndView mv=new ModelAndView();
     mv.setViewName("pm-edit");
      Sources sources=sourcesService.getSourcesInfo(sid);
      mv.addObject("sources",sources);
      return mv;
  }
    //添加菜单资源
    @RequestMapping(value="/saveInfo",method = RequestMethod.POST)
     public  String saveInfo(Sources sources){
        sourcesService.saveInfo(sources);
        return "redirect:/pm.jsp";
    }

    //权限列表
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Sources> getSourcesList(){

       List<Sources> list= sourcesService.getSourcesList(0);
       queryChildren(list.get(0));
       return list;
    }

    //完成递归查询
    private void queryChildren(Sources source) {

        Integer id = source.getId();
        List<Sources> sources = sourcesService.getSourcesList(id);

        for(Sources sources1:sources){
            queryChildren(sources1);
        }
        source.setChildren(sources);
    }
}
