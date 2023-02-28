<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();  
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
	<meta charset="UTF-8"> 
	<title>文章详情页</title>
	<link rel="stylesheet" href="bs/css/bootstrap.css">
	<style type="text/css">
		body{
			margin:0;
			padding:0;
			background:#eee;
		}
		 
		.container .row{
			line-height: 30px;
			htight:30px;
		}
		
	</style>
</head>
<body>
	<h2 class="text-center">文章详情</h2>
	<div class="container">
<div class="row">
			<div class="col-md-2 text-right">阅读章节</div>
			<div class="col-md-10">${messageInfo.chapters}</div>
		</div>
		<div class="row">
			<div class="col-md-2 text-right">所属图书</div>
			<div class="col-md-10">${messageInfo.book.bookName}</div>
		</div>
		<div class="row">
			<div class="col-md-2 text-right">章节名称</div>
			<div class="col-md-10">${messageInfo.title}</div>
		</div>
		
		<div class="row">
			<div class="col-md-2 text-right">文章内容</div>
			<div class="col-md-10">${messageInfo.content}</div>
		</div>


		
	</div>
</body>
</html>