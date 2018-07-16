<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
    <head>
        <meta charset="utf-8">
        <title>图书出版管理系统</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="图书出版管理系统">
        <meta name="author" content="neuedu">
        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reset.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/supersized.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="page-container">
            <span style="font-size:18px;font-weight:bold;">欢迎使用图书管理系统</span>
            <form action="${pageContext.request.contextPath}/login" method="post">
            	
                <input type="text" name="username" class="username" placeholder="请输入用户名">
                <input type="password" name="password" class="password" placeholder="请输入密码">
                <input type="text" name="vcode"> <img id="img1" style="cursor:pointer" width="120" height="30" onclick="refreshImage();" src="${pageContext.request.contextPath}/getImage">
                <button type="submit">登录</button>
                <div class="error"><span>+</span></div>
            </form>
        </div>
        <!-- Javascript -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery-1.8.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/supersized.3.2.7.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/supersized-init.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>
        <script type="text/javascript">
        	function refreshImage(){
        		var d = new Date();
        		document.getElementById("img1").src="${pageContext.request.contextPath}/getImage?timestamp="+d.getTime();
        	}
        </script>

    </body>

</html>

