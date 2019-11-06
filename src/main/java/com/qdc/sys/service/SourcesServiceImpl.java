package com.qdc.sys.service;

import com.qdc.sys.bean.Sources;
import com.qdc.sys.bean.SourcesExample;
import com.qdc.sys.mapper.SourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourcesServiceImpl implements SourcesService {

    @Autowired
    private SourcesMapper sourcesMapper;
    public List<Sources> getSourcesList(int i) {
        SourcesExample example=new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(i);
        List<Sources> sources = sourcesMapper.selectByExample(example);
        return sources;
    }

    public void saveInfo(Sources sources) {

        sourcesMapper.insert(sources);
    }

    public Sources getSourcesInfo(Integer sid) {

        return sourcesMapper.selectByPrimaryKey(sid);
    }


    public void updateInfo(Sources sources) {
        sourcesMapper.updateByPrimaryKeySelective(sources);
    }


    public void delete(Integer id) {
        sourcesMapper.deleteByPrimaryKey(id);

    }
}
