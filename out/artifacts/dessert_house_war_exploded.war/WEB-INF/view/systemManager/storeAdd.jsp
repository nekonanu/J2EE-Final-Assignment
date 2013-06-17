<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-9
  Time: 下午5:04
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
            $("#tableBody").append("<tr id='store_add"+index+"'></tr>");
            var element= $("#tableBody").find("tr").last();
            element.append("<td><input id='name"+index+"' class='input-xlarge' type='text'/></td>");
            element.append("<td><input id='location"+index+"' class='input-xlarge' type='text'/></td>");
            $("#store_add"+index).hide();
            $("#store_add"+index).fadeIn(300);
        });
        $("#saveBtn").click(function(){
            var list=new Array();
            for(var i=0;i<index;i++){
                var name=$("#name"+(i+1)).val();
                var location=$("#location"+(i+1)).val();
                if(name!=""&&location!=""){
                    list[i]={
                        storeName:name,
                        storeLocation:location
                    }
                }
            }
            $.ajax({
                type: "POST",
                url: "<%=request.getContextPath()%>/systemManager/storeAdd",
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
                        $("#errorMessage").append("商店名已存在！");
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
            <th>店铺名称</th>
            <th>店铺位置</th>
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