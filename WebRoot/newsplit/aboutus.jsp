<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<title><s:text name="news.title.aboutus" /></title>
<jsp:include page="style.jsp"></jsp:include>

</head>

<body>
	<section id="container" class="sidebar-closed">
<!--header start-->
<header class="header white-bg">
	<!--logo start-->
<a href="<%=basePath%>newsplit/index.jsp" class="logo"><s:text
name="news.title" /></a>
<!--logo end-->

<div class="top-nav ">
	<ul class="nav pull-right top-menu">
		<li>
			</li>

		</ul>
	</div>
</header>
<!--header end-->
<!--main content start-->
<section id="main-content">
<section class="wrapper">
<!-- page start-->
<section>
<div class="panel panel-primary">
	<div class="panel-body">
		<div class="row invoice-list">
			<div class="text-center corporate-id">
				<h2>
					<s:text name="news.title.aboutus" />
	</h2>
</div>
<div class="text-center">
	<h4>
		<strong><s:text name="news.title" /></strong>
				</h4>
				<p>
					版本号:V1.0<br /> 作者:王义华&nbsp;&nbsp;尹子都&nbsp;&nbsp;岳昆 <br>
					单位:云南大学信息学院 <br> 云南大学信息学院数据与知识工程课题组<br>
				</p>
			</div>

		</div>
		<div class="text-center invoice-btn">
			<a class="btn btn-info btn-lg"><i class="icon-envelope"></i>
				联系我们 </a>
		</div>
	</div>
</div>
</section>
<!-- page end-->
</section>
</section>
<!--main content end-->
</section>
<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="js.jsp"></jsp:include>
<script type="text/javascript">
$(function() {
	$("#li_user_record").addClass("active");
	//分词跳转
	$("#btn_newsplit").click(function() {
		location.href = "/news-recommend/newsplit/auto_recommend.jsp";
	});
});
</script>
</body>
</html>
