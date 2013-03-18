<%--
  Created by IntelliJ IDEA.
  User: nekosama
  Date: 13-3-9
  Time: 下午2:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
    $(document).ready(function () {
            var chart;
            var colors = Highcharts.getOptions().colors,
                categories = ['蛋糕', '布丁', '冰激凌'],
                name = '热销分析',
                data = [{
                    y: 73.3,
                    color: colors[0],
                    drilldown: {
                        name: '蛋糕',
                        categories: ['乌贼蛋塔', '金克拉'],
                        data: [72.7, 27.2],
                        color: colors[0]
                    }
                }, {
                    y: 26.6,
                    color: colors[1],
                    drilldown: {
                        name: '布丁',
                        categories: ['摸你傻'],
                        data: [100],
                        color: colors[1]
                    }
                }, {
                    y: 0,
                    color: colors[2],
                    drilldown: {
                        name: '冰激凌',
                        categories: [],
                        data: [],
                        color: colors[2]
                    }
                }];


        function setChart(name, categories, data, color) {
            chart.xAxis[0].setCategories(categories, false);
            chart.series[0].remove(false);
            chart.addSeries({
                name: name,
                data: data,
                color: color || 'white'
            }, false);
            chart.redraw();
        }

        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'chartContainer',
                type: 'column'
            },
            title: {
                text: '热销茶品分析'
            },
            subtitle: {
                text: '点击查看同类产品销量百分比，再次点击返回'
            },
            xAxis: {
                categories: categories
            },
            yAxis: {
                title: {
                    text: '销量百分比'
                }
            },
            plotOptions: {
                column: {
                    cursor: 'pointer',
                    point: {
                        events: {
                            click: function() {
                                var drilldown = this.drilldown;
                                if (drilldown) { // drill down
                                    setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
                                } else { // restore
                                    setChart(name, categories, data);
                                }
                            }
                        }
                    },
                    dataLabels: {
                        enabled: true,
                        color: colors[0],
                        style: {
                            fontWeight: 'bold'
                        },
                        formatter: function() {
                            return this.y +'%';
                        }
                    }
                }
            },
            tooltip: {
                formatter: function() {
                    var point = this.point,
                            s = this.x +':<b>'+ this.y +'% market share</b><br/>';
                    if (point.drilldown) {
                        s += '点击可以查看 '+ point.category +'同类产品销量百分比';
                    } else {
                        s += ' 再次点击可以返回';
                    }
                    return s;
                }
            },
            series: [{
                name: name,
                data: data,
                color: 'white'
            }],
            exporting: {
                enabled: false
            }
        });
    });

</script>

<div id="chartContainer" class="row">

</div>

<script type="text/javascript">
    $(document).ready(function(){
        var typeList=new Array();
        var totalPersentList=new Array();
        var subCategories=new Array();
        var subPercentList=new Array();
        var hotDataElement=$("#hotDataContainer").find(".hotData");
        console.log(hotDataElement);
        for(var i=0;i<hotDataElement.size();i++){
            typeList.push(hotDataElement.get(i).find(".hotDataType")[0].innerHTML);
        }

    });

</script>

<div id="hotDataContainer">
<c:forEach var="record" items="${hotStaDataRecords}" varStatus="index">
    <div id="hotData${index}" class="hotData">
        <div class="hotDataType">${record.type}</div>
        <div class="hotDataTotalNum">${record.totalPercent}</div>
        <c:forEach var="subRecord" items="${record.datas}" varStatus="subIndex">
            <div class="subHotData">
                <div class="subHotDataName">${subRecord.productName}</div>
                <div class="subHotDataNum">${subRecord.saleNumPercent}</div>
            </div>
        </c:forEach>
    </div>
</c:forEach>
</div>




