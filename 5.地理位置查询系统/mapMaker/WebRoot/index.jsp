<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@page import="java.util.*,java.text.*"%>
<html>
	<head>
		<title>输入用户名</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>

	<body>
		<div id="wrap">
			<div id="top_content">
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						请输入用户名
					</h1>
					
					<!-- action响应，index.do对应web.xml文件 -->
					<form action="index.do" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									用户名:
								</td>
								<td valign="middle" align="left">
									<!-- 提交后，传递username到后端 -->
									<input type="text" class="inputgri" name="username" />
									<%
										// 设置错误提示
										String msg1 = (String) request.getAttribute("index_error");
									%>
									<span style="color: red;"><%=(msg1 == null ? "" : msg1)%></span>
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="&nbsp;定位&nbsp;" />
						</p>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
