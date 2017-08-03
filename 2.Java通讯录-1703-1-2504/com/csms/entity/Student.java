package com.csms.entity;
 
/**
 * 
 * @program 创建对应数据表实体的学生类，方便dao与数据库的连接
 *
 */
 
public class Student {
	// 登录名||学生ID
    private String loginName;
    // 登录密码
    private String loginPSD;
    // 学生姓名
    private String stuName;
    // 学生所属系院
    private String stuSdept;
 
    
    public String getLoginName() {
        return loginName;
    }
 
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
 
    public String getLoginPSD() {
        return loginPSD;
    }
 
    public void setLoginPSD(String loginPSD) {
        this.loginPSD = loginPSD;
    }
 
    public String getStuName() {
        return stuName;
    }
 
    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
 
    public String getStuSdept() {
        return stuSdept;
    }
 
    public void setStuSdept(String stuSdept) {
        this.stuSdept = stuSdept;
    }

}