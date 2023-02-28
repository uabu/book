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
	<title>文章修改</title>
	<link rel="stylesheet" href="bs/css/bootstrap.css">
	<script type="text/javascript" src="bs/js/jquery.min.js"></script>
	<script type="text/javascript" src="bs/js/bootstrap.js"></script> 
	<link rel="stylesheet" type="text/css" href="bs/validform/style.css">
	<script type="text/javascript" src="bs/validform/Validform_v5.3.2_min.js"></script> 
	<script type="text/javascript" src="js/admin/userManage/userEdit.js"></script>
</head>
<body>
	<c:if test="${!empty userMessage}">
		<h3 class="text-center">${userMessage}</h3>
	</c:if>
	<div class="container">
		<h2 class="text-center">文章修改</h2>
		<form id="myForm" action="MsgServlet?action=update" method="post" class="form-horizontal">
			<input type="hidden" name="id" value="${messageInfo.id}">

             <div class="form-group">
				<label for="chapters" class="col-md-2 col-md-offset-2 control-label">阅读章节：</label>
				<div class="col-md-4">
					<input type="text" id="chapters" name="chapters" class="form-control" value="${messageInfo.chapters }">
				</div>
				<div class="col-md-4">
					<span class="Validform_checktip"></span>
				</div>
			</div>
			
				<div class="form-group">
				<label for="title" class="col-md-2 col-md-offset-2 control-label">章节名称：</label>
				<div class="col-md-4">
					<input type="text" id="title" name="title" class="form-control" value="${messageInfo.title }">
				</div>
				<div class="col-md-4">
					<span class="Validform_checktip"></span>
				</div>
			</div>
			<div class="form-group">
				<label for="content" class="col-md-2 col-md-offset-2 control-label">文章内容：</label>
				<div class="col-md-4">
				<textarea rows="10" cols="20" id="content" name = "content" >
${messageInfo.content}
						</textarea>
				</div>

			</div>
			
						
				<div class="form-group">
				<label for="title" class="col-md-2 col-md-offset-2 control-label">排序：</label>
				<div class="col-md-4">
					<input type="text" id="lbt" name="lbt" class="form-control" value="${messageInfo.lbt }">
				</div>
				<div class="col-md-4">
					<span class="Validform_checktip"></span>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-2  control-label col-md-offset-2">
					<input class="btn btn-success btn-block" type="submit" value="更改">
				</label>
				<label class="col-md-2 control-label">
					<input class="btn btn-warning btn-block" type="reset" value="重置">
				</label>
			</div>
		</form>
	</div>
</body>
</html>