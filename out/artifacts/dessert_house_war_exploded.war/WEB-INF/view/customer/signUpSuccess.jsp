<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<body class="global-background">
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner open">
        <a class="brand" href="#">客户注册</a>
        <ul class="nav pull-right">
            <li class="active">
                <a href="<%=request.getContextPath()%>">首页</a>
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
                        <a href="<%=request.getContextPath()%>">返回首页</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<div class="container-fluid normal-center">
    <div class="row">
        <div class="span6">
            <div class="hero-unit hero-unit-opacity2">
                <div class="row">
                    <div class="span4 offset3">
                        <p>注册成功</p>
                    </div>
                </div>
                <div class="row">
                    <div class="span3 offset3">
                        <a href="<%=request.getContextPath()%>/customer/login" class="btn btn-primary btn-large">返回登录</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>