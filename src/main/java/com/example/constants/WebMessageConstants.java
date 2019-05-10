package com.example.constants;

public interface WebMessageConstants {
    /**
     * 通用返回信息
     */
    String SUCCESS = "SUCCESS";
    String FAIL = "FAIL";

    /**
     * 通用信息提示
     * 0 - 99
     */
    String SCE_WEB_MSG_001 = "参数长度不可为 0";
    String SCE_WEb_MSG_002 = "缺少参数";

    /**
     * 通用上传类
     * file
     */
    String UPLOAD_FILE_MSG_5001 = "文件格式错误,目前只支持jpg和png格式";
    String UPLOAD_FILE_MSG_5002 = "允许最大上传为10MB";
}
