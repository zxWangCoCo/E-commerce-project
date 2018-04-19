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
	<c:if test="${not empty list_cart }">
	<h6>最新加入的商品</h6>
		<c:forEach items="${list_cart }" var="cart">
				<div class="one">
					<img src="upload/image/${cart.shp_tp }" height="60" width="60"/>
					<span class="one_name">
						${cart.sku_mch }
					</span>
					<span class="one_prece">
						<b>￥${cart.sku_jg}</b><br />
						&nbsp;&nbsp;&nbsp;&nbsp;删除
					</span>
				</div><br>
		</c:forEach>
					<div class="gobottom">
						共<span>2</span>件商品&nbsp;&nbsp;&nbsp;&nbsp;
						共计￥<span>${sum }</span>
						<button class="goprice">去购物车</button>
					</div>
				</c:if>
				
					<c:if test="${empty list_cart }">
					<div class="gobottom">
						<h4 style="color: red">购物车还没有宝贝，快去购物吧</h4>
					</div>
				</c:if>
</body>
</html>