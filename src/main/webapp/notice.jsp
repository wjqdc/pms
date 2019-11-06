<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>发件箱</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/static/js/moment.min.js"></script>

	<script type="application/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<script type="application/javascript">
	$(function(){
		$.ajax({
			type:"GET",
			url:'${pageContext.request.contextPath}/notic/jsonList',
			success:function (msg) {
               if(msg.map.statusCode==200){
                   $(msg.map.page.list).each(function (index,item) {


			   var tr= "<tr name='appendtr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';' onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' >"
                      + "<td><input name='id' type='checkbox' id='id' value='"+item.nid+"' class='np'></td>"
					   + " <td>"+((msg.map.page.pageNum-1)*msg.map.page.pageSize+(index+1))+"</td>"
					   + " <td>"+item.ntitle+"</td>"
					   + " <td align='center'><span >"+item.remark+"</span></td>"
					   + " <td>"+ moment(item.ndate).format('YYYY-MM-DD')+"</td>"
					   + " <td><a >删除</a></td>"
					   + "</tr>"
					  $("#list-table").find("tr[id='foucus-tr']").before(tr);
                   })
                   //2.遍历分页
                   var div="<div></div>";
                   var firstPage="<a onclick='findList(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNum="+msg.map.queryStr+"'>首页</a>"
                   var prePage="<a onclick='findList(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNum="+(msg.map.page.pageNum-1)+msg.map.queryStr+"'>上一页</a>"
                   var nextPage="<a onclick='findList(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNum="+(msg.map.page.pageNum+1)+msg.map.queryStr+"'>下一页</a>"
                   var endPage="<a onclick='findList(this.name)'  href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNum="+msg.map.page.pages+msg.map.queryStr+"'>末页</a>"

                     var  pages="";
                   $(msg.map.page.navigatepageNums).each(function (index,item) {
                     pages=pages+ "&nbsp;<a onclick='findList(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNum="+item+msg.map.queryStr+"'>"+item+"</a>&nbsp;"
                   });
                   $(div).append(firstPage).append(prePage).append(pages).append(nextPage).append(endPage).appendTo($("#page-td"));

               }
            }
		})
	});
	function findList(obj){
        $.ajax({
            type:"GET",
            url:obj,
            success:function (msg) {
                if(msg.map.statusCode==200){

                    $("tr[name=appendtr]").remove();

                    $(msg.map.page.list).each(function (index,item) {
                        var tr= "<tr name='appendtr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';' onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' >"
                            + "<td><input name='id' type='checkbox' id='id' value='"+item.nid+"' class='np'></td>"
                            + " <td>"+((msg.map.page.pageNum-1)*msg.map.page.pageSize+(index+1))+"</td>"
                            + " <td>"+item.ntitle+"</td>"
                            + " <td align='center'><span >"+item.remark+"</span></td>"
                            + " <td>"+ moment(item.ndate).format('YYYY-MM-DD')+"</td>"
                            + " <td><a >删除</a></td>"
                            + "</tr>"
                        $("#list-table").find("tr[id='foucus-tr']").before(tr);
                    })

                }
            }
        })

    }
	//中将数字类型的时间转换为年月日形式的时间格式
	//原生JS 调用：parseDate(item.ndate)
/*    function  parseDate(obj){
        var date = new Date(obj);
        var month=date.getMonth()+1;
        var day=date.getDate();
        if(date.getMonth()<9){
            month ="0"+month;
        }
        if(day<9){
            day ="0"+day;
        }
        return date.getFullYear()+"-"+month+"-"+day;
    }*/
</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:信息箱>>通知公告
 </td>
	  <td>
		  <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/notice-send.jsp';" value='发布新通告' />
	  </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->

<!--  内容列表   -->
<form name="form2">

<table id="list-table" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr name="rawtr" bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;发件箱&nbsp;</td>
</tr>
<tr  align="center" bgcolor="#FAFAF1" height="22" id="tr2">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">标题</td>
	<td width="10%">内容</td>
	<td width="8%">发送时间</td>
	<td width="8%">操作</td>
</tr>

<%--<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
	<td>1</td>
	<td>项目完成的咋样?</td>
	<td align="center"><span >最近工作累不？项目完成的咋样?加油哈</span></td>
	<td>2015-02-03</td>
	<td><a >删除</a></td>
</tr>--%>

	<tr id="foucus-tr"></tr>
<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="" class="coolbg">全选</a>
	<a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center" id="page-td">



    </td>
</tr>
</table>

</form>
  

</body>
</html>