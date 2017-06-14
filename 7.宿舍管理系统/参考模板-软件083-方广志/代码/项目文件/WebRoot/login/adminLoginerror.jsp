<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录失败，请重新登录！</title>
    
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
.STYLE1 {font-family: "宋体"}
.STYLE7 {font-size: 12px}
.STYLE10 {font-size: medium}
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
    <td height="26" background="/img/bg1.gif" bgcolor="#9999FF" class="padding" style="color:#FFFFFF ">
	<a href="http://127.0.0.1:8080/graduation/" class="a1">首页</a> | <a href="http://127.0.0.1:8080/graduation/admin/help.jsp" class="a1">帮助信息</a> | <a href="http://127.0.0.1:8080/graduation/admin/help.jsp" class="a1">作者信息</a> |  <a href="/bbsList.jsp" target="_blank" class="a1"></a></td>
  </tr>
  <tr>
    <td height="4" bgcolor="#333333"></td>
  </tr>
</table>

<table width="760" border="1" align="center" cellpadding="0" cellspacing="0" class="border1">
  <tr>
    <td width="216" valign="top"><form action="login.action" method="post" class="STYLE1">
	  
	  <table width="271" height="183" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="271" height="28" align="center" bgcolor="#FFFFFF" class="white">   <p>登录失败，请重新登录！  <br>
     <a href="index.jsp">返回</a>
       </p>&nbsp;</td>
    </tr>
  </table>
	 
</form>


</td>
    <td width="10" bgcolor="#CCCCCC"><span class="STYLE1"></span></td>
    <td width="526" valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" align="center" background="" bgcolor="#0099FF" class="STYLE1">公寓简介</td>
      </tr>
      <tr>
        <td height="3" bgcolor="#000033" class="STYLE1"></td>
      </tr>
    </table>
      <table width="100%" height="138"  border="0" cellpadding="0" cellspacing="0">
        <tr align="center">
          <td width="46%" height="138" class="STYLE1">          <img src="file:///D|/Workspaces/MyEclipse 8.5/graduation/src/com/css/images/p4.png" width="314" height="192"></td>
          <td width="54%" class="STYLE1"> 　　
          　　
          <div align="center">　　大连科技学院公寓不仅为学生提供了栖身之地，也是学生学习生活的重要场所。在做好卫生、安全、防火、防盗、防疫情等工作义不容辞。</div></td>
        </tr>
      </table>
      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="24" align="center" background="" bgcolor="#0099FF" class="STYLE1">公寓动态</td>
        </tr>
        <tr>
          <td height="3" bgcolor="#000033" class="STYLE1"></td>
        </tr>
      </table>
	  
	  <span class="STYLE1">
	  <!--显示前8条新闻-->
      </span>
	  <table width="100%"  border="0" cellspacing="0" cellpadding="2">
	  
        <tr>		
          <td width="50%" height="24" class="border2 STYLE1">
		  <img src="" width="6" height="6"> <a href="untitled1.html">我国大学生公寓建设发展历程</a> </td>
		  
          <td width="50%" height="24" class="border2 STYLE1">
	      <img src="" width="6" height="6"> <a href="untitled1.html">目前大学生公寓的特点		  </a></td>
        </tr>
	  
        <tr>		
          <td width="50%" height="24" class="border2 STYLE1">
		  <img src="" width="6" height="6"> <a href="untitled1.html">大学生公寓的基本构成要素</a></td>
		  
          <td width="50%" height="24" class="border2 STYLE1">
	      <img src="" width="6" height="6"> <a href="untitled1.html">大学生公寓空间的功能构成</a> </td>
        </tr>
	  
        <tr>		
          <td width="50%" height="24" class="border2 STYLE1">
			  <img src="" width="6" height="6">
		  <a href="shownews.asp?id=32" target="_blank"></a>		  <A href="/article.asp?articleid=779" target="_blank">走进第四届“寝室”文化节</A></td>
		  
          <td width="50%" height="24" class="border2 STYLE1">
		      <img src="" width="6" height="6">
		  <a href="shownews.asp?id=31" target="_blank"></a>		  <A href="/article.asp?articleid=723" target="_blank">如何防止学生宿舍被盗？</A></td>
        </tr>
      </table>
	  
      <table width="100%"  border="0" cellspacing="0" cellpadding="2">
        <tr>
          <td height="24" align="right" class="STYLE1"><a href="" class="STYLE7">查看全部&gt;&gt;&gt;&gt;</a></td>
        </tr>
      </table>
      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="24" align="center" background="" bgcolor="#0099FF" class="STYLE1">公寓新闻</td>
        </tr>
        <tr>
          <td height="3" bgcolor="#000033" class="STYLE1"></td>
        </tr>
      </table>
	  
	  <span class="STYLE1">
	  <!--最新6个产品-->
	  </span>
	  <table width="100%"  border="1" cellspacing="0" cellpadding="0">
	
        <tr valign="top">
		
          <td width="50%" class="STYLE1">
		  
		     <table width="100%"  border="0" cellspacing="0" cellpadding="2">
				<tr align="center">
				  <td height="24" colspan="2" bgcolor="#eeeeee">
					  <a href="showProd.asp?id=20" target="_blank">公寓安全</a>				  </td>
			  </tr>
				<tr>
				  <td width="44%" rowspan="3" align="center"><img src="file:///D|/Workspaces/MyEclipse 8.5/graduation/src/com/css/images/p2.png" width="104" height="161"></td>
				  <td width="56%" height="48"><A href="/article.asp?articleid=894" target="_blank">专家教你如何辨别黑心棉</A></td>
				</tr>
				<tr>
				  <td height="38"><A href="/article.asp?articleid=920" target="_blank">校园消防知识抢答赛</A></td>
				</tr>
				<tr>
				  <td height="60"><A href="/article.asp?articleid=723" target="_blank">如何防止学生宿舍被盗？</A></td>
				</tr>
		    </table>		  </td>
          <td width="50%" class="STYLE1">
		  
			  <table width="100%"  border="0" cellspacing="0" cellpadding="2">
				<tr align="center">
				  <td height="24" colspan="2" bgcolor="#eeeeee">
					  <a href="showProd.asp?id=19" target="_blank">公寓文化</a>				  </td>
				</tr>
				<tr>
				  <td width="44%" rowspan="3" align="center"><img src="file:///D|/Workspaces/MyEclipse 8.5/graduation/src/com/css/images/p5.png" width="104" height="161"></td>
				  <td width="56%" height="37"><A href="/article.asp?articleid=924" target="_blank">大学生宿舍新闻联播</A></td>
				</tr>
				<tr>
				  <td height="49"><A href="/article.asp?articleid=778" target="_blank">大学生寝室文化建设的探索</A></td>
				</tr>
				<tr>
				  <td height="60"><A href="/article.asp?articleid=770" target="_blank">青少年心理问题的不同本质</A></td>
				</tr>
			  </table>		  </td>
        </tr>
      </table>
	   
      <table width="100%"  border="0" cellspacing="0" cellpadding="2">
        <tr>
          <td height="24" align="right" class="STYLE7"><a href="" class="STYLE7">查看全部&gt;&gt;&gt;&gt;</a></td>
        </tr>
    </table></td>
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
