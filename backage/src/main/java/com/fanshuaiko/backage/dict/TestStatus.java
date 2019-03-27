package com.fanshuaiko.backage.dict;

/**
 * @ClassName TestStatus
 * @Description 考试状态字典类
 * @Author fanshuaiko
 * @Date 19-3-27 下午4:46
 * @Version 1.0
 **/
public enum TestStatus {

    NotBegin("0", "未开始"), Running("1", "进行中"), Finished("2", "已结束");

    private String CODE;
    private String NAME;

    TestStatus(String CODE, String NAME) {
        this.CODE = CODE;
        this.NAME = NAME;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    @Override
    public String toString() {
        return "TestStatus{" +
                "CODE='" + CODE + '\'' +
                ", NAME='" + NAME + '\'' +
                '}';
    }}
