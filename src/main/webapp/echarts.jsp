<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
    <!-- 引入 echarts.js -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.js"></script>
    <script type="text/javascript">
        window.onload=function () {
            var name=[];
            var values=[];
            $.ajax({
                type:"get",
              url:"${pageContext.request.contextPath}/echart/data",
                success:function (msg) {
                   for (key in msg){
                       name.push(key);
                       values.push(msg[key]);
                   }
                    // 基于准备好的dom，初始化echarts实例
                    var Chart = echarts.init(document.getElementById('main'));
                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '各操作系统手机销量展示',
                            subtext:'这是副文本标题',
                            top:'bottom',
                            left:'center',
                            textStyle:{
                                color:'#ccc',
                                fontSize:30
                            }
                        },
                        xAxis: {
                            data:name
                        },
                        yAxis: {},
                        series: [{
                            name: '销量',
                            type: 'bar',
                            data: values
                        }]
                    };
                    Chart.setOption(option);
                }
            })
        }
    </script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>

</body>
</html>