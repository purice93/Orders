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
	private WarShip   ship;              //ս��
	private int       liveNum     = 3;   //����Ĭ��Ϊ3
	private int       pass        = 0;   //ͨ����Ĭ��Ϊ0
	private int       higncore    = 0;   //��߷�Ĭ��Ϊ0
	private int       score       = 0;   //��ҵ÷� Ĭ��Ϊ0
	private int       delay       = 3000;//ս��װ���ٶȣ�Ĭ��Ϊ3000����
	private boolean   suspendFlag = false;  //��ͣ���
	private boolean   isRunning   = false;  //�ж���Ϸ�Ƿ��������б��
	private boolean   hitFlag     = false;  //Ǳͧ�Ƿ񱻻��б��
	private int       hitX        = 0;      //����λ��Ǳͧx����
	private int       hitY        = 0;      //����ս��Ǳͧy����
	private Image     bumbImage;            //ը��ͼƬ
	private Image     hitImage;             //����ǱͧЧ��ͼƬ
	public static final Object subLock = new Object(); //��ͣ��Ϸ�����߳��õ�����

    private Timer timer;  //������ͣ�������ʣ�����ը���ļ�ʱ��
	private Timer timer3; //������ͣ�ػ������ж��ƶ�Ԫ��λ�õļ�ʱ��
	private TimeManager tm = null; //�����������Ǳͧ������߳�

	private ArrayList<Bumb> bumbArray = new ArrayList<Bumb>();                   //ը�����󼯺�
	private ArrayList<Submarine> submarineArray  = new ArrayList<Submarine>();   //Ǳˮͧ���󼯺�
	private ArrayList<Torpedo> torpedoArray = new ArrayList<Torpedo>();          //���׶��󼯺�
	private ArrayList<Hit> hitArray = new ArrayList<Hit>();                      //Ǳͧ��ը���󼯺�                    
	private ArrayList<Blast> blastArray = new ArrayList<Blast>();                //ս����ը���󼯺�

	
	
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
		
		
		this.bumbImage = Toolkit.getDefaultToolkit().getImage("imgs/ը��.png");
		this.bumbImage = new ImageIcon(this.bumbImage).getImage();
		
		this.hitImage = Toolkit.getDefaultToolkit().getImage("imgs/ը��Ч��.png");
		this.hitImage = new ImageIcon(this.hitImage).getImage();
		
		this.setVisible(true);
	}
	
	
	//paint������ÿ��repaint��ʱ�򱻵���,�����ڵ�Ԫ�ض�����ȥ
	public   void  paint(Graphics g)
	{
		
		   	super.paint(g);
		

			Graphics2D g2 = (Graphics2D)g;

			//������Ϸ����Ч��
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
			
			//����ս������Ϸ����
			ship.drawShip(g2);
			this.showScore(g2);
			this.drawBombNum(g2);
			
			//���ƴ��ڵ�Ǳͧ��ը��������
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

	//��Ұ�����Ӧ����
	public void keyPressed(KeyEvent e)
	{
		//��a����<-����ս������
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			
			ship.moveLeft();
			this.repaint();
			
		}
		//��d����->����ս������
		else if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			ship.moveRight();
			this.repaint();
		}
		//���ո��Ͷ��ը��
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
	
	//��ʼ��Ϸ
	public void startGame()
	{
		//�����Ϸû����������

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
		//�����Ϸ�������У����ø����������,���¿�ʼ��Ϸ
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
	
	//��굥����壬��Ϸ��ͣ
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

	
	//���ݼ����ж����λ�ò����ж�Ǳͧ��ս���Ƿ񱻻���
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

	//����ը������������С���� ��ÿ��һ��ʱ��װ��
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
	
	//������Ϸ����
	public void showScore(Graphics2D g)
	{
		String s   = "�÷�:"; 
		String ss  = String.valueOf(this.score);
		
		String s1  = "����:"; 
		String ss1 = String.valueOf(this.pass);
		
		String s2  = "��߷�:";
		String ss2 = String.valueOf(this.higncore);
		
		String s3  = "������"; 
		String ss3 = String.valueOf(this.liveNum);
		
		g.setFont(new Font("����", Font.PLAIN, 30));

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
	
	//�ж�ͨ������ 
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
	
	//�����Ϸʧ��:3������û��
	public void loseGmae()
	{
		if(this.getLiveNum() == 0)
		{
			
			//System.out.println("�Ƿ���Ҫ��¼:" + this.isRecord());
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
	
	//����ը��
	public void drawBombNum(Graphics2D g)
	{
		//Image image = Toolkit.getDefaultToolkit().getImage("imgs/ը��.png");
		//image = new ImageIcon(image).getImage();
		for(int i = 0;i < this.ship.getBombNum();i ++)
		{
			
			g.drawImage(this.bumbImage,this.getWidth()/4 + 25*i + 70, 0,this);
		}
	}
	
	//��ͣ��Ϸ
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

	//������Ϸ
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
	
	
	//�õ���ҵ���߷�
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
	
	//�ж���ҳɼ��Ƿ����ǰʮ�����ǵĻ�����true
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
	
	
	

//������Ļ��Ҫ���Ƶ�Ԫ��
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
