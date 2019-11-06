package com.qdc.compare.service;

import com.qdc.compare.bean.Benchmarking;
import com.qdc.compare.mapper.BenchmarkingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenchmarkingServiceImpl implements BenchmarkingService {
    @Autowired
    private BenchmarkingMapper benchmarkingMapper;
    public void saveInfo(Benchmarking benchmarking) {
        benchmarkingMapper.saveInfo(benchmarking);
    }

    public List<Benchmarking> getAmountList(Integer year) {

        return  benchmarkingMapper.getAmountList(year);
    }
}
