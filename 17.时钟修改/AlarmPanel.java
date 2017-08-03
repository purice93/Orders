import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.*;

public class AlarmPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	JButton delButton;
	JLabel timeLabel;
	JLabel avaiLabel;
	Timer timer;
	Date dt;
	
	AlarmPanel(Date apt) {
		this.delButton = new JButton("Delete");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delAlarm();
			}
		});
		this.dt = apt;
		this.timeLabel = new JLabel(Global.dateFormat.format(apt)+"");
		this.avaiLabel = new JLabel("检查中 ");
		this.timeLabel.setBounds(10,0,40,21);
		this.avaiLabel.setBounds(10,0,20,21);
		this.delButton.setBounds(10,0,5,21);
		this.timeLabel.setFont(Global.MSYH);
		this.avaiLabel.setFont(Global.MSYH);
		this.delButton.setFont(Global.MSYH);
		this.add(avaiLabel);
		this.add(timeLabel);
		this.add(delButton);
		this.timer = new Timer();
		this.timer.schedule(new AlarmTask(this), this.dt);
	}
	
	static void addAlarm(Date apt){
		AlarmPanel x = new AlarmPanel(apt);
		Global.alarmContainer.add(x);
		Global.alarmList.add(x);
		setPanelBounds();
	}
	
	static void setPanelBounds() {
		int n = Global.alarmList.size();
		int i;
		for (i=0;i<n;i++)
			Global.alarmList.get(i).setBounds(10, 50*i+100, 220, 50);
		Global.alarmContainer.revalidate();
		Global.alarmContainer.repaint();
	}
	
	void delAlarm(){
		Global.alarmList.remove(this);
		Global.alarmContainer.remove(this);
		setPanelBounds();
	}
	
	static class AlarmTask extends TimerTask{
		AlarmPanel x;
		
		AlarmTask(AlarmPanel t){
			this.x = t;
		}
		
	    public void run(){	
	    	JOptionPane.showMessageDialog(new JFrame(),"Alarm time!");
	    	Global.alarmList.remove(this.x);
			Global.alarmContainer.remove(this.x);
			setPanelBounds();
	    }
	}
}
