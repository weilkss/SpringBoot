package com.example.controller;


import com.example.constants.WebMessageConstants;
import com.example.encapsulation.ResultUtil;
import com.example.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Author created by wxb007 on 2019/5/9 0009 15:47
 */

@RestController
@RequestMapping("/api")
public class uploadController {

    @Value("${fileUpload.path}")
    private String fileSavePath;

    /**
     * 上传文件 - 单个文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/upload/file")
    public ResultUtil upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return ResultUtil.error(5003, WebMessageConstants.UPLOAD_FILE_MSG_5003);
        }
        return new UploadFileUtil().upload(file, fileSavePath);
    }

    /**
     * 上传文件 - 多个文件
     *
     * @param files
     * @return
     * @throws Exception
     */
    @PostMapping("/upload/files")
    public ResultUtil upload(@RequestParam("files") MultipartFile[] files) throws Exception {
        if (files != null && files.length > 0) {
            return new UploadFileUtil().upload(files, fileSavePath);
        }
        return ResultUtil.error(5003, WebMessageConstants.UPLOAD_FILE_MSG_5003);
    }
}
