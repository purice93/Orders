<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@page import="java.util.*,java.text.*,entity.*"%>
<html>
	<head>
		<title>list</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<%
			Object obj = session.getAttribute("loginUser");
			if (obj == null) {
				response.sendRedirect("login.jsp");
				return;
			}
		%>
		<div id="wrap">
			<div id="top_content">
				<div id="header">
					<div id="rightheader">
						<p>
							<%
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
								String datestr = sdf.format(date);
								out.println(datestr);
							%>
							<br />
						</p>
					</div>
					<div id="topheader">
						<h1 id="title">
							<a href="#">Main</a>
						</h1>
					</div>
					<div id="navigation">
					</div>
				</div>

				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						Welcome!
					</h1>

					<table class="table">
						<tr class="table_header">
							<td>
								ID
							</td>
							<td>
								Username
							</td>
							<td>
								Gender
							</td>
							<td>
								Age
							</td>
							<td>

							</td>
						</tr>
						<%
							List<User> users = (List<User>) request.getAttribute("users");
							for (int i = 0; i < users.size(); i++) {
								User user = users.get(i);
						%>
						<tr class="row<%=i % 2 + 1%>">
							<td>
								<%=user.getId()%>
							</td>
							<td>
								<%=user.getUsername()%>
							</td>
							<td>
								<%=user.getGender()%>
							</td>
							<td>
								<%=user.getAge()%>
							</td>
							<td>
								<a href="load.do?id=<%=user.getId()%>">详细</a>
							</td>
						</tr>
						<%
							}
						%>
					</table>
					<p>
						<input type="button" class="button" value="退出系统"
							onclick="location='logout.do'" />
					</p>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
					ABC@126.com
				</div>
			</div>
		</div>
	</body>
</html>
