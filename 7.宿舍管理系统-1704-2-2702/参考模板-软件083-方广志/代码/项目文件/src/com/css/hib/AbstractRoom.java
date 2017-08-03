package com.css.hib;

/**
 * AbstractRoom entity provides the base persistence definition of the Room
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRoom implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sid;
	private String sname;
	private Integer sroom;
	private String smajor;
	private Integer sclass;
	private String semail;

	// Constructors

	/** default constructor */
	public AbstractRoom() {
	}

	/** full constructor */
	public AbstractRoom(Integer sid, String sname, Integer sroom,
			String smajor, Integer sclass, String semail) {
		this.sid = sid;
		this.sname = sname;
		this.sroom = sroom;
		this.smajor = smajor;
		this.sclass = sclass;
		this.semail = semail;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Integer getSroom() {
		return this.sroom;
	}

	public void setSroom(Integer sroom) {
		this.sroom = sroom;
	}

	public String getSmajor() {
		return this.smajor;
	}

	public void setSmajor(String smajor) {
		this.smajor = smajor;
	}

	public Integer getSclass() {
		return this.sclass;
	}

	public void setSclass(Integer sclass) {
		this.sclass = sclass;
	}

	public String getSemail() {
		return this.semail;
	}

	public void setSemail(String semail) {
		this.semail = semail;
	}

}