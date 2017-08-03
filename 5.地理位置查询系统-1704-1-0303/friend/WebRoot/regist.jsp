<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@page import="java.util.*,java.text.*"%>
<html>
	<head>
		<title>register</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script type="text/javascript" src="js/my.js">
		</script>
		<script type="text/javascript" src="js/prototype-1.6.0.3.js">
		</script>
		<script type="text/javascript">
			function check_number(){
				$('number_msg').innerHTML='';
				var flag=false;
				var xhr=getXhr();
				xhr.open('post','check_number.do',false);
				xhr.setRequestHeader('content-type','application/x-www-form-urlencoded');
				xhr.onreadystatechange=function(){
					if(xhr.readyState==4){
					var txt=xhr.responseText;
						if(txt=='ok'){
							$('number_msg').style.color='green';
							$('number_msg').innerHTML='验证码正确';
							flag=true;
						}else{
							$('number_msg').style.color='red';
							$('number_msg').innerHTML='验证码错误';
							flag=false;
						}
					}
				
				};
				$('number_msg').style.color='purple';
				$('number_msg').innerHTML='正在验证';
				xhr.send('number='+$F('number'));
				return flag;
			}
			function check_username(){
				//每次运行前清空前一次信息
				$('username_msg').innerHTML='';
				if($F('username').strip().length==0){
				//用户名为空
				$('username_msg').style.color='red';
				$('username_msg').innerHTML='用户名不能为空';
				return false;
				}
				//检查用户名是否被占用
				var flag=false;
				var xhr=getXhr();
				xhr.open('post','check_username.do',false);
				xhr.setRequestHeader('content-type','application/x-www-form-urlencoded');
				xhr.onreadystatechange=function(){
					if(xhr.readyState==4){
					var txt=xhr.responseText;
						if(txt=='ok'){
							$('username_msg').style.color='green';
							$('username_msg').innerHTML='用户名可以使用';
							flag=true;
						}else{
							$('username_msg').style.color='red';
							$('username_msg').innerHTML='用户名被占用';
							flag=false;
						}
					}
				};
				$('username_msg').style.color='purple';
				$('username_msg').innerHTML='正在验证';
				xhr.send('username='+$F('username'));
				return flag;
			}
			function beforeSubmit(){
				var flag=check_username()&&check_number();
				return flag;
			}
		
		</script>
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
						注册
					</h1>
					<form action="regist.do" method="post" onsubmit="return beforeSubmit();">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									用户名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="username"id="username" onblur="check_username();"/><span id="username_msg"></span>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									真实姓名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="name" />
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
									年龄:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="age" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									性别:
								</td>
								<td valign="middle" align="left">
									男
									<input type="radio" class="inputgri" name="gender" value="m"
										checked="checked" />
									女
									<input type="radio" class="inputgri" name="gender" value="f" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									电话:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="phone" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									对方要求:
								</td>
								<td valign="middle" align="left">
									<textarea rows="5" cols="30" name="ask" style="resize: none;"></textarea>
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
									<input type="text" class="inputgri" name="number" id="number" onblur="check_number();"/><span id="number_msg"></span>
								</td>

							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="确定" />
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
