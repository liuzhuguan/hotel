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
            window.location.href = "${pageContext.request.contextPath}/app/order.action?method=order&apartmentId=" + apartmentId + "&total=${total}";
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
                                <a href="${pageContext.request.contextPath}/app/menuList.do" class="dropdown-toggle" >菜单 </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app/room.do" class="dropdown-toggle" >客房 </a>
                            </li>
                            <li class="head-dpdn">
                            </li>
                            <li class="w3pages">
                                <a href="${pageContext.request.contextPath}/app/order.action?method=list" class="dropdown-toggle" >我的订单</a>
                            </li>
                            <li class="head-dpdn">
                                <a href="${pageContext.request.contextPath}/app/login.do">登录</a>
                            </li>
                            <li class="head-dpdn">
                                <a href="${pageContext.request.contextPath}/app/register.do">免费注册</a>
                            </li>
                            <li class="head-dpdn">
                                <a>admin 您好！</a>
                            </li>
                            <li class="head-dpdn">
                                <a href="${pageContext.request.contextPath}/app/loginout.action">退出</a>
                            </li>
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
                <h4>主页/客房:
                    <a href="menu.action?foodTypeId=1" style="color:white;">各种款式</a>&nbsp;
                </h4>
                <div class="clearfix"> </div>
            </div>

            <!-- 右侧展示菜品开始 -->
            <div class="products-row">

                <div class="col-xs-6 col-sm-4 product-grids">
                    <div class="flip-container">
                        <div class="flipper agile-products">
                            <div class="front">
                                <img  src="../images/app/room/room1.jpg" style="width: 250px;height: 170px" class="img-responsive" alt="img">
                                <div class="agile-product-text">
                                    <h5>单人间</h5>
                                </div>
                            </div>
                            <div class="back">
                                <h4>单人间</h4>
                                <p>可住1人，15平米左右</p>
                                <h6>
                                    <fmt:formatNumber value="168" pattern="0.0"></fmt:formatNumber><sup>￥/天</sup>
                                </h6>
                                <form action="#" method="post">
                                    <input type="hidden" id="apartmentId"  name="apartmentId" value="${apartment.id}">
                                    <button type="button"  onclick="addShopCar(${food.id})" class="w3ls-cart pw3ls-cart">
                                        <i class="fa fa-cart-plus" aria-hidden="true"></i>预定
                                    </button>
                                    <span class="w3-agile-line"> </span>
                                    <a href="#" >详情</a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="products-row">
                <div class="col-xs-6 col-sm-4 product-grids">
                    <div class="flip-container">
                        <div class="flipper agile-products">
                            <div class="front">
                                <img  src="../images/app/room/room2.jpg" style="width: 250px;height: 170px" class="img-responsive" alt="img">
                                <div class="agile-product-text">
                                    <h5>普通大床房</h5>
                                </div>
                            </div>
                            <div class="back">
                                <h4>普通大床房</h4>
                                <p>可住2人，25平米左右</p>
                                <h6>
                                    <fmt:formatNumber value="299" pattern="0.0"></fmt:formatNumber><sup>￥</sup>
                                </h6>
                                <form action="#" method="post">
                                    <input type="hidden" id="apartmentId"  name="apartmentId" value="${apartment.id}">
                                    <button type="button"  onclick="addShopCar(${food.id})" class="w3ls-cart pw3ls-cart">
                                        <i class="fa fa-cart-plus" aria-hidden="true"></i>预定
                                    </button>
                                    <span class="w3-agile-line"> </span>
                                    <a href="#" >详情</a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="products-row">
                <div class="col-xs-6 col-sm-4 product-grids">
                    <div class="flip-container">
                        <div class="flipper agile-products">
                            <div class="front">
                                <img  src="../images/app/room/room3.jpg" style="width: 250px;height: 170px" class="img-responsive" alt="img">
                                <div class="agile-product-text">
                                    <h5>豪华大床房</h5>
                                </div>
                            </div>
                            <div class="back">
                                <h4>豪华大床房</h4>
                                <p>可住2人，30平米左右</p>
                                <h6>
                                    <fmt:formatNumber value="399" pattern="0.0"></fmt:formatNumber><sup>￥</sup>
                                </h6>
                                <form action="#" method="post">
                                    <input type="hidden" id="apartmentId"  name="apartmentId" value="${apartment.id}">
                                    <button type="button"  onclick="addShopCar(${food.id})" class="w3ls-cart pw3ls-cart">
                                        <i class="fa fa-cart-plus" aria-hidden="true"></i>预定
                                    </button>
                                    <span class="w3-agile-line"> </span>
                                    <a href="#" >详情</a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="products-row">
                <div class="col-xs-6 col-sm-4 product-grids">
                    <div class="flip-container">
                        <div class="flipper agile-products">
                            <div class="front">
                                <img  src="../images/app/room/room10.jpg" style="width: 250px;height: 170px" class="img-responsive" alt="img">
                                <div class="agile-product-text">
                                    <h5>普通标准间</h5>
                                </div>
                            </div>
                            <div class="back">
                                <h4>普通标准间</h4>
                                <p>可住2人，20平米左右</p>
                                <h6>
                                    <fmt:formatNumber value="199" pattern="0.0"></fmt:formatNumber><sup>￥</sup>
                                </h6>
                                <form action="#" method="post">
                                    <input type="hidden" id="apartmentId"  name="apartmentId" value="${apartment.id}">
                                    <button type="button"  onclick="addShopCar(${food.id})" class="w3ls-cart pw3ls-cart">
                                        <i class="fa fa-cart-plus" aria-hidden="true"></i>预定
                                    </button>
                                    <span class="w3-agile-line"> </span>
                                    <a href="#" >详情</a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="products-row">
                <div class="col-xs-6 col-sm-4 product-grids">
                    <div class="flip-container">
                        <div class="flipper agile-products">
                            <div class="front">
                                <img  src="../images/app/room/room4.jpg" style="width: 250px;height: 170px" class="img-responsive" alt="img">
                                <div class="agile-product-text">
                                    <h5>豪华标准间</h5>
                                </div>
                            </div>
                            <div class="back">
                                <h4>豪华标准间</h4>
                                <p>可住2人，30平米左右</p>
                                <h6>
                                    <fmt:formatNumber value="258" pattern="0.0"></fmt:formatNumber><sup>￥</sup>
                                </h6>
                                <form action="#" method="post">
                                    <input type="hidden" id="apartmentId"  name="apartmentId" value="${apartment.id}">
                                    <button type="button"  onclick="addShopCar(${food.id})" class="w3ls-cart pw3ls-cart">
                                        <i class="fa fa-cart-plus" aria-hidden="true"></i>预定
                                    </button>
                                    <span class="w3-agile-line"> </span>
                                    <a href="#" >详情</a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="products-row">
                <div class="col-xs-6 col-sm-4 product-grids">
                    <div class="flip-container">
                        <div class="flipper agile-products">
                            <div class="front">
                                <img  src="../images/app/room/room12.jpg" style="width: 250px;height: 170px" class="img-responsive" alt="img">
                                <div class="agile-product-text">
                                    <h5>商务标准间</h5>
                                </div>
                            </div>
                            <div class="back">
                                <h4>商务标准间</h4>
                                <p>可住2人，35平米左右</p>
                                <h6>
                                    <fmt:formatNumber value="299" pattern="0.0"></fmt:formatNumber><sup>￥</sup>
                                </h6>
                                <form action="#" method="post">
                                    <input type="hidden" id="apartmentId"  name="apartmentId" value="${apartment.id}">
                                    <button type="button"  onclick="addShopCar(${food.id})" class="w3ls-cart pw3ls-cart">
                                        <i class="fa fa-cart-plus" aria-hidden="true"></i>预定
                                    </button>
                                    <span class="w3-agile-line"> </span>
                                    <a href="#" >详情</a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="products-row">
                <div class="col-xs-6 col-sm-4 product-grids">
                    <div class="flip-container">
                        <div class="flipper agile-products">
                            <div class="front">
                                <img  src="../images/app/room/room13.jpg" style="width: 250px;height: 170px" class="img-responsive" alt="img">
                                <div class="agile-product-text">
                                    <h5>普通套房</h5>
                                </div>
                            </div>
                            <div class="back">
                                <h4>普通套房</h4>
                                <p>可住2人，50平米左右</p>
                                <h6>
                                    <fmt:formatNumber value="699" pattern="0.0"></fmt:formatNumber><sup>￥</sup>
                                </h6>
                                <form action="#" method="post">
                                    <input type="hidden" id="apartmentId"  name="apartmentId" value="${apartment.id}">
                                    <button type="button"  onclick="addShopCar(${food.id})" class="w3ls-cart pw3ls-cart">
                                        <i class="fa fa-cart-plus" aria-hidden="true"></i>预定
                                    </button>
                                    <span class="w3-agile-line"> </span>
                                    <a href="#" >详情</a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="products-row">
                <div class="col-xs-6 col-sm-4 product-grids">
                    <div class="flip-container">
                        <div class="flipper agile-products">
                            <div class="front">
                                <img  src="../images/app/room/room14.jpg" style="width: 250px;height: 170px" class="img-responsive" alt="img">
                                <div class="agile-product-text">
                                    <h5>豪华套房</h5>
                                </div>
                            </div>
                            <div class="back">
                                <h4>豪华套房</h4>
                                <p>可住2人，70平米左右</p>
                                <h6>
                                    <fmt:formatNumber value="899" pattern="0.0"></fmt:formatNumber><sup>￥</sup>
                                </h6>
                                <form action="#" method="post">
                                    <input type="hidden" id="apartmentId"  name="apartmentId" value="${apartment.id}">
                                    <button type="button"  onclick="addShopCar(${food.id})" class="w3ls-cart pw3ls-cart">
                                        <i class="fa fa-cart-plus" aria-hidden="true"></i>预定
                                    </button>
                                    <span class="w3-agile-line"> </span>
                                    <a href="#" >详情</a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="products-row">
                <div class="col-xs-6 col-sm-4 product-grids">
                    <div class="flip-container">
                        <div class="flipper agile-products">
                            <div class="front">
                                <img  src="../images/app/room/room15.jpg" style="width: 250px;height: 170px" class="img-responsive" alt="img">
                                <div class="agile-product-text">
                                    <h5>总统套房</h5>
                                </div>
                            </div>
                            <div class="back">
                                <h4>总统套房</h4>
                                <p>100平米左右</p>
                                <h6>
                                    <fmt:formatNumber value="1299" pattern="0.0"></fmt:formatNumber><sup>￥</sup>
                                </h6>
                                <form action="#" method="post">
                                    <input type="hidden" id="apartmentId"  name="apartmentId" value="${apartment.id}">
                                    <button type="button"  onclick="addShopCar(${food.id})" class="w3ls-cart pw3ls-cart">
                                        <i class="fa fa-cart-plus" aria-hidden="true"></i>预定
                                    </button>
                                    <span class="w3-agile-line"> </span>
                                    <a href="#" >详情</a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 右侧展示菜品结束 -->
        </div>

        <!--左侧购物车  -->
        <div class="col-md-3 rsidebar" style="width: 22%;">
            <div class="rsidebar-top">
                <div class="sidebar-row">
                    <h4>房间类型</h4>
                    <ul class="faq">
                        <li class="item1"><a href="app/menuList.do?foodTypeId=1" >经济型</a></li>
                        <li class="item1"><a href="app/menuList.do?foodTypeId=2" >商务型</a></li>
                        <li class="item1"><a href="app/menuList.do?foodTypeId=3" >总统型</a></li>
                    </ul>
                    <div class="clearfix"> </div>
                </div>
            </div>
        </div>
        <div class="clearfix"> </div>
        <!--左侧购物车或订单展示结束  -->
    </div>
</div>
<script src="resources/login/image/js/bootstrap.js"></script>
</body>
</html>