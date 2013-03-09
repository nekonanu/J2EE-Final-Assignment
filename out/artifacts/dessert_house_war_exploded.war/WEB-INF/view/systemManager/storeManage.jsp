<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-9
  Time: 下午5:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
    $(document).ready(function(){
        $(".change-btn").click(function(){
            var id = $(this).get(0).getAttribute("store-id");
            var name=$("#store_name"+id).val();
            var location=$("#store_location"+id).val();
            $.ajax({
                url: "<%=request.getContextPath()%>/systemManager/storeManage",
                type:'POST',
                dataType:'json',
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify({storeID:id,storeName:name,storeLocation:location,op:"change"}),
                success:function(data){
                    if(data.result=="success"){
                        $("#info").append("修改成功！");
                        setTimeout(function(){
                            $("#info").empty();
                        },3000);
                    }else{
                        $("#errorMessage").empty();
                        $("#errorMessage").append("服务器未知错误！");
                        $("#errorModal").modal('show');
                    }
                }
            });
        });

        $(".delete-btn").click(function(){
            var id = $(this).get(0).getAttribute("store-id");
            $.ajax({
                url: "<%=request.getContextPath()%>/systemManager/storeManage",
                type:'POST',
                dataType:'json',
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify({storeID:id,storeName:"",storeLocation:"",op:"delete"}),
                success:function(data){
                    if(data.result=="success"){
                        $("#info").append("修改成功！");
                        setTimeout(function(){
                            $.ajax({
                                type: "GET",
                                url: "<%=request.getContextPath()%>/systemManager/storeManage",
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
            <th>店铺ID</th>
            <th>店铺名称</th>
            <th>店铺地址</th>
            <th>更改</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${storeManageRecords}" varStatus="index">
            <tr>
                <td>${record.id}</td>
                <td><input id="store_name${record.id}" type="text" class=" input-medium" value="${record.storeName}"></td>
                <td><input id="store_location${record.id}" type="text" class=" input-large" value="${record.storeLocation}"></td>
                <td><button class="btn btn-success change-btn" store-id="${record.id}">更改</button> </td>
                <td><button class="btn btn-danger delete-btn" store-id="${record.id}">删除</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>