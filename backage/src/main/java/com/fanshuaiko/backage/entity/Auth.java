package com.fanshuaiko.backage.entity;

/**
 * @ClassName Auth
 * @Description 登录认证实体类
 * @Author fanshuaiko
 * @Date 19-3-20 下午2:20
 * @Version 1.0
 **/
public class Auth {
    private int id;//主键
    private String no;//学号或者教师编号
    private String password;//密码
    private String roleId;//角色id
    private String flag;//标志，1.学生，2.教师

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", password='" + password + '\'' +
                ", roleId='" + roleId + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
