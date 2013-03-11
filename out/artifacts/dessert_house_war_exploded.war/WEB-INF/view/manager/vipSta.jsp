<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-9
  Time: 下午2:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
    $(document).ready(function(){
        var activeChart;
        var registerChart;
        var chargeLineChart;
        initActiveChart(activeChart);
        initRegisterLineChart(registerChart);

        function initActiveChart(chart){
            chart = new Highcharts.Chart({
                chart: {
                    renderTo: 'isActiveChart',
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                },
                title: {
                    text: '会员激活情况'
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
                    name: '会员激活情况',
                    data: [
                        {
                            name: '已激活会员',
                            y: ${vipStaActive},
                            sliced: true,
                            selected: true
                        },
                        ['未激活会员',    ${vipStaFreeze}]
                    ]
                }]
            });
        }
        function initRegisterLineChart(chart){
            var list=new Array();
            var num=$("#registerNum")[0];
            var year=$("#registerYear")[0];
            var month=$("#registerMonth")[0];
            var day=$("#registerDay")[0];
            for(var i=0;i<num.options.length;i++){
                list[i]=[Date.UTC(parseInt(year.options[i].text),parseInt(month.options[i].text),parseInt(day.options[i].text)),parseInt(num.options[i].text)];
            }

            chart = new Highcharts.Chart({
                chart: {
                    renderTo: 'registerFrequencyLine',
                    type: 'spline'
                },
                title: {
                    text: '会员注册分析'
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
                        text: '注册数'
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
                    name: '注册数',
                    data: list
                }]
            });
        }

        function setColors(){
            Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function(color) {
                return {
                    radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
                    stops: [
                        [0, color],
                        [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
                    ]
                };
            });
        }
    });
</script>


<div id="isActiveChart" class="row">

</div>
<div id="registerFrequencyLine" class="row">

</div>
<div class="row">
    <fieldset>
        <legend>会员信息</legend>
        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>用户名</th>
                <th>邮箱</th>
                <th>年龄</th>
                <th>性别</th>
                <th>余额</th>
                <th>折扣率</th>
                <th>注册时间</th>
                <th>到期时间</th>
                <th>是否激活</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="record" items="${vipStaRecords}" varStatus="index">
                <tr>
                    <td>${record.userName}</td>
                    <td>${record.email}</td>
                    <td>${record.age}</td>
                    <td><c:if test="${record.sex==0}">女</c:if><c:if test="${record.sex==1}">男</c:if> </td>
                    <td>${record.vipCard.remainAmount}</td>
                    <td>${record.vipCard.cutoff}</td>
                    <td><fmt:formatDate value="${record.vipCard.registerDate}" type="both"/></td>
                    <td><fmt:formatDate value="${record.vipCard.deadlineDate}" type="both"/></td>
                    <td><c:if test="${record.vipCard.status=='freeze'}">否</c:if><c:if test="${record.vipCard.status=='activate'}">是</c:if> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </fieldset>
</div>

<select id="registerNum" class="hide">
    <c:forEach var="record" items="${vipStaRegisterRecords}" varStatus="index">
        <option>${record.num}</option>
    </c:forEach>
</select>
<select id="registerYear" class="hide">
    <c:forEach var="record" items="${vipStaRegisterRecords}" varStatus="index">
        <option>${record.year}</option>
    </c:forEach>
</select>
<select id="registerMonth" class="hide">
    <c:forEach var="record" items="${vipStaRegisterRecords}" varStatus="index">
        <option>${record.month}</option>
    </c:forEach>
</select>
<select id="registerDay" class="hide">
    <c:forEach var="record" items="${vipStaRegisterRecords}" varStatus="index">
        <option>${record.day}</option>
    </c:forEach>
</select>