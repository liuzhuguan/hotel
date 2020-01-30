<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>添加商品到购物车</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> 
	//scrollTo() 方法可把内容滚动到指定的坐标
	addEventListener("load", 
		function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); }

		function addShopCar(foodId) {
			var apartmentId = $("#apartmentId").val();
		//	加入什么，送到哪里
			window.location.href = "${pageContext.request.contextPath}/app/shopCar.action?apartmentId=" + apartmentId + "&foodId=" + foodId + "&method=add";
		}

//		购买数量框事件
	function blurFn(obj, foodId, buyNum, apartmentId) {
		//获取输入
		var num = obj.value;
		//	乱输入时保持不变
		if (num < 1 || isNaN(num) || num == buyNum) {
			obj.value = buyNum;
		}
	//	正常输入就通过客房id找到对应购物车去修改数量
		else {
			window.location.href = "${pageContext.request.contextPath}/app/shopCar.action?method=update&foodId=" + foodId + "&buyNum=" + Math.ceil(num) + "&apartmentId=" + apartmentId;
		}
	}

	function deleteFn(apartmentId,foodId) {
		window.location.href = "${pageContext.request.contextPath}/app/shopCar.action?method=delete&foodId=" + foodId + "&apartmentId=" + apartmentId;
	}

	function orderDinner(apartmentId,apartmentStatus) {
        window.location.href = "${pageContext.request.contextPath}/app/apartment.action?apartmentId=" + apartmentId + "&apartmentStatus=" + apartmentStatus;
    }

    function order(apartmentId) {
        window.location.href = "${pageContext.request.contextPath}/app/order.action?method=order&apartmentId=" + apartmentId + "&total=" + "${total}";
    }

</script>

<!-- Custom Theme files -->
<link href="../css/app/bootstrap.css" type="text/css" rel="stylesheet" media="all">
<link href="../css/app/style.css" type="text/css" rel="stylesheet" media="all">  
<!-- //Custom Theme files --> 
<!-- js -->
<script src="../js/jquery-2.2.3.min.js"></script>  
<!-- //js -->
</head>
<body> 
	<!-- banner -->
	<div class="banner about-w3bnr">
		<!-- header -->
		<div class="header">
			<!-- //header-one -->    
			<!-- navigation -->
			<div class="navigation agiletop-nav">
				<div class="container" style="margin-right: 5px;margin-left: 5px;">
					<nav class="navbar navbar-default">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header w3l_logo">
							<h1><a href="index.do">Ascott</a></h1>
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
	<!-- //breadcrumb -->
	<!-- products -->
	<div class="products">	 
		<div class="container">
			<div class="col-md-9 product-w3ls-right" style="width: 70%;"> 
				<div class="product-top">
					<h4>主页/菜单:
						<c:if test="${not empty foodTypes}">
							<c:forEach items="${foodTypes}" var="foodType">
								<a href="menu.action?foodTypeId=${foodType.id}&id=${apartment.id}" style="color:white;">${foodType.typeName}</a>&nbsp;
							</c:forEach>
						</c:if>

						<c:if test="${empty foodTypes}">
							当前无菜品
						</c:if>
					</h4>
					<div class="clearfix"> </div>
				</div>
				
				<!-- 右侧展示菜品开始 -->
				<div class="products-row">
					<c:if test="${not empty foods}">
						<c:forEach items="${foods}" var="food">
							<div class="col-xs-6 col-sm-4 product-grids">
								<div class="flip-container">
									<div class="flipper agile-products">
										<div class="front">
											<img  src="${pageContext.request.contextPath}/images/app/food/${food.img}" style="width: 250px;height: 150px" class="img-responsive" alt="img">
											<div class="agile-product-text">
												<h5>${food.foodName}</h5>
											</div>
										</div>
										<div class="back">
											<h4>${food.foodName}</h4>
											<p>${food.remark}</p>
											<h6>
												<fmt:formatNumber value="${food.price}" pattern="0.00"></fmt:formatNumber><sup>￥</sup>
											</h6>
											<form action="#" method="post">
												<input type="hidden" id="apartmentId"  name="apartmentId" value="${apartment.id}">
												<button type="button"  onclick="addShopCar(${food.id})" class="w3ls-cart pw3ls-cart">
													<i class="fa fa-cart-plus" aria-hidden="true"></i>加入购物车
												</button>
												<span class="w3-agile-line"> </span>
												<a href="#" >详情</a>
											</form>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
					</div>

				<!-- 右侧展示菜品结束 -->
			</div>
			
			<!--左侧购物车  -->
			<div class="col-md-3 rsidebar" style="width: 28%;">
				<div class="rsidebar-top">
					<div class="sidebar-row">
						<ol class="breadcrumb w3l-crumbs">
							<li class="active">${apartment.apartment_name}客房购物车</li>
						</ol>
						<form method="post" action="" style="margin-top: 10px;">    
							<ul style="margin: 5cpx 0 20px;padding: 1em;list-style-type: none;border: 1px solid #ccc;border-radius: 4px;box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2);">
								<center><font style="color: red"></font> </center>
									<!--目前没有商品-->

									<!--目前有商品-->
										<c:if test="${not empty foods2}">
											<c:forEach items="${foods2}" var="food">
												<li style="margin: 10px;" name="food">
													<div>
														<!-- 可到菜品详情页 -->
														<a  href="#">${food.foodName}</a>
														<input name="id" value="${food.id}" type="hidden">
														<!--第一个参数：当前标签dom对象   第二个参数：商品的id   第三参数：购买数量     第四个参数：客房id -->
														<input  name=buyNum  value="${food.buyNum}"  onblur="blurFn(this,${food.id},${food.buyNum},${apartment.id});"
																style="width: 30px;border-radius: 3px;border: 1px solid #a3a3a3;text-align: right;padding: 2px 4px;" >
														<!-- 第一个参数：餐桌的id  第二个参数：菜品的id -->
														<input type="button"  value="×" onclick="deleteFn(${apartment.id},${food.id});" style="border-radius: 3px;border: 1px solid #a3a3a3;background: b7b7b7;"></input>
													</div>
													<div style="float: right;">
														<a><s>¥<fmt:formatNumber value="${food.price}" pattern="0.00"></fmt:formatNumber></s></a>
														<a><strong>¥<fmt:formatNumber value="${food.price * food.discount}" pattern="0.00"></fmt:formatNumber></strong></a></div>
												</li><hr>
											</c:forEach>
										</c:if>

									
							</ul>
							<div style="float:right;margin-top: 10px;">                    
								总金额:￥<fmt:formatNumber value="${total}" pattern="0.00"></fmt:formatNumber>
									<!-- 第一个参数：餐桌的id   第二个参数：更改对应table_status字段的值 -->
                                    <c:if test="${apartment.apartment_status == 1}">
                                        <input type="button"  onclick="order(${apartment.id})" value="下单"></input>
                                        <input type="button"  onclick="orderDinner(${apartment.id},0)" value="取消操作"></input>
                                    </c:if>
									<c:if test="${apartment.apartment_status == 0}">
                                        <input type="button"  onclick="orderDinner(${apartment.id},1)" value="预定房间"></input>
                                    </c:if>

							</div>    
						</form>
						<div class="clearfix"> </div> 
					</div>
				</div>
			</div>
			<div class="clearfix"> </div> 
			<!--左侧购物车或订单展示结束  -->
		</div>
	</div> 
    <script src="js/bootstrap.js"></script>
</body>
</html>