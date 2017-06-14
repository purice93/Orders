package com.stj.views;


import java.util.Random;

/*
 * 计时器 产生潜艇
 */
public class TimeManager implements Runnable
{
    private WarShip ship;
	private MyPanel panel;
	private int     speed = 1000;
	
	
	public TimeManager(WarShip ship,MyPanel panel)
	{
		this.ship = ship;
		this.panel    = panel;
	}
	
	public void run() 
	{
		Random r = new Random();
	
		while(this.panel.isRunning())
		{
			if(this.panel.isStop() == true)
			{
				//System.out.println("777");
				synchronized(MyPanel.subLock)
				{
						try
						{
							MyPanel.subLock.wait();
						}
						catch(Exception e)
						{
							e.printStackTrace();
							//this.flag = true;
							this.panel.endGame();
						}
				}
			}	
				Submarine sm = new Submarine(this.ship,this.panel);
				this.panel.getSubmarineArray().add(sm);
				Thread t = new Thread(sm);
				t.start();
				
				try
				{
					Thread.sleep(this.speed + r.nextInt(this.speed * 3));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		}
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	
	

}
