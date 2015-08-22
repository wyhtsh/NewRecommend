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
    
    <title><s:text name="news.title.newsresult"/>-<s:text name="news.title"/></title>
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
                          <header class="panel-heading">
                              性能评分
                          </header>
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
      		$("#li_news_result").parent().parent().addClass("active");
      		$("#li_news_result").addClass("active");
      	});
      </script>
  </body>
</html>
