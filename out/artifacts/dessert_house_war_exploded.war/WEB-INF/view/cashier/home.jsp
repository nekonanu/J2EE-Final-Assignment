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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
    $(document).ready(function(){
        <%--产品管理--%>
        $("#productManage").click(function(){
            $.ajax({
                type: "GET",
                url: "<%=request.getContextPath()%>/cashier/productManage",
                success: function(msg){
                    $("#contextContainer").empty();
                    $("#contextContainer").append(msg);
                }
            });
        });
        <%--产品添加--%>
        $("#productAdd").click(function(){
            $.ajax({
                type: "GET",
                url: "<%=request.getContextPath()%>/cashier/productAdd",
                success: function(msg){
                    $("#contextContainer").empty();
                    $("#contextContainer").append(msg);
                }
            });
        });
        <%--销售删改--%>
        $("#sale").click(function(){
            $.ajax({
                type: "GET",
                url: "<%=request.getContextPath()%>/cashier/sale",
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
                <a href="<%=request.getContextPath()%>/cashier/home">首页</a>
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
                        <a href="<%=request.getContextPath()%>/cashier/logout">注销</a>
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
                <li><a href="<%=request.getContextPath()%>/cashier/home">介绍</a></li>
                <li class="divider"></li>
                <li id="productAdd"><a>产品添加</a></li>
                <li id="productManage"><a>产品删改</a></li>
                <li class="divider"></li>
                <li id="sale"><a>销售处理</a></li>
            </ul>
        </div>
        <div class="span10">
            <div id="contextContainer" class="hero-unit hero-unit-opacity2 transition-container">
                <h1>Nekosama的糖果屋</h1>
                <p>「你要承受你心天的季候，如同你常常承受从田野上度过的四时。你要静守，度过你心里凄凉的冬日。」晚安~</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>