<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar"
	role="navigation">
	<div data-spy="affix" data-offset-top="45" data-offset-bottom="90">
		<ul class="nav" id="sidebar-nav">
			<li><a href="?show=view">View Profile</a></li><!-- his or his friend -->
			<!-- <li><a href="?show=deactivate">DeActivate Profile</a></li> -->
			<li><a href="?show=update">Update Profile</a></li>
			<li><a href="?show=friendlist">Show Me Friend List</a></li><!-- unfriend  show profile view health data -->
			<li><a href="?show=showpendreq">Show Pending Friend Request</a></li><!-- accept/reject -->
			<li><a href="?show=showsentreq">Show Sent Request</a></li><!-- withdraw -->
			<li><a href="?show=sentreq">Send a request</a> </li>
			<li><a href="?show=addhealth">Add Health data</a></li>
			<li><a href="?show=viewhealth">View Health data</a></li><!-- his or his friend -->
			<li><a href="?show=acforums">Show Active Forums</a></li>
			<!-- <li><a href="?show=viewforum">View Forum</a></li> -->
			<!-- <li><a href="?show=logout">Logout</a></li> -->
		</ul>
		<form action="logout" method="post">
			<input class="btn btn-primary" type="submit" value="Logout">
		</form>
		<form action="logout" method="get">
			<input class="btn btn-primary" type="submit" value="Deactivate">
		</form>
	</div>
</div>