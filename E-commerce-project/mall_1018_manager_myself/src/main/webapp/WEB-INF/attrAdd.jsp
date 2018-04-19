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
		//动态拼接table
	function add_table(index){
		var a='<table border="1" width="500px" id="attr_table_'+(index+1)+'">'
		var b='	<tr><td>属性名：<input type="text" name="list_attr['+(index+1)+'].shxm_mch"></td><td>添加属性值</td><td></td></tr>'
		var c='	<tr><td>属性值：<input type="text" name="list_attr['+(index+1)+'].list_value[0].shxzh"></td><td>单位:<input type="text" name="list_attr['+(index+1)+'].list_value[0].shxzh_mch"></td><td>删除</td></tr>'
		var d='	<tr><td>属性值：<input type="text" name="list_attr['+(index+1)+'].list_value[1].shxzh"></td><td>单位:<input type="text" name="list_attr['+(index+1)+'].list_value[1].shxzh_mch" onclick="add_table('+(index+1)+');"></td><td>删除</td></tr>'
		var e='</table>'
		$("#attr_table_"+index).after(a+b+c+d+e);
	};
		
</script>
<title>Insert title here</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',title:'Center'">
		<form action="attr_add.do" >
		<input type="hidden" value="${flbh2 } " name="flbh2">
		<table border="1" width="500px" id="attr_table_0">
			<tr><td>属性名：<input type="text" name="list_attr[0].shxm_mch"></td>
				<td>添加属性值</td><td></td></tr>
			<tr><td>属性值：<input type="text" name="list_attr[0].list_value[0].shxzh">
				</td><td>单位:<input type="text" name="list_attr[0].list_value[0].shxzh_mch"></td><td>删除</td></tr>
			<tr><td>属性值：<input type="text" name="list_attr[0].list_value[1].shxzh">
			</td><td>单位:<input type="text" name="list_attr[0].list_value[1].shxzh_mch" onclick="add_table(0);"></td><td>删除</td></tr>
		</table>	
		<br>
		<input type="submit" value="提交">
	</form>
	</div>
</body>
</html>