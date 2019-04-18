package com.fanshuaiko.backage.entity.VO;

import java.util.HashMap;

/**
 * @ClassName TestAnolyze
 * @Description 考试分析数据
 * @Author fanshuaiko
 * @Date 19-4-17 下午6:20
 * @Version 1.0
 **/
public class TestAnalyze {
    private Long testNo;//考试编号
    private String testName;//考试名称
    private int studentCount;//参考人数
    private int totalScore;//总分
    private int maxScore;//最高分
    private int minScore;//最低分
    private int passCount;//及格人数
    private int unPassCount;//不及格人数
    private double averageScore;//平均分
    private double passRate;//及格率
    private HashMap<Integer,Integer> eachPeriodScore;//各分数段的人数，10分为一个阶段

    public Long getTestNo() {
        return testNo;
    }

    public void setTestNo(Long testNo) {
        this.testNo = testNo;
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

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
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

    public HashMap<Integer, Integer> getEachPeriodScore() {
        return eachPeriodScore;
    }

    public void setEachPeriodScore(HashMap<Integer, Integer> eachPeriodScore) {
        this.eachPeriodScore = eachPeriodScore;
    }

    public int getPassCount() {
        return passCount;
    }

    public void setPassCount(int passCount) {
        this.passCount = passCount;
    }

    public int getUnPassCount() {
        return unPassCount;
    }

    public void setUnPassCount(int unPassCount) {
        this.unPassCount = unPassCount;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "TestAnalyze{" +
                "testNo=" + testNo +
                ", testName='" + testName + '\'' +
                ", studentCount=" + studentCount +
                ", totalScore=" + totalScore +
                ", maxScore=" + maxScore +
                ", minScore=" + minScore +
                ", passCount=" + passCount +
                ", unPassCount=" + unPassCount +
                ", averageScore=" + averageScore +
                ", passRate=" + passRate +
                ", eachPeriodScore=" + eachPeriodScore +
                '}';
    }
}
