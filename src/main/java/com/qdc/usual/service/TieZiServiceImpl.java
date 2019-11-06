package com.qdc.usual.service;

import com.qdc.usual.bean.TieZi;
import com.qdc.usual.mapper.TieZiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TieZiServiceImpl implements TieZiService {

    @Autowired
    private TieZiMapper tieZiMapper;

    public void saveInfo(TieZi tieZi) {
          tieZiMapper.saveInfo(tieZi);
    }
}
