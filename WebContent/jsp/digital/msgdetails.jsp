<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	pageContext.setAttribute("basePath", basePath);
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${basePath}">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>红色赓续</title>
	<link rel="stylesheet" href="bs/css/bootstrap.css">
	<script type="text/javascript" src="bs/js/jquery.min.js"></script>
	<script type="text/javascript" src="bs/js/bootstrap.js"></script>
	<script type="text/javascript" src="js/digital/landing.js"></script>
	<link href="css/digital/head_footer.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/digital/addcart.js"></script>
	<style type="text/css">
		.wrapper .pro_info{
			border-bottom: 1px #ccc solid;
			line-height: 34px;
			margin-top:20px;
		}
		.wrapper .pro_info tr td:first-child{
			font-weight: bold;

		}
		.wrapper .pro_info i{
			color:#333333;
			font-size:22px;
		}
		.wrapper .buy_pro{
			margin-top:20px;

		}
		.wrapper .pro_desc{
			margin:10px;
		}

		.wrapper .pro_desc h3{
			border-bottom: 1px #ccc solid;
			padding:10px;
		}
		.wrapper .pro_desc div{
			text-indent: 2em;
			line-height: 2em;
		}

	</style>
</head>
<body>

	<div class="container-fullid">
		<%@include file="header.jsp" %>
		<div class="wrapper">
			<!-- main start -->
			<div class="main container">
				<div class="sub-nav">
					<ol class="breadcrumb">
  						<li><a href="jsp/digital/index1.jsp">主页</a></li>
						<li><a href="#">${message.book.bookName}</a></li>
						<li class="active">${message.title}</li>
					</ol>
				</div>
			<div class="row" style="border: 1px #ccc solid;width: 100%;">

					<div class="col-md-7" style="margin-left: 44%;">
						<h3>阅读章节</h3>
						<h3>${messageInfo.chapters}</h3>
					</div>

				</div>

				<div class="row" style="border: 1px #ccc solid;width: 100%;">

					<div class="col-md-7" style="margin-left: 44%;">
						<h3>文章标题</h3>
						<h3>${messageInfo.title}</h3>
					</div>

				</div>
				<div class="row pro_desc" style="border: 1px #ccc solid;width: 100%;margin-left: -1%;">
					<h3  style="margin-left: 44%;border: 0px #ccc solid; ">文章内容</h3>
					<%-- <div>${messageInfo.content}</div> --%>
					 <div>${messageInfo.content}</div>
				</div>


				</div>
<!-- 分页栏 -->
								<ul class="pager row">
									<li><button class="prePage btn btn-sm btn-default">上一章</button></li>
									<li><button class="nextPage btn btn-sm btn-default">下一章</button></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
			</div>


		<%@include file="footer.jsp" %>
	</div>
<!--弹窗盒子start -->
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  	<div class="modal-dialog modal-sm">
    	<div class="modal-content" style="margin-top:300px;">
    		<div class="modal-body" style="color:green;font-size:20px;">
    		  	<img class="icon" src="images/digital/success-icon.png" alt="">
			  	 已成功加入购物车
			</div>

      		<div class="modal-footer">
      			<a href="javascript:void(0)" type="button" class="btn  btn-md btn-default" data-dismiss="modal">返回继续购物</a>
		        <a href="jsp/digital/cart.jsp" type="button" class="btn btn-md btn-success">立即去结算</a>
		    </div>
    	</div>
  	</div>
</div>
<!--弹窗盒子end -->
</body>
<script>

	//添加评论
	function addTalk() {
		$("#area").val();


	}
	//按钮禁用限制
	if("${pageBean.curPage}"==1){
		$(".homePage").attr("disabled","disabled");
		$(".prePage").attr("disabled","disabled");
	}
	if("${pageBean.curPage}"=="${pageBean.pageCount}"){
		$(".page-go").attr("disabled","disabled");
		$(".nextPage").attr("disabled","disabled");
		$(".lastPage").attr("disabled","disabled");
	}
	
	 

	//控制页面跳转范围
	$(".page-go").click(function(){
		var page=$("#page-input").val();
		var pageCount=${pageBean.pageCount};
		if(isNaN(page)||page.length<=0){
			$("#page-input").focus();
			alert("请输入数字页码");
			return;
		}
		if(page > pageCount || page < 1 ){
			alert("输入的页码超出范围！");
			$("#page-input").focus();
		}else{
			window.location="${basePath}jsp/admin/UserManageServlet?action=list&page="+page;
		}
	})

	//批量选中
	$("#batDelChoice").change(function(){
		if(!$("input[name='choice']").prop("checked")){
			$(this).prop("checked",true);
			$("input[name='choice']").prop("checked",true);

		}else{
			$(this).removeProp("checked");
			$("input[name='choice']").removeProp("checked");
		}
	})

</script>
</html>
