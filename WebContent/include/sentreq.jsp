<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "model.*" import= "controller.*" %>

<div class="col-sm-6">
	<div class="well">
		<form class="form" action="SendRequestServlet" method ="post">
			<h4>Send Request To User</h4>
			<div class="input-group text-center">
				<input type="text" name ="friend_uname" class="form-control input-lg"
					title="Enter your friend's username."
					placeholder="Enter your friend's username.">
					<span class="input-group-btn">
					<input type="submit" name="submit"
						class="btn btn-lg btn-primary" type="button" value="Send Request"></span>
			</div>
		</form>
	</div>
	<!--/well-->
</div>