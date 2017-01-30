<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
					<spring:message code="lbl.user.update" />
				</h3>
				<form:form
					action="${pageContext.request.contextPath}${UserController.SAVE_UPDATED_POSITION_MAPPING}${userFormUpdatePosition.id}"
					commandName="${UserController.USER_UPDATE_POSITION_ATTR}"
					method="post">
					<form:hidden path="id" />
					<form:hidden path="firstName"  required="true"/>
					<form:hidden path="lastName"  required="true"/>
					<form:hidden path="phone"  required="true"/>
					<form:hidden path="mail"  required="true"/>
					<form:hidden path="password"  required="true"/>
					<div class="form-group">
						<label for="position"><spring:message
								code="lbl.user.position" /></label>
						<form:input class="form-control" path="position"
							value="${userFormUpdatePosition.position}" />
					</div>

					<div class="form-group">
						<label for="position"><spring:message
								code="lbl.user.position" />:</label>
						<spring:message code="lbl.user.position" var="nameForPlaceholder" />
						<form:input type="text" class="form-control" path="position"
							value="${userFormUpdatePosition.position}" required="true" />
						<form:errors path="position" class="text-danger" />
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
</body>
 --%>