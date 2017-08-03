package com.csms.windows;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.csms.dao.SelectCourseDAO;
import com.sun.security.auth.NTDomainPrincipal;

public class modifyWindow {

	private JFrame frame = new JFrame();
	private StudentWindow studentWindow = null;

	public modifyWindow(StudentWindow awin, String courseId) {
		this.studentWindow = awin;
		this.displayInformation(this.frame, courseId);
	}

	// 更改面板显示
	private void displayInformation(final JFrame jfr, final String courseId) {
		PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "修改通信录");
		PublicWindowSet.addLabel(jfr, 16, 20, 100, 90, 30, "更改为:");

		final JTextField McourseID = new JTextField();
		PublicWindowSet.addTextField(McourseID, 100, 100, 120, 25, jfr);

		PublicWindowSet.addTableList(jfr, null);// 占位，避免冲突

		JButton button = new JButton("确定修改");
		button.setBounds(75, 400, 100, 30);
		button.setFont(new Font("楷体", Font.PLAIN, 16));
		button.setContentAreaFilled(false);

		// 设置在面板底部（南）
		jfr.add(button, BorderLayout.SOUTH);
		PublicWindowSet.windowAttribute(this.frame, 250, 300, "修改通讯录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyPersion(courseId, McourseID, jfr);
			}
		});
	}

	// 修改联系人
	public void modifyPersion(String courseId, JTextField mcourseID,
			JFrame jfr) {
		if (mcourseID.getText() == null) {
			PublicWindowSet.promptPopUp("输入不能为空!!!", "提示", jfr);
		}
		int result = new SelectCourseDAO()
				.modify(courseId, mcourseID.getText());
		if (result > 0) {
			PublicWindowSet.promptPopUp("修改成功!!!", "提示", jfr);
			studentWindow.flushWindows();
		}
	}

}
