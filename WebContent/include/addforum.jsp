<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="col-xs-9 col-sm-6" data-spy="scroll"
	data-target="#sidebar-nav">
	<div class="row">
		<div class="col-sm-6">
			<div class="well">
				<form class="form" action="ForumServlet" method="post">
					<h4>Add New Forum</h4>
					<div class="input-group text-center">
							<input 
								name="topic" type="text" class="form-control input-lg"
								title="enter Topic of forum" placeholder="Enter Topic of forum" required="required">
							<input 
								name="url" type="text"
								class="form-control input-lg"
								title="enter Url of forum"
								placeholder="Enter Url of forum" required="required"> 
							<input
								name="summary" type="text" class="form-control input-lg"
								title="enter Summary of forum"
								placeholder="Enter Summary of forum" required="required"> 
							<input
								type="submit" class="btn btn-lg btn-primary" value="Add Forum">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>