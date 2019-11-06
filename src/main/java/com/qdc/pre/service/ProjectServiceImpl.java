package com.qdc.pre.service;

import com.qdc.pre.bean.Project;
import com.qdc.pre.bean.ProjectExample;
import com.qdc.pre.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    public void saveInfo(Project project) {

        projectMapper.insert(project);
    }

    public List<Project> select() {
        ProjectExample example= new ProjectExample();
        List<Project> list = projectMapper.selectByExample(example);
        return list;
    }

    public List<Project> getProjectList(Integer cid, String keyword, Integer orderby) {


        return projectMapper.getProjectListWithSearch(cid,keyword,orderby);

    }

    public List<Project> getProjectWithAnalyseJsonList() {
        return projectMapper.getProjectWithAnalyseJsonList();
    }
}
