<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>首页</title>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function change_shfxz(checked,sku_id){
		var shfxz = "0";
		if(checked){
			shfxz = "1";
		}
		$.post("change_shfxz.do",{shfxz:shfxz,sku_id:sku_id},function(result){
				$("#cartListInner").html(result);
			}
		);
	}
</script>
<title>Insert title here</title>
</head>
<style type="text/css">
td {
	vertical-align: middle !important;
}

.form-group {
	padding: 5px 0;
}
</style>
	
<body>
	<div class="top">
		<div class="top_text">
			<a href="">用户登录</a>
			<a href="">用户注册</a>
			<a href="">供应商登录</a>
			<a href="">供应商注册</a>
		</div>
	</div>
	<div class="top_img">
		<img src="./images/top_img.jpg" alt="">
	</div>
	<div class="search">
		<div class="logo"><img src="./images/logo.jpg" alt=""></div>
		<div class="search_on">
			<div class="se">
				<input type="text" name="search" class="lf">
				<input type="submit" class="clik" value="搜索" style="height: 32px;">
			</div>
			<div class="se">
				<a href="">取暖神奇</a>
				<a href="">1元秒杀</a>
				<a href="">吹风机</a>
				<a href="">玉兰油</a>
			</div>
		</div>
		</div>
		<div id="cartListInner">
			<jsp:include page="cartListInner.jsp"></jsp:include>
		</div>
	<div class="footer">
		<div class="top"></div>
		<div class="bottom"><img src="images/foot.jpg" alt=""></div>
	</div>

<!-- 模态弹出窗内容 -->
<div class="modal" id="mymodal-data" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">新添收货地址</h4>
			</div>
			<div class="modal-body" style="overflow:hidden">
				<div class="form-group">
				    <div class="col-sm-10">
				      <input type="email" class="form-control" id="inputEmail3" placeholder="请输入您的所在地区">
				    </div>
				    <br>
				</div>
				<div class="form-group">
				    <div class="col-sm-10">
				      <input type="email" class="form-control" id="inputEmail3" placeholder="请输入您的详细地址">
				    </div>
				    <br>
				</div>
				<div class="form-group">
				    <div class="col-sm-10">
				      <input type="email" class="form-control" id="inputEmail3" placeholder="请输入您的邮政编码">
				    </div>
				    <br>
				</div>
				<div class="form-group">
				    <div class="col-sm-10">
				      <input type="email" class="form-control" id="inputEmail3" placeholder="请输入您的收货人姓名">
				    </div>
				    <br>
				</div>
				<div class="form-group">
				    <div class="col-sm-10">
				      <input type="email" class="form-control" id="inputEmail3" placeholder="请输入您的手机号码">
				    </div>
				    <br>
				</div>
				<div class="form-group">
				    <div class="col-sm-10">
				      <input type="email" class="form-control" id="inputEmail3" placeholder="请输入您的电话号码">
				    </div>
				    <br>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>
	
</body>
</html>