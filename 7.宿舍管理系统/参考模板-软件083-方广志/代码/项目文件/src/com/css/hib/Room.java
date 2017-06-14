package com.css.hib;

/**
 * Room entity. @author MyEclipse Persistence Tools
 */
public class Room extends AbstractRoom implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Room() {
	}

	/** full constructor */
	public Room(Integer sid, String sname, Integer sroom, String smajor,
			Integer sclass, String semail) {
		super(sid, sname, sroom, smajor, sclass, semail);
	}

}
