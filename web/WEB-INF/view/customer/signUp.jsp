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
                <form:form method="post" action="/customer/signUp" commandName="customerSignUpForm">
                    <fieldset>
                        <legend>快速注册</legend>
                        <div class="control-group">
                            <form:label class="control-label" for="inputUserName" path="userName">您的用户名</form:label>
                            <div class="controls">
                                <%--<input type="text" class="input-xlarge" name="userName" id="inputUserName">--%>
                                <form:input id="inputUserName" path="userName" cssClass="input-xlarge"/>
                                <p class="help-block">字母，数字皆可</p>
                            </div>
                            <div class="controls">
                                <form:errors path="userName" cssClass="alert alert-error"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <form:label class="control-label" for="inputPassword" path="password">您的密码</form:label>
                            <div class="controls">
                                <%--<input type="text" class="input-xlarge" name="password" id="inputPassword">--%>
                                <form:password path="password" id="inputPassword" cssClass="input-xlarge"/>
                                <p class="help-block">请妥善保管您的密码</p>
                            </div>
                            <div class="controls">
                                <form:errors path="password" cssClass="alert alert-error"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <form:label class="control-label" for="confirmPassword" path="repeatPassword">再次输入密码</form:label>
                            <div class="controls">
                                <%--<input type="text" class="input-xlarge" name="password" id="confirmPassword">--%>
                                <form:password path="repeatPassword" id="confirmPassword" cssClass="input-xlarge"/>
                                <p class="help-block">与上次输入一样</p>
                            </div>
                            <div class="controls">
                                <form:errors path="repeatPassword" cssClass="alert alert-error"/>
                                <form:errors path="passwordValid" cssClass="alert alert-error"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <input name="submit" type="submit" value="提交" class="btn btn-primary btn-large btn-center"/>
                        </div>
                    </fieldset>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>