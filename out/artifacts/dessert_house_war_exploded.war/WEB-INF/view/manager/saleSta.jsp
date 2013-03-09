<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-9
  Time: 下午2:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <fieldset>
        <legend>售出信息</legend>
        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>客户名</th>
                <th>商品名</th>
                <th>商品单价</th>
                <th>售出数量</th>
                <th>售出时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="record" items="${saleStaRecords}" varStatus="index">
                <tr>
                    <td>${record.user.userName}</td>
                    <td>${record.product.productName}</td>
                    <td>${record.product.price}</td>
                    <td>${record.saleNum}</td>
                    <td><fmt:formatDate value="${record.saleDate}" type="both"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </fieldset>
</div>