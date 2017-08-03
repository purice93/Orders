package com.stj.views;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Shape;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicGraphicsUtils;

import com.sun.awt.AWTUtilities;


/*
 * *游戏规则的对话框，提示游戏规则信息
 */
public class HelpDialog extends JDialog implements MouseMotionListener,MouseListener
{
	
	private static final long serialVersionUID = 1L;
	protected JFrame    frame1;   
	private MyPanel    panel;
	private boolean   flag = false;
	private boolean   isDraw = false;
	private boolean   isOutDraw = false;

	
	public HelpDialog(Frame frame,boolean modal,MyPanel panel)
	{
		super(frame,modal);
		this.panel = panel;

		this.addMouseMotionListener(this);
		this.addMouseListener(this);

		this.setLocation(frame.getBounds().x + 180,frame.getBounds().y + 200);
		
		this.setSize(300, 200);
		
		this.setUndecorated(true);   

		this.setVisiableRigeon(this.getWidth(), this.getHeight());

		this.setVisible(true);
	}
	

	public void paint(Graphics g)
	{
		
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
		Point2D start = new Point2D.Float(this.getWidth()/2, 0);
		Point2D end = new Point2D.Float(this.getWidth()/2, this.getHeight());
		float[] dist = {0.05f,1.0f};
		Color[] colors = {new Color(58,95,205), Color.CYAN};
		LinearGradientPaint  p =
		new LinearGradientPaint (start,end, dist, colors);
		
		g2.setPaint(p);
		g2.fillRect(0,0, this.getWidth(), this.getHeight());
		
		
		String title = new String("游戏规则");
		g2.setFont(new Font("华文行楷",Font.BOLD,25));
		g2.setColor(Color.yellow);
		BasicGraphicsUtils.drawString(g2, title, 100, 90, 50);
		
		String context = new String("按 <- 或 A 键 控制军舰向左");
		g2.setFont(new Font("华文行楷",Font.BOLD,15));
		g2.setColor(Color.yellow);
		
		BasicGraphicsUtils.drawString(g2, context, 100, 50, 100);
		String context1 = new String("按 -> 或 D 键 控制军舰向右");
		g2.setFont(new Font("华文行楷",Font.BOLD,15));
		g2.setColor(Color.yellow);
		BasicGraphicsUtils.drawString(g2, context1, 100, 50, 120);
		
		String context2 = new String("按空格键扔炸弹");
		g2.setFont(new Font("华文行楷",Font.BOLD,15));
		g2.setColor(Color.yellow);
		BasicGraphicsUtils.drawString(g2, context2, 100, 50, 140);
		
		//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);

		this.repaintShape();
	

	}  

	//设置对话框为圆角矩形
	public void setVisiableRigeon(int width,int height)
	{
		
		
		Shape shape = new RoundRectangle2D.Float(20,20,this.getWidth() - 40,this.getHeight()-40,15.0f,15.0f);
		Shape shape2 = new Ellipse2D.Double(this.getWidth() - 40, 10,30,30);
		Area area = new Area(shape);
		Area area2 = new Area(shape2);
		area.add(area2);
		
		
		AWTUtilities.setWindowShape(this,area);
		
		
	}


	public void mouseClicked(MouseEvent arg0) {
		
		
	}


	public void mouseEntered(MouseEvent arg0)
	{
	

		//System.out.println("enter");
	
	}


	public void mouseExited(MouseEvent arg0) 
	{
		//System.out.println("exit");
		this.flag = false;
		this.isDraw = false;
		
		if(!this.isOutDraw)
		{
			//this.repaint(this.getWidth() - 40,10,30,30);
			this.repaintShape();
		}
		this.isOutDraw = true;
	}


	public void mousePressed(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
	
		
		Shape shape = new Ellipse2D.Double(this.getWidth() - 40, 10, 30, 30);

		//System.out.print(111111);
		if(shape.contains(x,y))
		{
			this.panel.goOn();
			this.setVisible(false);
		}
		
	}


	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseMoved(MouseEvent arg0) 
	{
		

		int x = arg0.getX();
		int y = arg0.getY();
		//System.out.print(x + " ");
		//System.out.println(y);
		
		Shape shape = new Ellipse2D.Double(this.getWidth() - 40, 10, 30, 30);
		Shape shape2 = new Ellipse2D.Double(this.getWidth() - 40, 10,30,30);
		Area area = new Area(shape);
		Area area2 = new Area(shape2);
		area.add(area2);
		
		
		if(shape.contains(x,y))
		{
			this.flag = true;
			this.isOutDraw = false;
			if(!this.isDraw)
			{
				//this.repaint(this.getWidth() - 40,10,30,30);
				this.repaintShape();
			}
			this.isDraw = true;
		}
		
		else if(!shape.contains(x,y))
		{
			this.flag = false;
			this.isDraw = false;
			
			if(!this.isOutDraw)
			{
				//this.repaint(this.getWidth() - 40,10,30,30);
				this.repaintShape();
			}
			this.isOutDraw = true;
		}
	
		
		
	}
	
	public void repaintShape()
	{
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		if(!flag)
		{
			g2.setColor(Color.gray);
			
		}
		else if(flag)
		{
			g2.setColor(Color.red);
		}
		
		g2.fillOval(this.getWidth() - 40, 10, 30, 30);
		
		g2.setColor(Color.white);
		Shape shape = new RoundRectangle2D.Float( this.getWidth() - 40 + 3,10 + 15 - 3,24,6,1.0f,1.0f);
		AffineTransform af = AffineTransform.getRotateInstance(0.7,this.getWidth() - 40 + 3 + 12,10 + 15 -3 + 3);
		shape = af.createTransformedShape(shape);
		g2.fill(shape);
		
		Shape shape2 = new RoundRectangle2D.Float( this.getWidth() - 40 + 3,10 + 15 - 3,24,6,1.0f,1.0f);
		AffineTransform af2 = AffineTransform.getRotateInstance(-0.7,this.getWidth() - 40 + 3 + 12,10 + 15 -3 + 3);
		shape2 = af2.createTransformedShape(shape2);
		g2.fill(shape2);
	}


	
	 

}

		
	
