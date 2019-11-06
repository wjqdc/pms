package com.qdc.cust.service;

import com.qdc.cust.bean.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {

    void savaInfo(Customer customer);

    List<Customer> getCustmerList();

    Customer getDetailById(Integer cid);

    void updateCustomerById(Customer customer);


    boolean deletecustormer(Integer[] ids);

    List<Customer> serach(Integer cid, String keyword, Integer orderby);



}
