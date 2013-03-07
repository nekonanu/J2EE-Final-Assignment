<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-7
  Time: 下午3:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
    $(document).ready(function(){
        $("#orderSubmit").click(function(){
            var element=$(".table .active");
            var size=element.size();
//            console.log(size);
            var list=new Array();
//            console.log(element);
            for(var i=0;i<size;i++){
                var id=element.get(i).getAttribute("order-product_id");
                var num=$(".product_num").get(i).value;
//                console.log(num);

                console.log("product_id"+id);
                list[i]={
                    product_id:id,
                    product_num:num
                };
                console.log(list[i]);
            }

            var postData="{ name: 'John', time: '2pm' }";
            console.log(list);
            $.ajax({
                type: "POST",
                url: "<%=request.getContextPath()%>/customer/order",
                dataType:'json',
//                data:JSON.stringify([{product_id:'5',product_num:'2'},{product_id:'1',product_num:'4'}]),
                data:JSON.stringify(list),
                contentType:'application/json;charset=UTF-8',
                success: function(msg){
                    if(msg.result=="success"){
                        alert("cool!");
                    }

//                    $("#contextContainer").empty();
//                    $("#contextContainer").append(msg);
                }
            });
        });
    });
</script>
    <div class="row">
        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>商品ID</th>
                <th>名称</th>
                <th>剩余数量</th>
                <th>价格</th>
                <th>预定数量</th>
                <th>预定</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="record" items="${productRecords}" varStatus="index">
                <tr>
                    <td>${record.id}</td>
                    <td>${record.productName}</td>
                    <td>${record.remainNum}</td>
                    <td>${record.price}</td>
                    <td><input type="number" class="input-mini product_num" value="0"/> </td>
                    <td><button class="btn btn-primary btn-small" data-toggle="button" order-product_id="${record.id}">预定</button> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row">
        <div class="span2 offset5">
            <button id="orderSubmit" class="btn btn-primary btn-large">提交</button>
        </div>
    </div>
