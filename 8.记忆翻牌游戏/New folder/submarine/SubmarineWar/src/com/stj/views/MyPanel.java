package com.stj.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicGraphicsUtils;


public class MyPanel extends JPanel implements KeyListener,MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WarShip   ship;              //战舰
	private int       liveNum     = 3;   //命数默认为3
	private int       pass        = 0;   //通关数默认为0
	private int       higncore    = 0;   //最高分默认为0
	private int       score       = 0;   //玩家得分 默认为0
	private int       delay       = 3000;//战舰装弹速度，默认为3000毫秒
	private boolean   suspendFlag = false;  //暂停标记
	private boolean   isRunning   = false;  //判断游戏是否正在运行标记
	private boolean   hitFlag     = false;  //潜艇是否被击中标记
	private int       hitX        = 0;      //击中位置潜艇x坐标
	private int       hitY        = 0;      //击中战舰潜艇y坐标
	private Image     bumbImage;            //炸弹图片
	private Image     hitImage;             //击中潜艇效果图片
	public static final Object subLock = new Object(); //暂停游戏所有线程用到的锁

    private Timer timer;  //用来不停绘制玩家剩余可用炸弹的计时器
	private Timer timer3; //用来不停重绘面板和判断移动元素位置的计时器
	private TimeManager tm = null; //用来管理产生潜艇对象的线程

	private ArrayList<Bumb> bumbArray = new ArrayList<Bumb>();                   //炸弹对象集合
	private ArrayList<Submarine> submarineArray  = new ArrayList<Submarine>();   //潜水艇对象集合
	private ArrayList<Torpedo> torpedoArray = new ArrayList<Torpedo>();          //鱼雷对象集合
	private ArrayList<Hit> hitArray = new ArrayList<Hit>();                      //潜艇爆炸对象集合                    
	private ArrayList<Blast> blastArray = new ArrayList<Blast>();                //战舰爆炸对象集合

	
	
	public MyPanel()
	{
		super();
		
		this.setSize(645,458);

		ship = new WarShip(this);
		
		this.addKeyListener(this);
		this.addMouseListener(this);
		
		/*
		URL url = this.getClass().getResource("");
		File file = new File(url.getFile());
		while(!file.getName().equals("SubmarineWar"))
		{
			String path = file.getParent();
			file = new File(path);
		}
		String filepath = file + File.separator + "userInfo" + File.separator + "user";*/
		this.higncore =this.getHigeScore("userInfo/user");
		
		ActionListener al = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MyPanel.this.addBomb();
			}
		};
		timer = new Timer(delay,al);
		
		int delay3 = 10;
	  
	  	ActionListener al3 = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MyPanel.this.repaint();
				MyPanel.this.explode();
				if(MyPanel.this.suspendFlag && timer.isRunning())
				{
					MyPanel.this.timer.stop();
				}
				else if(!MyPanel.this.suspendFlag && !timer.isRunning())
				{
					MyPanel.this.timer.start();
				}
			}
		};
		timer3 = new Timer(delay3,al3);
		timer3.start();
		
		
		this.bumbImage = Toolkit.getDefaultToolkit().getImage("imgs/炸弹.png");
		this.bumbImage = new ImageIcon(this.bumbImage).getImage();
		
		this.hitImage = Toolkit.getDefaultToolkit().getImage("imgs/炸弹效果.png");
		this.hitImage = new ImageIcon(this.hitImage).getImage();
		
		this.setVisible(true);
	}
	
	
	//paint方法在每次repaint的时候被调用,将存在的元素都画上去
	public   void  paint(Graphics g)
	{
		
		   	super.paint(g);
		

			Graphics2D g2 = (Graphics2D)g;

			//绘制游戏背景效果
			Point2D start = new Point2D.Float(322,0);
			Point2D end   = new Point2D.Float(322,150);
			float[]   dist1   = {0.0f,1.0f};
			Color[]   colors1 = {new Color(72,209,187),Color.white};
			LinearGradientPaint p1 =
				new LinearGradientPaint(start, end, dist1, colors1);
			g2.setPaint(p1);
			g2.fillRect(0, 0,this.getWidth(), 150);
			
			
			
			Point2D pStart = new Point2D.Float(322,0);
			Point2D pEnd   = new Point2D.Float(322,458);
			float[]   dist   = {0.0f,0.3f,1.0f};
			Color[]   colors = {new Color(123,104,221),new Color(65,105,209),Color.blue};
			LinearGradientPaint p =
				new LinearGradientPaint(pStart, pEnd, dist, colors);
			g2.setPaint(p);
			g2.fillRect(0, 150,this.getWidth(), this.getHeight());
			
			//绘制战舰和游戏参数
			ship.drawShip(g2);
			this.showScore(g2);
			this.drawBombNum(g2);
			
			//绘制存在的潜艇，炸弹和鱼雷
			if(!this.bumbArray.isEmpty())
			{
				   for(int i = 0; i < this.bumbArray.size(); i ++)
				    {
				    	if(((Bumb)this.bumbArray.get(i)).flag == false)
				    	{
				    		((Bumb)this.bumbArray.get(i)).drawBumb(g2);
				    	}
				    	if(((Bumb)this.bumbArray.get(i)).flag == true)
				    	{
				    		this.bumbArray.remove(i);
				    	}
				    	
				    }
			}
		 
		    if(!this.submarineArray.isEmpty())
		    {
		        for(int i = 0; i < this.submarineArray.size(); i ++)
			    {
			    	if(((Submarine)this.submarineArray.get(i)).flag == false)
			    	{
			    		((Submarine)this.submarineArray.get(i)).drawSubmarine(g2);
			    	}
			    	if(((Submarine)this.submarineArray.get(i)).flag == true)
			    	{
			    		this.submarineArray.remove(i);
			    	}
			    }
		    }
		
		    if(!this.torpedoArray.isEmpty())
		    {
		    	
			    for(int i = 0; i < this.torpedoArray.size(); i ++)
			    	{
			    	if(((Torpedo)this.torpedoArray.get(i)).flag == false)
			    	{
			    		((Torpedo)this.torpedoArray.get(i)).drawTorpedo(g2);
			    		
			    	}
			    	if(((Torpedo)this.torpedoArray.get(i)).flag == true)
			    	{
			    		this.torpedoArray.remove(i);
			    	}
			    }
		    }
		    
		    if(!this.hitArray.isEmpty())
		    {
		    	for(int i = 0; i < this.hitArray.size(); i ++)
		    	{
		    	
		    		if(((Hit)this.hitArray.get(i)).isRunning() == false)
		    		{
		    			((Hit)this.hitArray.get(i)).drawHitting(g2);
		    		}
		    		if(((Hit)this.hitArray.get(i)).isRunning() == true)
		    		{
		    			this.hitArray.remove(i);
		    		}

		    	}
		    	
		    }
		    
		    if(!this.blastArray.isEmpty())
		    {
		    	for(int i = 0; i < this.blastArray.size(); i ++)
		    	{
		    		if(((Blast)this.blastArray.get(i)).isFlag() == false)
		    		{
		    			((Blast)this.blastArray.get(i)).drawBlast(g2);
		    		}
		    		if(((Blast)this.blastArray.get(i)).isFlag() == true)
		    		{
		    			this.blastArray.remove(i);
		    		}
		    	}
		    }
		    
		  
	    
	    
	}

	//玩家按键响应方法
	public void keyPressed(KeyEvent e)
	{
		//按a键或<-控制战舰向左
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			
			ship.moveLeft();
			this.repaint();
			
		}
		//按d键或->控制战舰向右
		else if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			ship.moveRight();
			this.repaint();
		}
		//按空格键投放炸弹
		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			int bn = this.ship.getBombNum();
			if( bn> 0 && bn <= 5)
			{
				 Bumb bumb = new Bumb(this,ship);
				 this.bumbArray.add(bumb);
				 Thread t =  new Thread(bumb);
				 t.start();
				 bn --;
				 this.ship.setBombNum(bn);
			}

		}
		
	}
	
	//开始游戏
	public void startGame()
	{
		//如果游戏没有正在运行

		if(!this.isRunning)
		{
			this.isRunning = true;
			
			tm = new TimeManager(ship,this);
			Thread t = new Thread(tm);
			t.start();
			for(int i = 0; i < this.bumbArray.size(); i++)
			{
				((Bumb)this.bumbArray.get(i)).flag = true;
				this.bumbArray.remove(i);
			}
			for(int i = 0; i < this.submarineArray.size(); i++)
			{
				((Submarine)this.submarineArray.get(i)).flag = true;
				this.submarineArray.remove(i);
			}
			for(int i = 0; i < this.torpedoArray.size(); i++)
			{
				((Torpedo)this.torpedoArray.get(i)).flag = true;
				this.torpedoArray.remove(i);
			}
		}
		//如果游戏正在运行，设置个项参数归零,重新开始游戏
		if(this.isRunning)
		{
			this.isRunning = false;
			this.liveNum = 3;
			this.pass   = 0;
			this.score  = 0;
			this.tm.setSpeed(1000);
			timer.setDelay(3000);
			this.ship.setBombNum(5);
			this.setFocusable(true);
			for(int i = 0; i < this.bumbArray.size(); i++)
			{
				((Bumb)this.bumbArray.get(i)).flag = true;
				//this.bumbArray.remove(i);
			}
			//System.out.println(this.submarineArray.size());
			for(int i = 0; i < this.submarineArray.size(); i++)
			{
				((Submarine)this.submarineArray.get(i)).flag = true;
				//this.submarineArray.remove(i);
			}
			for(int i = 0; i < this.torpedoArray.size(); i++)
			{
				((Torpedo)this.torpedoArray.get(i)).flag = true;
				//this.torpedoArray.remove(i);
			}
			this.isRunning = true;
		}
		
		
	}
	
	
	
	public void keyReleased(KeyEvent e)
	{
		

	}
	
	public void keyTyped(KeyEvent e)
	{
		
	}
	 
	
	public void mousePressed(MouseEvent e)
	{


	}
	

	
	public void mouseReleased(MouseEvent e)
	{
		
		
	}
	
	public void mouseEntered(MouseEvent e)
	{
		
	}
	
	//鼠标单击面板，游戏暂停
	public void mouseClicked(MouseEvent e)
	{
		this.stopGame();
		
		//isGoDialog.setVisible(true);
		
	}
	public void mouseExited(MouseEvent e)
	{
		
	}

	public WarShip getShip() {
		return ship;
	}

	public void setShip(WarShip ship) {
		this.ship = ship;
	}




	public ArrayList<Bumb> getBumbArray() {
		return bumbArray;
	}

	public void setBumbArray(ArrayList<Bumb> bumbArray) {
		this.bumbArray = bumbArray;
	}

	public void setSubmarineArray(ArrayList<Submarine> submarineArray) {
		this.submarineArray = submarineArray;
	}

	public ArrayList<Submarine> getSubmarineArray() {
		return submarineArray;
	}

	
	//根据集合中对象的位置参数判断潜艇和战舰是否被击中
	public void explode()
	{  
		//this.updateScreen();
		if(!this.bumbArray.isEmpty() && !this.submarineArray.isEmpty())
		{
		    for(int i = 0; i < this.bumbArray.size(); i ++)
		    {
		    	if(((Bumb)this.bumbArray.get(i)).flag == false)
		    	{
		    	    for(int j = 0; j < this.submarineArray.size(); j ++)
		    	    {
		    	    	if(((Submarine)this.submarineArray.get(j)).flag == false)
		    	    	{
		    	    		int by =((Bumb)this.bumbArray.get(i)).getBeginY();
		    	    		int syStart = ((Submarine)this.submarineArray.get(j)).getY() - ((Bumb)this.bumbArray.get(i)).getHeight();
		    	    		int syEnd   = ((Submarine)this.submarineArray.get(j)).getY() + ((Submarine)this.submarineArray.get(j)).getHeight();
		    	    		
		    	    		int bx = ((Bumb)this.bumbArray.get(i)).getBeginX();
		    	    		
		    	    		int sxStart =   ((Submarine)this.submarineArray.get(j)).getX() - ((Bumb)this.bumbArray.get(i)).getWidth();
		    	    		int sxEnd   =   ((Submarine)this.submarineArray.get(j)).getX() + ((Submarine)this.submarineArray.get(j)).getWeight();
		    	    		if( by >= syStart && by <= syEnd && bx >= sxStart && bx <= sxEnd)
		    	    		{
		    	    			this.bumbArray.get(i).flag = true;
		    	    			this.submarineArray.get(j).flag = true;
		    	    			this.hitX   = this.bumbArray.get(i).getBeginX() + 10;
		    	    			this.hitY   = this.bumbArray.get(i).getBeginY() + 30;
		    	    			//this.hitFlag  = true;
		    	    			Hit hit = new Hit(this.hitX,this.hitY,this);
		    	    			this.hitArray.add(hit);
		    	    			Thread  t = new Thread(hit);
		    	    			t.start();
			    	    		this.score += 10;
			    	    		this.addPass(this.score);
		    	    		}

		    	    	}
		    	    }
		    	}
		    	
		    }
		}
		

	    
	}

	//保持炸弹数量大于零小于五 并每隔一定时间装弹
	public void addBomb() {
		int bn = this.ship.getBombNum();
		
		if(bn >= 0 && bn < 5)
		{
			bn ++;
			this.ship.setBombNum(bn);
		}
		if(bn < 0)
		{
			bn =0;
			this.ship.setBombNum(bn);
		}
		if(bn >= 5)
		{
			bn = 5;
			this.ship.setBombNum(bn);
		}
	}


	public ArrayList<Torpedo> getTorpedoArray() {
		return torpedoArray;
	}

	public void setTorpedoArray(ArrayList<Torpedo> torpedoArray) {
		this.torpedoArray = torpedoArray;
	}
	
	//绘制游戏参数
	public void showScore(Graphics2D g)
	{
		String s   = "得分:"; 
		String ss  = String.valueOf(this.score);
		
		String s1  = "关数:"; 
		String ss1 = String.valueOf(this.pass);
		
		String s2  = "最高分:";
		String ss2 = String.valueOf(this.higncore);
		
		String s3  = "命数："; 
		String ss3 = String.valueOf(this.liveNum);
		
		g.setFont(new Font("宋体", Font.PLAIN, 30));

		g.setColor(Color.red);
		BasicGraphicsUtils.drawString(g, s, 100, 20, 30);
		BasicGraphicsUtils.drawString(g, s1, 100, 20, 60);
		BasicGraphicsUtils.drawString(g, s2, 100, this.getWidth() - 200, 30);
		BasicGraphicsUtils.drawString(g, s3, 100, this.getWidth() - 200, 60);
		g.setColor(Color.orange);
		BasicGraphicsUtils.drawString(g, ss, 100, 20 + 75, 30);
		BasicGraphicsUtils.drawString(g, ss1, 100, 20 + 75, 60);
		BasicGraphicsUtils.drawString(g, ss2, 100, this.getWidth() - 200 + 100, 30);
		BasicGraphicsUtils.drawString(g, ss3, 100, this.getWidth() - 200 + 75, 60);

		
	}
	
	//判断通关数量 
	public void addPass(int score)
	{
		if(score < 100)
		{
			this.pass = 0;
		}
		else if(score >= 100 && score < 200)
		{
			this.pass = 1;
			this.tm.setSpeed(800);
			timer.setDelay(2800);
		}
		else if(score >= 200 && score < 300)
		{
			this.pass = 2;
			this.tm.setSpeed(600);
			timer.setDelay(2500);
		}
		else if(score >= 300 && score < 500)
		{
			this.pass = 3;
			this.tm.setSpeed(500);
			timer.setDelay(2300);
		}
		else if(score >= 500 && score < 800)
		{
			this.pass = 4;
			this.tm.setSpeed(400);
			timer.setDelay(2000);
		}
		else if(score >= 800 && score < 1000)
		{
			this.pass = 5;
			this.tm.setSpeed(300);
			timer.setDelay(1800);
		}
		else if(score >= 1000 && score < 1500)
		{
			this.pass = 6;
			this.tm.setSpeed(200);
			timer.setDelay(1500);
			
		}
		else if(score >= 1500 && score < 2000)
		{
			this.pass = 7;
			this.tm.setSpeed(100);
			timer.setDelay(1200);
		}
		else if(score >= 2000)
		{
			this.pass = 8;
			this.tm.setSpeed(50);
			timer.setDelay(1000);
		}
		//System.out.println("speed:"+this.tm.getSpeed());
		//System.out.println("delay:"+timer.getDelay());
		
	}
	
	//玩家游戏失败:3条命都没了
	public void loseGmae()
	{
		if(this.getLiveNum() == 0)
		{
			
			//System.out.println("是否需要记录:" + this.isRecord());
			if(this.isRecord())
			{
				//MyPanel.this.setFocusable(false);
				MyPanel.this.suspendFlag = true;
				MyPanel.this.endGame();
				new InputDialog((JFrame)this.getParent().getParent().getParent().getParent(),true,this);
			}
			else
			{
				this.passGame();
			}
			
		}
	}

	public void passGame() {
		//MyPanel.this.setFocusable(false);
		MyPanel.this.suspendFlag = true;
		MyPanel.this.endGame();
		//MyPanel.this.timer2.stop();
		new ScoreDialog((JFrame)MyPanel.this.getParent().getParent().getParent().getParent(),true,MyPanel.this);
	}
	
	

	public int getLiveNum() {
		return liveNum;
	}

	public void setLiveNum(int liveNum) {
		this.liveNum = liveNum;
	}
	
	//绘制炸弹
	public void drawBombNum(Graphics2D g)
	{
		//Image image = Toolkit.getDefaultToolkit().getImage("imgs/炸弹.png");
		//image = new ImageIcon(image).getImage();
		for(int i = 0;i < this.ship.getBombNum();i ++)
		{
			
			g.drawImage(this.bumbImage,this.getWidth()/4 + 25*i + 70, 0,this);
		}
	}
	
	//暂停游戏
	public void stopGame()
	{
		if(this.isRunning)
		{
			
			//this.setFocusable(false);
			this.suspendFlag = true;
			new MyDialog((JFrame)this.getParent().getParent().getParent().getParent(),true,this);
			
			
		}

	}
	
	public boolean isStop()
	{
		
		return this.suspendFlag;
	}

	//继续游戏
	public void goOn()
	{
		
		
			synchronized(MyPanel.subLock)
			{
				MyPanel.subLock.notifyAll();
			}
			this.suspendFlag = false;
			this.requestFocus();
			//this.timer2.start();
		

	}
	
	public boolean isRunning()
	{
		return this.isRunning;
	
	}
	
	public void endGame()
	{
		this.isRunning = false;
	}
	
	
	//得到玩家的最高分
	public int getHigeScore(String filename)
	{
		File  file = new File(filename);
		BufferedReader bf = null;
		int    tempNum = 0;
		try
		{
			 bf = new BufferedReader(new FileReader(file));
			 String temp = null;

			while((temp = bf.readLine()) != null)
			{
				//System.out.println(temp);
				String[] info = temp.split(" ");
				//System.out.println(info[2]);
				int    num    = Integer.valueOf(info[1]);
				if(num > tempNum)
				{
					tempNum = num;
				}
				
					
			}
		
			
			 bf.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			if(bf != null)
			{
				try
				{
					bf.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		//System.out.println(tempNum);
		return tempNum;
		
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public int getHigncore() {
		return higncore;
	}

	public void setHigncore(int higncore) {
		this.higncore = higncore;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isSuspendFlag() {
		return suspendFlag;
	}

	public void setSuspendFlag(boolean suspendFlag) {
		this.suspendFlag = suspendFlag;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	
	public boolean isHitFlag() {
		return hitFlag;
	}

	public void setHitFlag(boolean hitFlag) {
		this.hitFlag = hitFlag;
	}

	public int getHitX() {
		return hitX;
	}

	public void setHitX(int hitX) {
		this.hitX = hitX;
	}

	public int getHitY() {
		return hitY;
	}

	public void setHitY(int hitY) {
		this.hitY = hitY;
	}

	 
	public ArrayList<Blast> getBlastArray() {
		return blastArray;
	}

	
	public void setBlastArray(ArrayList<Blast> blastArray) {
		this.blastArray = blastArray;
	}
	
	//判断玩家成绩是否进入前十名，是的话返回true
	public boolean isRecord()
	{
		boolean isRecord = false;
		
		File  file = new File("userInfo/user");
		BufferedReader bf = null;
		int    num = 0;
		String temp = null;
		try
		{
			 bf = new BufferedReader(new FileReader(file));
			 while((temp = bf.readLine()) != null)
			 {
				 
				 
				 String[] info = temp.split(" ");
				 int    score    = Integer.valueOf(info[1]);
	             if(this.getScore() > score)
	             {
	            	 //System.out.println("score max");
	            	 isRecord = true;
	             }
	             num ++;	
			 }
			 bf.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			if(bf != null)
			{
				try
				{
					bf.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		if(num < 10)
		{
			//System.out.println("num < 10 :" + num);
			isRecord = true;
		}
		
		
		return isRecord;
	}


	public int getDelay() {
		return delay;
	}


	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	
	

//更新屏幕上要绘制的元素
	/*
	public void updateScreen()
	{
		if(!this.bumbArray.isEmpty())
		{
			   for(int i = 0; i < this.bumbArray.size(); i ++)
			    {
				   	if(((Bumb)this.bumbArray.get(i)).flag == true)
			    	{
			    		this.bumbArray.remove(i);
			    	}
			    	
			    }
		}
	 
	    if(!this.submarineArray.isEmpty())
	    {
	        for(int i = 0; i < this.submarineArray.size(); i ++)
		    {
	        	if(((Submarine)this.submarineArray.get(i)).flag == true)
		    	{
	        		this.submarineArray.remove(i);
		    		
		    	}
		    }
	    }
	
	    if(!this.torpedoArray.isEmpty())
	    {
		    for(int i = 0; i < this.torpedoArray.size(); i ++)
		    {
		    	if(((Torpedo)this.torpedoArray.get(i)).flag == true)
		    	{
		    		this.torpedoArray.remove(i);
		    	}
		    }
	    }
	}
	*/
}
