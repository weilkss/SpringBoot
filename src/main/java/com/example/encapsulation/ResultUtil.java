package com.example.encapsulation;

import java.io.Serializable;

/**
 * 通用 ResultUtil 返回值封装类
 *
 * @Author created by wxb007 on 2019/5/9 0009 9:37
 */
public class ResultUtil<T> implements Serializable {
    private Integer status;
    private String message;
    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 返回成功 success
     */
    public static ResultUtil success(Object object) {
        ResultUtil result = new ResultUtil();
        result.setStatus(200);
        result.setMessage("成功");
        result.setData(object);
        return result;
    }

    public static ResultUtil success() {
        return ResultUtil.success(null);
    }

    /**
     * 返回失败 error
     */
    public static ResultUtil error(Integer code, String data, String msg) {
        ResultUtil result = new ResultUtil();
        result.setStatus(code);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    public static ResultUtil error(Integer code, String msg) {
        return ResultUtil.error(code, null, msg);
    }

    public static ResultUtil error() {
        return ResultUtil.error(500, null, "失败");
    }
}
