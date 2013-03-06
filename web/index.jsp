<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-2-28
  Time: 下午5:26
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String rootPath=request.getContextPath();
%>
<html>
<head>
    <title>Nekosama|糖果屋</title>
    <jsp:include page="WEB-INF/static/common/include.jsp"/>
</head>
<body class="global-background">
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner open">
        <a class="brand" href="#">Nekosama|糖果屋</a>
        <ul class="nav pull-right">
            <li class="active">
                <a href="#">首页</a>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle"
                   data-toggle="dropdown"
                   href="#">
                    更多
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="<%=rootPath%>/employee/login">管理员登陆</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<div id="welcome-page" class="container-fluid">
    <div class="hero-unit hero-unit-opacity">
        <h1>Nekosama糖果屋</h1>
        <p>
            有时间，静下来，无论外界有多纷繁复杂、吵闹，只要让自己的心静下来，哪怕只是让屋子里的灯关著，找一个可以看见星空的地方坐下来，把自己的烦恼告诉星空，它能听地懂.夜晚的天空是最干净的，没有杂质的。
        </p>
        <p>你的微笑，是我最大心愿。</p>
        <p><a class="btn btn-primary btn-large" href="<%=rootPath%>/customer/login">点击登陆</a></p>
    </div>
</div>
</body>
</html>