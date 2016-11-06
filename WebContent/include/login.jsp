<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<div class="col-xs-9 col-sm-6" data-spy="scroll"
	data-target="#sidebar-nav">
	<div class="row">
		<div class="col-sm-6">
			<div class="well">
				<form class="form" action="login" method="post">
					<h4>Login</h4>
					<div class="input-group text-center">
						<input name="emailid" type="email" class="form-control input-lg"
							title="Plz enter in it" placeholder="Enter your email address">
						<input name="password" type="password"
							class="form-control input-lg" title="plz enter it"
							placeholder="Enter password"> 
						<input type="submit" name="login"
							class="btn btn-lg btn-primary" value="Login">
						<input type="submit" name="reactivate"
							class="btn btn-lg btn-primary" value="ReActivate">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>