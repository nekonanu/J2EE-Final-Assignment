<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-9
  Time: 下午2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <fieldset>
        <legend>订购信息</legend>
        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>客户名</th>
                <th>商品名</th>
                <th>商品单价</th>
                <th>预定数量</th>
                <th>预定时间</th>
                <th>是否出货</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="record" items="${orderStaRecords}" varStatus="index">
                <tr>
                    <td>${record.user.userName}</td>
                    <td>${record.product.productName}</td>
                    <td>${record.product.price}</td>
                    <td>${record.orderNum}</td>
                    <td><fmt:formatDate value="${record.orderDate}" type="both"/></td>
                    <td><c:if test="${record.orderCheck=='false'}">为出货</c:if><c:if test="${record.orderCheck=='true'}">已出货</c:if> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </fieldset>
</div>