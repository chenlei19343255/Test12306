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
			<h3>书籍信息</h3>
			
		</div>
		<hr>
		<div class="grid_8">
			<p>
				<label>书籍名称：${bpo.book.bname}</label>
				
			</p>
			
			<label>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者：
				<c:forEach var="b" items="${batlist }">
					<li><b>${b.aname }&nbsp;&nbsp;&nbsp;${b.tname }</b></li>
				</c:forEach>
			</label>
			<p>
				<label>出&nbsp;&nbsp;版&nbsp;&nbsp;社：${bpo.book.publish.pname }</label>
				
			</p>
			<p>
				<label>版&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次：${bpo.book.border }</label>
				
			</p>
			<p>
				<label>印&nbsp;&nbsp;刷&nbsp;&nbsp;量：${bpo.book.bnum }</label>
				
			</p>
			<p>
				<label>出版时间：${bpo.book.pdate }</label>
				
			</p>
			<p>
				<label>字&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数：${bpo.book.cnum }</label>
				
			</p>
			<p>
				<label>单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：${bpo.book.price }</label>
				
			</p>
			<p>
				<label>出&nbsp;&nbsp;版&nbsp;&nbsp;号：${bpo.book.pubnum }</label>
				
			</p>
		
		</div>
	</div>
</body>
</html>