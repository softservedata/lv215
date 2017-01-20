<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3 class="text-center">Subjects</h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th>ID</th>
			<th>Subject Name <a
				href="${pageContext.request.contextPath}/subjects/sortbynameasc"><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="${pageContext.request.contextPath}/subjects/sortbynamedesc"><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<th>Subject Description <a
				href="${pageContext.request.contextPath}/subjects/sortbydescriptionasc"><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="${pageContext.request.contextPath}/subjects/sortbydescriptiondesc"><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<th>Tutors</th>
			<th></th>
			<th><a href="${pageContext.request.contextPath}/subjects/create"><i
					class="fa fa-plus fa-lg"></i></a></th>
		</tr>
		<tr>
			<td></td>
			<td><form:form method="post"
					action="${pageContext.request.contextPath}/subjects/searchByName"
					modelAttribute="search">
					<form:input path="name" placeholder="Search..." />
					<button type="submit">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>
			<td><form:form method="post"
					action="${pageContext.request.contextPath}/subjects/searchByDescription"
					modelAttribute="search">
					<form:input path="description" placeholder="Search..." />
					<button type="submit">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>
			<td><form:form method="post"
					action="${pageContext.request.contextPath}/subjects/searchByTutor"
					modelAttribute="searchTutor">
					<form:input path="lastName" placeholder="Search..." />
					<button type="submit">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>
			<td></td>
			<td></td>	
		</tr>
		<c:forEach var="subject" items="${subjects}">
			<tr>
				<td>${subject.id}</td>
				<td>${subject.name}</td>
				<td>${subject.description}</td>
				<td><c:forEach items="${subject.users}" var="user">
						<p>${user.firstName} ${user.lastName}</p>
					</c:forEach></td>
				<td><a
					href="${pageContext.request.contextPath}/subjects/delete/${subject.id}"
					onclick="return confirm('Are you sure you want to delete this location?')"><i
						class="fa fa-trash-o fa-lg"></i></a></td>
				<td><a
					href="${pageContext.request.contextPath}/subjects/edit/${subject.id}"><i
						class="fa fa-pencil-square-o fa-lg"></i></a></td>
			</tr>
		</c:forEach>
	</table>
</div>