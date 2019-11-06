<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>对标管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<!-- 引入 echarts.js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.js"></script>
<script type="text/javascript">
     $(function (){

         $.ajax({
             type:"GET",
             url:"${pageContext.request.contextPath}/Benchmarking/list/2018",
             succeess:function (msg) {
                 var company =[];
                 var salesAccount =[];
                 var chart = echarts.init(document.getElementById("box"));
                 $(msg).each(function (index,item) {
                     company.push(item.companyName);
                     salesAccount.push(item.salesAmount);
                 })
                     var option ={
                         title: {
                             text:'2018年同行销售额比较'
                         },
                         legend: {
                             data: ['销量额']
                         },
                         xAxis: {
                             data:company
                         },
                         yAxis: {},
                         series: [{
                             name: '销售额',
                             type: 'bar',
                             data: salesAccount
                         }]
                     };
                     chart.setOption(option);
             }
         });
     })
</script>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:对标管理>>对标分析
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<div id="box" style="width:800px;height:600px;"></div>

</body>
</html>