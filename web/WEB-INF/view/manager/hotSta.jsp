<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-9
  Time: 下午2:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <fieldset>
        <legend>热卖分析</legend>
        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>商品名</th>
                <th>商品单价</th>
                <th>库存数量</th>
                <th>预定总量</th>
                <th>售出总量</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="record" items="${hotStaRecords}" varStatus="index">
                <tr>
                    <td>${record.product.productName}</td>
                    <td>${record.product.price}</td>
                    <td>${record.product.remainNum}</td>
                    <td>${record.order_num}</td>
                    <td>${record.sale_num}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </fieldset>
</div>