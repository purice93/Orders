import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.*;

public class Countdown extends JPanel{
	private static final long serialVersionUID = 1L;
	static JLabel timeLabel;
	static JButton start_cancel;
	static JButton pause_cont;	
	static JButton set;
	static JTextPane tMin;
	static JTextPane tSec;
	static Timer timer;
	static int time = 0;
	static boolean isStarted;
	static boolean isRunning;
	
	Countdown() {
		isRunning = false;
		isStarted = false;
		Font MSYH_2 = new Font("微软雅黑", Font.PLAIN, 36);	
		
		timeLabel = new JLabel("00 : 00 . 000");
		timeLabel.setFont(MSYH_2);
		timeLabel.setLayout(null);
		timeLabel.setBounds(158, 50, 300, 200);
		add(timeLabel);
		
		start_cancel = new JButton("Start");
		start_cancel.setLayout(null);
		start_cancel.setBounds(158, 200, 90, 23);
		start_cancel.setFont(Global.MSYH);
		start_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isStarted) {
					if (time>0) {
						timer = new Timer();
						timer.schedule(new CountdownTask(), 0, 87);
						isStarted = true;
						isRunning = true;
						pause_cont.setEnabled(true);						
					}
					else {
						JOptionPane.showMessageDialog(new JFrame(),"Please set a time first.");
					}
				}
				else {
					timer.cancel();
					timeLabel.setText("00 : 00 . 000");
					time = 0;
					isStarted = false;
					isRunning = false;
				}
				StatusUpdate();
			}
		});
		add(start_cancel);
		
		pause_cont = new JButton("Pause");
		pause_cont.setLayout(null);
		pause_cont.setBounds(260, 200, 90, 23);
		pause_cont.setFont(Global.MSYH);
		pause_cont.setEnabled(false);
		pause_cont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isRunning) {
					isRunning = false;
					timer.cancel();
				}
				else {
					isRunning = true;
					timer = new Timer();
					timer.schedule(new CountdownTask(), 0, 87);
				}
				StatusUpdate();
			}
		});
		add(pause_cont);
		
		tMin = new JTextPane();
		tMin.setText("Min");
		tMin.setFont(Global.MSYH);
		tMin.setLayout(null);
		tMin.setBounds(158, 260, 40, 21);
		add(tMin);
		
		tSec = new JTextPane();
		tSec.setText("Sec");
		tSec.setFont(Global.MSYH);
		tSec.setLayout(null);
		tSec.setBounds(208, 260, 40, 21);
		add(tSec);
		
		set = new JButton("Setup");
		set.setLayout(null);
		set.setBounds(260, 260, 90, 23);
		set.setFont(Global.MSYH);
		set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int m = Integer.parseInt(tMin.getText());
					int s = Integer.parseInt(tSec.getText());
					if (m>=0 && m<60 && s<60 && m*s>=0) {
						time = 1000*(m*60+s);
						timeLabel.setText(String.format("%02d : %02d . %03d",time/60000,(time%60000)/1000,(time%60000)%1000));
					}
					else JOptionPane.showMessageDialog(new JFrame(),"Invalid value of time!");
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(new JFrame(),"Invalid value of time!");
				}
			}
		});
		add(set);
	}
	
	static void StatusUpdate() {
		if (isStarted) {
			start_cancel.setText("Cancel");
			tMin.setVisible(false);
			tSec.setVisible(false);
			set.setVisible(false);
		}
		else {
			start_cancel.setText("Start");
			pause_cont.setEnabled(false);
			tMin.setVisible(true);
			tSec.setVisible(true);
			set.setVisible(true);
		}
		if (isRunning) pause_cont.setText("Pause");
		else pause_cont.setText("Continue");
	}
	
	static class CountdownTask extends TimerTask{
	    public void run(){	
	    	if (time-87<=0) {
	    		isRunning = false;
	    		isStarted = false;
	    		time = 0;
	    		timeLabel.setText("00 : 00 . 000");
	    		timer.cancel();
	    		JOptionPane.showMessageDialog(new JFrame(),"Countdown finished!");
	    		StatusUpdate();
	    	}
	    	else {
	    		time -= 87;
	    		timeLabel.setText(String.format("%02d : %02d . %03d",time/60000,(time%60000)/1000,(time%60000)%1000));
	    	}
	    }
	}
}
