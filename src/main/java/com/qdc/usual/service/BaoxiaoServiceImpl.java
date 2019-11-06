package com.qdc.usual.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdc.usual.bean.Baoxiao;
import com.qdc.usual.bean.BaoxiaoExample;
import com.qdc.usual.mapper.BaoxiaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BaoxiaoServiceImpl implements BaoXiaoService {
    @Autowired
    private BaoxiaoMapper baoxiaoMapper;
    public void saveInfo(Baoxiao baoxiao) {
        String bxid = UUID.randomUUID().toString().replaceAll("-", "");
        baoxiao.setBxid(bxid);
        baoxiao.setBxstatus(0);
        baoxiaoMapper.insert(baoxiao);
    }

    public PageInfo<Baoxiao> getMyBaoXiaoList(Integer eid, Integer pageNum) {
        BaoxiaoExample example=new BaoxiaoExample();
        BaoxiaoExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(eid);
        PageHelper.startPage(pageNum,3);
        List<Baoxiao> baoxiaos = baoxiaoMapper.selectByExample(example);
        PageInfo<Baoxiao> page=new PageInfo<Baoxiao>(baoxiaos,5);
        return page;
    }
}
