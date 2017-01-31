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
				<h3 class="text-center">
					<spring:message code="lbl.user.changePassword" />
				</h3>
				<form:form
					action="${pageContext.request.contextPath}${UserController.SAVE_CHANGED_PASSWORD_MAPPING}${user.id}"
					commandName="${UserController.USER_MODEL_ATTR}" method="post">
					<form:hidden path="id" />
					<div class="form-group">
						<label for="password"><spring:message
								code="lbl.user.password" />:</label>
						<spring:message code="lbl.user.password" var="nameForPlaceholder" />
						<form:input type="password" class="form-control" path="password"
							 required="true" />
						<form:errors path="password" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="password"><spring:message
								code="lbl.user.newPassword" />:</label>
						<spring:message code="lbl.user.password" var="nameForPlaceholder" />
						<form:input type="password" class="form-control" path="password"
							 required="true" />
						<form:errors path="password" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="password"><spring:message
								code="lbl.user.newPassword" />:</label>
						<spring:message code="lbl.user.password"  var="nameForPlaceholder"/>
						<form:input type="password" class="form-control" path="password"
							 required="true" />
						<form:errors path="password" class="text-danger" />
					</div>

					<div class="form-group text-center">
						<input type="submit" class="btn btn-default"
							value="<spring:message code="lbl.form.save"/>"><a
							class="btn btn-default"
							href="${pageContext.request.contextPath}${UserController.USERS_MAPPING_FROM_HEADER}">Cancel</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>