<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>
<body>
	<div class="container">
		<div class="row">
			<div
				class="col-lg-1 col-lg-offset-2 col-md-1 col-sm-1 col-xs-1 panel-exit text-center">
				<h3>
					<a href="#" onclick="window.history.back()"
						title="<spring:message code="lbl.form.back" />"> <i
						class="fa fa-arrow-left"></i>
					</a>
				</h3>
			</div>
			<div
				class="col-lg-4 col-lg-offset-1 col-md-10 col-sm-10 col-xs-10 panel panel-default">
				<h3 class="text-center">
					<spring:message code="lbl.user.update" />
				</h3>
				<form:form
					action="${pageContext.request.contextPath}/${UserController.SAVE_UPDATED_USER_MAPPING}${userFormUpdate.id}"
					commandName="${UserController.USER_UPDATE_ATTR}" method="post">
					<form:hidden path="id" />
					<form:hidden path="password" />
					<div class="form-group">
						<label for="firstName"><spring:message
								code="lbl.user.firstName" />:</label>
						<spring:message code="lbl.user.firstName" var="nameForPlaceholder" />
						<form:input type="text" class="form-control" path="firstName"
							value="${userFormUpdate.firstName}" required="true" />
						<form:errors path="firstName" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="lastName"><spring:message
								code="lbl.user.lastName" />:</label>
						<spring:message code="lbl.user.lastName" var="nameForPlaceholder" />
						<form:input type="text" class="form-control" path="lastName"
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
					<div class="form-group text-center">
						<input type="submit" class="btn btn-default"
							value="<spring:message code="lbl.form.save"/>"><a
							class="btn btn-default"
							href="${pageContext.request.contextPath}${UserController.USERS_MAPPING_FROM_HEADER}"><spring:message
								code="lbl.form.cancel" /></a>
					</div>
				</form:form>
			</div>
			<div
				class="col-lg-1 col-lg-offset-1 col-md-1 col-sm-1 col-xs-1 panel-exit text-center">
				<h3>
					<a
						href="${pageContext.request.contextPath}${UserController.USERS_MAPPING_FROM_HEADER}">
						<i class="fa fa-table fa-lg"></i>
					</a>
				</h3>
			</div>
		</div>
	</div>
</body>
