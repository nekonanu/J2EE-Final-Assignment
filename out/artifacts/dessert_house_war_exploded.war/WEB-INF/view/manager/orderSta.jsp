<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-9
  Time: 下午2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
    $(document).ready(function(){
        var orderTypePie;
        var orderLine;
        initOrderLine(orderLine);
        initOrderTypePie(orderTypePie);

        function initOrderTypePie(chart){
            var list=new Array();
            var type_element=$("#orderTypeData")[0];
            var percent_element=$("#orderPercentData")[0];
            for(var i=0;i<type_element.options.length;i++){
                list[i]=[type_element.options[i].text,parseFloat(percent_element.options[i].text)];
            }

            chart = new Highcharts.Chart({
                chart: {
                    renderTo: 'orderTypeChart',
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                },
                title: {
                    text: '订购类型统计'
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
                    percentageDecimals: 1
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            color: '#000000',
                            connectorColor: '#000000'
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: '订购类型统计',
                    data: list
                }]
            });
        }

        function initOrderLine(chart){
            var list=new Array();
            var num=$("#orderPay")[0];
            var year=$("#orderYear")[0];
            var month=$("#orderMonth")[0];
            var day=$("#orderDay")[0];
            for(var i=0;i<num.options.length;i++){
                list[i]=[Date.UTC(parseInt(year.options[i].text),parseInt(month.options[i].text),parseInt(day.options[i].text)),parseInt(num.options[i].text)];
            }

            chart = new Highcharts.Chart({
                chart: {
                    renderTo: 'orderLineChart',
                    type: 'spline'
                },
                title: {
                    text: '订单交易量分析'
                },
                xAxis: {
                    type: 'datetime'
//                    dateTimeLabelFormats: { // don't display the dummy year
//                        month: '%e. %b',
//                        year: '%b'
//                    }
                },
                yAxis: {
                    title: {
                        text: '当日订单总交易量'
                    },
                    min: 0
                },
                tooltip: {
                    formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                                Highcharts.dateFormat('%e. %b', this.x) +': '+ this.y +' m';
                    }
                },

                series: [{
                    name: '交易量',
                    data: list
                }]
            });
        }
    });
</script>

<div id="orderTypeChart" class="row">

</div>
<div id="orderLineChart" class="row">

</div>
<div class="row">
    <fieldset>
        <legend>订购信息</legend>
        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>客户名</th>
                <th>商品名</th>
                <th>商品单价</th>
                <th>预定数量</th>
                <th>预定时间</th>
                <th>是否出货</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="record" items="${orderStaRecords}" varStatus="index">
                <tr>
                    <td>${record.user.userName}</td>
                    <td>${record.product.productName}</td>
                    <td>${record.product.price}</td>
                    <td>${record.orderNum}</td>
                    <td><fmt:formatDate value="${record.orderDate}" type="both"/></td>
                    <td><c:if test="${record.orderCheck=='false'}">为出货</c:if><c:if test="${record.orderCheck=='true'}">已出货</c:if> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </fieldset>
</div>
<select id="orderTypeData" class="hide">
    <c:forEach var="record" items="${orderTypePieRecords}" varStatus="index">
        <option>${record.type}</option>
    </c:forEach>
</select>
<select id="orderPercentData" class="hide">
    <c:forEach var="record" items="${orderTypePieRecords}" varStatus="index">
        <option>${record.percent}</option>
    </c:forEach>
</select>

<select id="orderPay" class="hide">
    <c:forEach var="record" items="${orderLineRecords}" varStatus="index">
        <option>${record.pay}</option>
    </c:forEach>
</select>
<select id="orderYear" class="hide">
    <c:forEach var="record" items="${orderLineRecords}" varStatus="index">
        <option>${record.year}</option>
    </c:forEach>
</select>
<select id="orderMonth" class="hide">
    <c:forEach var="record" items="${orderLineRecords}" varStatus="index">
        <option>${record.month}</option>
    </c:forEach>
</select>
<select id="orderDay" class="hide">
    <c:forEach var="record" items="${orderLineRecords}" varStatus="index">
        <option>${record.day}</option>
    </c:forEach>
</select>