<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<header class="header white-bg">
          <div class="sidebar-toggle-box">
              <div data-original-title="Toggle Navigation" data-placement="right" class="icon-reorder tooltips"></div>
          </div>
          <!--logo start-->
          <a href="<%=basePath%>newsplit/index.jsp" class="logo" ><s:text name="news.title"/></a>
          <!--logo end-->
          
          <div class="top-nav ">
              <ul class="nav pull-right top-menu">
                  <li>
                      <!-- <input type="text" class="form-control search" placeholder="Search"> -->
                  </li>
                  
              </ul>
          </div>
      </header>