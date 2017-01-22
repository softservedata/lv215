<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-lg-offset-4 panel panel-default">
				<h3 class="text-center">Update</h3>
				<form:form action="${pageContext.request.contextPath}${UserController.SAVE_UPDATED_USER_MAPPING}${userFormUpdate.id}"
					method="post" commandName="${UserController.USER_UPDATE_ATTR}">
					<form:hidden path="id" />
					<form:input path="firstName" value="${userFormUpdate.firstName}" />
					<br>
					<form:input path="lastName" value="${userFormUpdate.lastName}" />
					<br>
					<form:input path="mail" value="${userFormUpdate.mail}" />
					<br>
					<form:input path="phone" value="${userFormUpdate.phone}" />
					<br>
					<form:input path="position" value="${userFormUpdate.position}" />
					<br>
					<form:input path="password" type="password"
						value="${userFormUpdate.password}" />
					<br>
					<input type="submit" value="Save" />
				</form:form>
			</div>
		</div>
	</div>
</body>
