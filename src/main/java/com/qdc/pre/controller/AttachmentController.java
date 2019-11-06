package com.qdc.pre.controller;

import com.qdc.pre.bean.Attachment;
import com.qdc.pre.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/attach")
public class AttachmentController {
   @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveInfo(Attachment atta , MultipartFile attachment, HttpSession session){
        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath("/upload");
        File file=new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String originalFilename = attachment.getOriginalFilename();
       String realName= UUID.randomUUID().toString().replaceAll("-","")+originalFilename;
        try {
            attachment.transferTo(new File(realPath+"/"+realName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        atta.setPath(realPath+"/"+realName);
        attachmentService.saveInfo(atta);

        Map<String,Object> map=new HashMap<>();
           map.put("statusCode",200);
           map.put("message","保存成功");
           return map;
        //return "redirect:/project-file.jsp";
    }
}
