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
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function(){
		var flbh1 = "${flbh1}";//js中的EL表达式最好加""
		//根据二级选择获取三级选择
		$.getJSON("js/json/tm_class_1_"+flbh1+".js",function(data){
			$("#sku_tm_select").empty();//清空
			$(data).each(function(i,json){
				$("#sku_tm_select").append("<option value="+json.id+">"+json.ppmch+"</option>");
			});
		});
	});
	function get_spu_list(pp_id){
		var flbh2 = "${flbh2}";
		$.ajax({
			type:"post",
			url:"get_spu_list.do",
			data:{"pp_id":pp_id,"flbh2":flbh2},
			success:function(result){
				$("#spu_list").empty();//清空
				$(result).each(function(i,json){
					$("#spu_list").append("<option value="+json.id+">"+json.shp_mch+"</option>");
				});
			}
		});
	};
	function show_val(attr_id,checked){
		//cheched判断是否选中
		if(checked){
			//选中显示
			$("#val_"+attr_id).show();
		}else{
			//未选中，隐藏
			$("#val_"+attr_id).hide();
		}
	}
</script>
<title>Insert title here</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">west content</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'">
		<form action="save_sku.do">
			<input type="hidden" name="flbh1" value="${flbh1 }">
			<input type="hidden" name="flbh2" value="${flbh2 }">
		品牌：<select name="pp_id" id="sku_tm_select" onchange="get_spu_list(this.value);" ></select>
		商品：<select name="id" id="spu_list"></select><br>
		分类属性：
			<c:forEach items="${attr_list}" var="attr" varStatus="statu">
				<input value="${attr.id }" name="list_attr[${statu.index }].shxm_id" type="checkbox" onclick="show_val(${attr.id},this.checked)">${attr.shxm_mch }
			</c:forEach><br>
			<c:forEach items="${attr_list}" var="attr" varStatus="statu">
				<div id="val_${attr.id }" style="display: none;">
					<c:forEach items="${attr.list_value }" var="value">
						<input value="${value.id }" name="list_attr[${statu.index }].shxzh_id"  type="radio">${value.shxzh_mch }${value.shxzh }
					</c:forEach>
				</div>
			</c:forEach><br>
	商品库存名称：<input type="text" name="sku_mch"><br>
	商品库存数量：<input type="text" name="kc"><br>
	商品库存价格：<input type="text" name="jg"><br>
	商品库存地址：<input type="text" name="kcdz"><br>
	<input type="submit" value="提交">
</form>
	</div>
</body>
</html>