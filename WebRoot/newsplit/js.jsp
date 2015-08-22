<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath%>newsplit/js/jquery.js"></script>
<script src="<%=basePath%>newsplit/js/jquery-1.8.3.min.js"></script>
<script src="<%=basePath%>newsplit/js/bootstrap.min.js"></script>
<script src="<%=basePath%>newsplit/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath%>newsplit/js/jquery.nicescroll.js"
	type="text/javascript"></script>
<script src="<%=basePath%>newsplit/js/jquery.sparkline.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>newsplit/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
<script src="<%=basePath%>newsplit/js/owl.carousel.js"></script>
<script src="<%=basePath%>newsplit/js/jquery.customSelect.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>newsplit/assets/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="<%=basePath%>newsplit/assets/data-tables/DT_bootstrap.js"></script>

<!--common script for all pages-->
<script src="<%=basePath%>newsplit/js/common-scripts.js"></script>

<!--script for this page-->
<script src="<%=basePath%>newsplit/js/sparkline-chart.js"></script>
<script src="<%=basePath%>newsplit/js/easy-pie-chart.js"></script>
<!--script for this page only-->
<script src="<%=basePath%>newsplit/js/dynamic-table.js"></script>

<script>

      //owl carousel

      $(document).ready(function() {
          $("#owl-demo").owlCarousel({
              navigation : true,
              slideSpeed : 300,
              paginationSpeed : 400,
              singleItem : true

          });
      });

      //custom select box

      $(function(){
          $('select.styled').customSelect();
      });

  </script>
