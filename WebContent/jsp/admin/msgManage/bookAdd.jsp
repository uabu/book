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
	<title>增加图书</title>
	<link rel="stylesheet" type="text/css" href="bs/css/bootstrap.css">
	<script type="text/javascript" src="bs/js/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="bs/validform/style.css">
	<script type="text/javascript" src="bs/validform/Validform_v5.3.2_min.js"></script> 
	<style type="text/css">
		body{
			margin:0;
			padding:0;
			background:#eee;
		}
		#catalogAddForm{
			margin-top:30px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			var form=$("#bookAddForm").Validform({
				tiptype:2,//validform初始化
			});
			
			form.addRule([
				{
					ele:"#bookName",
				    datatype:"*2-15",
				    ajaxurl:"jsp/admin/BookServlet?action=find",
				    nullmsg:"请输入商品分类名称！",
				    errormsg:"分类名称至少2个字符,最多15个字符！",
				}
			]);
		})
	</script>
</head>
<body>
	<div class="container">
		<c:if test="${!empty bookMessage}">
			<h3 class="text-center">${bookMessage}</h3>
		</c:if>
		<h2 class="text-center">增加图书</h2>
		<form id="bookAddForm" class="form-horizontal" action="jsp/admin/BookServlet?action=add" method="post">
			<div class="form-group">
				<label for="bookName" class="col-sm-2 col-sm-offset-2 control-label">图书名称</label>
				<div class="col-sm-4 ">
					<input type="text" name="bookName" id="bookName" class="form-control"/>
				</div>
				<div class="col-sm-4 Validform_checktip"></div>
			</div>
			<div class="form-group">
					<label class="col-sm-2 col-sm-offset-4 control-label">
						<input class="btn btn-success btn-block" type="submit" value="提交">
					</label>
					<label class="col-sm-2 control-label">
						<input class="btn btn-warning btn-block" type="reset" value="重置">
					</label>
					
				</div>
			
		</form>
		
	</div>
	
	
</body>
</html>