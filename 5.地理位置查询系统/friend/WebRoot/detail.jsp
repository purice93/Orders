<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@page import="java.util.*,java.text.*,entity.*"%>
<html>
<head>
<title>detail</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
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
				<div id="navigation"></div>
			</div>

			<div id="content">
				<p id="whereami"></p>
				<h1>用户信息:</h1>
				<table class="table">
					<tr>
						<td>姓名</td>
						<td>电话</td>
					</tr>
					<%
						User detailUser = (User) session.getAttribute("detailUser");
					%>
					<tr>
						<td><%=detailUser.getName()%></td>
						<td><%=detailUser.getPhone()%></td>
					</tr>
				</table>
				<h1>对方要求:</h1>
				<table>
					<tr>
						<td colspan="2"><textarea cols="80"
								style="border: 0px; resize: none;"><%=detailUser.getAsk()%></textarea>
						</td>
					</tr>
				</table>
				<br />

				<%
					User loginUser = (User) session.getAttribute("loginUser");
					if (loginUser.getId() == detailUser.getId()) {
				%>
				<h1>上传照片:</h1>

				<form action="upload?id=<%=loginUser.getId()%>" method="post"
					enctype="multipart/form-data">
					Upload File Name: <input type="file" name="file1" /> <input
						type="submit" value="确定" />
				</form>
				<%
					}
				%>
				<h1>浏览照片:</h1>

				<table>
					<tr>
						<td>
							<%
								List<Pic> pics = (List<Pic>) session.getAttribute("pics");
								for (int i = 0; i < pics.size(); i++) {
									Pic pic = pics.get(i);
							%> 
							<img src="upload/pic_<%=detailUser.getId() + "/" + pic.getPicName()%>"/><br /> 
							<%
 							}
							 %>
						</td>
					</tr>
				</table>

			</div>
			<input type="button" onclick="location='list.do'" value="查看所有用户" />
		</div>
		<div id="footer">
			<div id="footer_bg">ABC@126.com</div>
		</div>
	</div>
</body>
</html>
