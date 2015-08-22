<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<title><s:text name="news.title.newscategory" />-<s:text
name="news.title" /></title>
<jsp:include page="style.jsp"></jsp:include>

</head>

<body>
	<section id="container" class="">
<!--header start-->
<!--header start-->
<jsp:include page="header.jsp"></jsp:include>
<!--header end-->
<!--sidebar start-->
<jsp:include page="menu.jsp"></jsp:include>
<!--sidebar end-->
<!--main content start-->
<section id="main-content">
<section class="wrapper">
<!-- page start-->
<div class="row">
	<div class="col-lg-12">
		<section class="panel">
<header class="panel-heading"> 新闻分类 </header>
<table class="table table-striped table-advance table-hover">
	<thead>
		<tr>
			<th style="width:8px;"><input type="checkbox"
			class="group-checkable" data-set="#sample_1 .checkboxes" /></th>
		<th>新闻ID</th>
		<th class="hidden-phone">新闻类别ID</th>
	</tr>
</thead>
<tbody>
	<s:iterator value="listCluster" id="record">
<tr class="odd gradeX">
	<td><input type="checkbox" class="checkboxes" value="1" /></td>
	<td>${record.newsid}</td>
<td>${record.clazz}</td>
</tr>
</s:iterator>
	</tbody>
</table>
<footer style="border-top:1px #ddd solid;">
	<div class="panel-body">
		<div>
			<ul class="pagination pagination-sm pull-right">
				<li><a href="newsplit/cluster.action?flag=prev">&lt;&lt;</a></li>
				<li><a>&nbsp;</a></li>
				<li><a href="newsplit/cluster.action?flag=next">&gt;&gt;</a></li>
			</ul>
		</div>
	</div>
</footer>
</section>
	</div>
</div>
<!-- page end-->
</section>
</section>
<!--main content end-->
</section>
<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="js.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
	$("#li_news_category").parent().parent().addClass("active");
	$("#li_news_category").addClass("active");
});
</script>
</body>
</html>
