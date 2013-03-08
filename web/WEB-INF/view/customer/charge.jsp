<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-7
  Time: 下午3:50
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <legend>会员卡充值</legend>
    <div class="control-group">
        <label class="control-label" for="store">所属店铺</label>
        <div class="controls">
            <input type="email" class="input-xlarge uneditable-input" disabled="true" id="store"  value="${charge_storeRecord.storeName}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="card_remain">会员卡余额</label>
        <div class="controls">
            <input type="email" class="input-xlarge uneditable-input" disabled="true" id="card_remain"  value="${charge_vipCardRecord.remainAmount}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="register_date">会员卡注册日期</label>
        <div class="controls">
            <input type="email" class="input-xlarge uneditable-input" disabled="true" id="register_date"  value="<fmt:formatDate value="${charge_vipCardRecord.registerDate}" type="both"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="deadline_date">会员卡截止日期</label>
        <div class="controls">
            <input type="email" class="input-xlarge uneditable-input" disabled="true" id="deadline_date"  value="<fmt:formatDate value="${charge_vipCardRecord.deadlineDate}" type="both"/>"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="charge_amount">充值金额</label>
        <div class="controls">
            <input type="email" class="input-xlarge" id="charge_amount"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="span2">
        <button id="chargeSubmit" class="btn btn-primary btn-large">提交</button>
    </div>
</div>



