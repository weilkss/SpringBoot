package com.example.util;

import com.example.constants.WebMessageConstants;
import com.example.encapsulation.ResultUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;


/**
 * 上传类
 * created by wxb007 on 2019/5/24 0024 17:46
 */
public class UploadFileUtil {
    /**
     * @param file
     * @param fileSavePath
     * @return
     * @throws Exception
     */
    public ResultUtil upload(MultipartFile file, String fileSavePath) throws Exception {

        String fileName = file.getOriginalFilename();
        Long size = file.getSize();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        if (!suffixName.equals(".jpg") && !suffixName.equals(".png")) {
            return ResultUtil.error(5001, WebMessageConstants.UPLOAD_FILE_MSG_5001);
        }

        if (size > 10 * 1024 * 1024) {
            return ResultUtil.error(5002, WebMessageConstants.UPLOAD_FILE_MSG_5002);
        }

        File targetFile = saveFile(fileSavePath, suffixName);

        file.transferTo(targetFile);

        try {
            //将路径中的\ 装成 /
            return ResultUtil.success(targetFile.getPath().replace("\\", "/"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error();
        }
    }

    /**
     * @param files
     * @param fileSavePath
     * @return
     * @throws Exception
     */
    public ResultUtil upload(MultipartFile[] files, String fileSavePath) throws Exception {

        String[] resultPaths = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getOriginalFilename();
            Long size = files[i].getSize();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            if (!suffixName.equals(".jpg") && !suffixName.equals(".png")) {
                return ResultUtil.error(5001, "第" + (i + 1) + WebMessageConstants.UPLOAD_FILE_MSG_5001);
            }

            if (size > 10 * 1024 * 1024) {
                return ResultUtil.error(5002, "第" + (i + 1) + "个文件超出上传大小限制，" + WebMessageConstants.UPLOAD_FILE_MSG_5002);
            }

            File targetFile = saveFile(fileSavePath, suffixName);

            files[i].transferTo(targetFile);

            //将路径中的\ 装成 /
            resultPaths[i] = targetFile.getPath().replace("\\", "/");

        }
        try {
            return ResultUtil.success(resultPaths);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error();
        }
    }

    /**
     * 保存文件
     *
     * @param fileSavePath
     * @param suffixName
     * @return
     */
    public File saveFile(String fileSavePath, String suffixName) {

        //重新生成文件名
        String newfileName = UUID.randomUUID() + suffixName;

        //每天生成一个新的文件夹 以日期命名
        String toDayDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

        //将图片保存到static文件夹里
        File targetFile = new File(fileSavePath + toDayDate + "/" + newfileName);

        //没有路径就创建
        if (!targetFile.getParentFile().exists()) targetFile.getParentFile().mkdirs();

        return targetFile;
    }
}
