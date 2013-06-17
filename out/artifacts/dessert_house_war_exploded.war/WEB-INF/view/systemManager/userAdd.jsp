<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-9
  Time: 下午5:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
    $(document).ready(function(){
        var index=0;
        $("#addBtn").click(function(){
            index++;
            $("#tableBody").append("<tr id='userAdd"+index+"'></tr>");
            var element= $("#tableBody").find("tr").last();
            //用户名
            element.append("<td><input class='input-mini' id='name"+index+"' type='text'/></td>");
            //邮箱
            element.append("<td><input class='input-mini' id='email"+index+"' type='text'/></td>");
            //年龄
            element.append("<td><input class='input-mini' type='number' id='age"+index+"' /> </td>");
            //性别
            element.append("<td><select class='input-mini' id='sexSelect"+index+"' ><option>男</option><option>女</option></select> </td>");
            //地址
            element.append("<td><input class='input-small'  type='text' id='address"+index+"'/></td>")
            //权限
            element.append("<td><select class='input-small' id='adminSelect"+index+"' ><option>客户</option><option>店员</option><option>经理</option><option>管理员</option></select> </td>");
            //店铺
            var storeOption="";
            var select=$("#storeName")[0];
            var storeid_select=$("#storeID")[0];
            for(var i=0;i<select.options.length;i++){
                storeOption+="<option store-id='"+storeid_select.options[i].text+"'>"+select.options[i].text+"</option>";
            }
            element.append("<td><select class='input-small' id='storeSelect"+index+"'>"+storeOption+"</select> </td>");

            $("#userAdd"+index).hide();
            $("#userAdd"+index).fadeIn(300);
        });
        $("#saveBtn").click(function(){
            var list=new Array();
            for(var i=0;i<index;i++){
                var name=$("#name"+(i+1)).val();
                var email=$("#email"+(i+1)).val();
                var age=$("#age"+(i+1)).val();
                var sex=0;
                if($("#sexSelect"+(i+1)).val()=="男"){
                    sex=1;
                }else{
                    sex=0;
                }
                var address=$("#address"+(i+1)).val();
                var type="";
                if($("#adminSelect"+(i+1)).val()=="客户"){
                    type="CUSTOMER";
                }else if($("#adminSelect"+(i+1)).val()=="店员"){
                    type="CASHIER";
                }else if($("#adminSelect"+(i+1)).val()=="经理"){
                    type="MANAGER";
                }else if($("#adminSelect"+(i+1)).val()=="管理员"){
                    type="SYSTEM_MANAGER";
                }
                var storeID=0;
                var storeName=$("#storeSelect"+(i+1)).val();
                var selected=$("#storeSelect"+(i+1))[0];
                for(var j=0;j<selected.options.length;j++){
                    if(selected.options[i].text==storeName){
                        storeID=selected.options[i].getAttribute("store-id");
                    }
                }


                if(name!=""){
                    list[i]={
                        userName:name,
                        email:email,
                        age:age,
                        sex:sex,
                        address:address,
                        type:type,
                        storeID:storeID
                    }
                }
                console.log(list);
            }
            $.ajax({
                type: "POST",
                url: "<%=request.getContextPath()%>/systemManager/userAdd",
                dataType:'json',
                data:JSON.stringify(list),
                contentType:'application/json;charset=UTF-8',
                success: function(msg){
                    if(msg.result=="success"){
                        $("#infoMessage").empty();
                        $("#infoMessage").append("操作成功！");
                        $("#infoModal").modal('show');
                    }else{
                        $("#errorMessage").empty();
                        $("#errorMessage").append(msg.errorMessage);
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
            <th>用户名</th>
            <th>邮箱</th>
            <th>年龄</th>
            <th>性别</th>
            <th>地址</th>
            <th>权限</th>
            <th>所属店铺</th>
        </tr>
        </thead>
        <tbody id="tableBody">
        </tbody>
    </table>
</div>
<div class="row">
    <div class="span2">
        <button id="addBtn" class="btn btn-primary"><i class="icon-plus"></i>添加</button>
    </div>
    <div class="span2 offset10">
        <button id="saveBtn" class="btn btn-primary"><i class="icon-ok"></i>保存</button>
    </div>
</div>