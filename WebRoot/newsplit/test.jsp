<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	
  </head>
  
  <body>
    <button id="btn_sub">submit</button>
    <jsp:include page="js.jsp"></jsp:include>
    <script type="text/javascript">
		$(function(){
			alert();
			$("#btn_sub").click(function(){
				$.ajax({
					url:'<%=basePath%>newsplit/test.action',
					dataType:'text',
					success:function(item){
						alert('1');
					},
					error:function(data){
						alert('0');
					}
				});
			});
		});
	</script>
  </body>
</html>
	
