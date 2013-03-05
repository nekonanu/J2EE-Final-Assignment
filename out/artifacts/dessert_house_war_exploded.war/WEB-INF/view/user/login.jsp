<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-5
  Time: 下午4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form name='f' action='/j_spring_security_check' method='POST' class="form-horizontal">
    <fieldset>
        <legend><i>甜品店</i> &nbsp; &nbsp;&nbsp;用户登录</legend>
        <div class="control-group">
            <label class="control-label" for="j_username">用户名</label>

            <div class="controls">
                <input type='text' name='j_username' value='' id="j_username">
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="j_password">密码</label>

            <div class="controls">
                <input type='password' name='j_password' id="j_password"/>
            </div>
        </div>

        <div class="control-group">
            <label class="controls" for="remember_me">
                <input id="remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/>
                记住我
            </label>
        </div>

        <input name="submit" type="submit" value="登录" class="btn btn-primary"/>
    </fieldset>
</form>
</body>
</html>