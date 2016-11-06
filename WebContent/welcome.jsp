<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "model.*" import= "controller.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>Health App</title>
<meta name="generator" content="Bootply" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
<link href="css/styles.css" rel="stylesheet">
</head>
<body>
<%
Object cur = session.getAttribute("user");
if(cur==null){
	RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	//System.out.print("fucnk ");
	rd.forward(request, response);
}
else{
	
}
if (request.getParameter("success") != null) {
	String wrong = (String) request.getParameter("success");
	switch (wrong) {
	case "true_fsent":
		out.println("<script>alert(\"Friend Request Sent \")</script>");
		break;
	case "false_fsent":out.println("<script>alert(\"Wrong Friend Username,Friend Request NOT Sent \")</script>");
		break;
	case "updated":
		out.println("<script>alert(\"updated\")</script>");
		break;
	}
}
%>
	<div class="page-container">

		<!-- top navbar -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="offcanvas"
				data-target=".sidebar-nav">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Health App</a>
		</div>
		</nav>

		<div class="container-fluid">
			<div class="row row-offcanvas row-offcanvas-left">
				<!--sidebar-->
				<%
				String slider =null;
				if(session.getAttribute("utype")!=null){
					int utype = (int)session.getAttribute("utype");
					
					if(utype<4){
						slider = "./include/slider_enduser.jsp";
					}else if(utype==4){
						slider = "./include/slider_admin.jsp";
					}else if(utype==5){
						slider = "./include/slider_mod.jsp";
					}
					else{
						slider = "./include/slider_enduser.jsp";
					}
				}
				%>
				<jsp:include page="<%=slider %>"></jsp:include>
				<!--/sidebar-->

				<!--/main-->
				<%
				String show = (String)request.getParameter("show");
				if(show==null||show.equalsIgnoreCase("null")||show.equalsIgnoreCase("view")){
					show="./include/view.jsp";
				}
				else if(show.equalsIgnoreCase("update")){
					show="./include/update.jsp";
				}
				else if(show.equalsIgnoreCase("friendlist")){
					show="./include/friendlist.jsp";
				}
				else if(show.equalsIgnoreCase("showpendreq")){
					show="./include/showpendreq.jsp";
				}
				else if(show.equalsIgnoreCase("showsentreq")){
					show="./include/showsentreq.jsp";
				}
				else if(show.equalsIgnoreCase("sentreq")){
					show="./include/sentreq.jsp";
				}
				else if(show.equalsIgnoreCase("addhealth")){
					show="./include/addhealth.jsp";
				}
				else if(show.equalsIgnoreCase("viewhealth")){
					show="./include/viewhealth.jsp";
				}
				else if(show.equalsIgnoreCase("acforums")){
					show="./include/acforums.jsp";
				}
				else if(show.equalsIgnoreCase("viewforum")){
					show="./include/viewforums.jsp";
				}
				else if(show.equalsIgnoreCase("addforum")){
					show="./include/addforum.jsp";
				}
				else if(show.equalsIgnoreCase("delforum")){
					show="./include/delforum.jsp";
				}
				else{
					show="./include/view.jsp";
				}
				%>
				<jsp:include page="<%=show %>"></jsp:include>
				<!--/.row-->
			</div>
		</div>
		<!--/.container-->
	</div>
	<!--/.page-container-->

	<footer><!--footer-->
	<div class="container">
		<div class="row">
			<ul class="list-unstyled text-right">
				<li class="col-sm-4 col-xs-6"><a href="#">About</a></li>
				<li class="col-sm-4 col-xs-6"><a href="#">Services</a></li>
				<li class="col-sm-4 col-xs-6"><a href="#">Studies</a></li>
				<li class="col-sm-4 col-xs-6"><a href="#">References</a></li>
				<li class="col-sm-4 col-xs-6"><a href="#">Login</a></li>
				<li class="col-sm-4 col-xs-6"><a href="#">Press</a></li>
				<li class="col-sm-4 col-xs-6"><a href="#">Contact</a></li>
				<li class="col-sm-4 col-xs-6"><a href="#">Impressum</a></li>
			</ul>
		</div>
		<!--/row-->
	</div>
	<!--/container--> </footer>

	<!-- script references -->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>