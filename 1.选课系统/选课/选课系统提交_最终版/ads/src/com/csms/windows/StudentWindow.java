/**
 * @program 画出学生选课系统的学生界面
 *
 */
package com.csms.windows;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.List;

import com.csms.dao.CourseDAO;
import com.csms.dao.SelectCourseDAO;
import com.csms.dao.StudentDAO;
import com.csms.entity.Student;
import com.csms.entity.Course;
import com.csms.entity.SelectCourse;
 
public class StudentWindow extends JFrame implements ActionListener {
    // 选课系统的学生窗口界面的大小
    private static final int WINDOWWIDE = 922;
    private static final int WINDOWHIGH = 570;
    // 从登录界面通过取出输入的用户名和用户密码，并通过查询取得该用户的所有的属性
    public Student student = null;
    // 判断打印课程的标志，flag！=1的时候就不能打印了，只能打印一次
    private static int courseFlag = 1;
    // 判断刷新选课的标志，flag！=1的时候就不能打印了，只能打印一次
    private static int flushFlag = 1;
    // 设置界面的table组件的变量
    private JTable table = null;
    private DefaultTableModel tableModel;
    private JButton addFlushBtn = new JButton("刷新课程"); // 定义按钮
    private JButton addSelectBtn = new JButton("确认选课"); // 定义按钮
    private JButton findBtn = new JButton("查找课程"); // 定义按钮
    private JButton stopBtn = new JButton("退出"); // 定义按钮
    private JTextField courseIDText = null;
 
    // Student界面设置总函数
    public StudentWindow() {
        // 取得用户的所有信息
        student = new StudentDAO().searchStudentAll(LoginWindow.getLoginUserName(), LoginWindow.getLoginPassword());
        // 1.左部信息显示区
        this.rigthInformation(student);
 
        // 2.添加右边的信息栏边框
        this.liftInformation(student);
 
        // 3.设置窗口的属性
        PublicWindowSet.windowAttribute(this, WINDOWWIDE, WINDOWHIGH, null, "学生界面窗口");
    }
 
    // 左边窗口信息栏
    public void liftInformation(Student student) {
        // 1).添加一个文本标签
        Icon lan = new ImageIcon("src//images//icons//lan.png");
        JLabel label = new JLabel(lan);
        label.setBounds(-20, 2, 230, 50);
        this.add(label);
 
        // 2).添加一个照片显示的窗口
        // addPhotoLabel,addLabel也是PublicWindowSet类中封装好的标签添加函数
        // 
        String photoUrl = "src//images//students//" + student.getLoginName() + ".jpg";// 暂时不提供
        PublicWindowSet.addPhotoLabel(this, 40, 70, 130, 170, photoUrl);
        // 添加学生信息标签
        PublicWindowSet.addLabel(this, 16, 40, 243, 200, 40, "姓名:   " + student.getStuName());
        PublicWindowSet.addLabel(this, 16, 40, 263, 200, 40, "学号:   " + student.getLoginName());
        PublicWindowSet.addLabel(this, 16, 40, 283, 200, 40, "学院:   " + student.getStuSdept());
 
        // 4).设置分隔标签
        Icon away = new ImageIcon("src//images//icons//tree1.png");
        JLabel awayLabel = new JLabel(away);
        awayLabel.setBounds(200, 50, 10, 450);
        this.add(awayLabel);
    }
 
    // 右部列表信息
    public void rigthInformation(Student student) {
    	// 设置JTabel的默认类型
        this.tableModel = new DefaultTableModel(new CourseList().userInfo, new CourseList().titles);
        // 添加JLabel组件
        this.table = new JTable(this.tableModel);
        // 禁止JLabel组件随着窗口的大小而改变
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // JTabel组件表格的显示尺寸
        table.setPreferredScrollableViewportSize(new Dimension(690, 0));
        // 使列表不可以整列的进行移动
        table.getTableHeader().setReorderingAllowed(false);
        // 使列表框不可被编辑
        table.enable(false);
        // 给JTabel组件添加滑动条
        JScrollPane scr = new JScrollPane(this.table);
 
        // 设置面板
        JPanel toolBar = new JPanel();
 
        // 添加底部信息提示标签
        Icon bottom = new ImageIcon("src//images//icons//bottom1.png");
        JLabel bottomLab = new JLabel(bottom);
        toolBar.add(bottomLab);
 
        // 添加课程号输入文本域
        courseIDText = new JTextField(25);
        toolBar.add(courseIDText);
 
        // 添加两个按钮：选课按钮和，刷新课程按钮
        toolBar.add(this.addFlushBtn);
        toolBar.add(this.addSelectBtn);
        toolBar.add(this.findBtn);
        toolBar.add(this.stopBtn);
 
        // 将底部操作组件添加入面板的南部区域
        this.add(toolBar, BorderLayout.SOUTH);
        // 将带滚动条的Tabel组件添加入面板的东部区域
        this.add(scr, BorderLayout.EAST);
 
        // 添加选课按钮和刷新课程列表按钮的响应事件
        this.addFlushBtn.addActionListener(this);
        this.addSelectBtn.addActionListener(this);
        this.findBtn.addActionListener(this);
        this.stopBtn.addActionListener(this);
        
        
        // 刷新课表
        // 先清空原始表信息
        while(this.tableModel.getRowCount() > 0) {
        	this.tableModel.removeRow(this.tableModel.getRowCount()-1);
        }
        List<Course> list = new CourseDAO().searchCourseInformation();
        Course arrange = null;
        for (int i = 0; i < list.size(); i++) {
            arrange = list.get(i);
            this.tableModel.addRow(new Object[] { arrange.getCourID(), arrange.getCourName(),
            		arrange.getCsum(), arrange.getCselected() });
        }
        flushFlag++;
    }
 
