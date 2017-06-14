/**
 * @program  ѧ��ʵ�������ݿ���в�������
 *
 */
package com.csms.dao;
 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
import com.csms.entity.*;
 
import com.csms.DBLink;
 
public class StudentDAO {
    // ѧ����¼��Ϣ��ѯ
    public Boolean loginConfirm(String name, String password) {
        // ����ѧ������
        Student student = new Student();
 
        // 1.ȡ�����ݿ����ӵĶ���conn
        Connection conn = DBLink.getConn();
        // 2. ��ѯȫ��ѧ����sql���
        String sql = "SELECT Sno,Spassword FROM student";
 
        Statement stm = null;
        ResultSet rs = null;
        try {
            // 3.����ѯ��䷢�͸����ݿ�,ִ�в�ѯ����
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
 
            // ������¼���в�ѯ
            while (rs.next()) {
                student.setLoginName(rs.getString("Sno"));
                student.setLoginPSD(rs.getString("Spassword"));
                if (student.getLoginName().equals(name) && student.getLoginPSD().equals(password)) {
                    return true;
                }
            }
            // �رղ�ѯָ������
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. ������ɣ��ر�����
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
 
    // ��ѯĳ��ѧ����������Ϣ�����ڵ�¼���ⴰ����ʾѧ��������
    public Student searchStudentAll(String loginName, String loginPsd) {
        // ����ѧ������
        Student student = null;
 
        // 1.ȡ�����ݿ����ӵĶ���conn
        Connection conn = DBLink.getConn();
 
        // 2. ��ѯȫ��ѧ����sql���
        String sql = "SELECT Sno,Spassword,Sname,Sx FROM student WHERE Sno=? AND Spassword=?";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            // 3.����ѯ��䷢�͸����ݿ�,ִ�в�ѯ����
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, loginName);
            ptm.setString(2, loginPsd);
            rs = ptm.executeQuery();
 
            student = new Student();
            // ������¼���в�ѯ
            while (rs.next()) {
                student.setLoginName(rs.getString("Sno"));
                student.setStuName(rs.getString("Sname"));
                student.setLoginPSD(rs.getString("Spassword"));
                student.setStuSdept(rs.getString("Sx"));
            }
 
            // �رղ�ѯָ������
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. ������ɣ��ر�����
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

    // ¼��ѧ����Ϣ
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
     
    // ɾ��ѧ����Ϣ
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