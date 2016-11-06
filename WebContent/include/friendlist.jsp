<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "model.*" import= "controller.*"%>
    <%@page import="java.util.ArrayList ,controller.* , model.*"%>
<%
    HealthController hc =(HealthController)session.getAttribute("hc");
    FriendshipController fc= hc.getFriendtable();
    Member cur = (Member)session.getAttribute("user");
    String username=cur.getUsername();
    ArrayList<String> userfrilist  =fc.showfriendlist(username);
%>
<div class="col-xs-12 col-sm-9" data-spy="scroll"
	data-target="#sidebar-nav">
	<div class="row">
		<div class="col-sm-9">
			<div class="panel panel-default">
				<div class="panel-heading">
					<a href="#" class="pull-right">View all</a>
					<h4>Your Friend</h4>
				</div>
				<div class="panel-body">
					<div class="list-group">
						<%
						for(String uname :userfrilist){//<a class="btn btn-success center-block" href="#">Success</a> <button class="btn btn-primary">Download</button>
							out.println("<a href=\"#\" class=\"list-group-item\">"+uname+"<input type='submit' value='Unfriend' onclick =\"window.location.href='FriendServlet?confusername="+uname+"&action=unfriend'\"  style='float:right; margin-left:5px'/>&nbsp;&nbsp;&nbsp;<input type='submit' value='View Health' onclick =\"window.location.href='FriendServlet?confusername="+uname+"&action=viewhealth'\"  style='float:right; margin-left:5px'/><input type='submit' value='View Profile' onclick =\"window.location.href='FriendServlet?confusername="+uname+"&action=viewprofile'\"  style='float:right; margin-left:5px'/></a>");
						} 
						%>
						<!-- <a href="#" class="list-group-item active">Active item</a> 
						<a href="#" class="list-group-item">Second item</a> 
						<a href="#"	class="list-group-item">Third item</a> 
						<a href="#" class="list-group-item">Another item</a> <a href="#"
							class="list-group-item">Another item</a> <a href="#"
							class="list-group-item">Another item</a> <a href="#"
							class="list-group-item">Another item</a> <a href="#"
							class="list-group-item">Another item</a> <a href="#"
							class="list-group-item">Another item</a>  -->
					</div>
				</div>
				<!--/panel-body-->
			</div>
		</div>
	</div>
</div>