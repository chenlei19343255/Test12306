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
			<h3>新增作者</h3>
			<p id="info1" class="error" style="display:none;">新增失败</p>
			<p id="info2" class="success" style="display:none;">新增成功</p>
		</div>
		<form action="${pageContext.request.contextPath }/s/addauthor" method="post">
			<div class="grid_8">
				<p>
					<label>姓名<small>必须填写</small></label>
					<input type="text" name="aname">
				</p>
				<p>
					<label>笔名<small>必须填写</small></label>
					<input type="text" name="bname">
				</p>
				<p>
					<label>国家或地区<small>请选择国家或地区</small></label>
					<select name="arname">
						<option>=请选择=</option>
						<c:forEach var="ar" items="${arlist}">
							<option>${ar.aname}</option>
						</c:forEach>
					</select>
				</p>
				<p>
					<label>性别<small>必须填写</small></label>
					
					<input type="radio" name="sex" value="1">男
					<input type="radio" name="sex" value="2">女
					<input type="radio" name="sex" value="3">保密
					 
					
					
				</p>
				<p>
					<label>生日<small>必须填写</small></label>
					<input type="date" name="birthday">
				</p>
				<p>
					<label>电话号码<small>必须填写</small></label>
					<input type="text" name="phone">
				</p>
				<p>
					<label>通信地址<small>必须填写</small></label>
					<input type="text" name="adress">
				</p>
				<p>
					<input style="width:auto !important;" type="submit" value="提交"/>
					<input style="width:auto !important;" type="reset" value="重置"/>
					<input style="width:auto !important;" type="button" value="返回" onclick="history.back();"/>
				</p>
			</div>
		</form>
	
	</div>
</body>
</html>