package com.qdc.cust.service;

import com.qdc.cust.bean.Customer;
import com.qdc.cust.bean.CustomerExample;
import com.qdc.cust.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public void savaInfo(Customer customer) {
        customer.setAddtime(new Date());
        customerMapper.insert(customer);
    }


    public List<Customer> getCustmerList() {
        CustomerExample customerExample=new CustomerExample();
        List<Customer> customers = customerMapper.selectByExample(customerExample);
        return customers;
    }


    public Customer getDetailById(Integer cid) {
        Customer customer = customerMapper.selectByPrimaryKey(cid);
        return customer;
    }


    public void updateCustomerById(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }


    public boolean deletecustormer(Integer[] ids) {
        List<Integer> idList= Arrays.asList(ids);
        CustomerExample example=new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        int i = customerMapper.deleteByExample(example);
        return ids.length==i;
    }


    public List<Customer> serach(Integer cid, String keyword, Integer orderby) {
       CustomerExample example=new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        if(cid==0){
            criteria.andComnameLike("%"+keyword+"%");
            CustomerExample.Criteria criteria1 = example.createCriteria();
            criteria1.andCompanypersonLike("%"+keyword+"%");
            example.or(criteria1);
        }else if(cid==1){
            criteria.andComnameLike("%"+keyword+"%");
        }else {
            criteria.andCompanypersonLike("%"+keyword+"%");
        }
        if(orderby==1){
            example.setOrderByClause("id desc");
        }
       List<Customer> customers=customerMapper.selectByExample(example);
        return customers;
    }


    public void importExcel(List<Customer> customers) {


    }


}
