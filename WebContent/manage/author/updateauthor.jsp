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
<script type="text/javascript">
	function init(){
		var sex = document.getElementsByName("sex");
		for(var i=0;i<sex.length;i++){
			if(sex[i].value==${apo.author.sex}){
				sex[i].checked=true;
				break;
			}
		}
	}
	
</script>
<body onload="init();">
	<jsp:include page="/manage/common/header.jsp"></jsp:include>
	<book:navi index="0"/>
	<div id="content" class="container_16 clearfix">
		<div class="grid_16">
			<h3>修改作者信息</h3>
			
		</div>
		<hr>
		<div class="grid_8">
			<form action="${pageContext.request.contextPath }/s/modifyAuthor" method="post">
				
				<p>
					<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
					<input type="text" name="aname" value="${apo.author.aname }">
					
				</p>
				<p>
					<label>笔&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
					<input type="text" name="abname" value="${apo.author.bname }">
				</p>
				<p>
					<label>国&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;家：</label>
					<select name="arname">
						
						<c:forEach var="ar" items="${arlist}">
							<option <c:if test="${ar.aname==apo.author.area.aname}">selected</c:if>>
							${ar.aname}
							</option>
						</c:forEach>
					</select>
					
				</p>
				<p>
					<label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
					<input type="radio" name="sex" value="1">男
					<input type="radio" name="sex" value="2">女
					<input type="radio" name="sex" value="3">保密
					
				</p>
				<p>
					<label>生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日：</label>
					<input type="date" name="birthday" value="${apo.author.birthday }">
				</p>
				
				<p>
					<label>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</label>
					<input type="text" name="phone" value="${apo.author.phone }">
				</p>
				<p>
					<label>通信地址：</label>
					<input type="text" name="adress" value="${apo.author.adress }">
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