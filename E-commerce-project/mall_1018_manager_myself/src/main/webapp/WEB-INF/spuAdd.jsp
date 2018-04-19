<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function click_image(index){
		//限制用户罪所只能选择三张图片
		if(index == 3){
			alert("最多只能选择三张图片");
			return false;
		}
		//触发file对象事件，选择照片
		$("#file_"+index).click();
	}
	
	//在选择图片完成后加载图片
	function replace_image(index){
		// 获得图片对象
		var blob_image = $("#file_"+index)[0].files[0];
		var url = window.URL.createObjectURL(blob_image);
		
		// 替换image路径
		$("#image_"+index).attr("src",url);
		
		//获取file文件的个数
		var length = $(":file").length;
		//判断用户是否序选的是最后一张，index从0开始
		if((index+1)==length){
			//调用追加函数， 用户选择的是最后一张图片
			add_image(index);
		}
	
	}
	
	function add_image(index){
		//注意相同类型的引号不能嵌套
		var a = '<div id ="d_'+(index+1)+'" style="float:left;margin-left:10px;border:1px red solid;">';
		var b = '<input id="file_'+(index+1)+'" type="file" name="files" style="display:none;" onChange="replace_image('+(index+1)+')"/>'
		var c = '<img id="image_'+(index+1)+'" onclick="click_image('+(index+1)+')" style="cursor:pointer;" src="image/upload_hover.png" width="100px" height="100px"/>'
		var d = '</div>';
		//在div后面添加用after
		$("#d_"+index).after(a+b+c+d);
	}
</script>
<title>硅谷商城</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">west content</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'">
	<hr>
	<form action="spu_add.do" enctype="multipart/form-data" method="post">
		<input type="hidden" name="flbh1" value="${spu.flbh1}"/>
		<input type="hidden" name="flbh2" value="${spu.flbh2}"/>
		<input type="hidden" name="pp_id" value="${spu.pp_id}"/>
		商品名称：<input name="shp_mch" type="text" /><br>
		商品描述：<textarea  name="shp_msh" rows="5" cols="30"></textarea><br>
		商品图片：<br>
		<div id ="d_0" style="float:left;margin-left:10px;border:1px red solid;">
			<input id="file_0" type="file" name="files" style="display:none;" onChange="replace_image(0)"/>
			<img id="image_0" onclick="click_image(0)" style="cursor:pointer;" src="image/upload_hover.png" height="100px" width="100px"/><br>
		</div><br>
		<input type="submit" value="提交"/>
	</form>
	</div>
	
</body>
</html>