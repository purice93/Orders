<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@page import="java.util.*,java.text.*"%>
<html>
	<head>
		<title>login</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
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
					<div id="navigation">
					</div>
				</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						login
					</h1>
					<form action="login.do" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									用户名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="username" />
									<%
										String msg1 = (String) request.getAttribute("login_error");
									%>
									<span style="color: red;"><%=(msg1 == null ? "" : msg1)%></span>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									密码:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="pwd" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									验证码:
									<img id="num" src="checkcode" />
									<a href="javascript:;"
										onclick="document.getElementById('num').src = 'checkcode?'+Math.random();">换一张</a>
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="number" />
									<%
										String msg2 = (String) request.getAttribute("checkcode_error");
									%>
									<span style="color: red;"><%=(msg2 == null ? "" : msg2)%></span>
								</td>

							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="&nbsp;确定&nbsp;" />
							<a href="regist.jsp">还没有帐户，请点击这儿注册</a>
						</p>
					</form>
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
