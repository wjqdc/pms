package com.qdc.sys.service;

import com.qdc.sys.bean.Sources;

import java.util.List;

public interface SourcesService {
    List<Sources> getSourcesList(int i);

    void saveInfo(Sources sources);

    Sources getSourcesInfo(Integer sid);

    void updateInfo(Sources sources);

    void delete(Integer id);
}
