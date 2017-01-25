<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>
<%@ page import="com.softserve.edu.schedule.controller.RegistrationController"%>


<h3 class="text-center">Users</h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th>Name<a
				href="${pageContext.request.contextPath}${UserController.SORT_BY_LASTNAME_ASC_MAPPING}"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a
				href="${pageContext.request.contextPath}${UserController.SORT_BY_LASTNAME_DESC_MAPPING}"><i
					class="fa fa-arrow-circle-o-down"></i></a></th>
			<th>Email</th>
			<th>Position<a
				href="${pageContext.request.contextPath}${UserController.SORT_BY_POSITION_ASC_MAPPING}"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a
				href="${pageContext.request.contextPath}${UserController.SORT_BY_POSITION_DESC_MAPPING}"><i
					class="fa fa-arrow-circle-o-down"></i></a></th>
			<th>Role</th>
			<th>Group</th>
			<th></th>
			<th></th>
			<th></th>
			<th><a
				href="${pageContext.request.contextPath}/${RegistrationController.USER_REGIST_MAPPING_FOR_ADMIN}"><i
					class="fa fa-plus"></i></a></th>
		</tr>
		
<tr>
			
			<td><form:form method="post"
					action="${pageContext.request.contextPath}${UserController.SEARCH_BY_LASTNANE_MAPPING}"
					modelAttribute="${UserController.SEARCH_MODEL_ATTR}">
					<form:input path="lastName" placeholder=" Search..." />
					<button type="submit" title="Search by lastName">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>
			<td></td>
			<td><form:form method="post"
					action="${pageContext.request.contextPath}${UserController.SEARCH_BY_POSITION_MAPPING}"
					modelAttribute="${UserController.SEARCH_MODEL_ATTR}">
					<form:input path="position" placeholder=" Search..." />
					<button type="submit" title="Search by position">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.lastName}<a> </a>${user.firstName}</td>
				<td>${user.mail}</td>
				<td>${user.position}</td>
				<td>${user.role}</td>
				<td><c:forEach items="${user.groups}" var="group">
						<p>${group.name}</p>
					</c:forEach></td>
				<td><a
					href="${pageContext.request.contextPath}${UserController.DELETE_USER_MAPPING}${user.id}">
					<!-- onclick="return confirm('The user can not be deleted if he is curated group. Are you sure you want to delete this user?');" --><i
						class="fa fa-trash-o"></i></a></td>
				<td><a
					href="${pageContext.request.contextPath}${UserController.EDIT_USER_MAPPING}${user.id}">
						<i class="fa fa-pencil-square-o"></i>
				</a></td>
				<td><c:if test="${user.status.ordinal() == 1}">
						<a
							href="${pageContext.request.contextPath}${UserController.BAN_USER_MAPPING}${user.id}">
							<i class="fa fa-ban"></i>
						</a>
					</c:if></td>
				<td><c:if
						test="${user.status.ordinal() == 2 or user.role.ordinal() == 0}">
						<a
							href="${pageContext.request.contextPath}${UserController.UNBAN_USER_MAPPING}${user.id}">
							<i class="fa fa-check-circle-o"></i>
						</a>
					</c:if></td>
			</tr>
		</c:forEach>
	</table>
</div>