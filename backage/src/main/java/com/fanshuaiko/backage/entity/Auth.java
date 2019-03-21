package com.fanshuaiko.backage.entity;

import java.io.Serializable;

/**
 * @ClassName Auth
 * @Description 登录认证实体类
 * @Author fanshuaiko
 * @Date 19-3-20 下午2:20
 * @Version 1.0
 **/
public class Auth implements Serializable {

    private int id;//主键
    private String username;//学号或者教师编号
    private String password;//密码
    private String roleId;//角色id
    private String flag;//标志，1.学生，2.教师
    private String salt;//盐值

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleId='" + roleId + '\'' +
                ", flag='" + flag + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
