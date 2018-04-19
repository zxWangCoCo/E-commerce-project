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
	function goto_login(){
		var form = $("#login_form").serialize();//表单序列化
		$.post("form_login.do",form,function(result){
			alert(result);
		});
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<form id="login_form">
		username:<input type="text" name="yh_mch">
		password:<input type="text" name="yh_mm">
		login:<input type="button" name="login" onclick="goto_login();">
	</form>
</body>
</html>