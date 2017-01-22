<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page
	import="com.softserve.edu.schedule.controller.RegistrationController"%>
<body>

<div class="container">
	<div class="row">
		<div class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
			<h3 class="text-center">REGISTRATION</h3>
			<form:form method="post"
				modelAttribute="${RegistrationController.USER_REGIST_MODEL_ATTR}">
				<form:hidden path="id" />
				<div class="form-group">
					<label for="firstName">FIRST NAME</label>
					<form:input class="form-control" path="firstName" placeholder="First Name" />
				</div>
				<div class="form-group">
					<label for="lastName">LAST NAME</label>
					<form:input class="form-control" path="lastName" placeholder="Last Name" />
				</div>
				<div class="form-group">
					<label for="mail">EMAIL</label>
					<form:input class="form-control" path="mail" placeholder="Email" />
				</div>
				<div class="form-group">
					<label for="phone">PHONE NUMBER</label>
					<form:input class="form-control" path="phone" placeholder="Phone number" />
				</div>
				<div class="form-group">
					<label for="position">POSITION</label>
					<form:input class="form-control" path="position"
						placeholder="Position" />
				</div>
				<div class="form-group">
					<label for="password">PASSWORD</label>
					<form:input class="form-control" type="password" 
						path="password" placeholder="Password" />
				</div>
				<div class="form-group text-center">
					<input type="submit" class="btn btn-default"
						value="<spring:message code="lbl.form.save"/>"> <a
						class="btn btn-default"
						href="${pageContext.request.contextPath}${UserController.USERS_MAPPING_FROM_HEADER}"><spring:message
							code="lbl.form.cancel" /></a>
				</div>
			</form:form>
		</div>
	</div>
</div>