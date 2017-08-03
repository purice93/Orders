/**
 * 主窗口
 *
 */

package com.csms.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.csms.dao.CalledNumberDao;
import com.csms.dao.StudentDao;
import com.csms.entity.CalledNumber;
import com.csms.entity.Student;

public class AdmintratorWindow extends JFrame implements ActionListener {
	private static final int WINDOWWIDE = 600;
	private static final int WINDOWHIGH = 320;

	private JFrame frame = new JFrame();
	private DefaultTableModel tableModel;
	private JTable table = null;

	private XSSFWorkbook xssfWorkbook = null;
	private XSSFRow xssfRow = null;
	private Frame f;
	private FileDialog fd;
	private File file1 = null;
	private File file2 = null;
	private List<Student> pList = new LinkedList<Student>();

	private CalledNumber calledNumber = null;
	private CalledNumberDao calledNumberDao = null;

	//
	private JButton loadFileBtn = new JButton("读取文件");
	private JButton insertStudentBtn = new JButton("添加学生");
	private JButton deleteStudentBtn = new JButton("删除学生");
	private JButton modifyStudentBtn = new JButton("修改学生");
	private JButton storeFileBtn = new JButton("存储文件");
	private JButton callNameBtn = new JButton("点名");

	public AdmintratorWindow() {

		// 2.添加右边的信息栏边框
		this.liftInformation(calledNumber);

		this.rigthInformation();
		PublicWindowSet.windowAttribute(this, WINDOWWIDE, WINDOWHIGH, "ͨ点名系统");
	}

	// 左边窗口信息栏
	public void liftInformation(CalledNumber calledNumber) {

		// 2).添加一个照片显示的窗口
		// addPhotoLabel,addLabel也是PublicWindowSet类中封装好的标签添加函数

		// 添加学生信息标签
		calledNumberDao = new CalledNumberDao();
		PublicWindowSet.addLabel(this, 16, 40, 20, 120, 80, "第" + calledNumberDao.getNumber() + "次 点名");

		// 4).设置分隔标签
		Icon away = new ImageIcon("src//images//icons//tree1.png");
		JLabel awayLabel = new JLabel(away);
		awayLabel.setBounds(25, 50, 10, 40);
		this.add(awayLabel);
	}

	// 图表窗口
	public void rigthInformation() {

		this.tableModel = new DefaultTableModel(new StudentList().studentInfo, new StudentList().titles);

		this.table = new JTable(this.tableModel);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		table.setPreferredScrollableViewportSize(new Dimension(150, 0));

		table.getTableHeader().setReorderingAllowed(false);

		table.enable(false);

		JScrollPane scr = new JScrollPane(this.table);

		JPanel toolBar = new JPanel();
		toolBar.add(this.loadFileBtn);
		toolBar.add(this.insertStudentBtn);
		toolBar.add(this.deleteStudentBtn);
		toolBar.add(this.modifyStudentBtn);
		toolBar.add(this.storeFileBtn);
		toolBar.add(this.callNameBtn);

		this.add(toolBar, BorderLayout.SOUTH);

		this.add(scr, BorderLayout.EAST);

		//
		this.loadFileBtn.addActionListener(this);
		this.insertStudentBtn.addActionListener(this);
		this.deleteStudentBtn.addActionListener(this);
		this.modifyStudentBtn.addActionListener(this);
		this.storeFileBtn.addActionListener(this);
		this.callNameBtn.addActionListener(this);

		//
		while (this.tableModel.getRowCount() > 0) {
			this.tableModel.removeRow(this.tableModel.getRowCount() - 1);
		}
	}

	//
	class StudentList extends AbstractTableModel {
		public String[] titles = { "学号", "姓名" };
		public Object[][] studentInfo = {}; //

		public int getRowCount() {
			return 0;
		}

		public int getColumnCount() {
			return 0;
		}

		// Ĭ
		@Override
		public Object getValueAt(int arg0, int arg1) {
			return null;
		}
	}

