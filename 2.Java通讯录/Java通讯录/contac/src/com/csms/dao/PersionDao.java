package com.csms.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import javax.swing.JTextField;
 
import com.csms.DBLink;
import com.csms.entity.Persion;
 

 
public class PersionDao {
	public void insertPersion(Persion ca) {
		Connection conn = DBLink.getConn();
        String sql = "INSERT INTO persion (name,phoneNumber,address,email) VALUES (?,?,?,?)";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
 
        try {
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, ca.getName());
            ptm.setString(2, ca.getPhoneNumber());
            ptm.setString(3, ca.getAddress());
            ptm.setString(4, ca.getEmail());
            ptm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
 
        }
	}

}