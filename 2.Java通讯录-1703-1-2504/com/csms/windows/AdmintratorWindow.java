/**
 * @program 管理员窗口
 *
 */
 
package com.csms.windows;
 
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
 
import com.csms.dao.AdminitartorDAO;
import com.csms.dao.CourseDAO;
import com.csms.dao.SelectCourseDAO;
import com.csms.dao.StudentDAO;
import com.csms.entity.Adminitartor;
import com.csms.entity.Course;
import com.csms.entity.SelectCourse;
import com.csms.entity.Student;
 
public class AdmintratorWindow {
    private static final int WINDOWWIDE = 200;
    private static final int WINDOWHIGH = 400;
    private static Adminitartor admin = null;
    String userName = LoginWindow.getLoginUserName();
    String userPsd = LoginWindow.getLoginPassword();
    String url = "src//images//icons//admin1.png";
    private JFrame frame = new JFrame();

    // 管理员窗口
    public AdmintratorWindow() {
        admin = this.getAdminitratorInformation();
        this.informationArea(frame);
 
        PublicWindowSet.windowAttribute(frame, WINDOWWIDE, WINDOWHIGH, url, "管理员窗口");
    }
 
    // 面板信息呈现
    public void informationArea(JFrame jfr) {
        // 大标题
        PublicWindowSet.addLabel(jfr, 26, 30, 10, 150, 30, "管理员信息");
        // 管理员账号名
        PublicWindowSet.addLabel(jfr, 16, 20, 80, 100, 15, "账号:");
        // 管理员界面功能按钮
        addButton(1, "添加学生", 140, jfr);
        addButton(2, "添加课程", 180, jfr);
        addButton(3, "课程修改", 220, jfr);
        addButton(4, "退出", 260, jfr);
        // 添加管理员的详细信息
        PublicWindowSet.addLabel(jfr, 16, 65, 80, 100, 15, admin.getAdminName());
    }
 
    // 添加button
    private static void addButton(int flag, String str, int high, JFrame frm) {
        JButton button = new JButton(str);
        button.setFont(new Font("楷体", Font.PLAIN, 16));
        button.setBounds(30, high, 130, 30);
        button.setContentAreaFilled(false);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (flag) {
                case 1:
                    // 添加学生
                    new releaseStudentInformationWindow();
                    break;
                case 2:
                    // 添加课程
                    new releaseCourseInformationWindow();
                    break;
                case 3:
                    // 课程修改
                    new deleteInformationWindow();
                    break;
                case 4:
                    // 退出
                    frm.dispose();
                    break;
                }
            }
        });
        frm.add(button);
    }
 
    // 获取管理员的所有信息
    public Adminitartor getAdminitratorInformation() {
        return new AdminitartorDAO().getInformation(userName, userPsd);
    }
}
 
// 添加学生信息
class releaseStudentInformationWindow {
 
    private JFrame frame = new JFrame();
    String url = "src//images//icons//admin.png";
    public releaseStudentInformationWindow() {
        this.displayInformation(this.frame);
 
    }
 
    private void displayInformation(JFrame jfr) {
        PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "学生信息录入");
        PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "学生ID:");
        PublicWindowSet.addLabel(jfr, 16, 20, 100, 90, 30, "登录密码:");
        PublicWindowSet.addLabel(jfr, 16, 20, 130, 90, 30, "学生姓名:");
        PublicWindowSet.addLabel(jfr, 16, 20, 160, 90, 30, "学生院系:");
 
        JTextField userName = new JTextField();
        PublicWindowSet.addTextField(userName, 100, 70, 120, 25, jfr);
        JTextField userPsd = new JTextField();
        PublicWindowSet.addTextField(userPsd, 100, 100, 120, 25, jfr);
        JTextField id = new JTextField();
        PublicWindowSet.addTextField(id, 100, 130, 120, 25, jfr);
        JTextField sdept = new JTextField();
        PublicWindowSet.addTextField(sdept, 100, 160, 120, 25, jfr);
 
        JButton button = new JButton("确定添加");
        button.setBounds(75, 330, 100, 30);
        button.setFont(new Font("楷体", Font.PLAIN, 16));
        button.setContentAreaFilled(false);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student student = new Student();
                if((userPsd.getText() == null) || (userPsd.getText() == null)|| (userName.getText() == null)|| 
                		(sdept.getText() == null)) {
                	PublicWindowSet.promptPopUp("输入不能为空!!!", "提示", jfr);
                }
                // ID
                student.setLoginName(userPsd.getText());
                // 登录密码
                student.setLoginPSD(userPsd.getText());
                // 学生姓名
                student.setStuName(userName.getText());
                // 学生系院
                student.setStuSdept(sdept.getText());
                new StudentDAO().insertStudentInformation(student);
                PublicWindowSet.promptPopUp("录入学生成功!!!", "提示", jfr);
                userName.setText("");
                userPsd.setText("");
                id.setText("");
                sdept.setText("");
            }
        });
        jfr.add(button);
        PublicWindowSet.windowAttribute(this.frame, 250, 400, url, "添加学生");
    }
}
 
