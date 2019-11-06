package com.qdc.compare.mapper;

import com.qdc.compare.bean.Benchmarking;

import java.util.List;

public interface BenchmarkingMapper {
    void saveInfo(Benchmarking benchmarking);

    List<Benchmarking> getAmountList(Integer year);
}
