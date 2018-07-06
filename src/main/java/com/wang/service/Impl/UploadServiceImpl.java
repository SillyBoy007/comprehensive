package com.wang.service.Impl;

import com.wang.entity.Upload;
import com.wang.mapper.UploadMapper;
import com.wang.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadMapper uploadMapper;
    public void saveFile(Upload upload) {
        uploadMapper.insert(upload);
    }
}
