package com.my.beans;
//Class.forName("org.sqlite.JDBC")
//DriverManager.getConnection("jdbc:sqlite:MyEducateSystem.db") 
public class DataBase {
	private String name;//name=database
	private String driver;//driver=org.sqlite.JDBC   Class.forName(driver)
	private String protocal;//jdbc:sqlite
	private String dbUrl;//d:/chengxu/educateSystem.db  getConnection(protocal+dbUrl)
	@Override
	public String toString() {
		return "Basedata [name=" + name + ", driver=" + driver + ", protocal=" + protocal + ", dbUrl=" + dbUrl + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getProtocal() {
		return protocal;
	}
	public void setProtocal(String protocal) {
		this.protocal = protocal;
	}
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public DataBase(String name, String driver, String protocal, String dbUrl) {
		super();
		this.name = name;
		this.driver = driver;
		this.protocal = protocal;
		this.dbUrl = dbUrl;
	}
	public DataBase(){}
	
}
