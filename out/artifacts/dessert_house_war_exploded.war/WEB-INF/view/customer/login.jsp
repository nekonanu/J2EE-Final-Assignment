<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-2-28
  Time: 下午5:26
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nekosama|糖果屋</title>
    <jsp:include page="../../static/common/include.jsp"/>
</head>
<body class="global-background">
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner open">
        <a class="brand" href="#">客户登录</a>
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
                        <a href="<%=request.getContextPath()%>/employee/login">管理员登录</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<div id="login_container" class="container-fluid ">
    <form class="form-horizontal" action='dessertHouse/j_spring_security_check' method='POST'>
        <div class="row">
            <div id="loginBox" class="span6 offset1">
                    <div class="row">
                        <div class="span2">
                            <fieldset>
                                <div class="control-group">
                                    <label class="control-label font-white" for="j_username">USERNAME</label>
                                    <div class="controls">
                                        <input type="text" class="input-xlarge" name='j_username' id="j_username">
                                        <p class="help-block font-white">请输入用户名</p>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label font-white" for="j_password">PASSWORD</label>
                                    <div class="controls">
                                        <input type="text" class="input-xlarge" name='j_password' id="j_password">
                                        <p class="help-block font-white">请输入密码</p>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="controls font-white" for="remember_me">
                                        <input id="remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/>
                                        记住我
                                    </label>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span1 offset2">
                            <input name="login" type="submit" value="登陆" class="btn btn-primary"/>
                        </div>
                        <div class="span1">
                            <a href="<%=request.getContextPath()%>/customer/signUp" class="btn btn-primary">注册</a>
                        </div>
                    </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>