	//
	public void actionPerformed(ActionEvent e) {

		// 1.读取文件
		if (e.getSource() == this.loadFileBtn) {
			Student student = null;
			List<String> array = new ArrayList<>();
			fd = new FileDialog(f, "Open", FileDialog.LOAD);
			fd.setVisible(true);

			try

			{
				file1 = new File(fd.getDirectory(), fd.getFile());
				InputStream instream = new FileInputStream(file1);

				xssfWorkbook = new XSSFWorkbook(instream);

				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					xssfRow = xssfSheet.getRow(rowNum);
					student = new Student();
					xssfRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					xssfRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					xssfRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					xssfRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
					xssfRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);

					student.setStudentId(xssfRow.getCell(0).getStringCellValue());
					student.setStudentName(xssfRow.getCell(1).getStringCellValue());
					student.setStudentPic(xssfRow.getCell(2).getStringCellValue());
					student.setCalledNumber(Integer.valueOf(xssfRow.getCell(3).getStringCellValue()));
					student.setLateNumber(Integer.valueOf(xssfRow.getCell(4).getStringCellValue()));

					pList.add(student);

				}
				instream.close();
				flushWindow(pList);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		}

		// 2.添加学生
		if (e.getSource() == this.insertStudentBtn) {
			setEnabled(false);
			studentInformationWindow siw = new studentInformationWindow(this, pList);
			setEnabled(true);
			flushWindow(pList);
		}

		// 3.删除学生
		if (e.getSource() == this.deleteStudentBtn) {
			new deleteWindow(this, pList);
		}

		// 4.修改学生
		if (e.getSource() == this.modifyStudentBtn) {
			new modifyWindow(this, pList);
		}

		// 5.存储文件
		if (e.getSource() == this.storeFileBtn) {
			Student student = null;

			try

			{
				file1 = new File(fd.getDirectory(), fd.getFile());
				OutputStream outstream = new FileOutputStream(file1);

				xssfWorkbook = new XSSFWorkbook();
				XSSFSheet xssfSheet = xssfWorkbook.createSheet();

				XSSFRow xssfRow = xssfSheet.createRow(0);
				xssfRow.createCell(0).setCellValue("学号");
				xssfRow.createCell(1).setCellValue("姓名");
				xssfRow.createCell(2).setCellValue("图片地址");
				xssfRow.createCell(3).setCellValue("被叫次数");
				xssfRow.createCell(4).setCellValue("迟到次数");

				for (int rowNum = 0; rowNum < pList.size(); rowNum++) {
					xssfRow = xssfSheet.createRow(rowNum + 1);
					xssfRow.createCell(0).setCellValue(pList.get(rowNum).getStudentId());
					xssfRow.createCell(1).setCellValue(pList.get(rowNum).getStudentName());
					xssfRow.createCell(2).setCellValue(pList.get(rowNum).getStudentPic());
					xssfRow.createCell(3).setCellValue(String.valueOf(pList.get(rowNum).getCalledNumber()));
					xssfRow.createCell(4).setCellValue(String.valueOf(pList.get(rowNum).getLateNumber()));

					xssfRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					xssfRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					xssfRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					xssfRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
					xssfRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
				}
				xssfWorkbook.write(outstream);
				outstream.flush();
				outstream.close();
				PublicWindowSet.promptPopUp("存储文件成功!!!", "提示", frame);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

		// 6.点名
		if (e.getSource() == this.callNameBtn) {
			new callWindow(this, pList);
		}
	}

	// 刷新窗口
	public void flushWindow(List<Student> pList2) {
		while (this.tableModel.getRowCount() > 0) {
			this.tableModel.removeRow(this.tableModel.getRowCount() - 1);
		}
		Student stu2 = new Student();
		for (int i = 0; i < 2; i++) {
			stu2 = pList2.get(i);
			this.tableModel.addRow(new Object[] { stu2.getStudentId(), stu2.getStudentName(), stu2.getStudentPic(),
					stu2.getCalledNumber(), stu2.getLateNumber() });
		}
	}

}

// 学生信息
class studentInformationWindow {

	private JFrame frame = new JFrame();
	private AdmintratorWindow admintratorWindow = null;

	private Frame f2;
	private FileDialog fd2;
	private File file3 = null;

	public studentInformationWindow(AdmintratorWindow admintratorWindow, List<Student> pList) {
		this.admintratorWindow = admintratorWindow;
		this.displayInformation(this.frame, pList);
	}

