<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-5
  Time: 下午4:12
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../static/common/include.jsp"/>
<script type="text/javascript">
    $(document).ready(function(){
        <%--订购商品--%>
        $("#orderPage").click(function(){
            $.ajax({
                type: "GET",
                url: "<%=request.getContextPath()%>/customer/order",
                success: function(msg){
                    $("#contextContainer").empty();
                    $("#contextContainer").append(msg);
                }
            });
        });
        <%--查看已订购--%>
        $("#orderInfo").click(function(){
            $.ajax({
                type: "GET",
                url: "<%=request.getContextPath()%>/customer/orderInfo",
                success: function(msg){
                    $("#contextContainer").empty();
                    $("#contextContainer").append(msg);
                }
            });
        });
        <%--用户信息--%>
        $("#userInfoPage").click(function(){
            $.ajax({
                type: "GET",
                url: "<%=request.getContextPath()%>/customer/userInfo",
                success: function(msg){
                    $("#contextContainer").empty();
                    $("#contextContainer").append(msg);
                }
            });
        });
        <%--会员充值--%>
        $("#chargePage").click(function(){
            $.ajax({
                type: "GET",
                url: "<%=request.getContextPath()%>/customer/charge",
                success: function(msg){
                    $("#contextContainer").empty();
                    $("#contextContainer").append(msg);
                }
            });
        });
        <%--消息盒子--%>
        $("#messageBoxPage").click(function(){
            $.ajax({
                type: "GET",
                url: "<%=request.getContextPath()%>/customer/messageBox",
                success: function(msg){
                    $("#contextContainer").empty();
                    $("#contextContainer").append(msg);
                }
            });
        });

    });
</script>

<html>
<head>
    <title>Nekosama|糖果屋</title>
</head>
<body class="home-background">
<jsp:include page="../common/dialog.jsp"/>
<div class="navbar navbar-inverse">
    <div class="navbar-inner open">
        <a class="brand" href="#">Nekosama|糖果屋</a>
        <ul class="nav pull-right">
            <li>
                <form class="navbar-search pull-left">
                    <input type="text" class="search-query" placeholder="搜索甜品">
                </form>
            </li>
            <li class="divider-vertical"></li>
            <li class="active">
                <a href="<%=request.getContextPath()%>/customer/home">首页</a>
            </li>
            <li class="divider-vertical"></li>
            <li class="dropdown">
                <a class="dropdown-toggle"
                   data-toggle="dropdown"
                   href="#">
                    更多
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="<%=request.getContextPath()%>/customer/logout">注销</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<div class="container">
    <div class="row-fluid">
        <div class="span2">
            <ul class="nav nav-list home-side-bar-rounded">
                <li class="nav-header">欢迎使用</li>
                <li><a href="<%=request.getContextPath()%>/customer/home">介绍</a></li>
                <li class="divider"></li>
                <li id="orderPage"><a>订购甜点</a></li>
                <li id="orderInfo"><a>查看订购</a></li>
                <li class="divider"></li>
                <li id="userInfoPage"><a>个人信息</a></li>
                <li id="chargePage"><a>会员充值</a></li>
                <li id="messageBoxPage"><a>消息盒</a></li>
            </ul>
        </div>
        <div class="span10">
            <div id="contextContainer" class="hero-unit hero-unit-opacity2  transition-container">
                <h1>Nekosama的糖果屋</h1>
                <p>「你要承受你心天的季候，如同你常常承受从田野上度过的四时。你要静守，度过你心里凄凉的冬日。」晚安~</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>