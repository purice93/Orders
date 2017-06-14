package com.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.DBLink;
import com.entity.User;

/**
 * 数据库操作类
 *
 */
public class UserDao {
	
	// 向数据库表user中插入数据
	public void insertUser(User user) {
		Connection conn = DBLink.getConn();
        String sql = "INSERT INTO user (username,udate,utime,tcode) VALUES (?,?,?,?)";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
 
        try {
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, user.getUsername());
            ptm.setString(2, user.getuDate());
            ptm.setString(3, user.getuTime());
            ptm.setString(4, user.getTcode());
            ptm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
 
        }
	}
	
	// 向数据库表grade中插入数据
	public void insertGrade(User user) {
		Connection conn = DBLink.getConn();
        String sql = "INSERT INTO grade (username,grade) VALUES (?,?)";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
 
        try {
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, user.getUsername());
            ptm.setInt(2, user.getGrade());
            ptm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
 
        }
	}

	// 从数据库表grade，通过用户名查找对应的分数
	public String selectScoreByUsername(String username) throws NumberFormatException, SQLException {
		// TODO Auto-generated method stub
		
		Connection conn = DBLink.getConn();
        String sql = "SELECT grade FROM grade WHERE username = (?)";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String grade = null;
        try {
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, username);
            rs = ptm.executeQuery();
            while (rs.next()) {
            	grade = rs.getString("grade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
 
        }
        return grade;
	}

	public void insertHaveScore(List<List<String>> haveScoreLists) {
		// TODO Auto-generated method stub
		Connection conn = DBLink.getConn();
        String sql = "INSERT INTO havegrade (username,stepnum,date,time,grade) VALUES (?,?,?,?,?)";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
 
        for(List<String> list: haveScoreLists) {
            try {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, list.get(0));
                ptm.setString(2, list.get(1));
                ptm.setString(3, list.get(2));
                ptm.setString(4, list.get(3));
                ptm.setInt(5, Integer.parseInt(list.get(4)));
                ptm.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
     
            }
        }
	}

	public void insertNoScore(List<List<String>> noScoreLists) {
		// TODO Auto-generated method stub
		Connection conn = DBLink.getConn();
        String sql = "INSERT INTO nograde (username,stepnum,date,time) VALUES (?,?,?,?)";
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
 
        for(List<String> list: noScoreLists) {
            try {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, list.get(0));
                ptm.setString(2, list.get(1));
                ptm.setString(3, list.get(2));
                ptm.setString(4, list.get(3));
                ptm.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
     
            }
        }
	}

	//创建操作数据库
	public void createDateBase(List<String> list) {
		// TODO Auto-generated method stub
		Connection conn = DBLink.getConn();
		
		String sqlstr1 = "DROP TABLE IF EXISTS `stepTable`";
		String sqlstr2 = "CREATE TABLE `stepTable`("; 
		for(int i=0;i<list.size()-1;i++) {
			sqlstr2+=list.get(i)+" VARCHAR(20),";     
		}
		sqlstr2+= list.get(list.size()-1)+" VARCHAR(20)";  
		sqlstr2+= " )ENGINE=InnoDB DEFAULT CHARSET=utf8;"; 
		
		PreparedStatement ptm = null;
		try {
            ptm = conn.prepareStatement(sqlstr1);
            ptm.execute();
            ptm = conn.prepareStatement(sqlstr2);
            ptm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
 
        }
	}

	public void insertStep(List<List<String>> opList, List<String> list) {
		// TODO Auto-generated method stub
		Connection conn = DBLink.getConn();
        String sqlstr = "INSERT INTO stepTable (";
        for(int i=0;i<list.size()-1;i++) {
			sqlstr+=list.get(i)+",";     
		}
		sqlstr+= list.get(list.size()-1);  
		sqlstr+= " ) VALUES (";
        
		for(int i=0;i<list.size()-1;i++) {
			sqlstr+="?,";     
		}
		sqlstr+= "?)";  
 
        PreparedStatement ptm = null;
        ResultSet rs = null;
 
        for(List<String> list2: opList) {
            try {
                ptm = conn.prepareStatement(sqlstr);
                
                for(int i=0;i<list.size();i++) {
                    ptm.setString(i+1, list2.get(i)); 
        		}
                ptm.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
     
            }
        }
	}

}