    // JTabel列表属性设置类
    class CourseList extends AbstractTableModel {
        public String[] titles = { "课程ID", "课程名","选课上限", "已选人数" };
        public Object[][] userInfo = {}; // 定义数据
 
        public int getRowCount() {
            return 0;
        }
 
        public int getColumnCount() {
            return 0;
        }
 
        public Object getValueAt(int rowIndex, int columnIndex) {
            return null;
        }
    }
 
    // 取得窗口的宽度
    public static int getWindowwide() {
        return WINDOWWIDE;
    }
 
    // 取得窗口的高度
    public static int getWindowhigh() {
        return WINDOWHIGH;
    }
 
    // 选课按钮和刷新按钮的时间响应机制处理函数
    public void actionPerformed(ActionEvent e) {
        // 刷新课程信息，从数据库中读出所有的课程信息
        if (e.getSource() == this.addFlushBtn) {
        	while(this.tableModel.getRowCount() > 0) {
            	this.tableModel.removeRow(this.tableModel.getRowCount()-1);
            }
            List<Course> list = new CourseDAO().searchCourseInformation();
            Course arrange = null;
            for (int i = 0; i < list.size(); i++) {
                arrange = list.get(i);
                this.tableModel.addRow(new Object[] { arrange.getCourID(), arrange.getCourName(),
                		arrange.getCsum(), arrange.getCselected() });
            }
        }
        // 确定添加课程信息
        if (e.getSource() == this.addSelectBtn) {
 
            // 1. 取得学生的ID
            String studentID = student.getLoginName();
            // 2. 取得所写的课程ID
            String courseID = courseIDText.getText();
            // 3. 将数据刷新到数据库的已选课程表
            if (courseID.length() != 0) {
            	
            	int sc_count= new SelectCourseDAO().getById(studentID, courseID);

        		List<Course> c_list= new SelectCourseDAO().getNumById(courseID);
        		Course sc_course = c_list.get(0);
                
            	if(sc_count > 0) {
            		PublicWindowSet.promptPopUp("你已经选过此课，不需要再选", "选课提示", this);
            	} else if (sc_course.getCsum() == sc_course.getCselected()) {
            			PublicWindowSet.promptPopUp("课已满，无法再选", "选课提示", this);
            	} else {
            		int count = new SelectCourseDAO().addStudentSelectCourseInformation(studentID, courseID);
                	if(count > 0) {
                		PublicWindowSet.promptPopUp("选课成功", "选课提示", this);
                	}
                }
            } else {
                PublicWindowSet.promptPopUp("请填写课程ID,在选课!", "选课提示", this);
            }
        }
        // 查找课程
        if (e.getSource() == this.findBtn) {
 
            // 1. 取得所写的课程ID
            String courseID = courseIDText.getText();
            // 3. 将数据刷新到数据库的已选课程表
            if (courseID.length() != 0) {
            	// 列出课程列表
                List<Course> list = new CourseDAO().searchCourseInformationById(courseID);
                Course arrange = null;
                while(this.tableModel.getRowCount() > 0) {
                	this.tableModel.removeRow(this.tableModel.getRowCount()-1);
                }
                
                for (int i = 0; i < list.size(); i++) {
                    arrange = list.get(i);
                    this.tableModel.addRow(new Object[] { arrange.getCourID(), arrange.getCourName(),
                    		arrange.getCsum(), arrange.getCselected() });
                }
                flushFlag++;
            	
            } else {
                PublicWindowSet.promptPopUp("请填写课程ID,再选课!", "选课提示", this);
            }
        }
        // 退出
        if (e.getSource() == this.stopBtn) {
        	this.dispose();
        }
    }
 
}
