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
  						<li><a href="jsp/digital/index.jsp">主页</a></li>
						<li><a href="#">${digitalInfo.catalog.catalogName}</a></li>
						<li class="active">${digitalInfo.digitalName}</li>
					</ol>
				</div>
				<div class="row">
					<div class="col-md-5">
						<img class="img-responsive" src="${digitalInfo.upLoadImg.imgSrc}" />
					</div>
					<div class="col-md-7">
						<table class="pro_info">
								<tr>
									<td colspan="3"><h2>${digitalInfo.digitalName}</h2></td>
								</tr>
								<tr>
									<td>价格：</td>
									<td><i>￥${digitalInfo.price}</i></td>
								</tr>
								<tr>
									<td>商品编号：</td>
									<td>${digitalInfo.digitalId}</td>
								</tr>
								<tr>
									<td>商品分类：</td>
									<td>${digitalInfo.catalog.catalogName}</td>
								</tr>


								<tr>
									<td>上架日期：</td>
									<td>${digitalInfo.addTime}</td>
								</tr>
								<tr>
									<td>服务：</td>
									<td>由本商城发货,并提供售后服务,预计七天内送达，我们会全力加快为您服务！</td>
								</tr>
							</table>
						<p class="buy_pro">
								<a class="btn" style="border:1px solid black;" href="#">立即购买</a>
								<button type="button" class="btn" style="background:#333333;color:#fff;" onclick="addToCart(${digitalInfo.digitalId})" data-toggle="modal" data-target=".bs-example-modal-sm">
								<img class="cart-icon" src="images/digital/btn-cart-icon.png" alt="">
								加入购物车</button>
						</p>
						<p>温馨提示：支持7天无理由退货</p>
					</div>

				</div>
				<div class="row pro_desc">
					<h3>商品简介</h3>
					<div>${digitalInfo.description}</div>
				</div>
				<div class="row pro_desc">
				<table class="table table-striped table-hover">
					<tr class="success">
						<th>评论内容</th>
						<th>评论时间</th>
					</tr>
					<c:choose>
						<c:when  test="${!empty talkList}">
							<c:forEach items="${talkList}" var="i" varStatus="n">
								<tr>
									<td>${i.content}</td>
									<td>${i.time}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="8"><h4 class="text-center">暂无评论</h4></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
				</div>
				<div class="row pro_desc">
					<form id="myForm" action="digitaldetail?action=addtalk" method="post" class="form-horizontal">

					<h3>添加评论</h3>
					<div>


						<input type="hidden" value="${digitalInfo.digitalId}" name = "digitalId">
						<textarea rows="3" cols="20" id="content" name = "content">

						</textarea>
						<input class="btn btn-success btn-block" type="submit" value="添加">
					</div>
					</form>
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
