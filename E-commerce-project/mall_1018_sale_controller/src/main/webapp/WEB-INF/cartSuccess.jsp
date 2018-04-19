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
<link rel="stylesheet" type="text/css" href="css/css.css"/>
<link rel="stylesheet" type="text/css" href="css/finishCart.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function b(){}
</script>
<title>Insert title here</title>
</head>
<body>
		<div class="search">
			<div class="logo"><img src="./images/logo.jpg" alt=""></div>
			<div class="search_on">
				<div class="se">
					<input type="text" name="search" class="lf">
					<input type="submit" class="clik" value="搜索">
				</div>
				<div class="se">
					<a href="">取暖神奇</a>
					<a href="">1元秒杀</a>
					<a href="">吹风机</a>
					<a href="">玉兰油</a>
				</div>
			</div>
			<div class="card">
				<a href="">购物车<div class="num">0</div></a>
				
				<!--购物车商品-->
			<div class="cart_pro">
				<h6>最新加入的商品</h6>
				<div class="one">
					<img src="images/lec1.png"/>
					<span class="one_name">
						商品名称商品名称商品名称商品名称
					</span>
					<span class="one_prece">
						<b>￥20000</b><br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除
					</span>
				</div>
				
				<div class="one border">
					<img src="images/lec1.png"/>
					<span class="one_name">
						商品名称商品名称商品名称商品名称
					</span>
					<span class="one_prece">
						<b>￥20000</b><br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除
					</span>
				</div>
				<div class="one border">
					<img src="images/lec1.png"/>
					<span class="one_name">
						商品名称商品名称商品名称商品名称
					</span>
					<span class="one_prece">
						<b>￥20000</b><br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除
					</span>
				</div>
				
				
				<div class="gobottom">
					共<span>2</span>件商品&nbsp;&nbsp;&nbsp;&nbsp;
					共计￥<span>20000</span>
					<button class="goprice">去购物车</button>
				</div>								
			</div>
			</div>
		</div>
		
		
		<div class="cartPro">
			<span class="dec">该商品已加入购物车</span>
			<div class="lec">
				<a href="###"><img src="images/lec1.png"/></a>
			</div>
			<span class="lec_name">
				联想拯救者联想拯救者联想拯救者联想拯救者
			</span>
			<span class="lec_des">
				数量1
			</span>
			<div class="shop_des">
				<a href="###"><img src="images/shop_des.png"/></a>
			</div>
			<div class="shop_cart">
				<a href="###"><img src="images/shop_cart.png"/></a>
			</div>
		</div>
</body>
</html>