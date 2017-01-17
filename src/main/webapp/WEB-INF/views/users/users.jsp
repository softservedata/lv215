
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach items="${users}" var="user">
	<tr>
		<td>${user.firstName}</td>
		<td>${user.lastName}</td>
		<td>${user.mail}</td>
		<td>${user.position}</td>
		<td>${subject.role}</td>
		<td><c:forEach items="${subject.groups}" var="group">
				<td>${group.name}</td>
			</c:forEach></td>
		<td><a href="/users/delete/${user.id}"> delete </a></td>
		<td><a href="/users/edit/${user.id}"> edit </a></td>
		<td><a href="/users/banUser/${user.id}"> ban </a></td>
		<td><a href="/users/unBanUser/${user.id}"> unban </a></td>
	</tr>
	<br />
</c:forEach>


<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Present users:</h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th>First name</th>
			<th>Last name</th>
			<th>Email</th>
			<th>Position</th>
			<th>Role</th>
			<th>Groups</th>
			<th></th>
			<th><a class="btn btn-success" href="users/registration">Add user</a></th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.firstName}</td>
				<td>${user.firstName}</td>
				<td><a href="users/${user.id}">${user.mail}</a></td>
				<td>${user.position}</td>
				<td>${user.role}</td>
				<td>
					<ul>
						<c:forEach items="${user.groups}" var="group">
							<li>${group.name}</li>
						</c:forEach>
					</ul>
				</td>
				<td><a class="btn btn-danger" href="users/delete/${user.id}">Delete</a></td>
				<td><a class="btn btn-success" href="users/edit/${user.id}">Edit</a></td>
			</tr>
		</c:forEach>
	</table>
</div> --%>