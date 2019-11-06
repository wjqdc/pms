package com.qdc.sys.service;

import com.qdc.sys.mapper.EmpRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpRoleServiceImpl implements EmpRoleService {

@Autowired
private EmpRoleMapper empRoleMapper;
    public void insert(int id, String[] roleids) {
        for (String roleid : roleids) {
            empRoleMapper.insert(id, Integer.parseInt(roleid));
        }

    }
}
