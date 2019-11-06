package com.qdc.pre.service;

import com.qdc.pre.bean.Project;

import java.util.List;

public interface ProjectService {


    void saveInfo(Project project);

    List<Project> select();

    List<Project> getProjectList(Integer cid, String keyword, Integer orderby);

    List<Project> getProjectWithAnalyseJsonList();
}
