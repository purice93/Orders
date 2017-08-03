/**
 * 主窗口
 *
 */

package com.windows;

import java.awt.BorderLayout;
import java.awt.FileDialog;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.dao.UserDao;
import com.entity.User;
import com.table.HaveScoreList;
import com.table.NoScoreList;
import com.table.OperationList;
import com.table.UserList;

public class AdmintratorWindow extends JFrame implements ActionListener {
	private static final int WINDOWWIDE = 1000;
	private static final int WINDOWHIGH = 640;

	private DefaultTableModel tableModel;
	private JTable table = null;
	private HSSFWorkbook hssfWorkbook;
	private HSSFRow hssfRow;

	private Frame f;
	private FileDialog fd;
	private File fileTxt = null;
	private File fileCsv = null;
	private JScrollPane scr = null;
	private UserDao userDao = new UserDao();
	private List<User> userList = new LinkedList<User>();

	//
	private JButton loadFileBtn = new JButton("读取原始文件");
	private JButton transferToCsvBtn = new JButton("转为CSV文件");
	private JButton intoDatabaseBtn = new JButton("将文件存入数据库");
	private JButton loadGradeBtn = new JButton("读取成绩单文件");
	private JButton showStepBtn = new JButton("显示操作流程表");
	private JButton haveScoreBtn = new JButton("显示登录信息/有成绩");
	private JButton noScoreBtn = new JButton("显示登录信息/无成绩");

	public AdmintratorWindow() {
		this.tableInformation();
		PublicWindowSet.windowAttribute(this, WINDOWWIDE, WINDOWHIGH, "ͨ报表转换系统");
	}

	// 图表窗口
	public void tableInformation() {

		// 定义7个按钮
		JPanel toolBar = new JPanel();
		toolBar.add(this.loadFileBtn);
		toolBar.add(this.transferToCsvBtn);
		toolBar.add(this.intoDatabaseBtn);
		toolBar.add(this.loadGradeBtn);
		toolBar.add(this.showStepBtn);
		toolBar.add(this.haveScoreBtn);
		toolBar.add(this.noScoreBtn);

		// 向主窗口中添加按钮
		this.add(toolBar, BorderLayout.NORTH);

		// 设置按钮监控
		this.loadFileBtn.addActionListener(this);
		this.transferToCsvBtn.addActionListener(this);
		this.intoDatabaseBtn.addActionListener(this);
		this.loadGradeBtn.addActionListener(this);
		this.showStepBtn.addActionListener(this);
		this.haveScoreBtn.addActionListener(this);
		this.noScoreBtn.addActionListener(this);

		// 定义一个图标窗口
		this.tableModel = new DefaultTableModel(new UserList().userInfo, new UserList().titles);
		this.table = new JTable(this.tableModel);
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);

		// 向主窗口中添加图表
		scr = new JScrollPane();
		scr.setViewportView(this.table);
		this.add(scr, BorderLayout.CENTER);

