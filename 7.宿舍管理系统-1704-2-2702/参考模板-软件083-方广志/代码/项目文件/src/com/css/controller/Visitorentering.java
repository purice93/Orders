package com.css.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.css.hib.BaseHibernateDAO;
import com.css.hib.Room;
import com.css.hib.RoomDAO;
import com.css.hib.Visitor;
import com.css.hib.VisitorDAO;

public class Visitorentering {


	private String visitor;
	private Integer visitornumber;
	private String visitorname;
	private String visitorconnection;
	private String thing;
	private String watch;
	
	private int first;
	private int pre;
	private int next;
	private int last;
	private int currentPage;
	private int countPage;

	private List list=new ArrayList();

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


	public String getVisitor() {
		return visitor;
	}


	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}


	public Integer getVisitornumber() {
		return visitornumber;
	}


	public void setVisitornumber(Integer visitornumber) {
		this.visitornumber = visitornumber;
	}


	public String getVisitorname() {
		return visitorname;
	}


	public void setVisitorname(String visitorname) {
		this.visitorname = visitorname;
	}


	public String getVisitorconnection() {
		return visitorconnection;
	}


	public void setVisitorconnection(String visitorconnection) {
		this.visitorconnection = visitorconnection;
	}


	public String getThing() {
		return thing;
	}


	public void setThing(String thing) {
		this.thing = thing;
	}


	public String getWatch() {
		return watch;
	}


	public void setWatch(String watch) {
		this.watch = watch;
	}


	public List getList() {
		return list;
	}


	public void setList(List list) {
		this.list = list;
	}


	public String execute(){
		
		Visitor visit=new Visitor();
		
		visit.setVisitor(getVisitor());
		visit.setVisitornumber(getVisitornumber());
		visit.setVisitorname(getVisitorname());
		visit.setVisitorconnection(getVisitorconnection());
		visit.setThing(getThing());
		visit.setWatch(getWatch());
		
		VisitorDAO dao=new VisitorDAO();
		
		try {
			dao.save(visit);
			List totalList = dao.findAll();
			
			return "toTrue";
		} catch (Exception e) {
			return "toFa";
		}
	
	}
	
	
	public String visitorentering(){
		VisitorDAO dao=new VisitorDAO();
		//查询，查数据
		list = dao.findAll();
		
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

		String hql = "from Visitor";
		BaseHibernateDAO bhdao = new BaseHibernateDAO();
		Query query = bhdao.getSession().createQuery(hql);
		query.setFirstResult((this.getCurrentPage() - 1) * 5);
		query.setMaxResults(5);
		list = query.list();
		
		return "toTrue";
	}
	
	public String deleteVisitor() {

		// 得到请求参数id的值
		String id = ServletActionContext.getRequest().getParameter("id");
		// 通过编号值找到对应记录后删除
		int num = Integer.parseInt(id);
		VisitorDAO dao = new VisitorDAO();
		Visitor u = dao.findById(num);
		dao.delete(u);

		// 查询，重新查表
		list = dao.findAll();

		return "toTrue";
	}
	
	public String testa() {

		return "toTrue";
	}
}
