<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "model.*" import= "controller.*"%>
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
Member cur = (Member)session.getAttribute("user");
if(cur!=null){
	RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
	rd.forward(request, response);
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
				<jsp:include page="./include/slider_home.jsp"/>
				<!--/sidebar-->

				<!--/main-->
				<%
					if (request.getParameter("wrong") != null) {
						String wrong = (String) request.getParameter("wrong");
						switch (wrong) {
						case "deactive_login":
							out.println("<script>alert(\"Couldn't log you in successfully. "
									+ "Your account is Deactivated Press Reactivate.\")</script>");
							break;
						case "yes_login":out.println("<script>alert(\"Wrong Credentials"
								+ " Please try again with Correct username and password to login.\")</script>");
							break;
						case "deactive_react":
							out.println("<script>alert(\"Reactivated"
									+ " Please press login again to login.\")</script>");
							break;
						case "login_react":out.println("<script>alert(\" Please press login to login. you are not deactivated.\")</script>");
							break;
						case "yes_react":out.println("<script>alert(\"Wrong Credentials"
								+ " Please try again with Correct username and password to reactivate.\")</script>");
							break;
						case "nuniuname":
							out.println("<script>alert(\"Enter Unique username. \")</script>");
							break;
						case "nunipriemail":
							out.println("<script>alert(\"Enter Unique primary email address. \")</script>");
							break;
						case "nunisecemail":
							out.println("<script>alert(\"Enter Unique secondary email address \")</script>");
							break;
						case "registered":
							out.println("<script>alert(\"Successfully Registered Plz login \")</script>");
							break;
						case "nutype":
							out.println("<script>alert(\"Select Correct Usertype\")</script>");
							break;
						}
					}
					String show = (String) request.getParameter("show");
					System.out.print(show);
					if (show == null || show.equalsIgnoreCase("null")
							|| show.equalsIgnoreCase("home")) {
						show = "./include/home.jsp";
					} else if (show.equalsIgnoreCase("login")) {
						show = "./include/login.jsp";
					} else if (show.equalsIgnoreCase("register")) {
						show = "./include/register.jsp";
					} else {
						show = "./include/aboutus.jsp";
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