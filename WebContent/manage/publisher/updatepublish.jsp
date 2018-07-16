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
			<h3>修改出版社信息</h3>
			
		</div>
		<hr>
		<div class="grid_8">
			<form action="${pageContext.request.contextPath }/s/modifyPublish" method="post">
				
				<p>
					<label>出版社全称：</label>
					<input type="text" name="pname" value="${ppo.pname }">
					
				</p>
				<p>
					<label>出版社简称：</label>
					<input type="text" name="sname" value="${ppo.sname }">
				</p>
				<p>
					<label>国家或地区：</label>
					<select name="arname">
						
						<c:forEach var="ar" items="${arlist}">
							<option <c:if test="${ar.aname==ppo.area.aname}">selected</c:if>>
							${ar.aname}
							</option>
						</c:forEach>
					</select>
					
				</p>
				<p>
					<label>办公电话：</label>
					<input type="text" name="tel" value="${ppo.tel}">
				</p>
				<p>
					<label>通信地址：</label>
					<input type="text" name="adress" value="${ppo.adress }">
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