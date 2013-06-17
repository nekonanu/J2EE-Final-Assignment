<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-8
  Time: 下午8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
    $(document).ready(function () {
        var index = 0;
        var fileList = new Array();
        $("#addBtn").click(function () {
            index++;
            $("#tableBody").append("<tr id='productAdd" + index + "'></tr>");
            var element = $("#tableBody").find("tr").last();
            element.append("<td><input id='name" + index + "' class='input-medium' type='text'/></td>");
            element.append("<td><input id='price" + index + "' class='input-mini' type='text'/></td>");
            element.append("<td><input id='num" + index + "' class='input-mini' type='number'/></td>");

            var storeOption = "";
            var select = $("#productType")[0];
            for (var i = 0; i < select.options.length; i++) {
                storeOption += "<option>" + select.options[i].text + "</option>";
            }
            element.append("<td><select id='productSelector" + index + "' class='input-medium'>" + storeOption + "</select></td>");

            var storeOption = "";
            var select = $("#storeData")[0];
            for (var i = 0; i < select.options.length; i++) {
                storeOption += "<option>" + select.options[i].text + "</option>";
            }
            element.append("<td><select id='storeName" + index + "' class='input-medium'>" + storeOption + "</select></td>");
            element.append("<td><span id='fileupload-zone"+index+"' class='btn btn-success fileinput-btn'><i class='icon-plus icon-white'></i><span id='file-span"+index+"'>选择文件</span><input class='fileupload-btn' id='fileupload"+index+"' type='file' name='files[]' multiple></span></td>");
            $("#productAdd" + index).hide();
            $("#productAdd" + index).fadeIn(300);


            $("#fileupload"+index).fileupload({
                url: "<%=request.getContextPath()%>/cashier/fileUpload",
                add: function(e, data){
                    console.log("add");
                    fileList.push(data.files[0].name);
                    data.submit();
                },
                dataType: 'json',
                dropZone: $("#fileupload-zone"+index),
                done: function (e, data) {
                    $("#info").append("上传成功！");
                    setTimeout(function(){
                        $("#info").empty();
                    },3000);
                    $("#fileupload-zone"+index).addClass('disabled');
                    $("#fileupload"+index).hide();
                }
            });

        });

        $("#saveBtn").click(function () {
            var list = new Array();
            for (var i = 0; i < index; i++) {
                var name = $("#name" + (i + 1)).val();
                var price = $("#price" + (i + 1)).val();
                var num = $("#num" + (i + 1)).val();
                var store_name = $("#storeName" + (i + 1)).val();
                var productType = $("#productSelector" + (i + 1)).val();
                var img_path = fileList[i];
                console.log(name);
                if (name != "" && store_name != "") {
                    list[i] = {
                        productName: name,
                        productPrice: price,
                        productNum: num,
                        storeName: store_name,
                        productType: productType,
                        imgPath: img_path
                    }
                }
            }

            console.log(list);
            $.ajax({
                type: "POST",
                url: "<%=request.getContextPath()%>/cashier/productAdd",
                dataType: 'json',
                data: JSON.stringify(list),
                contentType: 'application/json;charset=UTF-8',
                success: function (msg) {
                    if (msg.result == "success") {
                        $("#infoMessage").empty();
                        $("#infoMessage").append("操作成功！");
                        $("#infoModal").modal('show');
                    } else {
                        $("#errorMessage").empty();
                        $("#errorMessage").append("商品价格和商品数量只能为数字！");
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
            <th>商品名称</th>
            <th>商品单价</th>
            <th>库存数量</th>
            <th>类别</th>
            <th>所属店铺</th>
            <th>图片</th>
        </tr>
        </thead>
        <tbody id="tableBody">
        <%--<tr>--%>
            <%--<td>fuck</td>--%>
            <%--<td>fuck</td>--%>
            <%--<td>fuck</td>--%>
            <%--<td>fuck</td>--%>
            <%--<td>fuck</td>--%>
            <%--<td>--%>
                <%--<span class="btn btn-success fileinput-btn">--%>
                <%--<i class="icon-plus icon-white"></i>--%>
                <%--<span>Add files...</span>--%>
                <%--<!-- The file input field used as target for the file upload widget -->--%>
                <%--<input id="fileupload" type="file" name="files[]" multiple="">--%>
                <%--</span>--%>
            <%--</td>--%>
        <%--</tr>--%>
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
<select id="storeData" class="hide">
    <c:forEach var="record" items="${storeRecords}" varStatus="index">
        <option>${record.storeName}</option>
    </c:forEach>
</select>
<select id="productType" class="hide">
    <c:forEach var="record" items="${productTypeRecords}" varStatus="index">
        <option>${record}</option>
    </c:forEach>
</select>