<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="com.softserve.edu.schedule.controller.constants.UserControllerConst"%>
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
					<spring:message code="lbl.user.changePassword" />
				</h3>
				<form:form
					action="${pageContext.request.contextPath}/${UserControllerConst.SAVE_CHANGED_PASSWORD_MAPPING}${user.id}"
					commandName="${UserControllerConst.USER_MODEL_ATTR}" method="post">
					<form:hidden path="id" />
					<form:hidden path="password" />
					<div class="form-group">
						<label for="oldPassword"><spring:message
								code="lbl.user.password" />:</label>
						<spring:message code="lbl.user.password" var="nameForPlaceholder" />
						<form:input type="password" class="form-control"
							path="oldPassword" placeholder="${nameForPlaceholder}"
							required="true" />
						<form:errors path="oldPassword" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="firstNewPassword"><spring:message
								code="lbl.user.password" />:</label>
						<spring:message code="lbl.user.newPassword"
							var="nameForPlaceholder" />
						<form:input type="password" class="form-control"
							path="firstNewPassword" placeholder="${nameForPlaceholder}"
							required="true" />
						<form:errors path="firstNewPassword" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="secondNewPassword"><spring:message
								code="lbl.user.password" />:</label>
						<spring:message code="lbl.user.newPassword"
							var="nameForPlaceholder" />
						<form:input type="password" class="form-control"
							path="secondNewPassword" placeholder="${nameForPlaceholder}"
							required="true" />
						<form:errors path="secondNewPassword" class="text-danger" />
					</div>
					<div class="form-group text-center">
						<input type="submit" class="btn btn-default"
							value="<spring:message code="lbl.form.save"/>"> <a
							class="btn btn-default"
							href="${pageContext.request.contextPath}${UserControllerConst.USERS_MAPPING_FROM_HEADER}"><spring:message
								code="lbl.form.cancel" /></a>
					</div>
				</form:form>
			</div>
			<div
				class="col-lg-1 col-lg-offset-1 col-md-1 col-sm-1 col-xs-1 panel-exit text-center">
				<h3>
					<a
						href="${pageContext.request.contextPath}${UserControllerConst.USERS_MAPPING_FROM_HEADER}">
						<i class="fa fa-table fa-lg"></i>
					</a>
				</h3>
			</div>
		</div>
	</div>
</body>