	//
	private void displayInformation(JFrame jfr, List<Student> pList) {
		//
		PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "添加学生");
		PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "学号:");
		PublicWindowSet.addLabel(jfr, 16, 20, 100, 90, 30, "姓名:");
		PublicWindowSet.addLabel(jfr, 16, 20, 130, 90, 30, "图片地址:");

		//
		JTextField studentId = new JTextField();
		PublicWindowSet.addTextField(studentId, 100, 70, 120, 25, jfr);
		JTextField studentName = new JTextField();
		PublicWindowSet.addTextField(studentName, 100, 100, 120, 25, jfr);
		JTextField studentPic = new JTextField();
		PublicWindowSet.addTextField(studentPic, 100, 130, 120, 25, jfr);

		JButton studentPicBtn = new JButton("打开");
		studentPicBtn.setBounds(75, 400, 100, 25);
		studentPicBtn.setFont(new Font("黑体", Font.PLAIN, 16));
		studentPicBtn.setContentAreaFilled(false);
		studentPicBtn.setLocation(220, 130);
		jfr.add(studentPicBtn);

		PublicWindowSet.addTableList(jfr, null);//

		JButton button = new JButton("确认添加");
		button.setBounds(75, 400, 100, 30);
		button.setFont(new Font("黑体", Font.PLAIN, 16));
		button.setContentAreaFilled(false);

		//
		jfr.add(button, BorderLayout.SOUTH);
		PublicWindowSet.windowAttribute(this.frame, 400, 300, "添加学生");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				insertAction(pList, jfr, studentId, studentName, studentPic);
				//
				admintratorWindow.flushWindow(pList);
			}
		});

		studentPicBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = null;
				List<String> array = new ArrayList<>();
				fd2 = new FileDialog(f2, "Open", FileDialog.LOAD);
				fd2.setVisible(true);
				file3 = new File(fd2.getDirectory(), fd2.getFile());
				studentPic.setText(file3.getAbsolutePath());
			}
		});
	}

	//
	private void insertAction(List<Student> pList, JFrame jfr, JTextField studentId, JTextField studentName,
			JTextField studentPic) {
		Student student = new Student();
		if ((studentId.getText() == null) || (studentName.getText() == null) || (studentPic.getText() == null)) {
			PublicWindowSet.promptPopUp("输入不能为空!!!", "提示ʾ", jfr);
		}

		//
		student.setStudentId(studentId.getText());
		student.setStudentName(studentName.getText());
		student.setStudentPic(studentPic.getText());
		pList.add(student);
		PublicWindowSet.promptPopUp("添加成功!!!", "提示", jfr);
		jfr.setDefaultCloseOperation(jfr.DISPOSE_ON_CLOSE);
		jfr.dispose();
	}
}

// 删除窗口
class deleteWindow {

	private JFrame frame = new JFrame();
	private AdmintratorWindow admintratorWindow;

	public deleteWindow(AdmintratorWindow awin, List<Student> pList) {
		this.admintratorWindow = awin;
		this.displayInformation(this.frame, pList);
	}

	//
	private void displayInformation(JFrame jfr, List<Student> pList) {
		PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "删除学生");
		PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "学号:");

		JTextField Sid = new JTextField();
		PublicWindowSet.addTextField(Sid, 100, 70, 120, 25, jfr);

		PublicWindowSet.addTableList(jfr, null);//

		JButton button = new JButton("确认删除");
		button.setBounds(75, 400, 100, 30);
		button.setFont(new Font("黑体", Font.PLAIN, 16));
		button.setContentAreaFilled(false);

		//
		jfr.add(button, BorderLayout.SOUTH);
		PublicWindowSet.windowAttribute(this.frame, 250, 300, "删除学生");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete(pList, jfr, Sid);
				admintratorWindow.flushWindow(pList);
			}
		});
	}

	protected void delete(List<Student> pList, JFrame jfr, JTextField Sid) {
		Student student = new Student();
		if ((Sid.getText() == null)) {
			PublicWindowSet.promptPopUp("输入不能为空!!!", "提示ʾ", jfr);
		}

		String studentId = Sid.getText();
		for (int i = 0; i < pList.size(); i++) {
			if (pList.get(i).getStudentId().equals(studentId)) {
				pList.remove(i);
			}
		}
		PublicWindowSet.promptPopUp("删除成功!!!", "提示", jfr);
		jfr.dispose();
	}

}

//
class modifyWindow {

