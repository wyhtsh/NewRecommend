<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>

<footer>
	<section>
<div class="text-center">
	<ul>
		<li><span><a
href="<%=basePath%>newsplit/aboutus.jsp" target="block">关于我们</a></span></li>
</ul>
<ul>
	<li><span>云南大学信息学院数据与知识工程课题组©2014</span></li>
	</ul>
</div>
</section>
</footer>