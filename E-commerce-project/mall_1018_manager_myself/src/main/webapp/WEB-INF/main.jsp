<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<!-- easyui插件 -->
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	//页面加载完成后自动跳转上次添加页面也就是选项卡
	$(function(){
			var url = "${url}";
			var title = "${title}";
			if(url != " " && title != " "){
			add_tab(url,title);
		}
	});
	//使用方法追加选项卡
		function add_tab(url,title){
		// add a new tab panel  
		var b = $('#tt').tabs('exists',title);
		if(!b){
			$('#tt').tabs('add',{    
			    title:title,    
			    href:url,    
			    closable:true,    
			    tools:[{    
			        iconCls:'icon-mini-refresh',    
			        handler:function(){    
			            alert('refresh');    
			        }    
			    }]    
			});
		}else{
			$('#tt').tabs('select',title);
		}

	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div data-options="region:'west',split:true,title:'后台管理页面'" style="width:150px;padding:10px;">
		<div class="easyui-accordion" style="width:500px;height:300px;">
			<div title="About" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
				<a href="javascript:add_tab('goto_spu.do','商品信息管理')" >商品信息管理</a><br>
				<a href="javascript:add_tab('goto_attr.do','商品属性管理')" >商品属性管理</a><br>
				<a href="javascript:add_tab('goto_sku.do','商品库存单元管理')">商品库存单元管理</a><br>
			</div>
				<div title="About" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
				商品库存单元管理<br>
			</div>
		</div>
		
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'">
		<!-- 加载选项卡 -->
		<div id="tt" class="easyui-tabs" style="height: 500px">
			
		</div>
	</div>
</body>
</html>