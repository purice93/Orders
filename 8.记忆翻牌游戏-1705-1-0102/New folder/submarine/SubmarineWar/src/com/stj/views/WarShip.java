package com.stj.views;

import javax.swing.*;
import java.awt.*;

/*
 * 战舰对象
 */

public class WarShip 
{
	private int beginX ;  //初始位置和高度，宽度
	private int beginY ;
	private int width ;
	private int height;
	//private Color color = Color.GREEN;
	private   int bombNum  = 5;  //炸弹数量
	private   MyPanel panel;
	private   Image image;  //图片对象

	
	public WarShip(MyPanel panel)
	{
		this.panel = panel;
		 image = Toolkit.getDefaultToolkit().getImage("imgs/战舰.png");
		 image = new ImageIcon(image).getImage();
		 this.width = image.getWidth(panel);
		 this.height = image.getHeight(panel);
		 this.beginX = (panel.getWidth() - this.getWidth()) / 2;
		 this.beginY =  150 - this.getHeight() + 5;
	}

	public int getBeginX() {
		return beginX;
	}

	public void setBeginX(int beginX) {
		this.beginX = beginX;
	}

	public int getBeginY() {
		return beginY;
	}

	public void setBeginY(int beginY) {
		this.beginY = beginY;
	}
	
	

	
	public void drawShip(Graphics2D g)
	{		
		g.drawImage(image,this.beginX ,this.beginY,this.panel);
	}
	
	public void moveRight()
	{
		this.beginX +=5;
		if((this.beginX + this.width) > this.panel.getWidth())
		{
			System.out.println("panel:"+this.panel.getWidth());
			this.beginX = this.panel.getWidth() - this.width;
		}
		
		
	}
	
	public void moveLeft()
	{
		this.beginX -= 5;
		if(this.beginX < 0)
		{
			this.beginX = 0;
		}
		
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}



	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getBombNum() {
		return bombNum;
	}

	public void setBombNum(int bombNum) {
		this.bombNum = bombNum;
	}
	


	
}
