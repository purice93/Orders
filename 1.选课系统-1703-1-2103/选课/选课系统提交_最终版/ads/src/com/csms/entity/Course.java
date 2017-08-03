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
    // 课程总数
    private int Csum;
    // 已选择数量
    private int Cselected;
 
    
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

	public int getCsum() {
		return Csum;
	}

	public void setCsum(int csum) {
		Csum = csum;
	}

	public int getCselected() {
		return Cselected;
	}

	public void setCselected(int cselected) {
		Cselected = cselected;
	}

}