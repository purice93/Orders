package com.model;
import java.awt.Image;
import java.util.*;

import javax.swing.ImageIcon;
public class PlayImage {
	
	ArrayList<Image> al;
	ArrayList<Image> almgh;
	Image image=new ImageIcon("Image/mgh.png").getImage();
	public PlayImage()
	{
		al=new ArrayList<Image>();
		almgh=new ArrayList<Image>();
		initAlImage();
	}
	public void initAlImage()
	{
		//初始化动作图
		al.add(new ImageIcon("Image/gameP1_1.png").getImage());
		al.add(new ImageIcon("Image/gameP1_2.png").getImage());
		al.add(new ImageIcon("Image/gameP1_3.png").getImage());
		al.add(new ImageIcon("Image/gameP1_4.png").getImage());
		al.add(new ImageIcon("fz_image/d.png").getImage());
		al.add(new ImageIcon("fz_image/d2.png").getImage());
		al.add(new ImageIcon("fz_image/d3.png").getImage());
		al.add(new ImageIcon("fz_image/d4.png").getImage());
		al.add(new ImageIcon("Image/008.png").getImage());
		al.add(new ImageIcon("Image/009.png").getImage());
		al.add(new ImageIcon("fz_image/d5.png").getImage());
		al.add(new ImageIcon("fz_image/d6.png").getImage());
		al.add(new ImageIcon("Image/1.png").getImage());
		al.add(new ImageIcon("fz_image/d7.png").getImage());
		al.add(image);
		al.add(new ImageIcon("Image/jn2.png").getImage());
	}
	public ArrayList<Image> getAlImage()
	{
		return al;
	}
	public ArrayList<Image> getAlMghImage()
	{
		return almgh;
	}
}
