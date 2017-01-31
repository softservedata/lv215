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
					<spring:message code="lbl.user.profile" />
				</h3>
				<form:form commandName="${UserController.USER_MODEL_ATTR}">
					<form:hidden path="id" />
					<div class="form-group">
						<label for="firstName"><spring:message
								code="lbl.user.firstName" />:</label>
						<h4>${user.firstName}</h4>
					</div>
					<div class="form-group">
						<label for="lastName"><spring:message
								code="lbl.user.lastName" />:</label>
						<h4>${user.lastName}</h4>
					</div>
					<div class="form-group">
						<label for="mail"><spring:message code="lbl.user.mail" />:</label>
						<h4>${user.mail}</h4>
					</div>
					<div class="form-group">
						<label for="phone"><spring:message code="lbl.user.phone" />:</label>
						<h4>${user.phone}</h4>
					</div>
					<div class="form-group">
						<label for="position"><spring:message
								code="lbl.user.position" />:</label>
						<h4>${user.position}</h4>
					</div>
					<div class="form-group">
						<a class="form-control"
							href="${pageContext.request.contextPath}/${UserController.UPDATE_USER_MAPPING}${user.id}">
							<spring:message code="lbl.user.update" />
						</a>
					</div>
					<div class="form-group">
						<a class="form-control"
							href="${pageContext.request.contextPath}/${UserController.CHANGE_PASSWORD_MAPPING}${user.id}">
							<spring:message code="lbl.user.changePassword" />
						</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
