package com.example.controller;


import com.example.constants.WebMessageConstants;
import com.example.encapsulation.ResultUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * @Author created by wxb007 on 2019/5/9 0009 15:47
 */

@RestController
@RequestMapping("/api")
public class uploadController {

    @Value("${fileUpload.path}")
    private String fileSavePath;


    @GetMapping("/getTest")
    public String getTest() {
        return "this is test";
    }

    @PostMapping("/upload")
    public ResultUtil upload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        String fileName = multipartFile.getOriginalFilename();
        Long size = multipartFile.getSize();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));


        /**
         * 判断是否格式正确 目前只支持.jpg .png
         */
        if (!suffixName.equals(".jpg") && !suffixName.equals(".png")) {
            return ResultUtil.error(5001, WebMessageConstants.UPLOAD_FILE_MSG_5001);
        }


        /**
         * 判断限制大小 最大为10MB
         */
        if (size > 10 * 1024 * 1024) {
            return ResultUtil.error(5002, WebMessageConstants.UPLOAD_FILE_MSG_5002);
        }


        //重新生成文件名
        fileName = UUID.randomUUID() + suffixName;

        try {
            //每天生成一个新的文件夹 以日期命名
            SimpleDateFormat strNow = new SimpleDateFormat("yyyy-MM-dd");
            String toDayDate = strNow.format(Calendar.getInstance().getTime());


            //将图片保存到static文件夹里
            File targetFile = new File(fileSavePath + toDayDate + "/" + fileName);


            //没有路径就创建
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }

            multipartFile.transferTo(targetFile);


            //将路径中的\ 装成 /
            return ResultUtil.success(targetFile);

        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error();
        }
    }
}
