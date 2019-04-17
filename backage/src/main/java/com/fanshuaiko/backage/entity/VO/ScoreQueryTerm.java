package com.fanshuaiko.backage.entity.VO;

/**
 * @ClassName ScoreQueryTerm
 * @Description 多参数分页查询分数实体类
 * @Author fanshuaiko
 * @Date 19-4-17 上午11:37
 * @Version 1.0
 **/
public class ScoreQueryTerm {
    private String majorName; //专业名称
    private int classCo; //班级
    private String studentName; //学生姓名
    private int studentNo; //学号

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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    @Override
    public String toString() {
        return "ScoreQueryTerm{" +
                "majorName='" + majorName + '\'' +
                ", classCo=" + classCo +
                ", studentName='" + studentName + '\'' +
                ", studentNo=" + studentNo +
                '}';
    }
}
