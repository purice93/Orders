/**
 * @program  学生实体与数据库进行操作的类
 *
 */
package com.csms.dao;
 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
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
                // TODO Auto-generated catch block
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
        String sql = "SELECT Sno,Spassword,Sname,Sx FROM student WHERE Sno=? AND Spassword=?";
 
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
                student.setStuSdept(rs.getString("Sx"));
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return student;
    }

    // 录入学生信息
    public void insertStudentInformation(Student student){
        Connection conn = DBLink.getConn();
        String sql = "INSERT INTO student (Sno,Spassword,Sname,Sx) VALUES(?,?,?,?)";
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, student.getLoginName());
            ptm.setString(2, student.getLoginPSD());
            ptm.setString(3, student.getStuName());
            ptm.setString(4, student.getStuSdept());
            ptm.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(ptm!=null)
                    ptm.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
     
    // 删除学生信息
    public void deleteStudentInformation(String id){
        Connection conn = DBLink.getConn();
        String sql = "DELETE FROM student WHERE stuID=?";
        PreparedStatement ptm = null;
        try{
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, id);
            ptm.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(ptm != null){
                    ptm.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}