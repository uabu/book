<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="page-container page-header header-shadow">
<header>
  <a href="./">
    <img class="nav-logo" src="images/digital/LOGO-1.png" alt="">
  </a>
 
  <nav class="nav-menu">
        <a  href="jsp/digital/index.jsp"  class="nav-a active">
        <span> 首页<span class="sr-only">(current)</span></span>
        <span> HOME</span>
    </a>

        <a  href="jsp/digital/index1.jsp"  class="nav-a active">
        <span> 图书阅读</span>
        <span> NEWBOOKS</span>
    </a>

        <a  href="DigitalList?catalogId=2"  class="nav-a ">
        <span> 资料查阅 </span>
        <span> DATA ACCESS</span>
    </a>

        <a  href="DigitalList?catalogId=3"  class="nav-a ">
        <span> 热销榜单</span>
        <span>BEST-SELLER LIST</span>
    </a>
    <a  href="DigitalList?catalogId=4"  class="nav-a ">
        <span> 特惠商品 </span>
        <span> SPECIAI OFFER</span>
    </a>

    </nav> 
     <img class="nav-cut-line" src="images/digital/cut-line.png" alt="">
 			 <div class="header-controller">
  					<c:choose>
							<c:when test="${empty landing}">
								 <div class="header-controller-login">
								     <a href="jsp/digital/login.jsp">
								     	<img class="icon" src="images/digital/login-icon.png" alt="">
								     	<span class="text" id="uname">登录</span>
								     </a>
								 </div>
								 <div class="header-controller-register" id="regBar_350">
								 	<a href="jsp/digital/reg.jsp">
								        <img class="icon" src="images/digital/register-icon.png" alt="">
								        <span class="text">注册</span>
								    </a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="btn-group adminName " style="margin-right:80px;margin-bottom:15px;">
									<a href="javascript:void(0)">
										<img class="icon" src="images/digital/login-icon.png" alt="">
									    ${landing.name} <span class="caret"></span>
									</a>
									<ul class="dropdown-menu dropdown-menu-right">
									    <li><a href="OrderServlet?action=list" >我的订单</a></li>
									    <li><a style="border-top:1px #ccc solid" href="UserServlet?action=off" onClick="return confirm('确定要退出登录吗？')">退 出 登 录</a></li>
									</ul>
								</div>
							</c:otherwise>
						</c:choose>

				<div class="header-controller-cart"  >
						<a id="cart" href="jsp/digital/cart.jsp">
							<div class="icon" style="display:inline-block; position: relative;">
					            <img class="icon" src="images/digital/cart-icon.png" style="margin-top:-50px;" alt="">
					            <div class="wz-dot" style="position: absolute;border: 5px solid transparent; border-bottom-color: #e60012; top: -7px;
					    			left: 10px;">
					            </div>
					        </div>
							<span>购物车</span>

							<span class="badge num" style="color:#e60012;font-size: 12px;background:#D3B145;">
								<c:choose>
									<c:when test="${!empty shopCart}">
										${shopCart.getTotQuan()}
									</c:when>
									<c:otherwise>
										0
									</c:otherwise>
								</c:choose>
							</span>
						</a>
					</div>
</div>
</header>
</div>