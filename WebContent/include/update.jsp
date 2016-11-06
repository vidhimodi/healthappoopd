<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "model.*" import= "controller.*" %>
<h2 style="color:white">Update Profile</h2>
<hr>

<%
	Member cur = (Member)session.getAttribute("user");
	int ut = (Integer)session.getAttribute("utype");
	String utype=null;
	String work=null;
	String url = cur.getUrl()[0]+" "+cur.getUrl()[1]+" "+cur.getUrl()[2];
	if (ut<4) {
		utype="User_Type_:EndUser";
		work = "./update_enduser.jsp";
		session.setAttribute("temputype", ut);
	}
	else if(ut==4){
		utype="User_Type_:Administrator";
		work = "./update_admin.jsp";
		session.setAttribute("temputype", ut);

	}
	else {
		utype="User_Type_:Moderator";
		work = "./update_mod.jsp";
		session.setAttribute("temputype", ut);
	}
%>
<!--  -->
<form class="form" action="UpdateProfileServlet" method="post">
<div class="col-xs-12 col-sm-9" data-spy="scroll"
	data-target="#sidebar-nav">
	<div class="row">
		<div class="col-sm-12">
			<div class="well">
					<div class="col-sm-6">
						<div class="input-group text-center">
							<input name="uname" type="text" class="form-control input-lg"
								value="<%= cur.getUsername()%>" disabled>
							<input name="priemailid" type="email" class="form-control input-lg"
								value="<%= cur.getPrimary_email()%>" disabled>
							<input name="password" type="password" class="form-control input-lg" 
								value="<%= cur.getPassword()%>" >
							<input name="firstname" type="text" class="form-control input-lg"
								value="<%= cur.getFirstname()%>" text="<%= cur.getFirstname()%>" >
							<input name="lastname" type="text" class="form-control input-lg"
								value="<%= cur.getLastname()%>" text="<%= cur.getLastname()%>">
							<input name="secemailid" type="text" class="form-control input-lg"
								value="<%= cur.getSecondary_email()%>" disabled>
							<input name="streetname" type="text" class="form-control input-lg"
								value="<%= cur.getStreetname()%>">
						</div>
					</div>
					<div class="col-sm-6">
						<div class="input-group text-center">
							<input name="streetnumber" type="text" class="form-control input-lg"
								value="<%= cur.getStreetnumber()%>">
							<input name="mm" type="text" class="form-control input-lg"
								value="<%= cur.getMajormunicipality()%>">
							<input name="gg" type="text" class="form-control input-lg"
								value="<%= cur.getGoverningdistrict()%>">
							<input name="postal" type="text" class="form-control input-lg"
								value="<%= cur.getPostalarea()%>">
							<input name="aboutme" type="text" class="form-control input-lg"
								value="<%= cur.getAboutme()%>" text="<%= cur.getAboutme()%>" >
							<input name="utype" type="text" class="form-control input-lg"
								value="<%= utype %>" disabled>
							<input name="photourl" type="text" class="form-control input-lg"
								value="<%= url%>">
							<jsp:include page="<%=work%>"></jsp:include>
						</div>
					</div>
					<center><input type="submit"
					class="btn btn-lg btn-primary"  value="Update Me"></center>
			</div>
		</div>
	</div>
</div>
</form>