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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>文章增加</title>
	<link rel="stylesheet" href="bs/css/bootstrap.css"> 
	<script type="text/javascript" src="bs/js/jquery.min.js"></script>
	<script type="text/javascript" src="bs/js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" href="bs/validform/style.css">
	<script type="text/javascript" src="bs/validform/Validform_v5.3.2_min.js"></script> 
	<script type="text/javascript" src="js/admin/userManage/userAdd.js"></script>
	<style type="text/css">
		body{
			margin:0;
			padding:0;
			background:#eee;
		}
	</style>
</head>
<body>
	<c:if test="${!empty userMessage}">
		<h3 class="text-center">${userMessage}</h3>
	</c:if>
	<div class="container">
		
		<h2 class="text-center">文章增加</h2>
		<form id="myForm" action="MsgServlet?action=add" method="post" class="form-horizontal">
		<div class="form-group">
				<label for="chapters" class="col-md-2 col-md-offset-2 control-label">阅读章节：</label>
				<div class="col-md-4">
					<input name="chapters" id="chapters" type="text" class="form-control" >
				</div>
				<div class="col-md-4">
					<span class="Validform_checktip">*必填</span>
				</div>	
			</div>
			<div class="form-group">
				<label for="book" class="col-sm-2 col-sm-offset-2 control-label">所属图书</label>
				<div class="col-sm-4">
					<select name="book" id="book" class="form-control">
						<option value="">==请选择所属图书==</option>
						<c:if test="${!empty book}">
							<c:forEach items="${book}" var="i" >
								<option value="${i.bookId}">${i.bookName}</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>
			
			<div class="form-group">
				<label for="title" class="col-md-2 col-md-offset-2 control-label">文章标题：</label>
				<div class="col-md-4">
					<input name="title" id="title" type="text" class="form-control" >
				</div>
				<div class="col-md-4">
					<span class="Validform_checktip">*必填</span>
				</div>	
			</div>
			<div class="form-group">
				<label for="content" class="col-md-2 col-md-offset-2 control-label">文章内容：</label>
				<div class="col-md-4">
				<textarea rows="10" cols="20" id="content" name = "content">

						</textarea>
				</div>
				<div class="col-md-4">
					<span class="Validform_checktip">*必填</span>
				</div>
			</div>
				<div class="form-group">
				<label for="title" class="col-md-2 col-md-offset-2 control-label">排序：</label>
				<div class="col-md-4">
					<input name="lbt" id="lbt" type="text" class="form-control" >
				</div>
				<div class="col-md-4">
					<span class="Validform_checktip">*必填</span>
				</div>	
			</div>
			<div class="form-group">
				<label class="col-md-2  control-label col-md-offset-2">
					<input class="btn btn-success btn-block" type="submit" value="添加">
				</label>
				<label class="col-md-2 control-label">
					<input class="btn btn-warning btn-block" type="reset" value="重置">
				</label>
			</div>
		</form>
	</div>
</body>
</html>