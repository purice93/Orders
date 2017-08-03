package com.stj.views;

import java.util.Observable;
/*
 *监听者模式来监听游戏画面切换，从游戏进入画面到游戏主画面,用MyFrame对象监听MainPanel
 */

public class FrameObservable extends Observable
{
	public void notifyObservers(Object arg)
	{
		//System.out.println("notify");
		this.setChanged();
		super.notifyObservers(arg);  //  arg为被观察的对象
	}
}
