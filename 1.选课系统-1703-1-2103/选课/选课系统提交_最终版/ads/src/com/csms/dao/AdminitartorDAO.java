package com.csms.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import com.csms.DBLink;
import com.csms.entity.Adminitartor;
 
/**
 * @program 管理员信息与数据库进行操作的类
 *
 */
 
public class AdminitartorDAO {
 
    // 管理员登录确认
    public Boolean loginConfirm(String name, String password) {
        // 实例化管理员对象
        Adminitartor admin = new Adminitartor();
 
        // 1.获得数据库连接对象
        Connection conn = DBLink.getConn();
 
        // 2.查询管理员的sql语句
        String sql = "SELECT Username,password FROM adminitrator";
 
        Statement stm = null;
        ResultSet rs = null;
        try {
            // 3.将sql语句发送，执行查询
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
            // 4.操作完毕，释放连接
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
     
    // 取得管理员的所有的身份信息
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