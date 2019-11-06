package com.qdc.usual.service;

import com.github.pagehelper.PageInfo;
import com.qdc.usual.bean.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    void saveInfo(Notice notice);

    PageInfo<Notice> getNoticejsonList( Integer pageNum, Map<String, Object> parameterMap);

    List<Notice> latestnoticeList();

    Notice getNoticeInfo(Integer nid);
}
