package com.qdc.sys.service;

import com.qdc.sys.bean.Position;
import com.qdc.sys.bean.PositionExample;
import com.qdc.sys.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionMapper positionMapper;
    public List<Position> getJosnList() {
        PositionExample example=new PositionExample();
        List<Position> positions = positionMapper.selectByExample(example);
        return positions;
    }
}
