<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Subject</title>
</head>
<body>
	<h2>Subject</h2>
	<table>
		<tr>
			<th>ID</th>
			<th>Subject Name <a href="${pageContext.request.contextPath}/subjects/sortbynameasc"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a href="${pageContext.request.contextPath}/subjects/sortbynamedesc"><i
					class="fa fa-arrow-circle-o-down"></i></a></th>
			<th>Subject Description <a href="${pageContext.request.contextPath}/subjects/sortbydescriptionasc"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a href="${pageContext.request.contextPath}/subjects/sortbydescriptiondesc"><i
					class="fa fa-arrow-circle-o-down"></i></a></th>
			<th>Tutors</th>
			<th></th>
			<th><a href="${pageContext.request.contextPath}/subjects/create"><i class="fa fa-plus"></i></a></th>
		</tr>
		<c:forEach var="subject" items="${subjects}">
			<tr>
				<td>${subject.id}</td>
				<td>${subject.name}</td>
				<td>${subject.description}</td>
				<td><c:forEach items="${subject.users}" var="user">
						<p>${user.firstName}${user.lastName}</p>
					</c:forEach></td>
				<td><a href="${pageContext.request.contextPath}/subjects/delete/${subject.id}"><i
						class="fa fa-trash-o"></i></a></td>
				<td><a href="${pageContext.request.contextPath}/subjects/edit/${subject.id}"><i
						class="fa fa-pencil-square-o"></i></a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>