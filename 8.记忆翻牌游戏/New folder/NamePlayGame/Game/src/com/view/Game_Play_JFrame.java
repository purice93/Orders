package com.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.model.*;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.control.Game_P_con;
import com.control.Get;

public class Game_Play_JFrame extends JFrame{
	private Image backimage=new ImageIcon("Image/top.jpg").getImage();
	private Image backimagebottom=new ImageIcon("Image/zhuankui.jpg").getImage();
	private JPanel jpanelPlayGame;
	private JPanel jpanelBottomGame;
	PlayImage playImage;
	JPanel jpone;
	//敌人坐标
	int xdr=0;
	int ydr=0;
	int xmy;
	int ymy;
	int attack;
	//应该减去的气血值 魔法值
	static int sunsqixue;
	static int sunsmofa;
	//技能坐标
	int jnzb;
	int gjfx;
	int ssqx;
	//自己坐标
    //跑步的4个动作的路径
    ArrayList<Image> al=new ArrayList<Image>();
	public Game_Play_JFrame() 
	{
		//初始化图片
		playImage=new PlayImage();
		ImageIcon meiguhua=new ImageIcon("Image/meiguihua.png");
		this.setLayout(null);
		//上层背景
        initGameBackgroundImage();
        //底层 背景
        initGameBackgroundBottomImage();
        //启动敌人线程
        Thread t=new Thread(new MonsiterThread(jpanelPlayGame,this));
        t.start();
        Thread tmgh=new Thread(new MghDlThread(jpanelPlayGame,this));
        tmgh.start();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗口大小
		this.setSize(1000,600);
		this.addKeyListener(new Game_P_con(jpanelPlayGame,jpone,this));
		int x=(Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth())/2;
		int y=(Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight())/2-32;
	    //不允许用户改变窗口大小
	    this.setResizable(false);
	    //居中
	    this.setLocation(x, y);
	    //默认该窗口显示
	    this.setVisible(true);
	}
	
	public void initGameBackgroundImage()
	{
		jpanelPlayGame=new Game_Scene(backimage,0,0,1000,450);
		jpanelPlayGame.setLayout(null);
		jpanelPlayGame.setBounds(0, 0, 1000, 450);
		//添加人物头像等相关信息
		initGamePersonHai(jpanelPlayGame);
		//绘制血条魔法条
		initQixueMf(jpanelPlayGame,sunsqixue,sunsmofa);
		//加载人物
        initGamePerson();
        //加载敌人
        eneMy();
		this.add(jpanelPlayGame);
		
	}
	public void initGameBackgroundBottomImage()
	{
		jpanelBottomGame=new Game_Scene(backimagebottom,0,0,995,200);
		jpanelBottomGame.setBounds(0, 400, 1000, 200);
		this.add(jpanelBottomGame);
	}
	public void initGamePersonHai(JPanel jp)
	{
		
		Game_Character game_Character=new Game_Character(new ImageIcon("Image/name1.png"));
		//取得两个名字
		JLabel jlname1=new JLabel(game_Character.getName());
		JLabel jlname2=new JLabel(new Game_Character(new ImageIcon("Image/name2.png")).getName());
		jlname1.setBounds(30, 100, 50, 50);
		jlname2.setBounds(910, 100, 50, 50);
		JLabel jlqixue=new JLabel(new ImageIcon("Image/qixue.png"));
		JLabel jlmofa=new JLabel(new ImageIcon("Image/mofa.png"));
		JLabel jlqixue2=new JLabel(new ImageIcon("Image/qixue.png"));
		JLabel jlmofa2=new JLabel(new ImageIcon("Image/mofa.png"));
		jlqixue.setBounds(100, 20, 30, 20);
		jlmofa.setBounds(100, 60, 30, 20);
		
		jlqixue2.setBounds(860, 20, 30, 20);
		jlmofa2.setBounds(860, 60, 30, 20);
		//头像1坐标
		JPanel jptx1=new Game_Scene(game_Character.getTouxiang1(),0,0,70,70);
		//头像2坐标
		JPanel jptx2=new Game_Scene(game_Character.getTouxiang2(),0,0,70,70);
		jptx1.setBounds(20, 20, 70, 70);
		jptx2.setBounds(900,20,70,70);
		jp.add(jlqixue);
		jp.add(jlmofa);
		jp.add(jlqixue2);
		jp.add(jlmofa2);
		jp.add(jlname1);
		jp.add(jlname2);
		jp.add(jptx1);
		jp.add(jptx2);
	}
	JPanel jpqixuetiao2;
	
