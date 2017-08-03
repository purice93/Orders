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
    
    <title>来访信息录入成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function check(){
	var formvister=document.aaa;
		
	var visitor=document.aaa.visitor;
	var visitornumber=document.aaa.visitornumber;
	var visitorname=document.aaa.visitorname;
	var visitorconnection=document.aaa.visitorconnection;
	var thing=document.aaa.thing;
	var watch=document.aaa.watch;
		
	var r=/^[0-9]\d{0,8}[0-9]$/;
		
	//alert(visitor.value);
	if(!r.test(visitornumber.value)){	
		alert("来访人员人数为数字类型！");	
	}else if(visitor==null){
		alert("来访者姓名不允许为空！");
	}else{
		formvister.submit();
	}
}
</script>
</head>
  
<style type="text/css">
<!--
.STYLE11 {color: #FFCC00}
.STYLE14 {color: #FF0000; }
.STYLE15 {color: #000000; }
-->
</style>

<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#9999FF">&nbsp;</td>
  </tr>
</table>
<table width="760" height="50" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#00CCFF" class="border1">
  <tr>
    <td height="111"><form name="form1" method="post" action="">
      <input type="image" name="imageField" src="file:///D|/Workspaces/MyEclipse 8.5/graduation/src/com/css/images/p3.png" width="100%" height="100%">
        </form>
    </td>
  </tr>
</table>
<table width="760"  border="1" align="center" cellpadding="0" cellspacing="0" class="border1">
  <tr>
    <td height="29" background="/img/bg1.gif" bgcolor="#9999FF" class="padding" style="color:#FFFFFF ">
	<a href="http://127.0.0.1:8080/graduation/" class="a1">首页</a> | <a href="http://127.0.0.1:8080/graduation/admin/help.jsp" class="a1">帮助信息</a> | <a href="http://127.0.0.1:8080/graduation/admin/help.jsp" class="a1">作者信息</a> |  <a href="/bbsList.jsp" target="_blank" class="a1"></a></td>
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
 <form method="post" action="visitorentering.action" name="aaa">

  <table width="317" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#CCCCCC">

    <tr>

      <td height="24" colspan="2" align="center" bgcolor="#FF9900">来访人员信息录入</td>

    </tr>

    <tr>

      <td width="106" height="24" bgcolor="#FFFFFF">人员姓名：</td>

      <td width="192" height="24" bgcolor="#FFFFFF"><input id="visitor" name="visitor" type="text" size="20" maxlength="10"></td>

    </tr>

    <tr>

      <td height="24" bgcolor="#FFFFFF">人　　数</td>

      <td height="24" bgcolor="#FFFFFF"><input id="visitornumber" name="visitornumber" type="text"  size="20" maxlength="10"></td>

    </tr>

    <tr>

      <td height="24" bgcolor="#FFFFFF">被访者姓名：</td>

      <td height="24" bgcolor="#FFFFFF"><input id="visitorname" name="visitorname" type="text" size="20" maxlength="10"></td>
    </tr>

    <tr>

      <td height="24" bgcolor="#FFFFFF">所属关系：</td>

      <td height="24" bgcolor="#FFFFFF"><input id="visitorconnection" type="text" name="visitorconnection"></td>

    </tr>

    <tr>

      <td height="24" bgcolor="#FFFFFF">事　　由：</td>

      <td height="24" bgcolor="#FFFFFF"><input id="thing" type="text" name="thing"></td>

    </tr>

    <tr>

      <td height="24" bgcolor="#FFFFFF">值班人：</td>

      <td height="24" bgcolor="#FFFFFF"><input id="watch" type="text" name="watch"></td>

    </tr>

    <tr>

      <td height="24" colspan="2" align="center" bgcolor="#FFFFFF">

	  <input type="button" name="Submit" value="立即录入" onclick="check();">
	  
      <input type="reset" name="Submit2" value="重置">
      </td>

    </tr>

  </table>

</form>

<p align="center">&nbsp;</p></td>
  </tr>
</table>

<table width="760" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#eeeeee">
  <tr>
    <td height="20" align="center" bgcolor="#9999FF">公司地址：辽宁省大连市旅顺口区滨港路</td>
  </tr>
  <tr>
    <td height="20" align="center" bgcolor="#9999FF">联系电话：88888888 传真：88888888 邮编：888888</td>
  </tr>
  <tr>
    <td height="20" align="center" bgcolor="#9999FF">版权所有：大连科技学院</td>
  </tr>
</table>

</body>

</html>
