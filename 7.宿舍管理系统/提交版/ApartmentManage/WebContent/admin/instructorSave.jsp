<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var instructorName=document.getElementById("instructorName").value;
		if(instructorName==null||instructorName==""){
			document.getElementById("error").innerHTML="名称不能为空！";
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$("ul li:eq(3)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${instructor.instructorId!=null }">
				修改辅导员信息
			</c:when>
			<c:otherwise>
				添加辅导员信息
			</c:otherwise>
		</c:choose>
		</div>
		<form action="instructor?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="instructorId" name="instructorId" value="${instructor.instructorId }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>辅导员姓名：</td>
							<td><input type="text" id="instructorName"  name="instructorName" value="${instructor.instructorName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>辅导员邮箱：</td>
							<td><input type="text" id="instructorEmail"  name="instructorEmail" value="${instructor.instructorEmail }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
					</div>
					<div align="center">
						<font id="error" color="red">${error }</font>
					</div>
			</div>
		</form>
</div>