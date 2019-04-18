package com.fanshuaiko.backage.entity.mail;

/**
 * @ClassName ScoreParams
 * @Description 发送分数邮件参数
 * @Author fanshuaiko
 * @Date 19-4-18 下午5:43
 * @Version 1.0
 **/
public class ScoreParams {
    private Long studentNo;//学号
    private String name;//姓名
    private String toMailAddress;//收件人
    private String testName;//考试名称
    private int studentCount;//参考人数
    private int totalScore;//个人总分
    private int maxScore;//最高分
    private double averageScore;//平均分
    private double passRate;//及格率
    private int sort;//排名

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Long getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(Long studentNo) {
        this.studentNo = studentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ScoreParams{" +
                "studentNo=" + studentNo +
                ", name='" + name + '\'' +
                ", toMailAddress='" + toMailAddress + '\'' +
                ", testName='" + testName + '\'' +
                ", studentCount=" + studentCount +
                ", totalScore=" + totalScore +
                ", maxScore=" + maxScore +
                ", averageScore=" + averageScore +
                ", passRate=" + passRate +
                ", sort=" + sort +
                '}';
    }
}
