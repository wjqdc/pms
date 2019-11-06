package com.qdc.sys.service;

import com.qdc.sys.bean.Role;

import java.util.List;

public interface RoleService {
    int saveInfo(Role role);

    List<Role> getJsonlist();
}
