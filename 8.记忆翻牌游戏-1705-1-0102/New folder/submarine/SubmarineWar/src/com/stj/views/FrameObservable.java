package com.stj.views;

import java.util.Observable;
/*
 *������ģʽ��������Ϸ�����л�������Ϸ���뻭�浽��Ϸ������,��MyFrame�������MainPanel
 */

public class FrameObservable extends Observable
{
	public void notifyObservers(Object arg)
	{
		//System.out.println("notify");
		this.setChanged();
		super.notifyObservers(arg);  //  argΪ���۲�Ķ���
	}
}
