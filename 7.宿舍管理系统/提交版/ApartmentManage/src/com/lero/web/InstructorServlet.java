package com.lero.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lero.dao.InstructorDao;
import com.lero.model.Instructor;
import com.lero.model.DormManager;
import com.lero.model.PageBean;
import com.lero.util.DbUtil;
import com.lero.util.PropertiesUtil;
import com.lero.util.StringUtil;

public class InstructorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	InstructorDao instructorDao = new InstructorDao();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String s_instructorName = request.getParameter("s_instructorName");
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		Instructor instructor = new Instructor();
		if ("preSave".equals(action)) {
			instructorPreSave(request, response);
			return;
		} else if ("save".equals(action)) {
			instructorSave(request, response);
			return;
		} else if ("delete".equals(action)) {
			instructorDelete(request, response);
			return;
		} else if ("manager".equals(action)) {
			instructorManager(request, response);
			return;
		} else if ("addManager".equals(action)) {
			instructorAddManager(request, response);
		} else if ("move".equals(action)) {
			managerMove(request, response);
		} else if ("list".equals(action)) {
			if (StringUtil.isNotEmpty(s_instructorName)) {
				instructor.setInstructorName(s_instructorName);
			}
			session.removeAttribute("s_instructorName");
			request.setAttribute("s_instructorName", s_instructorName);
		} else if ("search".equals(action)) {
			if (StringUtil.isNotEmpty(s_instructorName)) {
				instructor.setInstructorName(s_instructorName);
				session.setAttribute("s_instructorName", s_instructorName);
			} else {
				session.removeAttribute("s_instructorName");
			}
		} else {
			if (StringUtil.isNotEmpty(s_instructorName)) {
				instructor.setInstructorName(s_instructorName);
				session.setAttribute("s_instructorName", s_instructorName);
			}
			if (StringUtil.isEmpty(s_instructorName)) {
				Object o = session.getAttribute("s_instructorName");
				if (o != null) {
					instructor.setInstructorName((String) o);
				}
			}
		}
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		Connection con = null;
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		request.setAttribute("pageSize", pageBean.getPageSize());
		request.setAttribute("page", pageBean.getPage());
		try {
			con = dbUtil.getCon();
			List<Instructor> instructorList = instructorDao.instructorList(con,
					pageBean, instructor);
			int total = instructorDao.instructorCount(con, instructor);
			String pageCode = this.genPagation(total, Integer.parseInt(page),
					Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("instructorList", instructorList);
			request.setAttribute("mainPage", "admin/instructor.jsp");
			request.getRequestDispatcher("mainAdmin.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void managerMove(HttpServletRequest request,
			HttpServletResponse response) {
		String instructorId = request.getParameter("instructorId");
		String dormManagerId = request.getParameter("dormManagerId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			instructorDao.managerUpdateWithId(con, dormManagerId, "0");
			request.getRequestDispatcher(
					"instructor?action=manager&instructorId=" + instructorId)
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void instructorAddManager(HttpServletRequest request,
			HttpServletResponse response) {
		String instructorId = request.getParameter("instructorId");
		String dormManagerId = request.getParameter("dormManagerId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			instructorDao.managerUpdateWithId(con, dormManagerId, instructorId);
			request.getRequestDispatcher(
					"instructor?action=manager&instructorId=" + instructorId)
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void instructorManager(HttpServletRequest request,
			HttpServletResponse response) {
		String instructorId = request.getParameter("instructorId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			List<DormManager> managerListWithId = instructorDao
					.dormManWithBuildId(con, instructorId);
			List<DormManager> managerListToSelect = instructorDao
					.dormManWithoutBuild(con);
			request.setAttribute("instructorId", instructorId);
			request.setAttribute("managerListWithId", managerListWithId);
			request.setAttribute("managerListToSelect", managerListToSelect);
			request.setAttribute("mainPage", "admin/selectManager.jsp");
			request.getRequestDispatcher("mainAdmin.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void instructorDelete(HttpServletRequest request,
			HttpServletResponse response) {
		String instructorId = request.getParameter("instructorId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			instructorDao.instructorDelete(con, instructorId);
			request.getRequestDispatcher("instructor?action=list").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void instructorSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String instructorId = request.getParameter("instructorId");
		String instructorName = request.getParameter("instructorName");
		String instructorEmail = request.getParameter("instructorEmail");
		Instructor instructor = new Instructor(instructorName, instructorEmail);
		if (StringUtil.isNotEmpty(instructorId)) {
			instructor.setInstructorId(Integer.parseInt(instructorId));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNum = 0;
			if (StringUtil.isNotEmpty(instructorId)) {
				saveNum = instructorDao.instructorUpdate(con, instructor);
			} else {
				saveNum = instructorDao.instructorAdd(con, instructor);
			}
			if (saveNum > 0) {
				request.getRequestDispatcher("instructor?action=list").forward(
						request, response);
			} else {
				request.setAttribute("instructor", instructor);
				request.setAttribute("error", "数量为零");
				request.setAttribute("mainPage",
						"instructor/instructorSave.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void instructorPreSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String instructorId = request.getParameter("instructorId");
		if (StringUtil.isNotEmpty(instructorId)) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				Instructor instructor = instructorDao.instructorShow(con,
						instructorId);
				request.setAttribute("instructor", instructor);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("mainPage", "admin/instructorSave.jsp");
		request.getRequestDispatcher("mainAdmin.jsp")
				.forward(request, response);
	}

	private String genPagation(int totalNum, int currentPage, int pageSize) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize
				: totalNum / pageSize + 1;
		StringBuffer pageCode = new StringBuffer();
		pageCode.append("<li><a href='instructor?page=1'>首页</a></li>");
		if (currentPage == 1) {
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
		} else {
			pageCode.append("<li><a href='instructor?page=" + (currentPage - 1)
					+ "'>上一页</a></li>");
		}
		for (int i = currentPage - 2; i <= currentPage + 2; i++) {
			if (i < 1 || i > totalPage) {
				continue;
			}
			if (i == currentPage) {
				pageCode.append("<li class='active'><a href='#'>" + i
						+ "</a></li>");
			} else {
				pageCode.append("<li><a href='instructor?page=" + i + "'>" + i
						+ "</a></li>");
			}
		}
		if (currentPage == totalPage) {
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
		} else {
			pageCode.append("<li><a href='instructor?page=" + (currentPage + 1)
					+ "'>下一页</a></li>");
		}
		pageCode.append("<li><a href='instructor?page=" + totalPage
				+ "'>尾页</a></li>");
		return pageCode.toString();
	}

}
