<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>
<body>
	<div class="container">
		<div class="row">
			<div
				class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
				<h3 class="text-center">
					<spring:message code="lbl.user.restorePassword" />
				</h3>
				<br>
				<div class="form-group text-center">
					<spring:message code="lbl.user.DearUser" />
				</div>
				<br>
				<form:form method="post"
					modelAttribute="${UserController.USER_MODEL_ATTR}">
					<div class="form-group">
						<label for="mail"><spring:message code="lbl.user.mail" />:</label>
						<spring:message code="lbl.user.mail" var="nameForPlaceholder" />
						<form:input type="text" class="form-control" path="mail"
							placeholder="${nameForPlaceholder}" />
						<form:errors path="mail" class="text-danger" />
					</div>
					<div class="form-group text-center">
						<input type="submit" class="btn btn-default"
							value="<spring:message code="lbl.form.restore"/>"> <a
							class="btn btn-default"
							href="${pageContext.request.contextPath}/"><spring:message
								code="lbl.form.cancel" /></a>
					</div>
				</form:form>
			</div>
		</div>
	</div>