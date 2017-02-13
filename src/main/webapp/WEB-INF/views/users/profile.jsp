<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
					<spring:message code="lbl.user.profile" />
				</h3>
				<img src="<c:url value='${user.pathImage}'/>" height="200"
					align="middle">
				<form:form commandName="${UserController.USER_MODEL_ATTR}">
					<form:hidden path="id" />
					<div class="form-group">
						<label for="firstName"><spring:message
								code="lbl.user.firstName" />:</label> <b>${user.firstName}</b>
					</div>
					<div class="form-group">
						<label for="lastName"><spring:message
								code="lbl.user.lastName" />:</label> <b>${user.lastName}</b>
					</div>
					<div class="form-group">
						<sec:authorize
							access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR')">
							<label for="mail"><spring:message code="lbl.user.mail" />:</label>
							<b>${user.mail}</b>
						</sec:authorize>
					</div>
					<div class="form-group">
						<sec:authorize
							access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR')">
							<label for="phone"><spring:message code="lbl.user.phone" />:</label>
							<b>${user.phone}</b>
						</sec:authorize>
					</div>
					<div class="form-group">
						<label for="position"><spring:message
								code="lbl.user.position" />:</label> <b>${user.position}</b>
					</div>
					<div class="form-group">
						<a class="form-control"
							href="${pageContext.request.contextPath}/${UserController.USER_MEETINGS_MAPPING}${user.id}">
							<spring:message code="lbl.user.showMitings" />
						</a>
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
