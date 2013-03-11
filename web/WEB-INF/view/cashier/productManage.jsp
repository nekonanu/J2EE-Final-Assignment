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
<script type="text/javascript">
    $(document).ready(function(){


        var element=$(".productTypeSelect");
        console.log(element);
        console.log(element[0]);
        console.log(element.get(0));
        for(var i=0;i<element.length;i++){
            var storeOption="";
            var select=$("#productType")[0];
            var trueType=$("#selectedType")[0];
            for(var j=0;j<select.options.length;j++){
                if(trueType.options[i].text==select.options[j].text){
                    storeOption+="<option selected='selected'>"+select.options[j].text+"</option>";
                }else{
                    storeOption+="<option>"+select.options[j].text+"</option>";
                }
            }
            element.get(i).innerHTML=storeOption;
        }

        $(".change-btn").click(function(){
            console.log($(this));
            var id = $(this).get(0).getAttribute("product-id");
            var name=$("#product_name"+id).val();
            var price=$("#product_price"+id).val();
            var remain_num=$("#product_remain"+id).val();
            var productType=$("#typeSelector"+id).val();
            $.ajax({
                url: "<%=request.getContextPath()%>/cashier/productManage",
                type:'POST',
                dataType:'json',
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify({productId:id,productName:name,productPrice:price,productRemainNum:remain_num,productType:productType,op:"change"}),
                success:function(data){
                    if(data.result=="success"){
                        $("#info").append("修改成功！");
                        setTimeout(function(){
                            $("#info").empty();
                        },3000);
                    }else{
                        $("#errorMessage").empty();
                        $("#errorMessage").append("商品单价和库存数量只能为数字！");
                        $("#errorModal").modal('show');
                    }
                }
            });
        });
        $(".delete-btn").click(function(){
            var id = $(this).get(0).getAttribute("product-id");
            $.ajax({
                url: "<%=request.getContextPath()%>/cashier/productManage",
                type:'POST',
                dataType:'json',
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify({productId:id,productName:"0",productPrice:"0",productRemainNum:"0",op:"delete"}),
                success:function(data){
                    if(data.result=="success"){
                        $("#info").append("修改成功！");
                        setTimeout(function(){
                            $.ajax({
                                type: "GET",
                                url: "<%=request.getContextPath()%>/cashier/productManage",
                                success: function(msg){
                                    $("#contextContainer").empty();
                                    $("#contextContainer").append(msg);
                                }
                            });
                        },3000);
                    }else{
                        $("#errorMessage").empty();
                        $("#errorMessage").append("有关联数据，无法删除！");
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
            <th>商品ID</th>
            <th>名称</th>
            <th>商品单价</th>
            <th>库存数量</th>
            <th>类别</th>
            <th>所属店铺</th>
            <th>更改</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${productRecords}" varStatus="index">
            <tr>
                <td>${record.id}</td>
                <td><input id="product_name${record.id}" type="text" class=" input-medium" value="${record.productName}"></td>
                <td><input id="product_price${record.id}" type="text" class=" input-mini" value="${record.price}"></td>
                <td><input id="product_remain${record.id}" type="text" class=" input-mini" value="${record.remainNum}"></td>
                <td><select id="typeSelector${record.id}" class="productTypeSelect input-small"></select></td>
                <td>${record.store.storeName}</td>
                <td><button class="btn btn-success change-btn" product-id="${record.id}">更改</button> </td>
                <td><button class="btn btn-danger delete-btn" product-id="${record.id}">删除</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<select id="productType" class="hide">
    <c:forEach var="record" items="${productTypeRecords}" varStatus="index">
        <option>${record}</option>
    </c:forEach>
</select>
<select id="selectedType" class="hide">
    <c:forEach var="record" items="${productRecords}" varStatus="index">
        <option>${record.productType}</option>
    </c:forEach>
</select>