<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>

<body>
	<div class="container">
		<div class="row">
			<div
				class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
				<h3 class="text-center">
					<spring:message code="lbl.user.update" />
				</h3>
				<form:form modelAttribute="${UserController.USER_MODEL_ATTR}"
					action="${pageContext.request.contextPath}${UserController.SAVE_CHANGED_ROLE_MAPPING}${user.id}"
					method="post">
					<div class="form-group">
						<form:select class="form-control" path="role">
							<c:forEach var="role" items="${roles}">
								<option value="${role}"><spring:message code="lbl.user.${role.getRole()}" /></option>
							</c:forEach>
						</form:select>
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