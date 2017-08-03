package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DAOFactory;
import dao.PicDAO;
import dao.UserDAO;
import entity.Pic;
import entity.User;

public class ActionServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"), uri
				.lastIndexOf("."));
		if (action.equals("/regist")) {
			String username = request.getParameter("username");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String gender = request.getParameter("gender");
			String phone = request.getParameter("phone");
			String ask = request.getParameter("ask");
			try {
				UserDAO userDAO = DAOFactory.getUserDAO();
				User user = new User();
				user.setUsername(username);
				user.setPwd(pwd);
				user.setName(name);
				user.setAge(age);
				user.setGender(gender);
				user.setPhone(phone);
				user.setAsk(ask);
				userDAO.save(user);
				response.sendRedirect("login.jsp");

			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}

		} else if (action.equals("/login")) {
			String number1 = request.getParameter("number");
			HttpSession session = request.getSession();
			String number2 = (String) session.getAttribute("number");
			if (!number1.equalsIgnoreCase(number2)) {
				request.setAttribute("checkcode_error", "验证码错误");
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
				return;
			} else {
				String username = request.getParameter("username");
				String pwd = request.getParameter("pwd");
				try {
					UserDAO userDAO = DAOFactory.getUserDAO();
					User user = userDAO.findByUsername(username);
					if (user != null && user.getPwd().equals(pwd)) {
						session.setAttribute("loginUser", user);
						response.sendRedirect("list.do");
					} else {
						request.setAttribute("login_error", "用户名或者密码错误");
						request.getRequestDispatcher("login.jsp").forward(
								request, response);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
		} else if (action.equals("/list")) {
			try {
				UserDAO userDAO = DAOFactory.getUserDAO();
				List<User> users = userDAO.findAll();
				request.setAttribute("users", users);
				request.getRequestDispatcher("list.jsp").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		} else if (action.equals("/load")) {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				UserDAO userDAO = DAOFactory.getUserDAO();
				PicDAO picDAO=DAOFactory.getPicDAO();
				User detailUser = userDAO.findById(id);
				List<Pic> pics=picDAO.findByUserId(id);
				HttpSession session=request.getSession();
				session.setAttribute("pics", pics);
				session.setAttribute("detailUser", detailUser);
				request.getRequestDispatcher("detail.jsp").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}

		} else if (action.equals("/modify")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String pwd = request.getParameter("pwd");
			String phone = request.getParameter("phone");
			String ask = request.getParameter("ask");
			try {
				UserDAO userDAO = DAOFactory.getUserDAO();
				User user = userDAO.findById(id);
				user.setPwd(pwd);
				user.setPhone(phone);
				user.setAsk(ask);
				userDAO.modify(user);
				response.sendRedirect("list");
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		} else if (action.equals("/logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.jsp");
		} else if (action.equals("/check_username")) {
			String username = request.getParameter("username");
			try {
				Thread.sleep(2000);
				UserDAO dao = DAOFactory.getUserDAO();
				User user = dao.findByUsername(username);
				if (user == null) {
					out.print("ok");
				} else {
					out.print("error");
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}

		} else if (action.equals("/check_number")) {
			try {
				Thread.sleep(2000);
				String number1 = request.getParameter("number");
				HttpSession session = request.getSession();
				String number2 = (String) session.getAttribute("number");
				
				if (number2 != null && number1.equalsIgnoreCase(number2)) {
					out.print("ok");
				} else {
					out.print("error");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
