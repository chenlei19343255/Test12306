<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="book" uri="/BookManagement"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书出版管理系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/960.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/template.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/colour.css" type="text/css" media="screen"/>
</head>
	<script type="text/javascript">
		function searchAuthor(pagenum){
			document.getElementById("pagenum").value=pagenum;
			document.getElementById("aform").submit();
		}
	</script>
<body>
	<jsp:include page="/manage/common/header.jsp"></jsp:include>
	<book:navi index="1"/>	
	<div id="content" class="container_16 clearfix">
		<form id="a1" action="#" method="post">
			<input type="hidden" id="authorid" name="authorid">
		</form>
		<form id="aform" action="${pageContext.request.contextPath}/s/alist" method="post">
			<input type="hidden" id="pagenum" name="pagenum">
			<div class="grid_4">
				<p>
					<label>作者<small>支持模糊查询</small></label>
					<input type="text" name="aname" value="${avo.aname }"/>
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
				<input type="button" value="新增" onclick="location.href='${pageContext.request.contextPath}/manage/author/addauthor.jsp'"/>
			</p>
		</div>
		<div class="grid_16">
			<table>
				<thead>
					<tr>
						<th>作者ID</th>
						<th>姓名</th>
						<th>笔名</th>
						<th>性别</th>
						<th>生日</th>
						<th>国家或地区</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="a" items="${alist}">
						<tr>
							
							<td>${a.authorid}</td>
							<td>${a.aname}</td>
							<td>${a.bname }</td>
							<td>
								<c:if test="${a.sex ==1}">男</c:if>
								<c:if test="${a.sex ==2}">女</c:if>
								<c:if test="${a.sex ==3}">保密</c:if>
							</td>
							<td>${a.birthday }</td>
							<td>${a.area.aname}</td>
							
							<td>
								<a href="#" onclick="displayAuthor(${a.authorid});">详细</a>
								<script type="text/javascript">
									function displayAuthor(authorid){
										document.getElementById("authorid").value=authorid;
										document.getElementById("a1").action="${pageContext.request.contextPath}/s/displayAuthor";
										document.getElementById("a1").submit();
									}
								</script>
								<a href="#" onclick="updateAuthor(${a.authorid});">修改</a>
								<script type="text/javascript">
									function updateAuthor(authorid){
										if(${online.power}==1){
											document.getElementById("authorid").value=authorid;
											document.getElementById("a1").action="${pageContext.request.contextPath}/updateAuthor";
											document.getElementById("a1").submit();	
										}
										
									}
								</script>
								<a href="#" onclick="delAuthor(${a.authorid});">删除</a>
								<script type="text/javascript">
									function delAuthor(authorid){
										if(${online.power}==1){
											if(confirm("确定要删除吗？")){
												document.getElementById("authorid").value=authorid;
												document.getElementById("a1").action="${pageContext.request.contextPath}/s/delAuthor";
												document.getElementById("a1").submit();
											}
										}
										
										
									}
								</script>
							</td>
						</tr>
						
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7" class="pagination">
							<a href="javascript:searchAuthor(1)">首页</a>
							<c:if test="${avo.pagenum>1 }">
								<a href="javascript:searchAuthor(${avo.pagenum-1 });">上一页</a>
							</c:if>
							<c:forEach var="i" begin="1" end="${maxpage }" step="1">
								<c:if test="${avo.pagenum==i }" >
									<a href="javascript:searchAuthor(${i})" class="active curved">${i }</a>
								</c:if>
								<c:if test="${avo.pagenum!=i }" >
									<a href="javascript:searchAuthor(${i})">${i }</a>
								</c:if>
							</c:forEach>
							<c:if test="${avo.pagenum<maxpage }">
								<a href="javascript:searchAuthor(${avo.pagenum+1 });">下一页</a>
							</c:if>
							<a href="javascript:searchAuthor(${maxpage })">尾页</a>
							
						</td>
						
					</tr>
				</tfoot>
			</table>
		</div>
	</div>	
</body>
</html>