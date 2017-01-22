<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>
<body>
	<div class="container">
		<div class="row">
			<div
				class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
				<h3 class="text-center">UPDATE</h3>
				<form:form
					action="${pageContext.request.contextPath}${UserController.SAVE_UPDATED_USER_MAPPING}${userFormUpdate.id}"
					commandName="${UserController.USER_UPDATE_ATTR}" method="post">
					<form:hidden path="id" />
					<div class="form-group">
						<label for="firstName">FIRST NAME</label>
						<form:input class="form-control" path="firstName"
							value="${userFormUpdate.firstName}" />
					</div>
					<div class="form-group">
						<label for="lastName">LAST NAME</label>
						<form:input class="form-control" path="lastName"
							value="${userFormUpdate.lastName}" />
					</div>
					<div class="form-group">
						<label for="mail">EMAIL</label>
						<form:input class="form-control" path="mail"
							value="${userFormUpdate.mail}" />
					</div>
					<div class="form-group">
						<label for="phone">PHONE NUMBER</label>
						<form:input class="form-control" path="phone"
							value="${userFormUpdate.phone}" />
					</div>
					<div class="form-group">
						<label for="position">POSITION</label>
						<form:input class="form-control" path="position"
							value="${userFormUpdate.position}" />
					</div>
					<div class="form-group">
						<label for="password">PASSWORD</label>
						<form:input class="form-control" type="password" path="password"
							value="${userFormUpdate.password}" />
					</div>
					<div class="form-group text-center">
						<input type="submit" class="btn btn-default"
							value="<spring:message code="lbl.form.save"/>"><a
							class="btn btn-default"
							href="${pageContext.request.contextPath}${UsernController.SAVE_UPDATED_USER_MAPPING}">Cancel</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
