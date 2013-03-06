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
    <form class="form-horizontal" action='/dessert_house_war/j_spring_security_check' method='POST'>
        <div class="row">
            <div class="span4 offset1 ">
                <fieldset>
                    <div class="control-group">
                        <label class="control-label" for="j_username">USERNAME</label>
                        <div class="controls">
                            <input type="text" class="input-xlarge" name='j_username' id="j_username">
                            <p class="help-block">请输入用户名</p>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="j_password">PASSWORD</label>
                        <div class="controls">
                            <input type="text" class="input-xlarge" name='j_password' id="j_password">
                            <p class="help-block">请输入密码</p>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="controls" for="remember_me">
                            <input id="remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/>
                            记住我
                        </label>
                    </div>
                </fieldset>
            </div>
        </div>
        <div class="row">
            <div class="span2 offset3">
                <input name="login" type="submit" value="登陆" class="btn"/>
            </div>
            <div class="span2">
                <button class="btn">注册</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>