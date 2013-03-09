<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-9
  Time: 下午7:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
    $(document).ready(function(){
        $(".change-btn").click(function(){
            var id = $(this).get(0).getAttribute("user-id");
            var type=$("#admin"+id).val();
            $.ajax({
                url: "<%=request.getContextPath()%>/systemManager/processAdminManage",
                type:'POST',
                dataType:'json',
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify({userID:id,type:type}),
                success:function(data){
                    if(data.result=="success"){
                        $("#info").append("修改成功！");
                        setTimeout(function(){
                            $("#info").empty();
                        },3000);
                    }else{
                        $("#errorMessage").empty();
                        $("#errorMessage").append(data.errorMessage);
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
            <th>用户ID</th>
            <th>用户名</th>
            <th>权限信息</th>
            <th>更改</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${adminManageRecords}" varStatus="index">
            <tr>
                <td>${record.id}</td>
                <td>${record.userName}</td>
                <td>
                    <select id="admin${record.id}">
                        <c:choose>
                            <c:when test="${record.type=='CUSTOMER'}"><option selected="selected">客户</option></c:when>
                            <c:otherwise><option>客户</option></c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${record.type=='CASHIER'}"><option selected="selected">店员</option></c:when>
                            <c:otherwise><option>店员</option></c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${record.type=='MANAGER'}"><option selected="selected">经理</option></c:when>
                            <c:otherwise><option>经理</option></c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${record.type=='SYSTEM_MANAGER'}"><option selected="selected">管理员</option></c:when>
                            <c:otherwise><option>管理员</option></c:otherwise>
                        </c:choose>
                    </select>
                </td>
                <td><button class="btn btn-success change-btn" user-id="${record.id}">更改</button> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>