<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<table>

	<tr>
		<th>First name<a
			href="${pageContext.request.contextPath}/users/sortbyfirstnameasc"><i
				class="fa fa-arrow-circle-o-up"></i></a> <a
			href="${pageContext.request.contextPath}/users/sortbyfirstnamedesc"><i
				class="fa fa-arrow-circle-o-down"></i></a></th>
		<th>Last name<a
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
			<td>${user.firstName}</td>
			<td>${user.lastName}</td>
			<td>${user.mail}</td>
			<td>${user.position}</td>
			<td>${user.role}</td>
			<td><c:forEach items="${user.groups}" var="group">
					<p>${group.name}</p>
				</c:forEach>
			</td>

<%-- 			<td>
				<form:form>
					<select>
						<c:forEach var="group" items="${user.groups}">
							<p>${group.name}</p>
						</c:forEach>
					</select>
				</form:form>
			</td> --%>

			<td><a href="${pageContext.request.contextPath}/users/delete/${user.id}"><i
					class="fa fa-trash-o"></i></a></td>
			<td><a href="${pageContext.request.contextPath}/users/edit/${user.id}">
					<i class="fa fa-pencil-square-o"></i></a></td>
			<td><a href="${pageContext.request.contextPath}/users/banUser/${user.id}">
					<i class="fa fa-ban"></i></a></td>
			<td><a href="${pageContext.request.contextPath}/users/unBanUser/${user.id}">
					<i class="fa fa-check-circle-o"></i></a></td>
		</tr>
		<br />
	</c:forEach>
</table>


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