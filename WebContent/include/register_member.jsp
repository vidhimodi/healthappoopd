<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String work = (String) request.getParameter("utype");
	System.out.print(work);
	int ut = 1;
	String utype=null;
	if (work == null || work.equalsIgnoreCase("null")
			|| work.equalsIgnoreCase("utype")) {
		//work = "./register_utype.jsp";
		response.sendRedirect("http://localhost:11181/healthappoopd/index.jsp?show=register");
	}
	else if(work.equalsIgnoreCase("1")){
		ut = 1;
		utype="User_Type_:EndUser";
		work = "./register_enduser.jsp";
		session.setAttribute("temputype", ut);
	}
	else if(work.equalsIgnoreCase("2")){
		ut = 2;
		utype="User_Type_:Administrator";
		work = "./register_admin.jsp";
		session.setAttribute("temputype", ut);
	}
	else if(work.equalsIgnoreCase("3")){
		ut = 3;
		utype="User_Type_:Moderator";
		work = "./register_mod.jsp";
		session.setAttribute("temputype", ut);
	}
%>
<!--  -->
<form class="form" action="RegisterServlet" method="post">
<div class="col-xs-12 col-sm-9" data-spy="scroll"
	data-target="#sidebar-nav">
	<div class="row">
	<h2>Sign Up</h2>
		<div class="col-sm-12">
			<div class="well">
					<div class="col-sm-6">
						<div class="input-group text-center">
							<input name="uname" type="text" class="form-control input-lg"
								title="enter unique username" placeholder="enter unique username" required="">
							<input name="priemailid" type="email" class="form-control input-lg"
								title="Plz enter in it" placeholder="Enter your primary email address" required="">
							<input name="password" type="password" class="form-control input-lg" 
								title="plz enter it" placeholder="Enter password" required="">
							<input name="firstname" type="text" class="form-control input-lg"
								title="enter firstname" placeholder="enter firstname" required="">
							<input name="lastname" type="text" class="form-control input-lg"
								title="enter lastname" placeholder="enter lastname" required="">
							<input name="secemailid" type="text" class="form-control input-lg"
								title="enter Secondary Email" placeholder="enter Secondary Email" required="">
							<input name="streetname" type="text" class="form-control input-lg"
								title="enter streetname" placeholder="enter streetname" required="">
						</div>
					</div>
					<div class="col-sm-6">
						<div class="input-group text-center">
							<input name="streetnumber" type="text" class="form-control input-lg"
								title="enter streetnumber" placeholder="enter streetnumber" required="">
							<input name="mm" type="text" class="form-control input-lg"
								title="enter major municipality" placeholder="enter major municipality" required="">
							<input name="gg" type="text" class="form-control input-lg"
								title="enter governing district" placeholder="enter governing district" required="">
							<input name="postal" type="text" class="form-control input-lg"
								title="enter postal area" placeholder="enter postal area" required="">
							<input name="aboutme" type="text" class="form-control input-lg"
								title="enter aboutme" placeholder="enter aboutme" required="">
							<input name="utype" type="text" class="form-control input-lg"
								title="Your User Type" value="<%= utype %>" required="" disabled>
							<input name="photourl" type="text" class="form-control input-lg"
								title="enter all photourl" placeholder="enter all photourl space separated" required="">
							<jsp:include page="<%=work%>"></jsp:include>
						</div>
					</div>
					<center><input type="submit"
					class="btn btn-lg btn-primary"  value="Register Me"></center>
			</div>
		</div>
	</div>
</div>
</form>