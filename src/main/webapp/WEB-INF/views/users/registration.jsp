<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page
	import="com.softserve.edu.schedule.controller.constants.RegistrationControllerConst"%>
<%@ page
	import="com.softserve.edu.schedule.controller.constants.UserControllerConst"%>
<body>
	<div class="container">
		<div class="row">
			<div
				class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
				<h3 class="text-center">
					<spring:message code="lbl.user.registration" />
				</h3>
				<form:form method="post" modelAttribute="${RegistrationControllerConst.USER_REGIST_MODEL_ATTR}">
					<form:hidden path="id" />
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<c:if test="${user.signInProvider != null}">
						<form:hidden path="signInProvider" />
					</c:if>
					<div class="form-group">
						<label for="firstName">
							<spring:message code="lbl.user.firstName" />
							:
						</label>
						<spring:message code="lbl.user.firstName" var="nameForPlaceholder" />
						<spring:message code="vm.incorectName" var="incorectName" />
						<form:input type="text" class="form-control" path="firstName"
							placeholder="${nameForPlaceholder}" pattern="[а-яА-ЯёЁіІєЄїЇa-zA-Z\\'\\-]{2,25}"
							title="${incorectName}" required="true" />
						<form:errors path="firstName" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="lastName">
							<spring:message code="lbl.user.lastName" />
							:
						</label>
						<spring:message code="lbl.user.lastName" var="nameForPlaceholder" />
						<spring:message code="vm.incorectName" var="incorectName" />
						<form:input type="text" class="form-control" path="lastName"
							placeholder="${nameForPlaceholder}" pattern="[а-яА-ЯёЁіІєЄїЇa-zA-Z\\'\\-]{2,25}"
							title="${incorectName}" required="true" />
						<form:errors path="lastName" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="mail">
							<spring:message code="lbl.user.mail" />
							:
						</label>
						<spring:message code="lbl.user.mail" var="nameForPlaceholder" />
						<spring:message code="vm.incorecMail" var="incorecMail" />
						<form:input type="text" class="form-control" path="mail" placeholder="${nameForPlaceholder}"
							pattern="^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$"
							title="${incorecMail}" required="true" />
						<form:errors path="mail" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="phone">
							<spring:message code="lbl.user.phone" />
							:
						</label>
						<spring:message code="lbl.user.phone" var="nameForPlaceholder" />
						<spring:message code="vm.wrongPhonNamber" var="wrongPhonNamber" />
						<form:input type="text" class="form-control" path="phone" placeholder="${nameForPlaceholder}"
							pattern="\d{3}-\d{7}" title="${wrongPhonNamber}" required="true" />
						<form:errors path="phone" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="position">
							<spring:message code="lbl.user.position" />
							:
						</label>
						<spring:message code="lbl.user.position" var="nameForPlaceholder" />
						<spring:message code="vm.incorectName" var="incorectName" />
						<form:input type="text" class="form-control" path="position"
							placeholder="${nameForPlaceholder}" pattern="[а-яА-ЯёЁіІєЄїЇa-zA-Z\s]{1,250}"
							title="${incorectName}" required="true" />
						<form:errors path="position" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="password">
							<spring:message code="lbl.user.password" />
							:
						</label>
						<spring:message code="lbl.user.password" var="nameForPlaceholder" />
						<spring:message code="vm.incorectPassword" var="incorectPassword" />
						<form:input type="password" class="form-control" path="password"
							placeholder="${nameForPlaceholder}" pattern="\A(?=\S*[a-z])(?=\S*[A-Z])\S{8,}\z"
							title="${vm.incorectPassword}" required="true" />
						<form:errors path="password" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="confirmPassword">
							<spring:message code="lbl.user.confirmPassword" />
							:
						</label>
						<spring:message code="lbl.user.confirmPassword" var="nameForPlaceholder" />
						<form:input type="password" class="form-control" path="confirmPassword"
							placeholder="${nameForPlaceholder}" required="true" />
						<form:errors path="confirmPassword" class="text-danger" />
					</div>
					<div class="form-group text-center">
						<input type="submit" class="btn btn-default" value="<spring:message code="lbl.form.save"/>">
						<a class="btn btn-default"
							href="${pageContext.request.contextPath}${UserControllerConst.USERS_MAPPING_FROM_HEADER}">
							<spring:message code="lbl.form.cancel" />
						</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>