<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-9
  Time: 下午2:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <fieldset>
        <legend>会员信息</legend>
        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>用户名</th>
                <th>邮箱</th>
                <th>年龄</th>
                <th>性别</th>
                <th>余额</th>
                <th>注册时间</th>
                <th>到期时间</th>
                <th>是否激活</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="record" items="${vipStaRecords}" varStatus="index">
                <tr>
                    <td>${record.userName}</td>
                    <td>${record.email}</td>
                    <td>${record.age}</td>
                    <td><c:if test="${record.sex==0}">女</c:if><c:if test="${record.sex==1}">男</c:if> </td>
                    <td>${record.vipCard.remainAmount}</td>
                    <td><fmt:formatDate value="${record.vipCard.registerDate}" type="both"/></td>
                    <td><fmt:formatDate value="${record.vipCard.deadlineDate}" type="both"/></td>
                    <td><c:if test="${record.vipCard.status=='freeze'}">否</c:if><c:if test="${record.vipCard.status=='activate'}">是</c:if> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </fieldset>
</div>