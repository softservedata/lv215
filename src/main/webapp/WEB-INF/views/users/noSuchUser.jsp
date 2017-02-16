<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>

<body>
	<div class="container">
		<div class="row">
			<div
				class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
				<h3 class="text-center">
					<spring:message code="lbl.user.noSuchUser" />
				</h3>
				<div class="form-group text-center">
					<a class="btn btn-default"
						href="${pageContext.request.contextPath}/${UserController.RESTORE_PASSWORD_MAPPING}"><spring:message
							code="lbl.user.tryAgain" /></a> <a class="btn btn-default"
						href="${pageContext.request.contextPath}/"><spring:message
							code="lbl.form.cancel" /></a>
				</div>
			</div>
		</div>
	</div>
</body>