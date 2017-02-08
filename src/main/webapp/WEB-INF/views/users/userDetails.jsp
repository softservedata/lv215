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
				class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
				<h3 class="text-center">
					<spring:message code="lbl.user.profile" />
				</h3>
				<%-- "<c:url value='${user.pathImage}'/>" --%>
				<img src="${user.pathImage}" width="120" height="120">
				<form:form commandName="${UserController.USER_MODEL_ATTR}"
					action="${pageContext.request.contextPath}/saveImage?${_csrf.parameterName}=${_csrf.token}"
					method="post" enctype="multipart/form-data">
					<input type="file" name="image">
					<button>save image</button>
				</form:form>

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
						<label for="mail"><spring:message code="lbl.user.mail" />:</label>
						<b>${user.mail}</b>
					</div>
					<div class="form-group">
						<label for="phone"><spring:message code="lbl.user.phone" />:</label>
						<b>${user.phone}</b>
					</div>
					<div class="form-group">
						<label for="position"><spring:message
								code="lbl.user.position" />:</label> <b>${user.position}</b>
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
