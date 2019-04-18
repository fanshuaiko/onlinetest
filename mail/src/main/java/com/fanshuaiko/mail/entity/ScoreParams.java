package com.fanshuaiko.mail.entity;

/**
 * @ClassName ScoreParams
 * @Description 发送分数邮件参数
 * @Author fanshuaiko
 * @Date 19-4-18 下午5:43
 * @Version 1.0
 **/
public class ScoreParams {
    private String toMailAddress;//收件人
    private String testName;//考试名称
    private int studentCount;//参考人数
    private int totalScore;//总分
    private int maxScore;//最高分
    private double averageScore;//平均分
    private double passRate;//及格率

    public String getToMailAddress() {
        return toMailAddress;
    }

    public void setToMailAddress(String toMailAddress) {
        this.toMailAddress = toMailAddress;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public double getPassRate() {
        return passRate;
    }

    public void setPassRate(double passRate) {
        this.passRate = passRate;
    }

    @Override
    public String toString() {
        return "ScoreParams{" +
                "toMailAddress='" + toMailAddress + '\'' +
                ", testName='" + testName + '\'' +
                ", studentCount=" + studentCount +
                ", totalScore=" + totalScore +
                ", maxScore=" + maxScore +
                ", averageScore=" + averageScore +
                ", passRate=" + passRate +
                '}';
    }
}
