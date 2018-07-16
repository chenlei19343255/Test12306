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
	<book:navi index="0"/>	
	<div id="content" class="container_16 clearfix">
		<div class="grid_16">
			<h3>新增图书</h3>
			<p id="info1" class="error" style="display:none;">新增失败</p>
			<p id="info2" class="success" style="display:none;">新增成功</p>
		</div>
		<form action="${pageContext.request.contextPath }/s/add" method="post">
			<div class="grid_8">
				<p>
					<label>书籍名称<small>必须填写</small></label>
					<input type="text" name="bname">
				</p>
				<p>
					<label>作者<small>请选择作者</small></label>
					<select name="aname">
						<option>=请选择=</option>
						<c:forEach var="a" items="${alist}">
							<option>${a.aname}</option>
						</c:forEach>
					</select>
				</p>
				<p>
					<label>出版社<small>请选择出版社</small></label>
					<select name="pname">
						<option>=请选择=</option>
						<c:forEach var="p" items="${plist}">
							<option>${p.pname}</option>
						</c:forEach>
					</select>
				</p>
				<p>
					<label>版次<small>必须填写</small></label>
					<input type="text" name="porder">
				</p>
				<p>
					<label>印刷量<small>必须填写</small></label>
					<input type="number" name="bnum">
				</p>
				<p>
					<label>出版时间<small>必须填写</small></label>
					<input type="date" name="pdate">
				</p>
				<p>
					<label>字数<small>必须填写</small></label>
					<input type="number" name="cnum">
				</p>
				<p>
					<label>单价<small>必须填写</small></label>
					<input type="number" name="price">
				</p>
				<p>
					<label>出版号<small>必须填写</small></label>
					<input type="text" name="pubnum">
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