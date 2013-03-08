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

<html>
<head>
    <title>Nekosama|糖果屋</title>
</head>
<body class="home-background">
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
    <div class="hero-unit">
        <h1>操作成功</h1>
    </div>
</div>
</body>
</html>