	public void initQixueMf(JPanel jp,int sunsqixue,int sunsmofa)
	{
		JPanel jpqixuetiao=new Game_Qx_Mf_Tiao(0,0,120,20);
		JPanel jpmofatiao=new Game_Qx_Mf_Tiao(0,0,120,20);
		ssqx=sunsqixue;
		
		jpqixuetiao2=new Game_Qx_Mf_Tiao(0,0,120-sunsqixue,20);
		System.out.println(120-sunsqixue);
		JPanel jpmofatiao2=new Game_Qx_Mf_Tiao(0,0,120,20);
		
		jpqixuetiao.setBounds(140, 20, 120, 20);
		jpqixuetiao2.setBounds(730, 20, 120, 20);
		
		jpmofatiao.setBounds(140, 60, 120, 20);
		jpmofatiao2.setBounds(730, 60, 120, 20);
		
		jpqixuetiao.setForeground(Color.red);
		jpqixuetiao2.setForeground(Color.red);
		
		jpmofatiao.setForeground(Color.BLUE);
		jpmofatiao2.setForeground(Color.BLUE);
		jp.add(jpmofatiao2);
		jp.add(jpmofatiao);
		
		jp.add(jpqixuetiao2);
		jp.add(jpqixuetiao);
	}
	
	public void initGamePerson() 
    {   
		jpone=new Person_Draw(0,0,55,65,playImage.getAlImage().get(0));
		jpone.setBounds(10, 383, 55, 65);
		jpanelPlayGame.add(jpone);	
    }
	JPanel jpdr;
	public void eneMy()
	{
		Image image1=new ImageIcon("fz_image/d.png").getImage();
		jpdr=new Zq3d(image1);
		xmy=this.WIDTH+600;
		ymy=this.HEIGHT+383;
		jpdr.setBounds(this.WIDTH+600, this.HEIGHT+383, 55, 65);
		jpanelPlayGame.add(jpdr);
	}
	//自己移动方法
	public void MoveMy(int x,int y,int tfx)
	{
		jpanelPlayGame.remove(jpone);
		jpone=new Zq3d(playImage.getAlImage().get(tfx)); 
		xmy=jpanelPlayGame.WIDTH+x;
		ymy=jpanelPlayGame.HEIGHT+383+y;
		jpone.setBounds(jpanelPlayGame.WIDTH+x, jpanelPlayGame.HEIGHT+383+y, 55, 65);
		jpanelPlayGame.add(jpone);
	}
	public void initMgh(JPanel jpmgh,int x,int y,int width,int height)
	{ 
		this.jnzb=x;
		jpmgh.setBounds(x, y, width, height);
		jpanelPlayGame.add(jpmgh);
	}
	
	//移动方法
	public void Move(int x,int y,Image image,int attack)
	{
		this.attack=attack;
		jpanelPlayGame.remove(jpdr);
		jpdr=new Zq3d(image);
		xdr=this.WIDTH+600+x;
		ydr=this.HEIGHT+383+y;
		jpdr.setBounds(this.WIDTH+600+x, this.HEIGHT+383+y, 55, 65);
		jpanelPlayGame.add(jpdr);
	}
	
	public int getJnzb() {
		return jnzb;
	}

	public int getXdr() {
		return xdr;
	}
	public int getYdr() {
		return ydr;
	}

	public int getAttack() {
		return attack;
	}

	public int getXmy() {
		return xmy;
	}

	public int getYmy() {
		return ymy;
	}

	public JPanel getJpone() {
		return jpone;
	}

	public JPanel getJpdr() {
		return jpdr;
	}

	public JPanel getJpqixuetiao2() {
		return jpqixuetiao2;
	}
	public void setGjfx(int gjfx) {
		this.gjfx=gjfx;
	}
	public int getGjfx() {
		return gjfx;
	}

	public int getSsqx() {
		return ssqx;
	}

	
	

}
