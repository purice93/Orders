import javax.swing.*;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.*;

public class Global {	
	static JTextPane TextY;
	static JTextPane TextM;
	static JTextPane TextD;
	static JTextPane Texth;
	static JTextPane Textm;
	static JTextPane Texts;
	static Calendar NCar;
	static LabelsPanel[] WPanels = new LabelsPanel[7];
	static LabelsPanel[] TPanels = new LabelsPanel[4];
	static JRadioButton NRB12;
	static List<AlarmPanel> alarmList = new LinkedList<AlarmPanel>();
	static SimpleDateFormat dateFormat = new SimpleDateFormat ("MM.dd   HH : mm : ss");
	static Font MSYH = new Font("微软雅黑", Font.PLAIN, 12);
	static JPanel alarmContainer;
	
	public static void ClockUpdate() {
		
		//主时钟
		if (NCar.get(Calendar.HOUR_OF_DAY)<=12) NRB12.setText("12(A.M.)");
		else NRB12.setText("12(P.M.)");
		if (!TextY.isFocusOwner()) TextY.setText(String.format("%02d",NCar.get(Calendar.YEAR)));
    	if (!TextM.isFocusOwner()) TextM.setText(String.format("%02d",NCar.get(Calendar.MONTH)+1));
    	if (!TextD.isFocusOwner()) TextD.setText(String.format("%02d",NCar.get(Calendar.DAY_OF_MONTH)));
    	if (!Texth.isFocusOwner()) 
    		if (NRB12.isSelected()) Texth.setText(String.format("%02d",NCar.get(Calendar.HOUR_OF_DAY)%12));
    		else Texth.setText(String.format("%02d",NCar.get(Calendar.HOUR_OF_DAY)));
    	if (!Textm.isFocusOwner()) Textm.setText(String.format("%02d",NCar.get(Calendar.MINUTE)));
    	if (!Texts.isFocusOwner()) Texts.setText(String.format("%02d",NCar.get(Calendar.SECOND)));
    	
    	//世界时间
    	Calendar temp;
    	//London    	
    	temp = (Calendar) NCar.clone();
    	temp.add(Calendar.HOUR_OF_DAY, -7);
    	Global.TPanels[0].ls[0].setText(dateFormat.format(temp.getTime()));
    	//Paris
    	temp = (Calendar) NCar.clone();
    	temp.add(Calendar.HOUR_OF_DAY, -6);
    	Global.TPanels[1].ls[0].setText(dateFormat.format(temp.getTime()));
    	//Tokyo
    	temp = (Calendar) NCar.clone();
    	temp.add(Calendar.HOUR_OF_DAY, 1);
    	Global.TPanels[2].ls[0].setText(dateFormat.format(temp.getTime()));
    	//New York
    	temp = (Calendar) NCar.clone();
    	temp.add(Calendar.HOUR_OF_DAY, -12);
    	Global.TPanels[3].ls[0].setText(dateFormat.format(temp.getTime()));
    	//Moscow
    	temp = (Calendar) NCar.clone();
    	temp.add(Calendar.HOUR_OF_DAY, -5);
    	Global.TPanels[4].ls[0].setText(dateFormat.format(temp.getTime()));
    	//Sydney
    	temp = (Calendar) NCar.clone();
    	temp.add(Calendar.HOUR_OF_DAY, +2);
    	Global.TPanels[5].ls[0].setText(dateFormat.format(temp.getTime()));
    	
    	//可用闹钟
    	for (AlarmPanel t:Global.alarmList) {
    		if (t.dt.after(Global.NCar.getTime())) t.avaiLabel.setText("可用");
    		else t.avaiLabel.setText("无效");
    	}
	}
}
