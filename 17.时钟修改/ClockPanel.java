import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClockPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	final int Xpoint=100;
	final int Ypoint=100;
	final int R=90;//设置表盘的位置和大小
	

public void paint(Graphics g){
	this.repaint();
	super.paint(g);
 	Graphics2D g2 = (Graphics2D)g;//得到graphics2D对象
 	int second = Global.NCar.get(Calendar.SECOND);//得到秒数
 	int minute = Global.NCar.get(Calendar.MINUTE);//得到分数
 	int hour = Global.NCar.get(Calendar.HOUR_OF_DAY);//得到小时数
 	int xHour=0,yHour=0,xSecond=0,ySecond=0,xMin=0,yMin=0;
	g.setColor(Color.white);//设置当前颜色
	for(int i=6,num=1;i<=360;i+=6){ 
		double alpha = Math.toRadians(i);//转换为度大致相等的角度，以弧度为单位的角度。
		int xPos=Xpoint+(int)(R*Math.sin(alpha));
		int yPos=Ypoint-(int)(R*Math.cos(alpha));
		if(i%30==0){
			if(num%3==0)
				g.setColor(Color.red);//数字3，6，9，12为红色
			else
				g.setColor(Color.black);//其余数字为黑色
			g.drawString(""+num,xPos-5,yPos+3);//写数字
			num=(num+1);
 			}
		else{
			g.setColor(Color.black);//其余为黑色的点
			g.drawString(".",xPos,yPos);
			}
		}
	g.setColor(Color.red);
	g.fillOval(Xpoint-2,Ypoint-2,4,4);
	//画秒针
	xSecond=(int)(Xpoint+(R-5)*Math.sin(second*(2*Math.PI/60)));
    ySecond=(int)(Ypoint-(R-5)*Math.cos(second*(2*Math.PI/60)));
    g.setColor(Color.red);
    g2.setStroke(new BasicStroke(1.0f));  
    g.drawLine(Xpoint,Ypoint,xSecond,ySecond);//g.drawLine()：在两点之间画线
    //画分针
    xMin=(int)(Xpoint+(R-20)*Math.sin((minute+second/60)*(2*Math.PI/60)));
    yMin=(int)(Ypoint-(R-20)*Math.cos((minute+second/60)*(2*Math.PI/60)));
    g2.setStroke(new BasicStroke(2.0f)); 
    g.setColor(Color.blue);
    g2.drawLine(Xpoint,Ypoint,xMin,yMin);
    //画时针
    xHour=(int)(Xpoint+(R-45)*Math.sin((hour+minute/60+second/60/60)*(2*Math.PI/12)));
    yHour=(int)(Ypoint-(R-45)*Math.cos((hour+minute/60+second/60/60)*(2*Math.PI/12)));
    g2.setStroke(new BasicStroke(3.0f)); 
    g.setColor(Color.black);
    g2.drawLine(Xpoint,Ypoint,xHour,yHour);
    }
}