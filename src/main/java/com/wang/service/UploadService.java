package com.wang.service;

import com.wang.entity.Upload;

public interface UploadService {
    /**
     * 上传文件
     * @param upload  文件实体类
     */
    void saveFile(Upload upload);
}
