
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>主页</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="keywords" content="" />
  <script type="application/x-javascript">
    /* 页面加载的时候添加一个定时器，0秒之后执行hideURLbar函数。hideURLbar函数将页面滚动至坐标（0,1）
     因为chrome等浏览器会有滚动缓存功能，比如你在A页面滚动后跳转到B页面，点击返回键回到A页面，会发现滚动条位置仍然保持 */
    addEventListener("load", function() {
      setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar(){
      window.scrollTo(0,1);
    }

    window.onload = function () {
        //    获取用户选中类型
        var apartmentStatus = "${apartmentStatus}";
        var agileinfo_search = document.getElementById("agileinfo_search");
        var options = agileinfo_search.options;

        $.each(options,function (i,item) {
            $(item).attr("selected",item.value == apartmentStatus);
        })
    }

    function dataChange(obj) {
        var ApartmentName = $("#ApartmentName").val();
        var ApartmentStatus = obj.value;

        window.location = "${pageContext.request.contextPath}/app/index.do?method=SubmitApartment&ApartmentName=" + ApartmentName + "&ApartmentStatus="+ ApartmentStatus;
    }

  </script>
  <!-- Custom Theme files -->
  <link href="../../../css/app/bootstrap.css" type="text/css" rel="stylesheet" media="all">
  <link href="../../../css/app/style.css" type="text/css" rel="stylesheet" media="all">
  <!-- //Custom Theme files -->
  <!-- js -->
  <script src="../../../js/jquery-2.2.3.min.js"></script>
  <script src="../../../js/bootstrap.js"></script>
  <!-- //js -->

</head>
<body style="background-image: url('${pageContext.servletContext.contextPath}/images/app/ss1.jpg') ; background-size: 1600px">
<!-- banner -->
<div >
  <!-- header -->
  <div class="header">
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
  <!-- banner-text -->
  <div class="banner-text">
    <div class="container" style="padding-left: 280px;margin-top: -100px;">
      <div class="agileits_search">
        <form action="index.do" method="post">
          <input type="hidden"  name="method"  value="SubmitApartment">
          <div id="serchResult" cstyle="margin-top: 20px;">
              <c:if test="${not empty apartments}">
                  <c:forEach items="${apartments}" var="apartment">
                      <a href="menu.action?id=${apartment.id}" style="color: white;font-size: 20px;">${apartment.apartment_name}</a>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
                  </c:forEach>
              </c:if>
            <br><br>
          </div>
          <input name="ApartmentName" id="ApartmentName" type="text" placeholder="搜索客房" >
          <select id="agileinfo_search" name="ApartmentStatus" onchange="dataChange(this)">
            <option value="">全部</option>
            <option value="1">正在使用</option>
            <option value="0">未使用</option>
          </select>
          <input type=submit   value="查看客房">
        </form>
      </div>
    </div>
  </div>
</div>

</body>
</html>