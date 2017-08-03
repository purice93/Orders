package com.stj.views;

import java.util.Observable;

public class SubmarineWarMain
{

	public static void main(String[] args) 
	{
		
		Observable ob = new FrameObservable();
		MyFrame mf = new MyFrame(ob);
		mf.showMyFrame();
		
	}

}
