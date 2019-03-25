package com.fanshuaiko.backage.dict;

/**
 * @ClassName QuestionType
 * @Description 题型字典类
 * @Author fanshuaiko
 * @Date 19-3-22 下午4:46
 * @Version 1.0
 **/
public enum QuestionType {
    SingleChoice("1", "单选题"), JudgeChoice("2", "【判断题"), MultipleChoice("3", "多选题"), Subjective("4","主观题");

    private String CODE;
    private String NAME;

    QuestionType(String code, String name) {
        this.CODE = code;
        this.NAME = name;
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
        return "QuestionType{" +
                "CODE=" + CODE +
                ", NAME='" + NAME + '\'' +
                '}';
    }
}
