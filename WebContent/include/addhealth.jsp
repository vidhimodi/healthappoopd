<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "model.* , java.sql.*" import= "controller.*" %>

<div class="col-xs-9 col-sm-6" data-spy="scroll"
	data-target="#sidebar-nav">
	<div class="row">
		<div class="col-sm-6">
			<div class="well">
				<form class="form" action="AddHealthServlet" method="get">
					<h4>Add Health Data</h4>
					<div class="input-group text-center">
							<input 
								name="1" type="number" class="form-control input-lg"
								title="enter valid Km u ran" placeholder="Enter valid Km u ran">
							<input 
								name="2" type="number"
								class="form-control input-lg"
								title="enter valid calories U burnt"
								placeholder="Enter valid calories U burnt"> 
							<input
								name="3" type="number" class="form-control input-lg"
								title="enter valid Blood Pressure"
								placeholder="Enter valid Blood Pressure"> 
							<input
								type="submit" class="btn btn-lg btn-primary" value="Add Health">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>