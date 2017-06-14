package com.csms.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import com.csms.DBLink;
import com.csms.entity.Adminitartor;
 
/**
 * @program ����Ա��Ϣ�����ݿ���в�������
 *
 */
 
public class AdminitartorDAO {
 
    // ����Ա��¼ȷ��
    public Boolean loginConfirm(String name, String password) {
        // ʵ��������Ա����
        Adminitartor admin = new Adminitartor();
 
        // 1.������ݿ����Ӷ���
        Connection conn = DBLink.getConn();
 
        // 2.��ѯ����Ա��sql���
        String sql = "SELECT Username,password FROM adminitrator";
 
        Statement stm = null;
        ResultSet rs = null;
        try {
            // 3.��sql��䷢�ͣ�ִ�в�ѯ
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                admin.setLoginName(rs.getString("Username"));
                admin.setLoginPSD(rs.getString("password"));
                if (name.equals(admin.getLoginName()) && password.equals(admin.getLoginPSD())) {
                    return true;
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4.������ϣ��ͷ�����
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
     
    // ȡ�ù���Ա�����е������Ϣ
    public Adminitartor getInformation(String userName, String password){
        Adminitartor admin = null;
        Connection conn = DBLink.getConn();
        String sql = "SELECT Username,trueName FROM adminitrator WHERE Username=? AND password=?";
        PreparedStatement ptm = null;
        ResultSet rs = null;
         
        try{
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, userName);
            ptm.setString(2, password);
            rs = ptm.executeQuery();
            while(rs.next()){
                admin = new Adminitartor();
                admin.setAdminName(rs.getString("Username"));
            }
            rs.close();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(ptm!=null){
                    ptm.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }      
        return admin;      
    }
}