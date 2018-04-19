<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function save_param(shxm_id,shxzh_id,shxmch){
		//1.数组传参
	//	$("#paramArea").append("<input name='shuxparam' type='text' value='"+shxm_id+"_"+shxzh_id+"'>"+shxmch);
		//2.json字符串传参
		$("#paramArea").append("<input name='shuxparam' type='text' value='{\"shxm_id\":"+shxm_id+",\"shxzh_id\":"+shxzh_id+"}'>"+shxmch);
		//调用异步方法提交参数
		get_list_by_attr();
	}
	function get_list_by_attr(){
		//定义一个数组
		//1.var array = new Array();
		var flbh2 = ${flbh2};
		var attrJson = {"flbh2":flbh2};//定义一个空格json对象
		//var jsonSrt = "flbh2="+${flbh2};//3.拼接字符串
		//遍历获得name为shuxparam的所有input对象数组
		$("#paramArea input[name='shuxparam']").each(function(i,json){
			//把每个input的value值赋给数组，提交
			//1.array[i] = json.value;
			//list_attr
			var json = $.parseJSON(json.value);//解析JSON字符串并返回解析后的对象
			attrJson["list_attr["+i+"].shxm_id"]=json.shxm_id;//拼接封装的对形象，注意数组的名字，无set不封装
			attrJson["list_attr["+i+"].shxzh_id"]=json.shxzh_id;
			//jsonStr = "&list_attr["+i+"].shxm_id]="+json.shxm_id+"&list_attr["+i+"].shxzh_id="+json.shxzh_id;
		});
		$.ajax({
			type:"get",
			url:"get_list_by_attr.do",
			//1.data:{arrayvalue:array},//将数组传给后台
			data:attrJson,//传入添加好的JSON对象
			success:function(result){
				$("#skuListInner").html(result);
			}
		});
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<div id="paramArea"></div>
	属性列表：<br>
	<c:forEach items="${attr_list }" var="attr">
		${attr.shxm_mch}:
		<c:forEach items="${attr.list_value }" var="value">
			<a href="javascript:save_param(${attr.id },${value.id },'${value.shxzh}${value.shxzh_mch}');">${value.shxzh }${value.shxzh_mch }</a>
		</c:forEach>
		<br>
	</c:forEach>
</body>
</html>