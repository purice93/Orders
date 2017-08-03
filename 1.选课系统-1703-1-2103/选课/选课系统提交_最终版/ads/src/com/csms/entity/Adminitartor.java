package com.csms.entity;
 
/**
 *
 * @program 创建对应数据表实体的管理员类，方便dao与数据库的连接
 *
 */
public class Adminitartor {
    // 登录名
    private String loginName;
    // 登录密码
    private String loginPSD;
    // 管理员名字
    private String adminName;
 
    
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
 
    public String getAdminName() {
        return adminName;
    }
 
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
 
}