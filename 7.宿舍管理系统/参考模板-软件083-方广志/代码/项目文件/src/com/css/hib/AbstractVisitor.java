package com.css.hib;

/**
 * AbstractVisitor entity provides the base persistence definition of the
 * Visitor entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractVisitor implements java.io.Serializable {

	// Fields

	private Integer id;
	private String visitor;
	private Integer visitornumber;
	private String visitorname;
	private String visitorconnection;
	private String thing;
	private String watch;

	// Constructors

	/** default constructor */
	public AbstractVisitor() {
	}

	/** full constructor */
	public AbstractVisitor(String visitor, Integer visitornumber,
			String visitorname, String visitorconnection, String thing,
			String watch) {
		this.visitor = visitor;
		this.visitornumber = visitornumber;
		this.visitorname = visitorname;
		this.visitorconnection = visitorconnection;
		this.thing = thing;
		this.watch = watch;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVisitor() {
		return this.visitor;
	}

	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}

	public Integer getVisitornumber() {
		return this.visitornumber;
	}

	public void setVisitornumber(Integer visitornumber) {
		this.visitornumber = visitornumber;
	}

	public String getVisitorname() {
		return this.visitorname;
	}

	public void setVisitorname(String visitorname) {
		this.visitorname = visitorname;
	}

	public String getVisitorconnection() {
		return this.visitorconnection;
	}

	public void setVisitorconnection(String visitorconnection) {
		this.visitorconnection = visitorconnection;
	}

	public String getThing() {
		return this.thing;
	}

	public void setThing(String thing) {
		this.thing = thing;
	}

	public String getWatch() {
		return this.watch;
	}

	public void setWatch(String watch) {
		this.watch = watch;
	}

}