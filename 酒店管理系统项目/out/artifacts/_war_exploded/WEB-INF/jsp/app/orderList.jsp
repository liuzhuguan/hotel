<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>订单列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <!-- Custom Theme files -->
    <link href="../css/app/bootstrap.css" type="text/css" rel="stylesheet" media="all">
    <link href="../css/app/style.css" type="text/css" rel="stylesheet" media="all">
    <!-- //Custom Theme files -->
    <!-- js -->
    <script src="../js/jquery-2.2.3.min.js"></script>
    <script src="../js/bootstrap.js"></script>
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
        <li class="active">我的订单</li>
    </ol>
</div>
<!-- //breadcrumb -->
<!-- products -->
<div class="products">
    <div class="container">
        <div class="col-md-12 ">
            <!-- 查询 所有订单遍历  未付款的排在上面     开始-->
            <div class="rsidebar-top col-md-12">
                <c:if test="${not empty orders}">
                    <c:forEach items="${orders}" var="order">
                        <div class="sidebar-row">
                            <h4>
							<span class="col-md-10">
								${order.apartment.apartment_name}
								订单编号：${order.orderCode}
								下单时间：<fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</span>
                                <span class="col-md-2">
								<font color="#0096e6">
									<c:if test="${order.orderStatus == 1}">
                                        已付
                                        ￥<fmt:formatNumber value="${order.totalPrice}"></fmt:formatNumber>
                                    </c:if>
                                    <c:if test="${order.orderStatus == 0}">
                                        未付
                                        ￥<fmt:formatNumber value="${order.totalPrice}"></fmt:formatNumber>
                                    </c:if>
								</font>
							</span>
                            </h4>
                            <ul class="faq">
                                <c:forEach items="${order.orderDetails}" var="orderDetail">
                                    <li>
                                        <a href="#">
                                            <span class="col-md-8">${orderDetail.food.foodName}</span>
                                            <span class="col-md-2">${orderDetail.buyNum}份</span>
                                            <span class="col-md-2">
										￥${orderDetail.food.discount * orderDetail.food.price }
									</span>
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                            <hr>
                        </div>
                    </c:forEach>
                </c:if>

                <!-- <div class="sidebar-row"><center><font color="red">暂时没有订单！</font></center></div> -->
            </div>
        </div>
        <div class="clearfix">&nbsp;</div>
        <!-- 查询 所有订单遍历  未付款的排在上面		结束-->
    </div>
</div>
</div>
</body>
</html>