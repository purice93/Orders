package com.dao;
/*
 * 员工数据库操作类
 * 用于员工的增删改查
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.entity.Employee;
import com.windows.DBLink;

public class EmployeeDAO {

	// 查找员工：用于初始化窗口时，加载所有的信息到窗口中
	public List<Employee> selectAll() {
		List<Employee> employeeList = new LinkedList<Employee>();
		Employee employee = null;
		String burnRate = null;
		Connection conn = DBLink.getConn();
		String sql = "SELECT * FROM employees";

		PreparedStatement ptm = null;
		ResultSet rs = null;

		try {
			ptm = conn.prepareStatement(sql);
			rs = ptm.executeQuery();
			while (rs.next()) {
				employee = new Employee();
				employee.setEmployeeID(rs.getString("id"));
				employee.setName(rs.getString("name"));
				employee.setGender(rs.getString("gender"));
				employee.setAge(rs.getString("age"));
				employee.setBirthday(rs.getString("birthday"));
				employee.setPosition(rs.getString("position"));
				employee.setSalary(rs.getString("salary"));
				employeeList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}

		return employeeList; 
	}
	
	// 添加员工
	public boolean insert(Employee employee) {
		boolean flag = false;
		Connection conn = DBLink.getConn();
		String sql = "INSERT INTO employees (id,name,gender,age,birthday,position,salary) VALUES (?,?,?,?,?,?,?)";

		PreparedStatement ptm = null;
		try {
			ptm = conn.prepareStatement(sql);
			ptm.setString(1, employee.getEmployeeID());
			ptm.setString(2, employee.getName());
			ptm.setString(3, employee.getGender());
			ptm.setString(4, employee.getAge());
			ptm.setString(5, employee.getBirthday());
			ptm.setString(6, employee.getPosition());
			ptm.setString(7, employee.getSalary());
			flag = ptm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return flag;
	}

	// 删除员工
	public boolean delete(Employee employee) {
		boolean flag = false;
		Connection conn = DBLink.getConn();
		String sql = "DELETE FROM employees WHERE id = (?)";

		PreparedStatement ptm = null;
		try {
			ptm = conn.prepareStatement(sql);
			ptm.setString(1, employee.getEmployeeID());
			flag = ptm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return flag;
	}

	// 修改员工
	public boolean modify(Employee employee) {
		boolean flag = false;
		Connection conn = DBLink.getConn();
		String sql = "UPDATE employees SET name=(?),gender=(?),age=(?),birthday=(?),`position`=(?),salary=(?) WHERE id = (?)";

		PreparedStatement ptm = null;
		try {
			ptm = conn.prepareStatement(sql);
			ptm.setString(1, employee.getName());
			ptm.setString(2, employee.getGender());
			ptm.setString(3, employee.getAge());
			ptm.setString(4, employee.getBirthday());
			ptm.setString(5, employee.getPosition());
			ptm.setString(6, employee.getSalary());
			ptm.setString(7, employee.getEmployeeID());
			flag = ptm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return flag;
	}

	// 查找员工
	public List<Employee> select(Employee employee) {
		List<Employee> employeeList = new LinkedList<Employee>();
		Connection conn = DBLink.getConn();
		String sql = "SELECT * FROM employees WHERE name = (?)";

		PreparedStatement ptm = null;
		ResultSet rs = null;

		try {
			ptm = conn.prepareStatement(sql);
			ptm.setString(1, employee.getName());
			rs = ptm.executeQuery();
			while (rs.next()) {
				employee = new Employee();
				employee.setEmployeeID(rs.getString("id"));
				employee.setName(rs.getString("name"));
				employee.setGender(rs.getString("gender"));
				employee.setAge(rs.getString("age"));
				employee.setBirthday(rs.getString("birthday"));
				employee.setPosition(rs.getString("position"));
				employee.setSalary(rs.getString("salary"));
				employeeList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}

		return employeeList;
	}
}