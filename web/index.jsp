<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-2-28
  Time: 下午5:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎来到Nekosama的糖果屋</title>
    <jsp:include page="WEB-INF/static/common/include.jsp"/>
</head>
<body id="login_body">
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner open">
        <a class="brand" href="#">Nekosama的糖果屋</a>
        <ul class="nav pull-right">
            <li class="active">
                <a href="#">首页</a>
            </li>
            <li>
                <a href="#">关于</a>
            </li>
        </ul>
    </div>
</div>
<div id="login_container" class="container-fluid ">
    <div class="row">
        <div class="span4 offset1 ">
            <form class="form-horizontal">
                <fieldset>
                    <div class="control-group">
                        <label class="control-label" for="input01">USERNAME</label>
                        <div class="controls">
                            <input type="text" class="input-xlarge" id="input01">
                            <p class="help-block">请输入用户名</p>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="input01">PASSWORD</label>
                        <div class="controls">
                            <input type="text" class="input-xlarge" id="input02">
                            <p class="help-block">请输入密码</p>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="span2 offset2">
            <button class="btn">Login</button>
        </div>
        <div class="span4">
            <button class="btn">Sign up</button>
        </div>
    </div>
</div>
</body>
</html>