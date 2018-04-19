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
	function b(){}
</script>
<title>Insert title here</title>
</head>
<body>
	商品列表:<br>
		<c:forEach items="${list_sku }" var="sku">
		<div style="margin-top:10px;margin-left:10px;float:left;border:1px red solid;width:250px;height:300px" >
			<img  src="upload/image/${sku.spu.shp_tp}" width="150px" height="150px"><br>
			<a href="goto_sku_detail.do?sku_id=${sku.id}&spu_id=${sku.spu.id}" target="_blank">库存名称：${sku.sku_mch }<br></a>
			价格：${sku.jg}<br>
			销量：${sku.sku_xl}<br>
			库存：${sku.kc}<br>
			库存地址：${sku.kcdz}<br>
		</div>
		</c:forEach>
</body>
</html>