		// 初始化时清空图表信息
		while (this.tableModel.getRowCount() > 0) {
			this.tableModel.removeRow(this.tableModel.getRowCount() - 1);
		}
	}

	// 监控按钮响应
	public void actionPerformed(ActionEvent e) {

		// 1.读取原始文件
		if (e.getSource() == this.loadFileBtn) {
			// 定义一个读取文件对话框
			fd = new FileDialog(f, "Open", FileDialog.LOAD);
			fd.setVisible(true);

			// 读取txt文件
			try {
				fileTxt = new File(fd.getDirectory(), fd.getFile());
				BufferedReader br = new BufferedReader(new FileReader(fileTxt));
				String str = null;

				// 循环读取每行
				while ((str = br.readLine()) != null) {
					// 如果此行是空白行，那么就不进行解析了，忽略此行
					if (str.trim().equals("")) {
						continue;
					}

					// 声明一个正则表达式：一个或多个空格
					String[] temp = str.trim().split("\\s{2,}|\t");

					// 跳过前几个无效的几行信息，只读取字符串个数为12或11的行
					if (temp.length != 12 && temp.length != 11) {
						continue;
					}

					// 字符串个数为12时，读取5、6、7、8；字符串个数为11时，读取4、5、6、7；
					if (temp.length == 12) {
						User userTemp = new User();
						userTemp.setUsername(temp[5]);
						userTemp.setuDate(temp[6]);
						userTemp.setuTime(temp[7]);
						userTemp.setTcode(temp[8]);
						userList.add(userTemp);
					} else if (temp.length == 11) {
						User userTemp = new User();
						userTemp.setUsername(temp[4]);
						userTemp.setuDate(temp[5]);
						userTemp.setuTime(temp[6]);
						userTemp.setTcode(temp[7]);
						userList.add(userTemp);
					}
				}
				br.close();
				flushUserWindow(userList);
				PublicWindowSet.promptPopUp("读取原始文件成功!!!", "提示", this);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

		// 转为csv文件
		else if (e.getSource() == this.transferToCsvBtn) {
			try {
				fileCsv = new File("原始数据.csv");
				// 创建文件
				if (!fileCsv.exists()) {
					fileCsv.createNewFile();
				}
				hssfWorkbook = new HSSFWorkbook();
				// 创建sheet对象
				Sheet sheet1 = hssfWorkbook.createSheet("第一页");

				int rowNum = 0;
				Row row = (Row) sheet1.createRow(rowNum++);
				// 写入列数据
				// 首先写入标题行信息
				int colNum = 0;
				row.createCell(colNum++).setCellValue("username");
				row.createCell(colNum++).setCellValue("udate");
				row.createCell(colNum++).setCellValue("utime");
				row.createCell(colNum++).setCellValue("ucode");

				// 循环写入数据
				for (User user : userList) {
					row = (Row) sheet1.createRow(rowNum++);
					// 写入列数据
					colNum = 0;
					row.createCell(colNum++).setCellValue(user.getUsername());
					row.createCell(colNum++).setCellValue(user.getuDate());
					row.createCell(colNum++).setCellValue(user.getuTime());
					row.createCell(colNum++).setCellValue(user.getTcode());
				}
				// 创建文件流
				OutputStream stream = new FileOutputStream(fileCsv);
				// 写入数据
				hssfWorkbook.write(stream);
				// 关闭文件流
				stream.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			PublicWindowSet.promptPopUp("转为csv文件成功!!!", "提示", this);
		} 
		
		// 将文件存入数据库
		else if (e.getSource() == this.intoDatabaseBtn) {//
			// 导入数据库
			userDao = new UserDao();
			for (User user : userList) {
				userDao.insertUser(user);
			}
			PublicWindowSet.promptPopUp("将文件存入数据库成功!!!", "提示", this);
		} 
		
		// 读取成绩单文件
		else if (e.getSource() == this.loadGradeBtn) {
			// 写入分数表到数据库
			try {
				File fileGrade = new File("成绩单百分制.xls");
				InputStream instream = new FileInputStream(fileGrade);

				hssfWorkbook = new HSSFWorkbook(instream);

				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

				// 循环读入信息
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					hssfRow = hssfSheet.getRow(rowNum);
					User user = new User();
					hssfRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					hssfRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);

					user.setUsername(hssfRow.getCell(0).getStringCellValue());
					user.setGrade(Integer.parseInt(hssfRow.getCell(1).getStringCellValue()));
					new UserDao().insertGrade(user);
				}
				instream.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			PublicWindowSet.promptPopUp("读取成绩单文件成功!!!", "提示", this);
		} 
		
		// 显示操作流程表
		else if (e.getSource() == this.showStepBtn) {
			OperationList operationList = new OperationList();
			List<String> list = new ArrayList<>();
			Set<String> set = getTcodeNum(userList);
			Iterator<String> iterator = set.iterator();
			
			// 循环读取数据
			while (iterator.hasNext()) {
				list.add(iterator.next());
			}
			list.add(0, "username");// 插入一列用户名
			// 数据源
			List<List<String>> opList = getOperationData(userList, list);
			this.tableModel = new DefaultTableModel(operationList.OperationInfo, list.toArray());
			this.table = new JTable(this.tableModel);
			this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 设置可左右滑动，自适应宽度

			scr.setViewportView(table);

			flushWindowStep(opList);
			PublicWindowSet.promptPopUp("显示操作流程表成功!!!", "提示", this);
		} 
		
		// 显示登录信息/有成绩
		else if (e.getSource() == this.haveScoreBtn) {// 有分数表
			HaveScoreList haveScoreList = new HaveScoreList();
			List<String> list = new ArrayList<>();
			// 数据源
			List<List<String>> HaveScoreLists = null;
			try {
				HaveScoreLists = getHaveScoreData(userList);
			} catch (NumberFormatException | ParseException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.tableModel = new DefaultTableModel(haveScoreList.scoreInfo, haveScoreList.titles);
			this.table = new JTable(this.tableModel);
			this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 设置可左右滑动，自适应宽度
			scr.setViewportView(table);
			flushWindowScore(HaveScoreLists);
			PublicWindowSet.promptPopUp("显示登录信息/有成绩成功!!!", "提示", this);
		} 
		
		// 显示登录信息/无成绩
		else if (e.getSource() == this.noScoreBtn) {// 无分数表
			NoScoreList noScoreList = new NoScoreList();
			List<String> list = new ArrayList<>();
			// 数据源
			List<List<String>> noScoreLists = null;
			try {
				noScoreLists = getNoScoreData(userList);
			} catch (NumberFormatException | ParseException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.tableModel = new DefaultTableModel(noScoreList.scoreInfo, noScoreList.titles);
			this.table = new JTable(this.tableModel);
			this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 设置可左右滑动，自适应宽度

			scr.setViewportView(table);

			flushWindowScore(noScoreLists);
			PublicWindowSet.promptPopUp("显示登录信息/无成绩成功!!!", "提示", this);
		}
	}

	// 获取有成绩信息数据
	private List<List<String>> getHaveScoreData(List<User> userList2)
			throws ParseException, NumberFormatException, SQLException {
		// TODO Auto-generated method stub
		List<List<String>> list = new LinkedList<>();
		List<String> subList = null;

		// 获取所有的用户名列表
		Set<String> usernameSet = getUsernameList(userList2);
		Iterator<String> iterator = usernameSet.iterator();

		System.out.println(usernameSet.size());
		// 1.遍历用户名
		int num = 0;
		while (iterator.hasNext()) {
			subList = new LinkedList<>();
			String username = iterator.next();
			subList.add(username);
			System.out.println("1" + username + num++);

			int stepnum = 0;// 操作次数
			int date = 0;// 登陆次数
			long diff = 0;// 登录时长(毫秒)
			int time = 0;// 登录时长(小时)
			int grade = 0;// 分数

			// 2.遍历每个用户
			Set<String> dateSet = new HashSet<>();
			for (int i = 0; i < userList2.size(); i++) {
				User user = userList2.get(i);
				if (user.getUsername().equals(username)) {
					stepnum++;// 1.累计操作次数
					dateSet.add(user.getuDate());// 记录登陆的日期
				}
				date = dateSet.size();// 2.登陆次数

			}
			// 3.登录时长
			time = getTimeSum(userList2, dateSet, username);

			// 4.得到成绩
			String gradeStr = userDao.selectScoreByUsername(username);
			if (gradeStr != null) {
				grade = Integer.parseInt(gradeStr);
			}

			subList.add(String.valueOf(stepnum));
			subList.add(String.valueOf(date));
			subList.add(String.valueOf(time));
			subList.add(String.valueOf(grade));
			System.out.println(subList.toString());
			list.add(subList);
		}
		return list;
	}

	// 查找无成绩信息
	private List<List<String>> getNoScoreData(List<User> userList2)
			throws ParseException, NumberFormatException, SQLException {
		// TODO Auto-generated method stub
		List<List<String>> list = new LinkedList<>();
		List<String> subList = null;

		// 获取所有的用户名列表
		Set<String> usernameSet = getUsernameList(userList2);
		Iterator<String> iterator = usernameSet.iterator();

		System.out.println(usernameSet.size());
		// 1.
		int num = 0;
		while (iterator.hasNext()) {
			subList = new LinkedList<>();
			String username = iterator.next();
			subList.add(username);
			System.out.println("1" + username + num++);

			int stepnum = 0;// 操作次数
			int date = 0;// 登陆次数
			long diff = 0;// 登录时长(毫秒)
			int time = 0;// 登录时长(小时)

			// 2.遍历每个用户
			Set<String> dateSet = new HashSet<>();
			for (int i = 0; i < userList2.size(); i++) {
				User user = userList2.get(i);
				if (user.getUsername().equals(username)) {
					stepnum++;// 1.累计操作次数
					dateSet.add(user.getuDate());// 记录登陆的日期
				}
				date = dateSet.size();// 2.登陆次数

			}
			// 3.登录时长
			time = getTimeSum(userList2, dateSet, username);

			subList.add(String.valueOf(stepnum));
			subList.add(String.valueOf(date));
			subList.add(String.valueOf(time));
			System.out.println(subList.toString());
			list.add(subList);
		}
		return list;
	}

	
	// 根据用户名计算登录总时长
	private int getTimeSum(List<User> userList2, Set<String> dateSet, String username) throws ParseException {
		// TODO Auto-generated method stub
		// 3.登陆总时长
		long diff = 0;
		Iterator<String> dateiter = dateSet.iterator();
		List<Date> timeList = new ArrayList<>();
		while (dateiter.hasNext()) {
			String dateStr = dateiter.next();
			int num = 0;
			for (int j = 0; j < userList2.size(); j++) {
				User user2 = userList2.get(j);
				if (user2.getUsername().equals(username) && user2.getuDate().equals(dateStr)) {
					String timeStr = user2.getuTime();// 得到具体时间
					Date dTime = null;
					SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
					dTime = formatter.parse(timeStr);
					timeList.add(dTime);
				}
			}
			// 排序
			Collections.sort(timeList);
			Date firstDate = timeList.get(0);
			Date lastDate = timeList.get(timeList.size() - 1);
			diff += lastDate.getTime() - firstDate.getTime();// 这样得到的差值是微秒级别
		}
		int time = (int) (diff / (1000 * 60 * 60));// 得到小时
		return time;
	}

	
	// 获取操作流程数据
	private List<List<String>> getOperationData(List<User> userList2, List list2) {
		// TODO Auto-generated method stub
		List<List<String>> list = new LinkedList<>();
		List<String> subList = null;

		// 获取所有的用户名列表
		Set<String> usernameSet = getUsernameList(userList2);
		Iterator<String> iterator = usernameSet.iterator();
		while (iterator.hasNext()) {
			Iterator<String> iterator2 = list2.iterator();
			subList = new LinkedList<>();
			String username = iterator.next();
			subList.add(username);
			while (iterator2.hasNext()) {
				String tcode = iterator2.next();
				int tcodeNum = 0;
				for (User user : userList2) {
					if (user.getUsername().equals(username) && user.getTcode().equals(tcode)) {
						tcodeNum++;
					}
				}
				subList.add(String.valueOf(tcodeNum));
			}
			list.add(subList);
		}

		return list;
	}

	// 获取所有的用户，set不重复且排序
	private Set<String> getUsernameList(List<User> userList) {
		// TODO Auto-generated method stub
		// 不重复且保持先后顺序
		Set<String> set = new LinkedHashSet<String>();
		for (int i = 0; i < userList.size(); i++) {
			set.add(userList.get(i).getUsername());
		}
		return set;
	}

	// 获取所有的操作类型
	private Set<String> getTcodeNum(List<User> userList) {
		// TODO Auto-generated method stub
		// 不重复且保持先后顺序
		Set<String> set = new LinkedHashSet<String>();
		for (int i = 0; i < userList.size(); i++) {
			set.add(userList.get(i).getTcode());
		}
		return set;
	}

	// 刷新原始信息表窗口
	private void flushUserWindow(List<User> userList) {
		// TODO Auto-generated method stub
		while (this.tableModel.getRowCount() > 0) {
			this.tableModel.removeRow(this.tableModel.getRowCount() - 1);
		}
		User user = new User();
		for (int i = 0; i < userList.size(); i++) {
			user = userList.get(i);
			this.tableModel
					.addRow(new Object[] { user.getUsername(), user.getuDate(), user.getuTime(), user.getTcode() });
		}
	}

	// 刷新流程表窗口
	public void flushWindowStep(List<List<String>> opList) {
		while (this.tableModel.getRowCount() > 0) {
			this.tableModel.removeRow(this.tableModel.getRowCount() - 1);
		}
		List<String> list = new LinkedList();
		for (int i = 0; i < opList.size(); i++) {
			list = opList.get(i);
			this.tableModel.addRow(list.toArray());
		}
	}

	// 刷新有/无成绩表信息
	private void flushWindowScore(List<List<String>> scoreLists) {
		while (this.tableModel.getRowCount() > 0) {
			this.tableModel.removeRow(this.tableModel.getRowCount() - 1);
		}
		List<String> list = new LinkedList();
		for (int i = 0; i < scoreLists.size(); i++) {
			list = scoreLists.get(i);
			this.tableModel.addRow(list.toArray());
		}
	}
}
