/**
 * 
 * @program ������Ӧ���ݱ�ʵ�����ѡ�γ��࣬����dao�����ݿ������
 *
 */
 
package com.csms.entity;
 
public class SelectCourse {
    // �γ�ID
    private String courseID;
    // �γ�ID
    private String studengID;
    
    public String getCourseID() {
        return courseID;
    }
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
 
    public String getStudengID() {
        return studengID;
    }
 
    public void setStudengID(String studengID) {
        this.studengID = studengID;
    }    
}