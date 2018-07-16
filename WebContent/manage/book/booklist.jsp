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
		<form id="f1" action="#" method="post">
			<input type="hidden" id="bookid" name="bookid">
		</form>
		<form id="sform" action="${pageContext.request.contextPath }/s/blist" method="post">
			<input type="hidden" id="pagenum" name="pagenum">
			<div class="grid_4">
				<p>
					<label>书籍名称<small>支持模糊查询</small></label>
					<input type="text" name="bname" value="${bvo.bname }"/>
				</p>
			</div>
			<div class="grid_4">
				<p>
					<label>作者<small>支持模糊查询</small></label>
					<input type="text" name="aname" value="${bvo.aname }" />
				</p>
			</div>
			<div class="grid_4">
				<p>
					<label>出版社</label>
					  
					<select name="pname">
						<option>全部</option>
						<c:forEach var="p" items="${plist}">
							<option <c:if test="${p.pname==bvo.pname}">selected</c:if> >${p.pname}</option>
						</c:forEach>
					</select>
				</p>
			</div>
			<div class="grid_2">
				<p>
					<label>&nbsp;</label>
					<input type="submit" value="查找" />
				</p>
			</div>
		</form>
		<div class="grid_2">
			<p>
				<label>&nbsp;</label>
				<input type="button" value="新增" onclick="location.href='${pageContext.request.contextPath}/manage/book/addbook.jsp'"/>
			</p>
		</div>
		<div class="grid_16">
			<table>
				<thead>
					<tr>
						<th>图书ID</th>
						<th>图书名称</th>
						<th>作者</th>
						<th>出版社</th>
						<th>出版号</th>
						<th>版次</th>
						<th>单价</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="b" items="${blist}">
						<tr>
							
							<td>${b.book.bookid}</td>
							<td>${b.book.bname}</td>
							<td>${b.author.aname }</td>
							<td>${b.book.publish.pname }</td>
							<td>${b.book.pubnum }</td>
							<td>${b.book.border}</td>
							<td>${b.book.price}</td>
							<td>
								<a href="#" onclick="displayBook(${b.book.bookid});">详细</a>
								<script type="text/javascript">
									function displayBook(bookid){
										document.getElementById("bookid").value=bookid;
										document.getElementById("f1").action="${pageContext.request.contextPath}/s/display"
										document.getElementById("f1").submit();
									}
								</script>
								<a href="#" onclick="updateBook(${b.book.bookid});">修改</a>
								<script type="text/javascript">
									function updateBook(bookid){
										if(${online.power}==1){
											document.getElementById("bookid").value=bookid;
											document.getElementById("f1").action="${pageContext.request.contextPath}/s/update"
											document.getElementById("f1").submit();
										}
										
									}
								</script>
								<a href="#" onclick="delBook(${b.book.bookid});">删除</a>
								<script type="text/javascript">
									function delBook(bookid){
										if(${online.power}==1){
											if(confirm("确定要删除吗？")){
												document.getElementById("bookid").value=bookid;
												document.getElementById("f1").action="${pageContext.request.contextPath}/s/del"
												document.getElementById("f1").submit();
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
						<td colspan="8" class="pagination">
							<a href="javascript:doSearch(1)">首页</a>
							<c:if test="${bvo.pagenum>1 }">
								<a href="javascript:doSearch(${bvo.pagenum-1 })">上一页</a>
							</c:if>
							
							<c:forEach var="i" begin="1" end="${maxpage }" step="1">
								<c:if test="${bvo.pagenum==i }">
									<a href="javascript:doSearch(${i })" class="active curved">${i }</a>
								</c:if>
								<c:if test="${bvo.pagenum!=i }">
									<a href="javascript:doSearch(${i })">${i }</a>
								</c:if>
							</c:forEach>
							
							<c:if test="${bvo.pagenum<maxpage }">
								<a href="javascript:doSearch(${bvo.pagenum+1 })">下一页</a>
							</c:if>
							<a href="javascript:doSearch(${maxpage })">尾页</a>
						  
						
						<script type="text/javascript">
								
								function doSearch(pagenum){
									document.getElementById("pagenum").value=pagenum;
									document.getElementById("sform").submit();
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