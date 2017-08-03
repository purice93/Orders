package com.csms.entity;
 
/**
 * 
 * @program 创建对应数据表实体的课程类，方便dao与数据库的连接
 *
 */
public class Course {
    // 课程ID，唯一标识
    private String courID;
    // 课程名字
    private String courName;
 
    
    public String getCourID() {
        return courID;
    }
 
    public void setCourID(String courID) {
        this.courID = courID;
    }
 
    public String getCourName() {
        return courName;
    }
 
    public void setCourName(String courName) {
        this.courName = courName;
    }
}