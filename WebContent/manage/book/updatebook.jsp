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
	<book:navi index="0"/>
	<div id="content" class="container_16 clearfix">
		<div class="grid_16">
			<h3>修改书籍信息</h3>
			
		</div>
		<hr>
		<div class="grid_8">
			<form action="${pageContext.request.contextPath }/s/modify" method="post">
				<p>
					<label>书籍名称：</label>
					<input type="text" name="bname" value="${bpo.book.bname}">
					
				</p>
				<p>
					<label>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者：</label>
					<select name="aname">
						
						<c:forEach var="a" items="${alist}">
							<option <c:if test="${a.aname==bpo.author.aname}">selected</c:if>>${a.aname}</option>
						</c:forEach>
					</select>
					
				</p>
				<p>
					<label>出&nbsp;&nbsp;版&nbsp;&nbsp;社：</label>
					<select name="pname">
						
						<c:forEach var="p" items="${plist}">
							<option <c:if test="${p.pname==bpo.book.publish.pname}">selected</c:if>>${p.pname}</option>
						</c:forEach>
					</select>
					
				</p>
				<p>
					<label>版&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次：</label>
					<input type="text" name="porder" value="${bpo.book.border }">
				</p>
				<p>
					<label>印&nbsp;&nbsp;刷&nbsp;&nbsp;量：</label>
					<input type="number" name="bnum" value="${bpo.book.bnum }">
				</p>
				<p>
					<label>出版时间：</label>
					<input type="date" name="pdate" value="${bpo.book.pdate }">
				</p>
				<p>
					<label>字&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数：</label>
					<input type="number" name="cnum" value="${bpo.book.cnum }">
				</p>
				<p>
					<label>单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</label>
					<input type="number" name="price" value="${bpo.book.price }">
				</p>
				<p>
					<label>出&nbsp;&nbsp;版&nbsp;&nbsp;号：</label>
					<input type="text" name="pubnum" value="${bpo.book.pubnum }">
				</p>
				
				<p>
					<input style="width:auto !important;" type="submit" value="提交"/>
					<input style="width:auto !important;" type="reset" value="重置"/>
					<input style="width:auto !important;" type="button" value="返回" onclick="history.back();"/>
				</p>
			</form>
		</div>
	</div>
</body>
</html>