package com.qdc.compare.service;

import com.qdc.compare.bean.Benchmarking;

import java.util.List;

public interface BenchmarkingService {
    void saveInfo(Benchmarking benchmarking);

    List<Benchmarking> getAmountList(Integer year);
}
