package com.qdc.sys.mapper;


import java.util.List;

import com.qdc.sys.bean.Archives;
import com.qdc.sys.bean.ArchivesExample;
import org.apache.ibatis.annotations.Param;

public interface ArchivesMapper {
    int countByExample(ArchivesExample example);

    int deleteByExample(ArchivesExample example);

    int deleteByPrimaryKey(String dnum);

    int insert(Archives record);

    int insertSelective(Archives record);

    List<Archives> selectByExample(ArchivesExample example);

    Archives selectByPrimaryKey(String dnum);

    int updateByExampleSelective(@Param("record") Archives record, @Param("example") ArchivesExample example);

    int updateByExample(@Param("record") Archives record, @Param("example") ArchivesExample example);

    int updateByPrimaryKeySelective(Archives record);

    int updateByPrimaryKey(Archives record);
}