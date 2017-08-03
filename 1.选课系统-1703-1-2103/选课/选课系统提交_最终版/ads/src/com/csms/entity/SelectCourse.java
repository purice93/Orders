/**
 * 
 * @program 创建对应数据表实体的已选课程类，方便dao与数据库的连接
 *
 */
 
package com.csms.entity;
 
public class SelectCourse {
    // 课程ID
    private String courseID;
    // 课程ID
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