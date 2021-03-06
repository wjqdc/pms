package com.qdc.sys.service;

import com.qdc.sys.bean.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getManagers();

    Employee login(Employee employee);

    List<Employee> getOthers(Integer eid);

    int saveInfo(Employee emp);
}
