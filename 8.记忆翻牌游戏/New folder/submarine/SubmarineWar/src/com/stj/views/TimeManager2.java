package com.stj.views;


import java.util.ArrayList;
import java.util.Random;
/*
 * 计时器 产生鱼雷
 */
public class TimeManager2 implements Runnable
{
	private WarShip ship;
	private ArrayList<Torpedo> torpedoArray ;

	private MyPanel panel;
	private Submarine sm;
	
	
	public TimeManager2(Submarine sm,MyPanel panel,WarShip ship,ArrayList<Torpedo> torpedoArray)
	{
		this.sm = sm;
	    this.torpedoArray =  torpedoArray;
		this.panel    = panel;
		this.ship     = ship;
		
	}
	
	public void run() 
	{
		
		Random r = new Random();
		
		
			while(!this.sm.flag)
			{
				//System.out.println("333");
			
				
					if(this.panel.isStop() == true)
					{
						synchronized(MyPanel.subLock)
						{
							//System.out.println("stop");
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
				
				Torpedo tp = new Torpedo(this.panel,this.ship,this.sm);
				//System.out.println("1111111111111");
				this.torpedoArray.add(tp);
				Thread t = new Thread(tp);
				t.start();
				try
				{
					int time = r.nextInt(4000) + 2000;
					Thread.sleep(time);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	
	

}
