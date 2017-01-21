<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="table-responsive">
	<h2>Users</h2>
	<table class="table table-hover">
		<tr>
			<th>Name<a
				href="${pageContext.request.contextPath}/users/sortbylastnameasc"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a
				href="${pageContext.request.contextPath}/users/sortbylastnamedesc"><i
					class="fa fa-arrow-circle-o-down"></i></a></th>
			<th>Email</th>
			<th>Position<a
				href="${pageContext.request.contextPath}/users/sortbypositionasc"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a
				href="${pageContext.request.contextPath}/users/sortbypositiondesc"><i
					class="fa fa-arrow-circle-o-down"></i></a></th>
			<th>Role</th>
			<th>Group</th>
			<th></th>
			<th></th>
			<th></th>
			<th><a
				href="${pageContext.request.contextPath}/users/registration"><i
					class="fa fa-plus"></i></a></th>
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
				<td>
					 <a
					href="${pageContext.request.contextPath}/users/delete/${user.id}"
					onclick="return confirm('The user can not be deleted if it is curated group. Are you sure you want to delete this user?');"><i
						class="fa fa-trash-o"></i></a>
				</td>
				<td><a
					href="${pageContext.request.contextPath}/users/edit/${user.id}">
						<i class="fa fa-pencil-square-o"></i>
				</a></td>
				<td><c:if test="${user.status.ordinal() == 1}">
						<a href="${pageContext.request.contextPath}/users/banUser/${user.id}">
							<i class="fa fa-ban"></i>
						</a>
					</c:if></td>
				<td><c:if
						test="${user.status.ordinal() == 2 or user.role.ordinal() == 0}">
						<a href="${pageContext.request.contextPath}/users/unBanUser/${user.id}">
							<i class="fa fa-check-circle-o"></i>
						</a>
					</c:if></td>
			</tr>
		</c:forEach>
	</table>
</div>