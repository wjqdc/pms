package com.qdc.cust.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdc.cust.bean.Goods;
import com.qdc.cust.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GoodsServiceImpl {
    @Autowired
    private GoodsMapper goodsMapper;
     public List<Goods> getGoodsList(){
         PageHelper.startPage(3,5);
         List<Goods> goodsList = goodsMapper.getGoodsList();

         PageInfo<Goods> page = new PageInfo(goodsList,3);

         System.out.println("总页数："+page.getPages());
         System.out.println("总记录数："+page.getTotal());

         System.out.println("当前页："+page.getPageNum());

         System.out.println("上一页："+page.getPrePage());
         System.out.println("下一页："+page.getNextPage());

         System.out.println("是否有上一页："+page.isHasPreviousPage());
         System.out.println("是否有下一页："+page.isHasNextPage());


         System.out.println("是否为首页："+page.isIsFirstPage());
         System.out.println("是否为末页："+page.isIsLastPage());


         System.out.println("PageSize："+page.getPageSize());
         List<Goods> list = page.getList();
         for (Goods goods : list) {
             System.out.println(goods);
         }

         int[] nums = page.getNavigatepageNums();

         System.out.println(Arrays.toString(nums));

         return goodsList;
     }
}
