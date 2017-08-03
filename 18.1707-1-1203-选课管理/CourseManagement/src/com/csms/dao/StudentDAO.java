/**
 * @program  学生实体与数据库进行操作的类
 *
 */
package com.csms.dao;
 
import java.sql.*;
import com.csms.entity.*;
import com.csms.DBLink;

public class StudentDAO {
    // 学生登录信息查询
    public Boolean loginConfirm(String name, String password) {
        // 创建学生对象
        Student student = new Student();
 
        // 1.取得数据库连接的对象conn
        Connection conn = DBLink.getConn();
        // 2. 查询全体学生的sql语句
        String sql = "SELECT Sno,Spassword FROM student";
 
        Statement stm = null;
        ResultSet rs = null;
        try {
            // 3.将查询语句发送给数据库,执行查询操作
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
 
            // 逐条记录进行查询
            while (rs.next()) {
                student.setLoginName(rs.getString("Sno"));
                student.setLoginPSD(rs.getString("Spassword"));
                if (student.getLoginName().equals(name) && student.getLoginPSD().equals(password)) {
                    return true;
                }
            }
            // 关闭查询指针链接
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 操作完成，关闭连接
            try {
                if (stm != null)
                    stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
 
    // 查询某个学生的所有信息，用于登录标题窗口显示学生的姓名
    public Student searchStudentAll(String loginName, String loginPsd) {
        // 设置学生对象
        Student student = null;
 
        // 1.取得数据库连接的对象conn
        Connection conn = DBLink.getConn();
 
        // 2. 查询全体学生的sql语句
        String sql = "SELECT Sno,Spassword,Sname FROM student WHERE Sno=? AND Spassword=?";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            // 3.将查询语句发送给数据库,执行查询操作
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, loginName);
            ptm.setString(2, loginPsd);
            rs = ptm.executeQuery();
 
            student = new Student();
            // 逐条记录进行查询
            while (rs.next()) {
                student.setLoginName(rs.getString("Sno"));
                student.setStuName(rs.getString("Sname"));
                student.setLoginPSD(rs.getString("Spassword"));
            }
 
            // 关闭查询指针链接
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 操作完成，关闭连接
            try {
                if (ptm != null)
                    ptm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student;
    }

	public static String getNameById(String studengID) {
		// 设置学生对象
        Student student = null;
 
        // 1.取得数据库连接的对象conn
        Connection conn = DBLink.getConn();
 
        // 2. 查询全体学生的sql语句
        String sql = "SELECT Sname FROM student WHERE Sno=?";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            // 3.将查询语句发送给数据库,执行查询操作
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, studengID);
            rs = ptm.executeQuery();
 
            student = new Student();
            // 逐条记录进行查询
            while (rs.next()) {
                student.setLoginName(rs.getString("Sno"));
            }
 
            // 关闭查询指针链接
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 操作完成，关闭连接
            try {
                if (ptm != null)
                    ptm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student.getStuName();
	}

    
}