	private JFrame frame = new JFrame();
	private AdmintratorWindow admintratorWindow = null;

	public modifyWindow(AdmintratorWindow awin, List<Student> pList) {
		this.admintratorWindow = awin;
		this.displayInformation(this.frame, pList);
	}

	//
	private void displayInformation(JFrame jfr, List<Student> pList) {
		PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "修改学生");
		PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "被改学号:");
		PublicWindowSet.addLabel(jfr, 16, 20, 100, 90, 30, "学号:");
		PublicWindowSet.addLabel(jfr, 16, 20, 130, 90, 30, "姓名:");
		PublicWindowSet.addLabel(jfr, 16, 20, 160, 90, 30, "图片地址:");

		JTextField bId = new JTextField();
		PublicWindowSet.addTextField(bId, 100, 70, 120, 25, jfr);
		JTextField studentId = new JTextField();
		PublicWindowSet.addTextField(studentId, 100, 100, 120, 25, jfr);
		JTextField studentName = new JTextField();
		PublicWindowSet.addTextField(studentName, 100, 130, 120, 25, jfr);
		JTextField studentPic = new JTextField();
		PublicWindowSet.addTextField(studentPic, 100, 160, 120, 25, jfr);

		PublicWindowSet.addTableList(jfr, null);//

		JButton button = new JButton("确认修改");
		button.setBounds(75, 400, 100, 30);
		button.setFont(new Font("黑体", Font.PLAIN, 16));
		button.setContentAreaFilled(false);

		//
		jfr.add(button, BorderLayout.SOUTH);
		PublicWindowSet.windowAttribute(this.frame, 250, 300, "修改学号");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyStudent(pList, jfr, bId, studentId, studentName, studentPic);
				admintratorWindow.flushWindow(pList);
			}
		});
	}

	// �޸���ϵ��
	protected void modifyStudent(List<Student> pList, JFrame jfr, JTextField bId, JTextField studentId,
			JTextField studentName, JTextField studentPic) {
		Student student = new Student();
		if ((bId.getText() == null) || (studentId.getText() == null) || (studentName.getText() == null)
				|| (studentPic.getText() == null)) {
			PublicWindowSet.promptPopUp("输入不能为空!!!", "提示", jfr);
		}

		String bStudentId = bId.getText();

		student.setStudentId(studentId.getText());
		student.setStudentName(studentName.getText());
		student.setStudentPic(studentPic.getText());

		for (int i = 0; i < pList.size(); i++) {
			if (pList.get(i).getStudentName().equals(bStudentId)) {
				pList.set(i, student);
			}
		}

		PublicWindowSet.promptPopUp("修改成功!!!", "提示", jfr);
	}

}

// 学生信息
class callWindow {

	private JFrame frame = new JFrame();

	private AdmintratorWindow admintratorWindow = null;
	private JPanel toolB = new JPanel();

	public callWindow(AdmintratorWindow admintratorWindow, List<Student> pList) {
		this.admintratorWindow = admintratorWindow;
		this.displayInformation(this.frame, pList, toolB);
	}

