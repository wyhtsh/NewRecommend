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
<section id="main-content" class="container">
<section class="wrapper">
<!-- page start-->
<div class="row">
<div class="col-sm-6">
<section class="panel">
<header class="panel-heading"> 推荐操作选择 </header>
<div class="panel-body">
<form id="opt_form" class="form-horizontal" role="form">
<div class="form-group">
	<label for="inputEmail1"
		class="col-sm-offset-2 col-sm-4 control-label">提取关键词</label>
	<div class="col-sm-6">
		<div class="col-sm-10 text-center">
			<input id="ck_keyword" name="keyword" type="checkbox"
				data-toggle="switch" />
		</div>
	</div>
</div>
<div class="form-group">
	<label for="inputEmail1"
		class="col-sm-offset-2 col-sm-4 control-label">映射概念集</label>
	<div class="col-sm-6">
		<div class="col-sm-10 text-center">
			<input id="ck_concept" name="concept" type="checkbox"
				data-toggle="switch" />
		</div>
	</div>
</div>
<div class="form-group">
	<label for="inputEmail1"
		class="col-sm-offset-2 col-sm-4 control-label">新闻分类</label>
	<div class="col-sm-6">
		<div class="col-sm-10 text-center">
			<input id="ck_cluster" name="cluster" type="checkbox"
				data-toggle="switch" />
		</div>
	</div>
</div>
<div class="form-group">
	<label for="inputEmail1"
		class="col-sm-offset-2 col-sm-4 control-label">生成用户行为偏好</label>
	<div class="col-sm-6">
		<div class="col-sm-10 text-center">
			<input id="ck_preference" name="preference" type="checkbox"
				data-toggle="switch" />
		</div>
	</div>
</div>
<div class="form-group">
	<label for="inputEmail1"
		class="col-sm-offset-2 col-sm-4 control-label">用户行为偏好分类</label>
	<div class="col-sm-6">
		<div class="col-sm-10 text-center">
			<input id="ck_usercluster" name="usercluster"
				type="checkbox" data-toggle="switch" />
		</div>
	</div>
</div>
<div class="form-group">
	<label for="inputEmail1"
		class="col-sm-offset-2 col-sm-4 control-label">新闻推荐</label>
	<div class="col-sm-6">
		<div class="col-sm-10 text-center">
			<input id="ck_recommend" name="recommend" type="checkbox"
				data-toggle="switch" />
		</div>
	</div>
</div>
<div class="form-group" style="display:none;">
	<label for="inputEmail1"
			class="col-sm-offset-2 col-sm-4 control-label">效果评价</label>
		<div class="col-sm-6">
			<div class="col-sm-10 text-center">
				<input id="ck_estimator" name="estimator" type="checkbox"
					data-toggle="switch" />
			</div>
		</div>
	</div>
</form>
<div class="form-group">
	<div class="col-lg-offset-8 col-lg-4">
		<button id="btn_submit" class="btn btn-danger">提交</button>
	</div>
</div>
</div>
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
$("#btn_submit").click(function(){
var arr = [0,0,0,0,0,0,0];
if($("#ck_keyword").parent().hasClass('switch-on')){
	arr[0] = 1;
}
if($("#ck_concept").parent().hasClass('switch-on')){
	arr[1] = 1;
}
if($("#ck_cluster").parent().hasClass('switch-on')){
	arr[2] = 1;
}
if($("#ck_preference").parent().hasClass('switch-on')){
	arr[3] = 1;
}
if($("#ck_recommend").parent().hasClass('switch-on')){
	arr[4] = 1;
}
if($("#ck_usercluster").parent().hasClass('switch-on')){
	arr[5] = 1;
}
if($("#ck_estimator").parent().hasClass('switch-on')){
	arr[6] = 1;
}
var v = "";
for(var i = 0; i<(arr.length-1); i++){
	v += arr[i]+"#";
}
v += arr[arr.length-1];
$.ajax({
	url:'<%=basePath%>newsplit/autoRecom.action',
	dataType:'text',
	data:{arr:v},
	success:function(item){
		var obj = eval("("+item+")");
		alert(obj.res);
	},
	error:function(data){
		alert('操作失败');
	}
});
});
});
</script>
<script src="<%=basePath %>newsplit/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="<%=basePath %>newsplit/js/bootstrap-switch.js"></script>
<!--custom tagsinput-->
<script src="<%=basePath %>newsplit/js/jquery.tagsinput.js"></script>
<!--custom checkbox & radio-->
<script type="text/javascript" src="<%=basePath %>newsplit/js/ga.js"></script>


<!--script for this page-->
<script src="<%=basePath %>newsplit/js/form-component.js"></script>


</body>
</html>
