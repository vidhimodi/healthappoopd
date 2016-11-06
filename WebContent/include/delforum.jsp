<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="col-sm-6">
	<div class="well">
		<form class="form" action="ForumServlet" method="get">
			<h4>Enter Forum Id To Be Deleted</h4>
			<div class="input-group text-center">
				<input type="number" name="forumid" class="form-control input-lg"
					title="Enter Forum Id To Be Deleted"
					placeholder="Enter Forum Id To Be Deleted" required="required">
				<span class="input-group-btn"> <input type="submit"
					name="submit" class="btn btn-lg btn-primary" type="button"
					value="Delete Forum"></span>
			</div>
		</form>
	</div>
	<!--/well-->
</div>