package com.csms.entity;
 
/**
 *
 * @program ������Ӧ���ݱ�ʵ��Ĺ���Ա�࣬����dao�����ݿ������
 *
 */
public class Adminitartor {
    // ��¼��
    private String loginName;
    // ��¼����
    private String loginPSD;
    // ����Ա����
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