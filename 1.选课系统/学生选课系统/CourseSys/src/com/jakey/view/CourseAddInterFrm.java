/*
 * CourseAddInterFrm.java
 *
 * Created on __DATE__, __TIME__
 */

package com.jakey.view;

import java.awt.print.Book;
import java.sql.Connection;

import javax.swing.JOptionPane;

import com.jakey.dao.CourseDao;
import com.jakey.model.Course;
import com.jakey.util.DbUtil;
import com.jakey.util.StringUtil;

/**
 *
 * @author  __USER__
 */
public class CourseAddInterFrm extends javax.swing.JInternalFrame {
	DbUtil dbUtil=new DbUtil();
	CourseDao coursedao=new CourseDao();

	/** Creates new form CourseAddInterFrm */
	public CourseAddInterFrm() {
		initComponents();
		this.setLocation(200, 50);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		courseTimeTxt = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		courseNameTxt = new javax.swing.JTextField();
		courseTeacherTxt = new javax.swing.JTextField();
		capacityTxt = new javax.swing.JTextField();
		jb_add = new javax.swing.JButton();
		jb_reset = new javax.swing.JButton();

		setClosable(true);
		setIconifiable(true);
		setTitle("\u8bfe\u7a0b\u6dfb\u52a0");

		jLabel1.setText("\u8bfe\u7a0b\u540d\u79f0");

		jLabel2.setText("\u4e0a\u8bfe\u65f6\u95f4");

		jLabel3.setText("\u4efb\u8bfe\u8001\u5e08");

		jLabel4.setText("\u8bfe\u7a0b\u5bb9\u91cf");

		jb_add
				.setIcon(new javax.swing.ImageIcon(
						"D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\add.png")); // NOI18N
		jb_add.setText("\u6dfb\u52a0");
		jb_add.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jb_addActionPerformed(evt);
			}
		});

		jb_reset
				.setIcon(new javax.swing.ImageIcon(
						"D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\reset.png")); // NOI18N
		jb_reset.setText("\u91cd\u7f6e");
		jb_reset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jb_resetActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(41, 41, 41)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				courseNameTxt,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				144,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				60,
																				60,
																				60)
																		.addComponent(
																				jLabel2)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				courseTimeTxt,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				144,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel3)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												courseTeacherTxt,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												144,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								jb_add))
																		.addGap(
																				60,
																				60,
																				60)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel4)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												capacityTxt,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												144,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								jb_reset))))
										.addContainerGap(44, Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(46, 46, 46)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(jLabel2)
														.addComponent(
																courseTimeTxt,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																courseNameTxt,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(jLabel4)
														.addComponent(
																courseTeacherTxt,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																capacityTxt,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												55, Short.MAX_VALUE)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jb_reset)
														.addComponent(jb_add))
										.addGap(20, 20, 20)));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jb_addActionPerformed(java.awt.event.ActionEvent evt) {
		String courseName=this.courseNameTxt.getText();
		String courseTime=this.courseTimeTxt.getText();
		String courseTeacher=this.courseTeacherTxt.getText();
		String  capacity=this.capacityTxt.getText();
		if(StringUtil.isEmpty(courseName)){
			JOptionPane.showMessageDialog(this, "课程名称不能为空!");
			return;
		}
		if(StringUtil.isEmpty(courseTime)){
			JOptionPane.showMessageDialog(this, "上课时间不能为空!");
			return;
		}
		if(StringUtil.isEmpty(courseTeacher)){
			JOptionPane.showMessageDialog(this, "任课老师不能为空!");
			return;
		}
		if(StringUtil.isEmpty(capacity)){
			JOptionPane.showMessageDialog(this, "课程容量不能为空!");
			return;
		}
		Course course=new Course(courseName,courseTime,courseTeacher,Integer.parseInt(capacity));
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int n=coursedao.courseAdd(con, course);
			if(n==1){
				JOptionPane.showMessageDialog(this, "课程添加成功!");
				this.resetValue();
			}else{
				JOptionPane.showMessageDialog(this, "课程添加失败!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "课程添加失败!");
		}
		
	}

	private void jb_resetActionPerformed(java.awt.event.ActionEvent evt) {
		this.resetValue();
	}
private void resetValue(){
	this.courseNameTxt.setText("");
	this.courseTeacherTxt.setText("");
	this.courseTimeTxt.setText("");
	this.capacityTxt.setText("");
}
	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JTextField capacityTxt;
	private javax.swing.JTextField courseNameTxt;
	private javax.swing.JTextField courseTeacherTxt;
	private javax.swing.JTextField courseTimeTxt;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JButton jb_add;
	private javax.swing.JButton jb_reset;
	// End of variables declaration//GEN-END:variables

}