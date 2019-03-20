package com.fanshuaiko.backage.utils;


import java.io.Serializable;
/**
 * @ClassName ResultData
 * @Description 返回数据实体类
 * @Author fanshuaiko
 * @Date 19-3-15 上午10:57
 * @Version 1.0
 **/
public class ResultData<T> implements Serializable {
    public static final String SUCCESS = "0";
    private static final long serialVersionUID = 0L;
    private String code = "0";
    private String message;
    private T data;

    public ResultData() {
    }

    public ResultData(String code) {
        this.code = code;
        this.message = "SUCCESS";
    }

    public ResultData(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultData(T data, String code) {
        this.data = data;
        this.code = code;
    }

    public ResultData(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultData<T> newSuccessResultData() {
        return new ResultData();
    }

    public static <T> ResultData<T> newResultData(String code) {
        return new ResultData(code);
    }

    public static <T> ResultData<T> newResultData(String code, String message) {
        return new ResultData(code, message);
    }

    public static <T> ResultData<T> newResultData(T data) {
        return new ResultData("0", "SUCCESS", data);
    }

    public static <T> ResultData<T> newResultData(String code, String message, T data) {
        return new ResultData(code, message, data);
    }

    public static boolean isSuccess(ResultData t) {
        return t != null && "0".equals(t.getCode());
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        if ("0".equals(this.code)) {
            if (this.message == null) {
                this.message = "SUCCESS";
            }
        } else if (this.message == null) {
            this.message = "操作失败";
        }

        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ResultData [code=");
        builder.append(this.code);
        builder.append(", message=");
        builder.append(this.getMessage());
        builder.append(", data=");
        builder.append(this.data);
        builder.append("]");
        return builder.toString();
    }
}
