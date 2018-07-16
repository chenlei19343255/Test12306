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
			<h3>作者信息</h3>
			
		</div>
		<hr>
		<div class="grid_8">
			
			<p>
				<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：${apo.author.aname }</label>
				
			</p>
			<p>
				<label>笔&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：${apo.author.bname }</label>
				
			</p>
			
			<label>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品：
					<c:forEach var="i" items="${bnamelist}">
						<li><b>${i}</b></li>
					</c:forEach>
			</label>
				
			
			
			<p>
				<label>国&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;家：${apo.author.area.aname }</label>
				
			</p>
			<p>
				<label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：
					<c:if test="${apo.author.sex == 1}">男</c:if>
					<c:if test="${apo.author.sex == 2}">女</c:if>
					<c:if test="${apo.author.sex == 3}">保密</c:if>
				</label>
				
			</p>
			<p>
				<label>生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日：${apo.author.birthday }</label>
				
			</p>
			
			<p>
				<label>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：${apo.author.phone }</label>
				
			</p>
			<p>
				<label>通信地址：${apo.author.adress }</label>
				
			</p>
		
		</div>
	</div>
</body>
</html>