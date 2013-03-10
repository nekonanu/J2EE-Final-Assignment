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
<jsp:include page="../../static/common/include.jsp"/>
<script type="text/javascript">
    $(document).ready(function(){
        $("#login").click(function(){
            var user_name=$("#userName").val();
            var pass_word=$("#password").val();
            $.ajax({
                url:"<%=request.getContextPath()%>/customer/login",
                type:'POST',
                dataType:'json',
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify({userName:user_name,password:pass_word}),
                success:function(data){
                    if(data.result=="success"){
                        window.location.href="<%=request.getContextPath()%>/customer/home";
                    }else{
                        $("#errorMessage").empty();
                        $("#errorMessage").append("用户名或密码错误");
                        $("#errorModal").modal('show');
                    }
                }
            });
        });
    });
</script>
<head>
    <title>Nekosama|糖果屋</title>

</head>
<body class="global-background">
<jsp:include page="../common/dialog.jsp"/>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner open">
        <a class="brand" href="#">客户登录</a>
        <ul class="nav pull-right">
            <li class="active">
                <a href="<%=request.getContextPath()%>">首页</a>
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
    <div class="row">
        <div id="loginBox" class="span6 offset1">
            <div class="row">
                <div class="span2">
                    <fieldset>
                        <div class="control-group">
                            <label class="control-label font-white" for="userName">用户名</label>
                            <div class="controls">
                                <input type="text" class="input-xlarge" name='userName' id="userName">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label font-white" for="password">密码</label>
                            <div class="controls">
                                <input type="password" class="input-xlarge" name='password' id="password">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="controls font-white" for="remember_me">
                                <input id="remember_me" name="remember_me" type="checkbox" value="true"/>
                                记住我
                            </label>
                        </div>
                    </fieldset>
                </div>
            </div>
            <div class="row">
                <div class="span2">
                    <button  class="btn btn-primary btn-large" id="login">登录</button>
                    <a  href="<%=request.getContextPath()%>/customer/signUp" class="btn btn-primary btn-large">注册</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>