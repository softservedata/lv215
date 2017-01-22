<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>

<div class="container">
	<div class="row">
		<div class="col-lg-4 col-lg-offset-4 panel panel-default">
			<h3 class="text-center">User: ${user.lastName} ${user.firstName}</h3>
			<table class="table table-hover">
				<tr>
					<td><a
						href="${pageContext.request.contextPath}${UserController.UPDATE_USER_MAPPING}${user.id}">
							update </a></td>
				</tr>
				<tr>
					<td><a
						href="${pageContext.request.contextPath}${UserController.UPDATE_POSITION_MAPPING}${user.id}">
							change position </a></td>
				</tr>
				<tr>
					<td><a
						href="${pageContext.request.contextPath}${UserController.CHANGE_ROLE_MAPPING}${user.id}">
							change role</a></td>
				</tr>
			</table>
		</div>
	</div>
</div>