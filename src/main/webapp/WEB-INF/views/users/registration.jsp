<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-6">
		<h3>Registration</h3>
		<form:form role="form" method="post" modelAttribute="userFormCreate">
			<form:errors path="*" />
			<div class="form-group">
				<label for="name">User first name:</label>
				<form:input type="text" class="form-control" path="firstName"
					id="name" placeholder="first name" required="true" />
			</div>
			<div class="form-group">
				<label for="name">User last name:</label>
				<form:input type="text" class="form-control" path="lastName"
					id="name" placeholder="last name" required="true" />
			</div>
			<div class="form-group">
				<label for="name">User email:</label>
				<form:input type="text" class="form-control" path="mail"
					id="name" placeholder="email" required="true" />
			</div>
			<div class="form-group">
				<label for="capacity">Phone number:</label>
				<form:input class="form-control" type="text" path="phone"
					id="text" required="true" />
			</div>
			<div class="form-group">
				<label for="name">User position:</label>
				<form:input type="text" class="form-control" path="position"
					id="name" placeholder="position" required="true" />
			</div>
						<div class="form-group">
				<label for="name">Password:</label>
				<form:input type="password" class="form-control" path="password"
					id="name" placeholder="password" required="true" />
			</div>

			<input type="submit" class="btn btn-primary" value="Save user">
			<input type="reset" class="btn btn-danger" value="Reset form">
		</form:form>
	</div>
</div>
<br>