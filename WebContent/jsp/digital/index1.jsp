<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="bean.Digital" %>
<%@ page import="dao.DigitalDao" %>
<%@ page import="dao.impl.DigitalDaoImpl" %>
<%@ page import="bean.Book" %>
<%@ page import="dao.BookDao" %>
<%@ page import="dao.impl.BookDaoImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   DigitalDao bd = new DigitalDaoImpl();
   List<Digital> digitals = bd.digitalListlbt();
   request.setAttribute("digitalListes",digitals);
   //response.sendRedirect(INDEX_PATH);

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
	<link href="css/digital/head_footer.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/digital/getCatalog.js"></script>
	<%--<script type="text/javascript" src="js/digital/index.js"></script>--%>
	<script type="text/javascript" src="js/digital/landing.js"></script>
	<link rel="stylesheet" href="css/digital/index.css" />
	<script type="text/javascript" src="js/digital/addcart.js"></script>

	<style type="text/css">
		.dropdown-menu{
			margin:0;
		}
		.mycss:hover{
			font-weight: bold;
			color: #D3B145;
		}
	</style>

</head>
<body>

	<div class="container-fullid">
		<%@include file="header.jsp" %>
		<div class="wrapper">
			<!-- banner start -->
				<div class="banner">
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
						<li data-target="#carousel-example-generic" data-slide-to="3"></li>
						<li data-target="#carousel-example-generic" data-slide-to="4"></li>
					</ol>
					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<img src="images/digital/banner1.jpg" alt="...">
						</div>
						<div class="item">
							<img src="images/digital/banner2.jpg" alt="...">
						</div>
						<div class="item">
							<img src="images/digital/banner3.jpg" alt="...">
						</div>
						<div class="item">
							<img src="images/digital/banner4.jpg" alt="...">
						</div>
					</div>
				    <!-- Controls -->
					<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
					    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					</a>
					<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
					    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					</a>
				</div>
			</div>
				<!-- main start -->
			<div class="main container" style="margin-left: 0%">
				<div class="row">
					<div class="col-md-2 main-left">
						<h3>图书推荐</h3>

						<ul id="book-list">
							<li><a href="MsgList">所有图书</a></li>
						</ul>
					</div>

					<div class="search col-md-4 col-md-offset-6" style="margin-top:20px;margin-left: 38%;">
						<div class="input-group">
							<form action="MsgList2" method="get">
		     	 				<input style="float: left;width: 160px;" type="text" class="form-control" name="seachname" placeholder="输入要搜索商品">
		       					&nbsp;&nbsp;&nbsp;
		       					<span style="float: left;width: 40px;" class="btn btn-default" type="submit" ><img class="icon" src="images/digital/search-icon.png" alt=""></span>
							</form>
   						</div>
					</div>
			
				</div>
			</div>
		</div>
		<br/><br/>

		<%@include file="footer.jsp" %>
	</div>
<script>
	$.ajax({
		url:"ShopIndex",
		dataType:"json",
		async:true,
		data:{},
		type:"POST",
		success:function(data){
			datalist(data);
		}

	})

	function datalist(data){


		//推荐商品
		if(data.recDigitals!=null){
			$.each(data.recDigitals,function(i,n){
				var tag="<li class='col-md-3'><div class='list'>" +
						"<a href='digitaldetail?digitalId="+n.digitalId+"'><img class='img-responsive' src='"+n.upLoadImg.imgSrc+"'/></a>"+
						"<div class='proinfo'><h2><a class='text-center' href='digitaldetail?digitalId="+n.digitalId+"'>"+n.digitalName+"</a></h2>"+
						"<p><i>￥"+n.price+"</i><a class='btn btn-danger btn-xs' href='javascript:void(0)' onclick='addToCart("+n.digitalId+")' " +
						"data-toggle='modal' data-target='.bs-example-modal-sm'>加入购物车</a></p></div></div></li>";

				$("#recDigitals ul").append(tag);
			})
		}

		//新增加的书
		if(data.newDigitals!=null){
			$.each(data.newDigitals,function(i,n){
				var tag="<li class='col-md-3'><div class='list'>" +
						"<a href='digitaldetail?digitalId="+n.digitalId+"'><img class='img-responsive' src='"+n.upLoadImg.imgSrc+"'/></a>"+
						"<div class='proinfo'><h2><a class='text-center' href='digitaldetail?digitalId="+n.digitalId+"'>"+n.digitalName+"</a></h2>"+
						"<p><i>￥"+n.price+"</i><a class='btn btn-danger btn-xs' href='javascript:void(0)' onclick='addToCart("+n.digitalId+")' " +
						"data-toggle='modal' data-target='.bs-example-modal-sm'>加入购物车</a></p></div></div></li>";

				$("#newDigitals ul").append(tag);

			})
		}


	}

</script>
</html>
