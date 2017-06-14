package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDAO;
import dao.PicDAO;
import entity.Pic;

public class PicDAOImpl extends BaseDAO implements PicDAO{

//	public void delete(int userId) throws Exception {
//		Connection conn = null;
//		try {
//			conn = getConnection();
//			PreparedStatement prep = conn
//					.prepareStatement("delete from f_pic where userId=?");
//			prep.setInt(1, userId);
//			prep.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			closeConnection();
//		}
//
//		
//	}

	public void save(Pic pic) throws Exception {
		Connection conn=null;
		try {
			conn=getConnection();
			PreparedStatement prep=conn.prepareStatement("insert into f_pic(picName,userId) values (?,?)");
			prep.setString(1, pic.getPicName());
			prep.setInt(2, pic.getUserId());
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			closeConnection();
		}

	}
	public List<Pic>findByUserId(int userId)throws Exception{
		Connection conn=null;
		List<Pic> pics=new ArrayList<Pic>();
		try {
			conn=getConnection();
			PreparedStatement prep=conn.prepareCall("select * from f_pic where userId=?");
			prep.setInt(1, userId);
			ResultSet rs=prep.executeQuery();
			while(rs.next()){
				Pic pic=new Pic();
				pic.setId(rs.getInt("id"));
				pic.setPicName(rs.getString("picName"));
				pic.setUserId(userId);
				pics.add(pic);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			closeConnection();
		}
		
		return pics;
	}

}
