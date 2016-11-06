<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@page import="java.util.ArrayList ,controller.* , model.*"%>
<%
    HealthController hc =(HealthController)session.getAttribute("hc");
    FriendshipController fc= hc.getFriendtable();
    Member cur = (Member)session.getAttribute("user");
    String username=cur.getUsername();

    ArrayList<String> userfrisent  =fc.showrequesteduserlist(username);
%>
<div class="col-xs-12 col-sm-9" data-spy="scroll"
	data-target="#sidebar-nav">
	<div class="row">
		<div class="col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<a href="#" class="pull-right">View all</a>
					<h4>Friend To Be Made</h4>
				</div>
				<div class="panel-body">
					<div class="list-group">
						<%
						for(String uname :userfrisent){//<a class="btn btn-success center-block" href="#">Success</a> <button class="btn btn-primary">Download</button>
							out.println("<a href=\"#\" class=\"list-group-item\">"+uname+"<input type='submit' value='withdraw' onclick =\"window.location.href='FriendServlet?confusername="+uname+"&action=withdraw'\"  style='float:right'/></a>");
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