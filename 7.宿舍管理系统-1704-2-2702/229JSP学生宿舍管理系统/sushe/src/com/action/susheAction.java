package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.dao.TAdminDAO;
import com.dao.TSusheDAO;
import com.model.TAdmin;
import com.model.TSushe;
import com.opensymphony.xwork2.ActionSupport;
import com.util.Pagination;

public class susheAction extends ActionSupport
{
	private int id;
	private String fangjianhao;
	 
	private String message;
	private String path;
	

	private TSusheDAO susheDAO;
	
	
	public String susheAdd()
	{
		TSushe sushe=new TSushe();
		sushe.setFangjianhao(fangjianhao);
		sushe.setDel("no");
		susheDAO.save(sushe);
		this.setMessage("操作成功");
		this.setPath("susheMana.action");
		return "succeed";
	}
	
	public String susheDel()
	{
		TSushe sushe=susheDAO.findById(id);
		sushe.setDel("yes");
		susheDAO.attachDirty(sushe);
		this.setMessage("删除成功");
		this.setPath("susheMana.action");
		return "succeed";
	}
	
	
	public String susheMana()
	{
		String sql="from TSushe where del='no'";
		List susheList=susheDAO.getHibernateTemplate().find(sql);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("susheList", susheList);
		return ActionSupport.SUCCESS;
	}

	public String getFangjianhao()
	{
		return fangjianhao;
	}

	public void setFangjianhao(String fangjianhao)
	{
		this.fangjianhao = fangjianhao;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public TSusheDAO getSusheDAO()
	{
		return susheDAO;
	}

	public void setSusheDAO(TSusheDAO susheDAO)
	{
		this.susheDAO = susheDAO;
	}

	
}
