<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'adminshowall.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<style type="text/css">
<!--
.STYLE11 {
	color: #FFCC00
}

.STYLE14 {
	color: #FF0000;
}

.STYLE15 {
	color: #000000;
}
-->
</style>

<script type="text/javascript">

	
	function updateAdmin(id){
		if(confirm("确定要更新吗？")){
			window.location="updateAdmin.action?id="+id;
	}
	}
</script>

<body>
		<table width="760" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td bgcolor="#9999FF">&nbsp;
					
				</td>
			</tr>
		</table>
		<table width="760" height="50" border="1" align="center"
			cellpadding="0" cellspacing="0" bgcolor="#00CCFF" class="border1">
			<tr>
				<td height="111">
					<form name="form1" method="post" action="">
						<input type="image" name="imageField"
							src="file:///D|/Workspaces/MyEclipse 8.5/graduation/src/com/css/images/p3.png" width="100%" height="100%">
					</form>
				</td>
			</tr>
		</table>
		<table width="760" border="1" align="center" cellpadding="0"
			cellspacing="0" class="border1">
			<tr>
				<td height="29" background="/img/bg1.gif" bgcolor="#9999FF"
					class="padding" style="color: #FFFFFF">
					<a href="http://127.0.0.1:8080/graduation/" class="a1">首页</a> | <a href="http://127.0.0.1:8080/graduation/admin/help.jsp" class="a1">帮助信息</a> | <a href="http://127.0.0.1:8080/graduation/admin/help.jsp" class="a1">作者信息</a> |  <a href="/bbsList.jsp" target="_blank" class="a1"></a></td>
  </tr>
			</tr>
			<tr>
				<td height="4" bgcolor="#333333"></td>
			</tr>
		</table>

<table width="760" height="352" border="1" align="center"
			cellpadding="0" cellspacing="0" class="border1">
			<tr>
				<td width="526" height="350" valign="top">
					<table width="764" height="40" cellpadding="0" cellspacing="0"
						bordercolor="#FFFFFF">
						<tr>
							<td width="134">
								<form name="form2" method="post" action="adminLoginshowall.action">
									<input type="Submit" name="Submit4" value="操作员管理">
								</form>
							</td>
							<td width="156">
								<form name="form3" method="post" action="testa.action">
									<input type="Submit" name="Submit5" value="学生入住信息">
								</form>
							</td>
							<td width="146">
								<form name="form4" method="post" action="testb.action">
									<input type="Submit" name="Submit6" value="来访人员录入">
								</form>
							</td>
							<td width="150">
								<form name="form5" method="post" action="visitorshowall.action">
									<input type="Submit" name="Submit7" value="来访人员查询">
								</form>
							</td>
							<td width="174">
								<form action="adminshowall.action" name="form6" method="post">
									<input type="Submit" name="Submit8" value="显示学生寝室信息">
								</form>
							</td>
						</tr>

					</table>
					<table width="760" height="61" border="1" align="center" cellpadding="0"
						cellspacing="0">
		
						<tr>
									<td width="188" height="21" bordercolor="#99FFFF">
										<div align="center">
											管理员姓名
										</div>
						  </td>
									<td width="196" bordercolor="#99FFFF">
										<div align="center">
											管理员密码
										</div>
						  </td>
									<td width="86" bordercolor="#99FFFF">
										<div align="center">
											修改
										</div>
						  </td>
									<td width="280">

										<s:iterator value="list" var="list">
					  <tr>
												<td height="38" bordercolor="#99FFFF">
													<div align="center">
														<s:property value="#list.adminname" />
													</div>
												</td>
												<td bordercolor="#99FFFF">
													<div align="center">
														<s:property value="#list.adminpassword" />
													</div>
												</td>
												<td>
													<a
														href="javascript:updateAdmin('<s:property value="#list.id" />')">修改</a>
												</td>
					  </tr>
										</s:iterator>
	  </table>
	</td>

			</tr>

		</table>


		<table width="760" border="1" align="center" cellpadding="0"
			cellspacing="0" bgcolor="#eeeeee">
			<tr>
				<td height="20" align="center" bgcolor="#9999FF">
					公司地址：辽宁省大连市旅顺口区滨港路
				</td>
			</tr>
			<tr>
				<td height="20" align="center" bgcolor="#9999FF">
					联系电话：88888888 传真：88888888 邮编：888888
				</td>
			</tr>
			<tr>
				<td height="20" align="center" bgcolor="#9999FF">
					版权所有：大连科技学院
				</td>
			</tr>
		</table>
		
		
</body>



</html>
