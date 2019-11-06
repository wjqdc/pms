package com.qdc.sys.service;

import com.qdc.sys.bean.Role;
import com.qdc.sys.mapper.RoleSourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleSourceServiceImpl implements RoleSourceService {

    @Autowired
    private RoleSourceMapper roleSourceMapper;

    public void saveInfo(int roleid, String ids) {
        String[] idArr = ids.split(",");
        for(String s:idArr){
            roleSourceMapper.insert(roleid,Integer.parseInt(s));
        }
    }


}
