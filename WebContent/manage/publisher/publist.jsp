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
<body>
	<jsp:include page="/manage/common/header.jsp"></jsp:include>
	<book:navi index="2"/>		
	<div id="content" class="container_16 clearfix">
		<form id="p1" action="#" method="post">
			<input type="hidden" id="pubid" name="pubid">
		</form>
		<form id="pform" action="${pageContext.request.contextPath}/s/plist" method="post">
			<input type="hidden" id="pagenum" name="pagenum">
			<div class="grid_4">
				<p>
					<label>出版社名称<small>支持模糊查询</small></label>
					<input type="text" name="pname" value="${pvo.pname }"/>
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
						<th>出版社ID</th>
						<th>出版社全称</th>
						<th>国家或地区</th>
						<th>办公电话</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${plist}">
						<tr>
							<td>${p.pubid}</td>
							<td>${p.pname}</td>
							<td>${p.area.aname }</td>
							<td>${p.tel }</td>
							<td>
								<a href="#" onclick="displayPublish(${p.pubid});">详细</a>
								<script type="text/javascript">
									function displayPublish(pubid){
										document.getElementById("pubid").value=pubid;
										document.getElementById("p1").action="${pageContext.request.contextPath}/s/displayPublish"
										document.getElementById("p1").submit();
									}
								</script>
								<a href="#" onclick="updatePublish(${p.pubid})">修改</a>
								<script type="text/javascript">
									function updatePublish(pubid){
										if(${online.power}==1){
											document.getElementById("pubid").value=pubid;
											document.getElementById("p1").action="${pageContext.request.contextPath}/s/updatePublish"
											document.getElementById("p1").submit();
										}
										
									}
								</script>
								<a href="#" onclick="delPublish(${p.pubid})">删除</a>
								<script type="text/javascript">
									function delPublish(pubid){
										if(${online.power}==1){
											if(confirm("确定要删除吗？")){
												document.getElementById("pubid").value=pubid;
												document.getElementById("p1").action="${pageContext.request.contextPath}/s/delPublish"
												document.getElementById("p1").submit();
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
						<td colspan="5" class="pagination">
							<a href="javascript:searchPublish(1)">首页</a>
							<c:if test="${pvo.pagenum>1 }">
								<a href="javascript:searchPublish(${pvo.pagenum-1 })">上一页</a>
							</c:if>
							<c:forEach var="i" begin="1" end="${maxpage }" step="1">
								<c:if test="${pvo.pagenum==i }">
									<a href="javascript:searchPublish(${i })" class="active curved">${i }</a>
								</c:if>
								<c:if test="${pvo.pagenum!=i }">
									<a href="javascript:searchPublish(${i })">${i }</a>
								</c:if>
							</c:forEach>
							<c:if test="${pvo.pagenum<maxpage }">
								<a href="javascript:searchPublish(${pvo.pagenum+1 })">下一页</a>
							</c:if>
							<a href="javascript:searchPublish(${maxpage })">尾页</a>
							
							<script type="text/javascript">
								function searchPublish(pagenum){
									document.getElementById("pagenum").value=pagenum;
									document.getElementById("pform").submit();
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