// 录入排课信息
class releaseCourseInformationWindow {
    private JFrame frame = new JFrame();
    String url = "src//images//icons//admin.png";
 
    private void displayInformation(JFrame jfr) {
        PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "排课信息录入");
        PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "课程ID:");
        PublicWindowSet.addLabel(jfr, 16, 20, 100, 90, 30, "课程名称:");
        PublicWindowSet.addLabel(jfr, 16, 20, 130, 90, 30, "课程人数:");
 
        JTextField courID = new JTextField();
        PublicWindowSet.addTextField(courID, 100, 70, 120, 25, jfr);
        JTextField name = new JTextField();
        PublicWindowSet.addTextField(name, 100, 100, 120, 25, jfr);
        JTextField number = new JTextField();
        PublicWindowSet.addTextField(number, 100, 130, 120, 25, jfr);
 
        JButton button = new JButton("确定添加");
        button.setBounds(75, 350, 100, 30);
        button.setFont(new Font("楷体", Font.PLAIN, 16));
        button.setContentAreaFilled(false);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Course ca = new Course();
            	if((courID.getText() == null) || (courID.getText() == null)|| (courID.getText() == null) 
            			|| (courID.getText() == null)) {
                	PublicWindowSet.promptPopUp("输入不能为空!!!", "提示", jfr);
                }
                ca.setCourID(courID.getText());
                ca.setCourName(name.getText());
                ca.setCsum(Integer.valueOf(number.getText()));
                

                new CourseDAO().insertCourse(ca);
                PublicWindowSet.promptPopUp("录入课程成功!!!", "提示", jfr);
                courID.setText("");
                name.setText("");
                number.setText("");
            }
        });
        jfr.add(button);
 
        PublicWindowSet.windowAttribute(this.frame, 250, 420, url, "添加课程");
    }
 
    public releaseCourseInformationWindow() {
        this.displayInformation(frame);
    }
}
 
// 删除信息
class deleteInformationWindow {
    private JFrame frame = new JFrame();
    String url = "src//images//icons//admin.png";
 
    public deleteInformationWindow() {
        this.displayInformation(frame);
    }
 
    private void displayInformation(JFrame jfr) {
        // 添加分类大标签
        PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "课程修改");
 
        // 添加课程信息
        PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "课程ID:");
        JTextField couIDField = new JTextField();
        PublicWindowSet.addTextField(couIDField, 85, 70, 125, 25, jfr);
        this.addButton(1, "查看所有课程", 235, this.frame, couIDField);
        this.addButton(2, "删除课程信息", 270, this.frame, couIDField);
 
        // 设置窗口属性
        PublicWindowSet.windowAttribute(jfr, 250, 420, url, "课程修改");
    }
 
    public void addButton(int flag, String str, int high, JFrame jfr, JTextField tf) {
        JButton button = new JButton(str);
        button.setFont(new Font("楷体", Font.PLAIN, 16));
        button.setContentAreaFilled(false);
        button.setBounds(50, high, 150, 30);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (flag) {
                case 1:
                    // 查看排课信息
                    DefaultTableModel courTableModel;
                    String[] courStr = { "课程ID", "课程名字","课程总人数", "已选人数" };
                    JFrame courFrame = new JFrame();
                    PublicWindowSet.windowAttribute(courFrame, 300, 400, null, "排课信息");
                    courTableModel = PublicWindowSet.addTableList(courFrame, courStr);
                    List<Course> courseList = new CourseDAO().searchAllCourse();
                    Course courseArrange = null;
                    for (int i = 0; i < courseList.size(); i++) {
                        courseArrange = courseList.get(i);
                        courTableModel.addRow(new Object[] { 
                        		courseArrange.getCourID(),
                        		courseArrange.getCourName(),
                        		courseArrange.getCsum(),
                        		courseArrange.getCselected()
                        });
                    }
                    break;
                case 2:
                    // 删除排课信息
                    String arrangeId = tf.getText();
                    if (arrangeId.length() == 0) {
                        PublicWindowSet.promptPopUp("请输入课程ID！！！", "错误提示", jfr);
                    } else {
                        new CourseDAO().deleteArrageCourse(arrangeId);
                        PublicWindowSet.promptPopUp("删除课程成功!!!", "提示", jfr);
                        tf.setText("");
                    }
                    break;
                }
            }
        });
        jfr.add(button);
    }
}