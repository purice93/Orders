package com.csms.entity;
 
/**
 * 
 * @program ������Ӧ���ݱ�ʵ���ѧ���࣬����dao�����ݿ������
 *
 */
 
public class Student {
	// ��¼��||ѧ��ID
    private String loginName;
    // ��¼����
    private String loginPSD;
    // ѧ������
    private String stuName;
    // ѧ������ϵԺ
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