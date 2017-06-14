package com.css.hib;

/**
 * Visitor entity. @author MyEclipse Persistence Tools
 */
public class Visitor extends AbstractVisitor implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Visitor() {
	}

	/** full constructor */
	public Visitor(String visitor, Integer visitornumber, String visitorname,
			String visitorconnection, String thing, String watch) {
		super(visitor, visitornumber, visitorname, visitorconnection, thing,
				watch);
	}

}
