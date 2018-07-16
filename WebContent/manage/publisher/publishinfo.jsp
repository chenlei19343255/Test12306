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
				<label>出版社全称：${ppo.pname }</label>
				
			</p>
			<p>
				<label>出版社简称：${ppo.sname}</label>
				
			</p>
			
			<p>
				<label>国家或地区：${ppo.area.aname }</label>
				
			</p>
			<label>出版作品：
				<c:forEach var="i" items="${bnamelist }">
					<li><b>${i}</b></li>
				</c:forEach>
			</label>
			<p>
				<label>办公电话：${ppo.tel }</label>
				
			</p>
			
			
			<p>
				<label>通信地址：${ppo.adress }</label>
				
			</p>
		
		</div>
	</div>
</body>
</html>