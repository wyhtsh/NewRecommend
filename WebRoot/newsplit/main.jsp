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

<title><s:text name="news.title" /></title>
<jsp:include page="style.jsp"></jsp:include>

</head>

<body>
	<section id="container" class="">
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
<header class="panel-heading">
	<div class="row">
		<div class="col-sm-6">用户新闻浏览记录</div>
		<div class="col-sm-6">
			<button id="btn_newsplit" type="button"
				class="btn btn-lg btn-primary pull-right">新闻一键推荐</button>
		</div>
	</div>
</header>
<table class="table table-striped table-advance table-hover">
	<thead>
		<tr>
			<th style="width:8px;"><input type="checkbox"
	class="group-checkable" data-set="#sample_1 .checkboxes" /></th>
<th style="width:20px;">用户ID</th>
<th class="hidden-phone" style="width:20px;">新闻ID</th>
<th class="hidden-phone" style="width:100px;">时间</th>
<th style="width:200px;">标题</th>
		<th>新闻内容</th>
	</tr>
</thead>
<tbody>
	<s:iterator value="recordResult" id="record">
<tr class="odd gradeX">
	<td><input type="checkbox" class="checkboxes" value="1" /></td>
	<td>${record.userid}</td>
<td class="hidden-phone"><a
	href="mailto:jhone-doe@gmail.com">${record.newid}</a></td>
<td class="hidden-phone">${record.viewdate}</td>
<td class="center hidden-phone">${record.newtitle}</td>
<td class="hidden-phone"><p>${record.newcontent}</p></td>
</tr>
</s:iterator>
	</tbody>
</table>
<footer style="border-top:1px #ddd solid;">
	<div class="panel-body">
		<div>
			<ul class="pagination pagination-sm pull-right">
				<li><a href="newsplit/userRecord.action?flag=prev">&lt;&lt;</a></li>
				<li><a>&nbsp;</a></li>
				<li><a href="newsplit/userRecord.action?flag=next">&gt;&gt;</a></li>
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
