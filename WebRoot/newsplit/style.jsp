<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content='<s:text name="news.keywords"/>'>
<meta name="author" content="Mosaddek">
<meta name="keyword" content='<s:text name="news.keywords"/>'>
<link rel="shortcut icon" href="img/favicon.html">
<!-- Bootstrap core CSS -->
<link href="<%=basePath%>/newsplit/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=basePath%>/newsplit/css/bootstrap-reset.css"
	rel="stylesheet">
<!--external css-->
<link
	href="<%=basePath%>/newsplit/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
<link
	href="<%=basePath%>/newsplit/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css"
	rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet"
	href="<%=basePath%>/newsplit/css/owl.carousel.css" type="text/css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>assets/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>assets/bootstrap-daterangepicker/daterangepicker.css" />
<!-- Custom styles for this template -->
<link href="<%=basePath%>/newsplit/css/style.css" rel="stylesheet">
<link href="<%=basePath%>/newsplit/css/style-responsive.css"
	rel="stylesheet" />