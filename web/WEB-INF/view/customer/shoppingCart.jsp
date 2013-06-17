<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-6-14
  Time: 下午2:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
    $(document).ready(function(){
        function getPage(role,path){
            $.ajax({
                type: "GET",
                url: "<%=request.getContextPath()%>/"+role+"/"+path,
                success: function(msg){
                    $("#contextContainer").hide("drop", {direction: "left"}, 300, function () {
                        $(this).html(msg);
                        $(this).fadeIn(400);
                    });
                }
            });
        }

        var size= $.cookie("size");
        if(size != undefined){
            for(var i=1;i<=size;i++){
                var id= $.cookie("product_id"+i);
                var name= $.cookie("product_name"+i);
                var type= $.cookie("product_type"+i);
                var price= $.cookie("product_price"+i);
                var remain_num= $.cookie("product_remain_num"+i);
                var num= $.cookie("product_num"+i);
                $("#table_body").append("<tr></tr>");
                var child=$("#table_body").find("tr").last();
                child.append("<td>"+id+"</td>")
                child.append("<td>"+name+"</td>")
                child.append("<td>"+type+"</td>")
                child.append("<td>"+price+"</td>")
                child.append("<td>"+remain_num+"</td>")
                child.append("<td>"+num+"</td>")
            }
        }

        $("#shopping_cart_submit").click(function(){
            var list = new Array();
            for(var i=1;i<=size;i++){
                var id= $.cookie("product_id"+i);
                var num= $.cookie("product_num"+i);
                list[i-1]={
                    product_id:id,
                    product_num:num
                };
            }
            var selection=$("#select-label");
            var pay_method="";
            if(selection.val() == "会员卡付款"){
                pay_method="vip_pay";
            }else{
                pay_method="normal_pay";
            }
            var data={order_data:list,pay_method:pay_method};

            $.ajax({
                type: "POST",
                url: "<%=request.getContextPath()%>/customer/order",
                dataType:'json',
                data:JSON.stringify(data),
                contentType:'application/json;charset=UTF-8',
                success: function(msg){
                    if(msg.result=="success"){
                        $("#infoMessage").empty();
                        $("#infoMessage").append(msg.infoMessage);
                        $("#infoModal").modal('show');
                        $("#infoModal").on('hidden',function(){
                            getPage("customer","shoppingCart");
                        });
                        <%--清除购物车的cookie--%>
                        for(var i=1;i<=size;i++){
                            $.removeCookie("product_id"+i);
                            $.removeCookie("product_name"+i);
                            $.removeCookie("product_type"+i);
                            $.removeCookie("product_price"+i);
                            $.removeCookie("product_remain_num"+i);
                            $.removeCookie("product_num"+i);
                            $.removeCookie("size");
                        }
                    }else{
                        console.log(msg.errorMessage);
                        $("#errorMessage").empty();
                        $("#errorMessage").append(msg.errorMessage);
                        $("#errorModal").modal('show');
                        $("#errorModal").on('hidden',function(){
                            getPage("customer","charge");
                        });
                    }
                }
            });
        });
    });
</script>

<div class="row">
    <div class="span4">
        <h3><strong>欢迎来到购物车</strong></h3>
    </div>
    <div class="span4 offset4">
        <div id="control" class="control-group">
            <label class="control-label" for="select-label">请选择付款方式</label>
            <div class="controls">
                <select id="select-label">
                    <option>会员卡付款</option>
                    <option>货到付款</option>
                </select>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="row">
        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>商品ID</th>
                <th>名称</th>
                <th>类型</th>
                <th>商品单价</th>
                <th>库存数量</th>
                <th>预定数量</th>
            </tr>
            </thead>
            <tbody id="table_body">
            </tbody>
        </table>
    </div>
</div>
<div class="row">
    <div class="span4">
        <button id="shopping_cart_submit" class="btn btn-primary btn-large">结算</button>
    </div>
</div>