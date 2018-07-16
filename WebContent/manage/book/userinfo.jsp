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
			<h3>用户信息</h3>
			
		</div>
		<hr>
		<div class="grid_8">
			<p>
				<label>账号：${online.lname}</label>
				
			</p>
			<p>
				<label>密码：${online.lpass}</label>
				
			</p>
			<p>
				<label>昵称：${online.uname}</label>
				
			</p>
		
		</div>
	</div>
</body>
</html>