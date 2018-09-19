package com.pinyougou.shop.controller;

import entity.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import util.FastDFSClient;

@RestController
public class UploadController {
    @Value("${FILE_SERVICE_URL}")
    private String file_service_url;

    @RequestMapping("/upload")
    public Result upload(MultipartFile file) {
        String filename = file.getOriginalFilename();//获取文件名
        String extName = filename.substring(filename.indexOf(".") + 1);//截取文件后缀名；
        try {
            FastDFSClient client = new FastDFSClient("classpath:config/fdfs_client.conf");
            String fileId = client.uploadFile(file.getBytes(), extName);
            String url = file_service_url + fileId;//图片的完整地址
            return new Result(true, url);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "上传失败");
        }

    }
}
