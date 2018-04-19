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
<title>Insert title here</title>
</head>
<body>
<br>					

			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'north',split:true,border:false" style="height:50px">
						<!-- this.value的意思是获取此行的value值，即option的value值 -->
					一级分类<select data-options="width:150" class="easyui-combobox" id="attr_class_1_select" onchange="get_attr_class_2(this.value)"><option>请选择</option></select>
					二级分类<select data-options="width:150" class="easyui-combobox" id="attr_class_2_select" onchange="get_attr_list_json(this.value)"><option>请选择</option></select>
				</div>
				<div data-options="region:'west',split:true,border:false" style="width:100px">
					查询<br>
					<a href="javascript:goto_attr_add();">添加</a><br>
					删除<br>
					编辑<br>
				</div>
				<div data-options="region:'center',border:false">
					<div id="attrListInner" class="easyui-datagrid"></div>
				</div>
			</div>
	
	<script type="text/javascript">
		$(function(){
			//一级分类获取今天资源js文件
			/* $.getJSON("js/json/class_1.js",function(data){
				$(data).each(function(i,json){			//将一级js的id传给二级选择,并拼接option
					$("#attr_class_1_select").append("<option value="+json.id+">"+json.flmch1+"</option>");
				});
			}); */
			$('#attr_class_1_select').combobox({    
			    url:'js/json/class_1.js',    
			    valueField:'id',    
			    textField:'flmch1',
			    onChange:function get_attr_class_2(){
			    	//获取attr_calss_1_id值
			    	var attr_calss_1_id = $(this).combo("getValue");
					//将二级js的id传给三级选择,//根据一级文件获取二级文件
				/* 	$.getJSON("js/json/class_2_"+attr_calss_1_id+".js",function(data){
						$("#attr_class_2_select").empty();//清空
						$(data).each(function(i,json){
							$("#attr_class_2_select").append("<option value="+json.id+">"+json.flmch2+"</option>");
						});
					}); */
					$('#attr_class_2_select').combobox({    
					    url:"js/json/class_2_"+attr_calss_1_id+".js",    
					    valueField:'id',    
					    textField:'flmch2',
					    onChange:function(){
					    	var flbh2 = $(this).combo("getValue");
					    	get_attr_list_json(flbh2);
					    }
					});
				}
			});
		});
		//跳转添加页面
		function goto_attr_add(){
			//由于下拉列表已经变成了combobox,要用easyui方法获取
			var attr_class_2_id = $("#attr_class_2_select").combobox("getValue");//$("#attr_class_2_select").val();
			//window.location.href="goto_attr_add.do?flbh2="+attr_class_2_id;
			add_tab("goto_attr_add.do?flbh2="+attr_class_2_id,"添加属性");
		};
		
		//ajax异步查询
		function get_attr_list(flbh2){
			//使用ajax异步加载页面
			$.ajax({
				type:"post",
				url:"get_attr_list.do",
				data:{"flbh2":flbh2},
				success:function(result){
					//将返回的页面加到div中
					$("#attrListInner").html(result);	
				}
			});
		
		}
		//ajax异步查询
		function get_attr_list_json(flbh2){
			//ajax返回以后调用easyui方法，优化内嵌页
			$('#attrListInner').datagrid({ 
				//给后台传入参数
				queryParams: {
					flbh2:flbh2
				},
			    url:'get_attr_list_json.do',    
			    columns:[[ //columns:表示表格的列属性   
			        {field:'id',title:'id',width:100},    
			        {field:'shxm_mch',title:'属性名',width:100},    
			        {field:'list_value',title:'属性值',width:270 ,
			        	//此方法是处理特殊属性
			        	formatter: function(value,row,index){
							//自定义处理定义属性的代码
							var str = "";
							$(value).each(function(i,json){
								str = str + json.shxzh_mch + json.shxzh+"";
							});
							return str;
			        	}
			        },    
			        {field:'chjshj',title:'创建时间',width:160,
			        	//处理创建时间
			        	formatter: function(value,row,index){
							//自定义处理定义属性的代码,new一个Date
							var date = new Date(value);
							//将date装换成String格式
							var dateObj = date.toLocaleString();
							return dateObj;
			        	}
			        }    
			    ]]    
			}); 
		}
</script>
</body>
</html>