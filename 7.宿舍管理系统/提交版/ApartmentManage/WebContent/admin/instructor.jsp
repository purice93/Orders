<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function instructorDelete(instructorId) {
		if(confirm("您确定要删除这个辅导员吗？")) {
			window.location="instructor?action=delete&instructorId="+instructorId;
		}
	}
	$(document).ready(function(){
		$("ul li:eq(4)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			辅导员信息管理
		</div>
		<form name="myForm" class="form-search" method="post" action="instructor?action=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='instructor?action=preSave'">添加</button>
				<span class="data_search">
					名称:&nbsp;&nbsp;<input id="s_instructorName" name="s_instructorName" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_instructorName }">
					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th width="15%">辅导员编号</th>
						<th>辅导员姓名</th>
						<th>辅导员邮箱</th>
						<th width="20%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach  varStatus="i" var="instructor" items="${instructorList }">
					<tr>
						<td>${i.count+(page-1)*pageSize }</td>
						<td>${instructor.instructorName }</td>
						<td>${instructor.instructorEmail==null||instructor.instructorEmail==""?"无":instructor.instructorEmail }</td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='instructor?action=preSave&instructorId=${instructor.instructorId }'">修改</button>&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="instructorDelete(${instructor.instructorId})">删除</button></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red">${error }</font></div>
		<div class="pagination pagination-centered">
			<ul>
				${pageCode }
			</ul>
		</div>
</div>