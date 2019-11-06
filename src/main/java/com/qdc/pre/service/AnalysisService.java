package com.qdc.pre.service;


import com.qdc.pre.bean.Analysis;

import java.util.List;

public interface AnalysisService {


    void saveInfo(Analysis analysis);

    List<Analysis> info();
}
