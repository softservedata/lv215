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
				<h3 class="text-center">UPDATE: ${user.lastName} ${user.firstName}</h3>
				
					<form:hidden path="id" />
					<div class="form-group">
						<a
						href="${pageContext.request.contextPath}${UserController.UPDATE_USER_MAPPING}${user.id}">
							UPDATE </a>
					</div>
					<div class="form-group">
						<a
						href="${pageContext.request.contextPath}${UserController.UPDATE_POSITION_MAPPING}${user.id}">
							CHANGE POSITION </a>
					</div>
					<div class="form-group">
						<a
						href="${pageContext.request.contextPath}${UserController.CHANGE_ROLE_MAPPING}${user.id}">
							CHANGE ROLE</a>
					</div>
				
			</div>
		</div>
	</div>
</body>