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
						<label for="firstName"><spring:message
								code="lbl.user.firstName" />:</label>
						<spring:message code="lbl.user.firstName" var="nameForPlaceholder" />
						<form:input  type="text" class="form-control" path="firstName"
							value="${userFormUpdate.firstName}" required="true" />
						<form:errors path="firstName" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="lastName"><spring:message
								code="lbl.user.lastName" />:</label>
						<spring:message code="lbl.user.lastName" var="nameForPlaceholder" />
						<form:input  type="text" class="form-control" path="lastName"
							value="${userFormUpdate.lastName}" required="true" />
						<form:errors path="lastName" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="mail"><spring:message code="lbl.user.mail" />:</label>
						<spring:message code="lbl.user.mail" var="nameForPlaceholder" />
						<form:input class="form-control" path="mail"
							value="${userFormUpdate.mail}" required="true" />
						<form:errors path="mail" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="phone"><spring:message code="lbl.user.phone" />:</label>
						<spring:message code="lbl.user.phone" var="nameForPlaceholder" />
						<form:input type="text" class="form-control" path="phone"
							value="${userFormUpdate.phone}" required="true" />
						<form:errors path="phone" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="position"><spring:message
								code="lbl.user.position" />:</label>
						<spring:message code="lbl.user.position" var="nameForPlaceholder" />
						<form:input type="text" class="form-control" path="position"
							value="${userFormUpdate.position}" required="true" />
						<form:errors path="position" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="password"><spring:message
								code="lbl.user.password" />:</label>
						<spring:message code="lbl.user.password" var="nameForPlaceholder" />
						<form:input type="password" class="form-control" path="password"
							value="${userFormUpdate.password}" required="true" />
						<form:errors path="password" class="text-danger" />
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
