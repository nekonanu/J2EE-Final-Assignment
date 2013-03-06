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
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner open">
        <a class="brand" href="#">Nekosama|糖果屋</a>
        <ul class="nav pull-right">
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
</body>
</html>