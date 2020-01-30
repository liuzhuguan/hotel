<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>订单详情页</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="" />
	<link href="../css/app/bootstrap.css" type="text/css" rel="stylesheet" media="all">
	<link href="../css/app/style.css" type="text/css" rel="stylesheet" media="all">
	<script src="../js/jquery-2.2.3.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script type="text/javascript">
		function pay(orderId) {
			window.location.href = "${pageContext.request.contextPath}/app/order.action?method=pay&orderId=" + orderId;
		}

		function deleteOrder(orderId) {
			window.location.href = "${pageContext.request.contextPath}/app/order.action?method=delete&orderId=" + orderId;
		}

	</script>
</head>
<body>
<!-- banner -->
<div class="banner about-w3bnr">
	<!-- header -->
	<div class="header">
		<!-- //header-one -->
		<!-- navigation -->
		<div class="navigation agiletop-nav">
			<div class="container">
				<nav class="navbar navbar-default">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header w3l_logo">

						<h1><a href="app/index.do">Ascott</a></h1>
					</div>
					<div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="${pageContext.request.contextPath}/app/index.do" class="active">主页</a></li>
							<!-- Mega Menu -->
							<li>
								<a href="${pageContext.request.contextPath}/app/room.do" class="dropdown-toggle" >客房 </a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/app/menuList.do" class="dropdown-toggle" >菜单 </a>
							</li>
							<li class="w3pages">
								<a href="${pageContext.request.contextPath}/app/order.action?method=list" class="dropdown-toggle">我的订单</a>
							</li>

							<c:if test="${empty session_user}">
								<li class="head-dpdn">
									<a href="${pageContext.request.contextPath}/app/login.do">登录</a>
								</li>
								<li class="head-dpdn">
									<a href="${pageContext.request.contextPath}/app/register.do">免费注册</a>
								</li>
							</c:if>

							<c:if test="${not empty session_user}">
								<li class="head-dpdn">
									<a>${session_user.loginName} 您好！</a>
								</li>
								<li class="head-dpdn">
									<a href="${pageContext.request.contextPath}/app/loginout.action">退出</a>
								</li>
							</c:if>
						</ul>
					</div>
				</nav>
			</div>
		</div>
		<!-- //navigation -->
	</div>
	<!-- //header-end -->
</div>
<!-- //banner -->
<!-- breadcrumb -->
<div class="container">
	<ol class="breadcrumb w3l-crumbs">
		<li><a href="${pageContext.request.contextPath}/app/index.do"><i class="fa fa-home"></i> 主页</a></li>
		<li class="active">${apartment.apartment_name}订单详情</li>
	</ol>
</div>
<!-- //breadcrumb -->
<!-- products -->
<div class="products">
	<div class="container">
		<div class="col-md-12 ">
			<!-- 查询 所有订单遍历  未付款的排在上面     开始-->
			<div class="rsidebar-top col-md-12">
				<div class="sidebar-row">

					<!--遍历订单开始-->
					<c:if test="${not empty orders}">
						<c:forEach items="${orders}" var="order" >
							<h4>
							<span class="col-md-12">
								订单编号：${order.orderCode}
								下单时间：${order.orderDate}
							</span>
							</h4>
							<ul class="faq">
								<c:if test="${not empty order.orderDetails}">
									<c:forEach items="${order.orderDetails}" var="orderDetail">
										<li>
											<a href="#">
												<span class="col-md-8">${orderDetail.food.foodName}</span>
												<span class="col-md-2">${orderDetail.buyNum}份</span>
												<span class="col-md-2">
													￥<fmt:formatNumber value="${orderDetail.food.price * orderDetail.food.discount}" pattern="0.00"></fmt:formatNumber>
												</span>
											</a>
										</li>
									</c:forEach>
								</c:if>
							</ul>
							<br/>
							<span class="col-md-8"></span>
							<span class="col-md-4">
							<font color="#0096e6">
							总计:￥ <fmt:formatNumber value="${order.totalPrice}" pattern="0.00"></fmt:formatNumber></font>
								<!-- 订单id -->
								<input type="button" onclick="pay(${order.id})" value="付款">
								<input type="button" onclick="deleteOrder(${order.id})" value="取消订单">
						</span>
						</c:forEach>
					</c:if>

					<!--遍历订单结束-->

					<!-- <center><font color="red">暂时没有订单！</font></center> -->
				</div>
			</div>
			<div class="clearfix">&nbsp;</div>
		</div>
	</div>
</div>
</body>
</html>