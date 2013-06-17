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

        function getPage(role,path){
            $.ajax({
                type: "GET",
                url: "<%=request.getContextPath()%>/"+role+"/"+path,
                success: function(msg){
                    $("#contextContainer").hide("drop", {direction: "left"}, 300, function () {
                        $(this).html(msg);
                        $(this).fadeIn(400);
                    });
                }
            });
        }


        $("#homePage").click(function(){
            $("#contextContainer").hide("drop", {direction: "left"}, 300, function () {
                $(this).empty();
                $(this).append("<h1>Nekosama的糖果屋</h1>");
                $(this).append("<p>「你要承受你心天的季候，如同你常常承受从田野上度过的四时。你要静守，度过你心里凄凉的冬日。」晚安~</p>");
                $(this).hide();
                $(this).fadeIn(400);
            });
        });

        $("#orderPage").click(function(){
            getPage("customer","order");
        });
        <%--查看已订购--%>
        $("#orderInfo").click(function(){
            getPage("customer","orderInfo");
        });
        <%--用户信息--%>
        $("#userInfoPage").click(function(){
            getPage("customer","userInfo");
        });
        <%--会员充值--%>
        $("#chargePage").click(function(){
            getPage("customer","charge");
        });
       <%--购物车--%>
        $("#shoppingCart").click(function(){
            getPage("customer","shoppingCart");
        });

        $("#search").keydown(function(event){
            <%--keycode enter === 13--%>
            if(event.which == 13){
                $.ajax({
                    type: "GET",
                    url: "<%=request.getContextPath()%>/customer/searchProduct",
                    contentType:'charset=UTF-8',
                    data: {searchText:$(this).val()},
                    success: function(msg){
                        $("#contextContainer").hide("drop", {direction: "left"}, 300, function () {
                            $(this).html(msg);
                            $(this).fadeIn(400);
                        });
                    }
                });
            }
        });

    });
</script>

<html>
<head>
    <title>Nekosama|糖果屋</title>
</head>
<body class="home-background">
<jsp:include page="../common/dialog.jsp"/>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner open">
        <a class="brand" href="#">Nekosama|糖果屋</a>
        <ul class="nav pull-right">
            <li>
                <div class="navbar-search pull-left">
                    <input id="search" type="text" class="search-query" placeholder="搜索甜品">
                </div>
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
<div class="container container-fixed">
    <div class="row-fluid">
        <div class="span2">
            <ul class="nav nav-list home-side-bar-rounded">
                <li class="nav-header">欢迎使用</li>
                <li><a id="homePage"><i class="icon-home"></i>首页</a></li>
                <li class="divider"></li>
                <li id="orderPage"><a><i class="icon-heart"></i>订购甜点</a></li>
                <li id="shoppingCart"><a><i class="icon-shopping-cart"></i>购物车结算</a></li>
                <li id="orderInfo"><a><i class="icon-eye-open"></i>查看订购</a></li>
                <li class="divider"></li>
                <li id="userInfoPage"><a><i class="icon-user"></i>个人信息</a></li>
                <li id="chargePage"><a><i class="icon-star"></i>会员充值</a></li>
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