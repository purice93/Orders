
package com.windows;
/*
 * 启动后的登录界面
 */
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.dao.AdminDAO;
public class LoginWindow {
	JFrame frame = new JFrame();// 定义一个主面板框
	
	// 定义主界面的高和宽大小
	private static final int WINDOWSWIDE = 400;
	private static final int WINDOWSHIGH = 300;
	
	// 定义两个输入框：登录名和登录密码（id和password）
	final JTextField userText;
	final JPasswordField pswText;

	// 定义两个字符串，分别用来获取输入的登录名和密码
	private static String loginUserName;
	private static String loginPassword;

	// PublicWindowSet工具类用来设置窗口和窗口中的控件属性
	public LoginWindow() {
		// LoginWindow构造函数中，设置登录名和密码输入框的相对于主窗口frame的位置和大小
		PublicWindowSet.addLabel(frame, 16, 65, 70, 90, 30, "id:");
		userText = new JTextField();
		PublicWindowSet.addTextField(userText, 160, 70, 200, 30, frame);
		
		PublicWindowSet.addLabel(frame, 16, 65, 105, 90, 30, "password:");
		pswText = new JPasswordField();
		PublicWindowSet.addTextField(pswText, 160, 105, 200, 30, frame);

		this.addButton("登录", frame);// 添加按钮到主窗口frame中
		
		// 设置主窗口的左上角标题和大小
		PublicWindowSet.windowAttribute(frame, WINDOWSWIDE, WINDOWSHIGH, null,"餐厅员工管理系统");

	}

	// 添加按钮，设置按钮位置和大小，设置按钮监听函数，对按钮进行响应
	private void addButton(String buttonName, JFrame jfr) {
		// 设置按钮属性
		JButton button = new JButton(buttonName);
		button.setBounds(160, 200, 100, 25);
		button.setFont(new Font("Courier", Font.PLAIN, 12));
		
		// 对（登录）按钮进行监听
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					// 获取输入框的值
					loginUserName = userText.getText();
					loginPassword = pswText.getText();
					LoginWindow.setLoginUserName(loginUserName);
					LoginWindow.setLoginPassword(loginPassword);
					
					// 对输入值进行判断
					if (loginUserName.length() == 0 || loginPassword.length() == 0) {
						PublicWindowSet.promptPopUp("用户名或密码不能为空", "prompt", jfr);
					} else {
						if (new AdminDAO().loginConfirm(loginUserName, loginPassword)) {
							// 如果数据库中存在对应的id和password，生成餐厅员工管理窗口ManagementWindow
							new ManagementWindow();
						} else {
							PublicWindowSet.promptPopUp("用户名或密码错误!!!", "promptʾ", jfr);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		jfr.add(button);

	}

	// 四个函数用于对用户名和密码（id-password）的值进行设置和调用
	public static String getLoginUserName() {
		return loginUserName;
	}

	public static void setLoginUserName(String loginUserName) {
		LoginWindow.loginUserName = loginUserName;
	}

	public static String getLoginPassword() {
		return loginPassword;
	}

	public static void setLoginPassword(String loginPassword) {
		LoginWindow.loginPassword = loginPassword;
	}
}