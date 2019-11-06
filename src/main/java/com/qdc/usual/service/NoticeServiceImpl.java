package com.qdc.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdc.common.StringUtils;
import com.qdc.usual.bean.Baoxiao;
import com.qdc.usual.bean.BaoxiaoExample;
import com.qdc.usual.bean.Notice;
import com.qdc.usual.bean.NoticeExample;
import com.qdc.usual.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    public void saveInfo(Notice notice) {
        notice.setNdate(new Date());
         noticeMapper.insert(notice);
    }

    public PageInfo<Notice> getNoticejsonList( Integer pageNum, Map<String, Object> parameterMap) {
       /* NoticeExample example=new NoticeExample();
        List<Notice> notices = noticeMapper.selectByExample(example);
        return notices;*/
        NoticeExample example=new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();
        Map<String, String> myBatisMap = StringUtils.parseParameterMapToMyBatisMap(parameterMap);

        PageHelper.startPage(pageNum,3);
        List<Notice> Notices = noticeMapper.selectByExample(example);
        PageInfo<Notice> page=new PageInfo<Notice>(Notices,5);
        return page;
    }


    public List<Notice> latestnoticeList() {
         NoticeExample example=new NoticeExample();
         example.setOrderByClause("ndate desc limit 3");

        List<Notice> notices = noticeMapper.selectByExample(example);
        return notices;
    }

    public Notice getNoticeInfo(Integer nid) {

        Notice notice = noticeMapper.selectByPrimaryKey(nid);
        return notice;
    }
}
