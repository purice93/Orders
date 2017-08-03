package com.css.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.css.hib.BaseHibernateDAO;
import com.css.hib.Room;
import com.css.hib.RoomDAO;

public class Adminshowall {
	private Integer sid;
	private String sname;
	private Integer sroom;
	private String smajor;
	private Integer sclass;
	private String semail;

	private List list = new ArrayList();

	private String id;
	
	private Integer rsid;
	private String rsname;
	private Integer rsroom;
	private String rsmajor;
	private Integer rsclass;
	private String rsemail;

	private String studentid;
	
	private Integer fid;
	private String fname;
	private Integer froom;
	private String fmajor;
	private Integer fclass;
	private String femail;

	private int first;
	private int pre;
	private int next;
	private int last;
	private int currentPage;
	private int countPage;

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getPre() {
		return pre;
	}

	public void setPre(int pre) {
		this.pre = pre;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCountPage() {
		return countPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public Integer getFroom() {
		return froom;
	}

	public void setFroom(Integer froom) {
		this.froom = froom;
	}

	public String getFmajor() {
		return fmajor;
	}

	public void setFmajor(String fmajor) {
		this.fmajor = fmajor;
	}

	public Integer getFclass() {
		return fclass;
	}

	public void setFclass(Integer fclass) {
		this.fclass = fclass;
	}

	public String getFemail() {
		return femail;
	}

	public void setFemail(String femail) {
		this.femail = femail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRsid() {
		return rsid;
	}

	public void setRsid(Integer rsid) {
		this.rsid = rsid;
	}

	public String getRsname() {
		return rsname;
	}

	public void setRsname(String rsname) {
		this.rsname = rsname;
	}

	public Integer getRsroom() {
		return rsroom;
	}

	public void setRsroom(Integer rsroom) {
		this.rsroom = rsroom;
	}

	public String getRsmajor() {
		return rsmajor;
	}

	public void setRsmajor(String rsmajor) {
		this.rsmajor = rsmajor;
	}

	public Integer getRsclass() {
		return rsclass;
	}

	public void setRsclass(Integer rsclass) {
		this.rsclass = rsclass;
	}

	public String getRsemail() {
		return rsemail;
	}

	public void setRsemail(String rsemail) {
		this.rsemail = rsemail;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Integer getSroom() {
		return sroom;
	}

	public void setSroom(Integer sroom) {
		this.sroom = sroom;
	}

	public String getSmajor() {
		return smajor;
	}

	public void setSmajor(String smajor) {
		this.smajor = smajor;
	}

	public Integer getSclass() {
		return sclass;
	}

	public void setSclass(Integer sclass) {
		this.sclass = sclass;
	}

	public String getSemail() {
		return semail;
	}

	public void setSemail(String semail) {
		this.semail = semail;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String execute() {

		RoomDAO dao = new RoomDAO();
		// 查询，查数据
		List totalList = dao.findAll();
		if (totalList.size() % 5 == 0) {
			countPage = totalList.size() / 5;
		} else {
			countPage = totalList.size() / 5 + 1;
		}
		this.setFirst(1);
		this.setLast(this.getCountPage());
		System.out.println("=" + this.getCountPage());

		if (this.getCountPage() > 1) {
			if (this.getCurrentPage() == 1) {
				this.setPre(1);
				this.setNext(this.getCurrentPage() + 1);
			}
			if (this.getCountPage() > 1
					&& this.getCurrentPage() < this.getCountPage()) {
				this.setPre(this.getCurrentPage() - 1);
				this.setNext(this.getCurrentPage() + 1);
			}
			if (this.getCountPage() == this.getCountPage()) {
				this.setPre(this.getCurrentPage() - 1);
				this.setNext(this.getCountPage());
			}
		} else {
			this.setFirst(1);
			this.setPre(1);
			this.setNext(1);
			this.setLast(1);
		}

		String hql = "from Room";
		BaseHibernateDAO bhdao = new BaseHibernateDAO();
		Query query = bhdao.getSession().createQuery(hql);
		query.setFirstResult((this.getCurrentPage() - 1) * 5);
		query.setMaxResults(5);
		list = query.list();

		return "toTrue";
	}

	public String register() {
		

		Room room = new Room();
		

		room.setSid(getSid());
		room.setSname(getSname());
		room.setSroom(getSroom());
		room.setSmajor(getSmajor());
		room.setSclass(getSclass());
		room.setSemail(getSemail());

		RoomDAO roomdao = new RoomDAO();

		try {
			roomdao.save(room);
			list = roomdao.findAll();

			return "toTrue";
		} catch (Exception e) {
			return "toFa";
		}
		

		// 查询，查数据
		

	}

	public String deleteRoom() {

		// 得到请求参数id的值
		String id = ServletActionContext.getRequest().getParameter("id");
		// 通过编号值找到对应记录后删除
		int num = Integer.parseInt(id);
		RoomDAO roomdao = new RoomDAO();
		Room room = roomdao.findById(num);
		roomdao.delete(room);

		// 查询，重新查表
		list = roomdao.findAll();

		return "toTrue";
	}

	public String updateRoom() {

		// 得到请求参数id的值
		id = ServletActionContext.getRequest().getParameter("id");

		int num = Integer.parseInt(id);
		RoomDAO dao = new RoomDAO();
		Room r = dao.findById(num);

		rsid = r.getSid();
		rsname = r.getSname();
		rsroom = r.getSroom();
		rsmajor = r.getSmajor();
		rsclass = r.getSclass();
		rsemail = r.getSemail();

		return "toTrue";
	}

	public String finishUpdate() {
		int num = Integer.parseInt(studentid);
		RoomDAO dao = new RoomDAO();
		Room r = dao.findById(num);

		r.setSid(fid);
		r.setSname(fname);
		r.setSroom(froom);
		r.setSmajor(fmajor);
		r.setSclass(fclass);
		r.setSemail(femail);
		dao.merge(r);

		list = dao.findAll();

		return "toTrue";
	}
	
	public String testb() {

		return "toTrue";
	}

}
