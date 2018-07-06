package com.wang.mapper;

import com.wang.entity.Upload;
import com.wang.entity.UploadExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UploadMapper {
    int countByExample(UploadExample example);

    int deleteByExample(UploadExample example);

    int deleteByPrimaryKey(String id);

    int insert(Upload record);

    int insertSelective(Upload record);

    List<Upload> selectByExample(UploadExample example);

    Upload selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Upload record, @Param("example") UploadExample example);

    int updateByExample(@Param("record") Upload record, @Param("example") UploadExample example);

    int updateByPrimaryKeySelective(Upload record);

    int updateByPrimaryKey(Upload record);
}