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
			<div
				class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
				<h3 class="text-center">REGISTRATION</h3>
				<form:form method="post"
					modelAttribute="${RegistrationController.USER_REGIST_MODEL_ATTR}">
					<form:hidden path="id" />
					<div class="form-group">
						<label for="firstName"><spring:message
								code="lbl.user.firstName" />:</label>
						<spring:message code="lbl.user.firstName" var="nameForPlaceholder" />
						<form:input type="text" class="form-control" path="firstName"
							placeholder="${nameForPlaceholder}" required="true" />
						<form:errors path="firstName" class="text-danger" />
					</div>


					<%-- 				
				<div class="form-group">
					<label for="name"><spring:message code="lbl.room.roomName"/>:</label>
					<spring:message code="lbl.room.roomName" var="nameForPlaceholder"/>
					<form:input type="text" class="form-control" path="name" id="name"
						placeholder="${nameForPlaceholder}" required="true" />
					<form:errors path="name" class="text-danger"/>
				</div>
				
				
				 --%>

					<div class="form-group">
						<label for="lastName"><spring:message
								code="lbl.user.lastName" />:</label>
						<spring:message code="lbl.user.lastName" var="nameForPlaceholder" />
						<form:input type="text" class="form-control" path="lastName"
							placeholder="${nameForPlaceholder}" required="true" />
						<form:errors path="lastName" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="mail"><spring:message code="lbl.user.mail" />:</label>
						<spring:message code="lbl.user.mail" var="nameForPlaceholder" />
						<form:input type="text" class="form-control" path="mail"
							placeholder="${nameForPlaceholder}" required="true" />
						<form:errors path="mail" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="phone"><spring:message code="lbl.user.phone" />:</label>
						<spring:message code="lbl.user.phone" var="nameForPlaceholder" />
						<form:input type="text" class="form-control" path="phone"
							placeholder="${nameForPlaceholder}" required="true" />
						<form:errors path="phone" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="position"><spring:message
								code="lbl.user.position" />:</label>
						<spring:message code="lbl.user.position" var="nameForPlaceholder" />
						<form:input type="text" class="form-control" path="position"
							placeholder="${nameForPlaceholder}" required="true" />
						<form:errors path="position" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="password"><spring:message
								code="lbl.user.password" />:</label>
						<spring:message code="lbl.user.password" var="nameForPlaceholder" />
						<form:input type="password" class="form-control" path="password"
							placeholder="${nameForPlaceholder}" required="true" />
						<form:errors path="password" class="text-danger" />
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