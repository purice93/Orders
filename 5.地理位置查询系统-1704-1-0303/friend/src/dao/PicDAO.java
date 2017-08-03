package dao;

import java.util.List;

import entity.Pic;

public interface PicDAO {
//	public void delete(int id) throws Exception;
	public void save(Pic pic) throws Exception;
	public List<Pic> findByUserId(int userId)throws Exception;
}
