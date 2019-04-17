package com.fanshuaiko.backage.entity.VO;

/**
 * @ClassName ScoreReturnVo
 * @Description 学生分数返回实体类
 * @Author fanshuaiko
 * @Date 19-4-17 上午11:46
 * @Version 1.0
 **/
public class ScoreReturnVo {
    private String testName; //考试名称
    private int studentNo; //学号
    private String studentName; //姓名
    private String majorName; //专业
    private int classCo; //班级
    private int totalScore; //总分

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public int getClassCo() {
        return classCo;
    }

    public void setClassCo(int classCo) {
        this.classCo = classCo;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "ScoreReturnVo{" +
                "testName='" + testName + '\'' +
                ", studentNo=" + studentNo +
                ", studentName='" + studentName + '\'' +
                ", majorName='" + majorName + '\'' +
                ", classCo=" + classCo +
                ", totalScore=" + totalScore +
                '}';
    }
}
