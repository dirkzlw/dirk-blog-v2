package com.zlw.blog.controller;

import com.zlw.blog.utils.FastDFSUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ranger
 * @create 2019-04-29 17:58
 */
@Controller
public class UploadController {

    //引入图片服务器地址
    @Value("${FDFS_ADDRESS}")
    private String FDFS_ADDRESS;
    @Value("${FDFS_CLIENT_PAHT}")
    private String FDFS_CLIENT_PAHT;

    /**
     * 实现图片上传
     *
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public Map<String, String> upload(@RequestParam("file") MultipartFile file) throws IOException {

        String imgUrl = FastDFSUtils.uploadFile(FDFS_CLIENT_PAHT,FDFS_ADDRESS,file);

        Map<String, String> map = new HashMap<>();
        map.put("data",imgUrl);//这里应该是项目路径

        return map;
    }
}
