<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

</head>
<body>
	<hr>							<!-- this.value的意思是获取此行的value值，即option的value值 -->
	一级分类<select id="class_1_select" onchange="get_class_2(this.value)"><option>请选择</option></select>
	二级分类<select id="class_2_select"><option>请选择</option></select>
	品牌<select id="tm_select"><option>请选择</option></select>
	<br>
	查询<br>
	<a href="javascript:goto_spu_add();">添加</a><br>
	删除<br>
	编辑<br>
	<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		//一级分类获取今天资源js文件
		$.getJSON("js/json/class_1.js",function(data){
			$(data).each(function(i,json){			//将一级js的id传给二级选择,并拼接option
				$("#class_1_select").append("<option value="+json.id+">"+json.flmch1+"</option>");
			});
		});
	});
	//根据一级文件获取二级文件
	function get_class_2(calss_1_id){
									//将二级js的id传给三级选择
		$.getJSON("js/json/class_2_"+calss_1_id+".js",function(data){
			$("#class_2_select").empty();//清空
			$(data).each(function(i,json){
				$("#class_2_select").append("<option value="+json.id+">"+json.flmch2+"</option>");
			});
		});
		get_tm(calss_1_id);
	};
	//根据二级选择获取三级选择
	function get_tm(calss_1_id){
		$.getJSON("js/json/tm_class_1_"+calss_1_id+".js",function(data){
			$("#tm_select").empty();//清空
			$(data).each(function(i,json){
				$("#tm_select").append("<option value="+json.id+">"+json.ppmch+"</option>");
			});
		});
	};
	//跳转添加页面
	function goto_spu_add(){
		var class_1_id = $("#class_1_select").val();
		var class_2_id = $("#class_2_select").val();
		var tm_id =$("#tm_select").val();
		window.location.href="goto_spu_add.do?flbh1="+class_1_id+"&flbh2="+class_2_id+"&pp_id="+tm_id;
	};
</script>
</body>
</html>