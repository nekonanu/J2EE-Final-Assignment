<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-5
  Time: 下午4:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fieldset>
    <legend>个人信息</legend>
    <div class="control-group">
        <label class="control-label" for="userName">用户名</label>
        <div class="controls">
            <input type="text" class="input-xlarge uneditable-input" disabled="true" id="userName" value="${userInfoRecord.userName}"/>
            <%--<button class="btn btn-primary">更改</button>--%>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="e_mail">电子邮件</label>
        <div class="controls">
            <input type="email" class="input-xlarge uneditable-input"  disabled="true" id="e_mail"  value="${userInfoRecord.email}"/>
            <%--<button class="btn btn-primary">更改</button>--%>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="address">居住地址</label>
        <div class="controls">
            <input type="email" class="input-xlarge uneditable-input" disabled="true" id="address"  value="${userInfoRecord.email}"/>
            <%--<button class="btn btn-primary">更改</button>--%>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="type">用户类型</label>
        <div class="controls">
            <input type="email" class="input-xlarge uneditable-input" disabled="true" id="type"  value="${userInfoRecord.type}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="store">所属店铺</label>
        <div class="controls">
            <input type="email" class="input-xlarge uneditable-input" disabled="true" id="store"  value="${userInfoRecord.store.storeName}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="card_remain">会员卡余额</label>
        <div class="controls">
            <input type="email" class="input-xlarge uneditable-input" disabled="true" id="card_remain"  value="${userInfoRecord.vipCard.remainAmount}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="register_date">会员卡注册日期</label>
        <div class="controls">
            <input type="email" class="input-xlarge uneditable-input" disabled="true" id="register_date"  value="<fmt:formatDate value="${userInfoRecord.vipCard.registerDate}" type="both"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="deadline_date">会员卡截止日期</label>
        <div class="controls">
            <input type="email" class="input-xlarge uneditable-input" disabled="true" id="deadline_date"  value="<fmt:formatDate value="${userInfoRecord.vipCard.deadlineDate}" type="both"/>"/>
        </div>
    </div>

</fieldset>
