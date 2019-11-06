package com.qdc.pre.service;

import com.qdc.pre.bean.Analysis;
import com.qdc.pre.bean.AnalysisExample;
import com.qdc.pre.bean.Project;
import com.qdc.pre.mapper.AnalysisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Resource
    private AnalysisMapper analysisMapper;
    public void saveInfo(Analysis analysis) {
        analysisMapper.insert(analysis);
    }

    public List<Analysis> info() {
        Project project=new Project();
        AnalysisExample example=new AnalysisExample();
        AnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(project.getPid());
        List<Analysis> list = analysisMapper.selectByExample(example);
        return list;

    }
}
