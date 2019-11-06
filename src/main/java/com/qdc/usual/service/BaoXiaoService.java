package com.qdc.usual.service;

import com.github.pagehelper.PageInfo;
import com.qdc.usual.bean.Baoxiao;

public interface BaoXiaoService {
    void saveInfo(Baoxiao baoxiao);

    PageInfo<Baoxiao> getMyBaoXiaoList(Integer eid, Integer pageNum);
}
