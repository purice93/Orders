package com.control;
import java.util.*;

public class Get {
	ArrayList<Integer> al;
	private int zt;
	private int x;
	private int y;
	public Get()
	{
		al=new ArrayList<Integer>();
		
	}
	public ArrayList<Integer> getAl() {
	
		al.clear();
		//����״̬
		
		al.add(getZt());
		al.add(getX());
		al.add(getY());
		return al;
	}

	public int getZt() {
		return zt;
	}
	public void setZt(int zt) {
		this.zt = zt;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	
}
