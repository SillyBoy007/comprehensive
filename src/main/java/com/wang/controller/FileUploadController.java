package com.wang.controller;

import com.wang.entity.Upload;
import com.wang.entity.vo.RetResult;
import com.wang.service.UploadService;
import com.wang.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
public class FileUploadController {
    @Autowired
    private UploadService uploadService;
    @RequestMapping("/uploadImg")
    @ResponseBody
    public RetResult upload(@RequestParam("file") MultipartFile multipartFile, HttpSession session){

        if (!StringUtils.isEmpty(multipartFile) && multipartFile.getSize()>0){
            String filename = multipartFile.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf(".") + 1);
            if (filename.endsWith("jpg")||filename.endsWith("png")){

                    String realPath = session.getServletContext().getRealPath("/")+"/image/"+new Date().getTime()+"."+suffix;
                File newfile = new File(realPath);
                try {
                    multipartFile.transferTo(newfile);
                    return RetResult.successRet(null);
                } catch (IOException e) {
                    e.printStackTrace();
                    return RetResult.errorRet(1,"文件上传异常");
                }
            }else {
                return RetResult.errorRet(2,"不支持该上传类型");
            }
        }else {
            return RetResult.errorRet(3,"文件为空");
        }

    }
    @RequestMapping("/formupload")
    @ResponseBody
    public RetResult formupload(@RequestParam("username") String username,@RequestParam("file") MultipartFile multipartFile, HttpSession session){

        if (!StringUtils.isEmpty(multipartFile) && multipartFile.getSize()>0){
            String filename = multipartFile.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf(".") + 1);
            if (filename.endsWith("jpg")||filename.endsWith("png")){

                String realPath = session.getServletContext().getRealPath("/")+"/image/"+new Date().getTime()+"."+suffix;
                File newfile = new File(realPath);
                try {
                    multipartFile.transferTo(newfile);
                    Upload upload = new Upload();
                    upload.setPath(realPath);
                    upload.setUploadtime(CommonUtils.transDate(new Date()));
                    upload.setId(CommonUtils.getUUID());
                    upload.setUsername(username);
                    uploadService.saveFile(upload);
                    return RetResult.successRet(null);
                } catch (IOException e) {
                    e.printStackTrace();
                    return RetResult.errorRet(1,"文件上传异常");
                }
            }else {
                return RetResult.errorRet(2,"不支持该上传类型");
            }
        }else {
            return RetResult.errorRet(3,"文件为空");
        }

    }
}
