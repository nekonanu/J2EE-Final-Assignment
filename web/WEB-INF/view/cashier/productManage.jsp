<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-8
  Time: 下午4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>商品ID</th>
            <th>名称</th>
            <th>商品单价</th>
            <th>库存数量</th>
            <th>所属店铺</th>
            <th>更改</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${productRecords}" varStatus="index">
            <tr>
                <td>${record.id}</td>
                <td><input type="text" class="disabled input-medium" disabled="true" value="${record.productName}"></td>
                <td><input type="text" class="disabled input-mini" disabled="true" value="${record.price}"></td>
                <td><input type="text" class="disabled input-mini" disabled="true" value="${record.remainNum}"></td>
                <td>${record.store.storeName}</td>
                <td><button class="btn btn-success change-btn" product-id="${record.id}">更改</button> </td>
                <td><button class="btn btn-danger delete-btn" product-id="${record.id}">删除</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>