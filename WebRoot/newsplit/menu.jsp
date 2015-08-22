<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>

<!--sidebar start-->
<aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
<ul class="sidebar-menu">
	<li id="li_user_record"><a class=""
		href="<%=basePath%>newsplit/userRecord.action"> <i
class="icon-dashboard"></i> <span>用户历史浏览记录</span>
</a></li>
<li id="sub-news" class="sub-menu"><a href="javascript:;"
	class=""> <i class="icon-book"></i> <span>新闻关键词提取</span> <span
class="arrow"></span>
</a>
	<ul class="sub">
		<li id="li_split_record"><a class=""
			href="<%=basePath%>newsplit/splitword.action">ICTCLAS50分词记录</a></li>
<li id="li_news_map"><a class=""
	href="<%=basePath%>newsplit/concept.action">概念集合映射</a></li>
<li id="li_news_category"><a class=""
	href="<%=basePath%>newsplit/cluster.action">新闻分类</a></li>
	</ul></li>
<li id="sub-user" class="sub-menu"><a href="javascript:;"
	class=""> <i class="icon-book"></i> <span>用户推荐</span> <span
class="arrow"></span>
</a>
	<ul class="sub">
		<li id="li_user_preference"><a class=""
			href="<%=basePath%>newsplit/preference.action">用户行为偏好</a></li>
<li id="li_news_recommend"><a class=""
	href="<%=basePath%>newsplit/recommend.action">新闻推荐</a></li>
	</ul></li>
<li id="li_news_result" style="display:none;"><a class=""
href="<%=basePath%>/newsplit/news_result.jsp"> <i
class="icon-envelope"></i> <span>性能评分</span>
	</a></li>

</ul>
<!-- sidebar menu end-->
	</div>
</aside>
<!--sidebar end-->