	//
	private void displayInformation(JFrame jfr, List<Student> pList, JPanel toolB2) {
		//
		PublicWindowSet.addLabel(jfr, 26, 35, 10, 170, 30, "点名");
		PublicWindowSet.addLabel(jfr, 16, 20, 70, 90, 30, "学号:");
		PublicWindowSet.addLabel(jfr, 16, 20, 100, 90, 30, "姓名:");

		//
		JTextField studentId = new JTextField();
		PublicWindowSet.addTextField(studentId, 100, 70, 120, 25, jfr);
		JTextField studentName = new JTextField();
		PublicWindowSet.addTextField(studentName, 100, 100, 120, 25, jfr);

		PublicWindowSet.addTableList(jfr, null);//

		JButton button1 = new JButton("随机点名");
		JButton button2 = new JButton("优先缺勤");
		JButton button3 = new JButton("学号后两位");

		button1.setBounds(75, 400, 100, 30);
		button2.setBounds(75, 400, 100, 30);
		button3.setBounds(75, 400, 100, 30);

		button1.setFont(new Font("黑体", Font.PLAIN, 16));
		button2.setFont(new Font("黑体", Font.PLAIN, 16));
		button3.setFont(new Font("黑体", Font.PLAIN, 16));

		button1.setContentAreaFilled(false);
		button2.setContentAreaFilled(false);
		button3.setContentAreaFilled(false);

		JButton studentCame = new JButton("确定到了");
		studentCame.setBounds(275, 70, 100, 25);
		studentCame.setFont(new Font("黑体", Font.PLAIN, 16));
		studentCame.setContentAreaFilled(false);
		jfr.add(studentCame);

		JButton studentNoCame = new JButton("人没有到");
		studentNoCame.setBounds(275, 100, 100, 25);
		studentNoCame.setFont(new Font("黑体", Font.PLAIN, 16));
		studentNoCame.setContentAreaFilled(false);
		jfr.add(studentNoCame);

		// 占位设置
		JButton studentNoCame1 = new JButton();
		studentNoCame1.setContentAreaFilled(false);
		jfr.add(studentNoCame1);

		//
		toolB.add(button1);
		toolB.add(button2);
		toolB.add(button3);

		jfr.add(toolB, BorderLayout.SOUTH);

		PublicWindowSet.windowAttribute(this.frame, 600, 300, "点名");

		studentCame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = new Student();
				CameAction(pList, studentId);
				PublicWindowSet.promptPopUp("成功！请继续点名（未迟到）", "提示", jfr);
			}
		});

		studentNoCame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = new Student();
				NoCameAction(pList, studentId);
				PublicWindowSet.promptPopUp("成功！请继续点名（迟到）", "提示", jfr);
			}
		});

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = new Student();
				student = callOneAction(pList, jfr);
				studentId.setText(student.getStudentId());
				studentName.setText(student.getStudentName());
			}
		});

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = new Student();
				student = callActionByNumber(pList, jfr);
				studentId.setText(student.getStudentId());
				studentName.setText(student.getStudentName());
			}
		});

		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = new Student();
				student = callActionByTwo(pList, jfr);
				studentId.setText(student.getStudentId());
				studentName.setText(student.getStudentName());

			}
		});
	}

	protected void NoCameAction(List<Student> pList, JTextField studentId) {
		Student student = new Student();
		for (int i = 0; i < pList.size(); i++) {
			if (pList.get(i).equals(studentId)) {
				pList.get(i).setCalledNumber(pList.get(i).getCalledNumber() + 1);
				pList.get(i).setLateNumber(pList.get(i).getLateNumber() + 1);
			}
		}
	}

	protected void CameAction(List<Student> pList, JTextField studentId) {
		Student student = new Student();
		for (int i = 0; i < pList.size(); i++) {
			if (pList.get(i).equals(studentId)) {
				pList.get(i).setCalledNumber(pList.get(i).getCalledNumber() + 1);
			}
		}
	}

	protected Student callActionByTwo(List<Student> pList, JFrame jfr) {
		List<Student> list = new LinkedList<>();
		;
		for (int i = 0; i < pList.size(); i++) {
			String strId = pList.get(i).getStudentId();
			int twoNum = (strId.charAt(strId.length() - 2) - '0') * 10 + (strId.charAt(strId.length() - 1) - '0');
			if (twoNum % 5 == 2) {
				list.add(pList.get(i));
			}
		}
		Student student = new Student();
		int ranNum = new Random().nextInt(list.size());
		student.setStudentId(list.get(ranNum).getStudentId());
		student.setStudentName(list.get(ranNum).getStudentName());
		return student;
	}

	protected Student callActionByNumber(List<Student> pList, JFrame jfr) {
		List<Student> list = new LinkedList<>();
		for (int i = 0; i < pList.size(); i++) {
			int CalledNumber = pList.get(i).getLateNumber();

			for (int j = 0; j < CalledNumber + 1; j++) {
				list.add(pList.get(i));
			}
		}
		Student student = new Student();
		int ranNum = new Random().nextInt(list.size());
		student.setStudentId(list.get(ranNum).getStudentId());
		student.setStudentName(list.get(ranNum).getStudentName());
		return student;
	}

	//
	protected Student callOneAction(List<Student> pList, JFrame jfr) {
		Student student = new Student();
		int ranNum = new Random().nextInt(pList.size());
		student.setStudentId(pList.get(ranNum).getStudentId());
		student.setStudentName(pList.get(ranNum).getStudentName());
		return student;
	}
}
