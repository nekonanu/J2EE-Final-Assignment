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

        $(".btn_shopping_cart").click(function(){
            var product_id=$(this)[0].getAttribute("product-id");
            var num=$("#product_num"+product_id).val();
            var name=$("#product_name"+product_id).html();
            var type=$("#product_type"+product_id).html();
            var remain_num=$("#remain_num"+product_id).html();
            var price=$("#product_price"+product_id).html();
            <%--更新数量--%>
            if($.cookie("size") == undefined){
                $.cookie("size",1);
            }else{
                $.cookie("size", parseInt($.cookie("size")) +1);
            }
            $.cookie("product_id"+ $.cookie("size"),product_id);
            $.cookie("product_num"+ $.cookie("size"),num);
            $.cookie("product_name"+ $.cookie("size"),name);
            $.cookie("product_type"+ $.cookie("size"),type);
            $.cookie("product_remain_num"+ $.cookie("size"),remain_num);
            $.cookie("product_price"+ $.cookie("size"),price);
            $("#infoMessage").empty();
            $("#infoMessage").append("商品添加购物车成功！");
            $("#infoModal").modal('show');
        });

        $(".btn_buy").click(function(){
            var list = new Array();
            var id=$(this)[0].getAttribute("product-id");
            var num=$("#product_num"+id).val();
            console.log("product_id"+id);
            list[0]={
                product_id:id,
                product_num:num
            };
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
            <h2><strong>欢迎订购！</strong></h2>
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
        <c:forEach var="record" items="${productRecords}" varStatus="index">
            <div class="row row-margin">
                <div class="span4">
                    <img class="img-rounded" src="${record.imgPath}" width="100%" height="100%"/>
                </div>
                <div class="span6 offset2">
                    <div class="row">
                        <div class="span4">
                            <span>商品ID：</span>
                        </div>
                        <div class="span8">
                            <span>${record.id}</span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span4">
                            <span>商品名称：</span>
                        </div>
                        <div class="span8">
                            <span id="product_name${record.id}">${record.productName}</span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span4">
                            <span>商品类型：</span>
                        </div>
                        <div class="span8">
                            <span id="product_type${record.id}">${record.productType}</span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span4">
                            <span>商品库存：</span>
                        </div>
                        <div class="span8">
                            <span id="remain_num${record.id}">${record.remainNum}</span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span4">
                            <span>商品价格：</span>
                        </div>
                        <div class="span8">
                            <span id="product_price${record.id}">${record.price}</span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span4">
                            <span>预定数量：</span>
                        </div>
                        <div class="span8">
                            <input id="product_num${record.id}" type="number" class="input-mini product_num" value="0"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span6">
                            <button id="btn_shopping_cart" class="btn btn-primary btn_shopping_cart" product-id="${record.id}">加入购物车</button>
                        </div>
                        <div class="span6">
                            <button id="btn_buy" class="btn btn-primary btn_buy" product-id="${record.id}">立即购买</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>