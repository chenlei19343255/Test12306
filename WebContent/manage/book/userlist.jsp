<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="book" uri="/BookManagement"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/960.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/template.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/colour.css" type="text/css" media="screen"/>
</head>
<body>
	<jsp:include page="/manage/common/header.jsp"></jsp:include>
	<book:navi index="2"/>		
	<div id="content" class="container_16 clearfix">
		<form id="u1" action="#" method="post">
			<input type="hidden" id="userid" name="userid">
		</form>
		<form id="uform" action="${pageContext.request.contextPath}/s/userinfo" method="post">
			<input type="hidden" id="pagenum" name="pagenum">
			<div class="grid_4">
				<p>
					<label>用户名称<small>支持模糊查询</small></label>
					<input type="text" name="uname" value="${uvo.uname}"/>
				</p>
			</div>
			<div class="grid_2">
				<p>
					<label>&nbsp;</label>
					<input type="submit" value="查找" />
				</p>
			</div>
		</form>
		<div class="grid_8">&nbsp;</div>
		<div class="grid_2">
			<p>
				<label>&nbsp;</label>
				<input type="button" value="新增" onclick="location.href='${pageContext.request.contextPath}/manage/publisher/addpublish.jsp'"/>
			</p>
		</div>
		<div class="grid_16">
			<table>
				<thead>
					<tr>
						<th>账号</th>
						<th>密码</th>
						<th>昵称</th>
						<th>权限</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="u" items="${ulist}">
						<tr>
							<td>${u.lname}</td>
							<td>${u.lpass}</td>
							<td>${u.uname }</td>
							<td>
								<c:if test="${u.power ==1}">管理员</c:if>
								<c:if test="${u.power ==2}">普通用户</c:if>
							</td>
							<td>
								<a href="#">详细</a>
								
								<a href="#" >修改</a>
								
								<a href="#" >删除</a>
								
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5" class="pagination">
							<a href="javascript:searchUser(1)">首页</a>
							<c:if test="${uvo.pagenum>1 }">
								<a href="javascript:searchUser(${uvo.pagenum-1 })">上一页</a>
							</c:if>
							<c:forEach var="i" begin="1" end="${maxpage }" step="1">
								<c:if test="${uvo.pagenum==i }">
									<a href="javascript:searchUser(${i })" class="active curved">${i }</a>
								</c:if>
								<c:if test="${uvo.pagenum!=i }">
									<a href="javascript:searchUser(${i })">${i }</a>
								</c:if>
							</c:forEach>
							<c:if test="${uvo.pagenum<maxpage }">
								<a href="javascript:searchUser(${uvo.pagenum+1 })">下一页</a>
							</c:if>
							<a href="javascript:searchUser(${maxpage })">尾页</a>
							
							<script type="text/javascript">
								function searchUser(pagenum){
									document.getElementById("pagenum").value=pagenum;
									document.getElementById("uform").submit();
								}
							</script>
							
						</td>
						
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>