package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * 页面控制器类
 * 前端请求处理类：servlet
 */
public class ActionServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置请求编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		// 匹配请求路径
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));

		// 当请求是从初始页面传来：index.do
		if (action.equals("/index")) {
			// session用于保存请求信息
			HttpSession session = request.getSession();
			// 获取前端请求输入的“用户名”
			String username = request.getParameter("username");

			// 页面逻辑传递
			try {
				// 声明一个getcity类
				GetCity getcity = new GetCity();
				// 获取所对应用户名在.xlxs表格中的所有城市
				List<City> cityList = getcity.findByUsername(username);
				if (!cityList.isEmpty()) {
					// 将获取的城市信息保存在上下文信息中，同时保存在请求信息中，传递给前端页面（mapMaker.jsp中）
					session.setAttribute("cityList", cityList);
					request.setAttribute("cityList", cityList);

					// 页面转到mapMaker.jsp页面
					request.getRequestDispatcher("mapMaker.jsp").forward(request, response);
				} else {
					// 如果没有获取到所对应的城市信息，则说明用户名输错，或者城市信息为空；输出提示信息，并将页面转到index.jsp初始页面
					request.setAttribute("index_error", "输入不正确或者不存在");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}
	}
}
