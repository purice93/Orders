package com.view;

import javax.swing.JPanel;

public class MghDlThread implements Runnable{
	JPanel jpanelPlayGame;
	Game_Play_JFrame game_Play_JFrame;
	Thread [] tmgh=new Thread[20];
	public MghDlThread(JPanel jpanelPlayGame,Game_Play_JFrame game_Play_JFrame)
	{
		this.jpanelPlayGame=jpanelPlayGame;
		this.game_Play_JFrame=game_Play_JFrame;
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根

		for(int i=0;i<20;i++)
        {
        	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
        	tmgh[i]=new Thread(new MghThread(jpanelPlayGame,game_Play_JFrame));
        	tmgh[i].start();
        }
		
	}

}
