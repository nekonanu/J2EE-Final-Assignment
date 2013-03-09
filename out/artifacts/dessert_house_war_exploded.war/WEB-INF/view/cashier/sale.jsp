<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-9
  Time: 上午10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
    $(document).ready(function(){
        $("#saleSubmit").click(function(){
            var list=new Array();
            var element=$("td .active");
            for(var i=0;i<element.length;i++){
                list[i]={
                    orderID:element[i].getAttribute("order-id")
                };
            }
            console.log(list);
            $.ajax({
                type: "POST",
                url: "<%=request.getContextPath()%>/cashier/sale",
                dataType:'json',
                data:JSON.stringify(list),
                contentType:'application/json;charset=UTF-8',
                success: function(msg){
                    if(msg.result=="success"){
                        $("#infoMessage").empty();
                        $("#infoMessage").append("提交操作成功");
                        $("#infoModal").modal('show');

                    }else{
                        $("#errorMessage").empty();
                        $("#errorMessage").append("服务器故障！");
                        $("#errorModal").modal('show');
                    }
                }
            });
        });
    });
</script>

<div class="row">
    <label id="info" class="label label-success"></label>
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>订单编号</th>
            <th>客户</th>
            <th>商品名</th>
            <th>单价</th>
            <th>订购数量</th>
            <th>预定时间</th>
            <th>所属店铺</th>
            <th>发货</th>
        </tr>
        </thead>
        <tbody id="tableBody">
            <c:forEach var="record" items="${saleOrderRecords}" varStatus="index">
                <tr>
                    <td>${record.id}</td>
                    <td>${record.user.userName}</td>
                    <td>${record.product.productName}</td>
                    <td>${record.product.price}</td>
                    <td>${record.orderNum}</td>
                    <td><fmt:formatDate value="${record.orderDate}" type="both"/></td>
                    <td>${record.store.storeName}</td>
                    <td><button class="btn btn-success  btn-small dispatch-btn" data-toggle="button" order-id="${record.id}">发货</button> </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div class="row">
    <div class="span2">
        <button id="saleSubmit" class="btn btn-primary btn-large">提交</button>
    </div>
</div>