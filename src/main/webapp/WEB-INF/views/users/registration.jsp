<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page
	import="com.softserve.edu.schedule.controller.RegistrationController"%>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-lg-offset-4 panel panel-default">
				<h3 class="text-center">Registration</h3>
				<form:form method="post"
					modelAttribute="${RegistrationController.USER_REGIST_MODEL_ATTR}">
					<form:hidden path="id" />
					<form:input path="firstName" placeholder="First Name" />
					<br>
					<form:input path="lastName" placeholder="Last Name" />
					<br>
					<form:input path="mail" placeholder="email" />
					<br>
					<form:input path="phone" placeholder="phone number" />
					<br>
					<form:input path="position" placeholder="position" />
					<br>
					<form:input path="password" type="password" placeholder="password" />
					<br>
					<input type="submit" value="Register" />
				</form:form>
			</div>
		</div>
	</div>
</body>
