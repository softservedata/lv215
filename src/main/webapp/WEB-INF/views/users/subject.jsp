<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Subject</title>
</head>
<body>
	<h2>Subject</h2>


	<form:form action="/shedule/subject" method="post"
		commandName="subjectForm">
		<table>
			<tr>
				<td colspan="2" align="center"><h2>Subject Form Demo -
						Registration</h2></td>
			</tr>
			<form:hidden path="id" />
			<tr>
				<td>Subject name:</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>Subject Description:</td>
				<td><form:input path="description" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Register" /></td>
			</tr>
		</table>
	</form:form>

	<c:forEach items="${subjects}" var="subject">
		<tr>
			<td>${subject.id}</td>
			<td>${subject.name}</td>
			<td>${subject.description}</td>
			<td><c:forEach items="${subject.users}" var="user">
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>,
				</c:forEach></td>
			<td><a href="/shedule/subject/delete/${subject.id}">delete</a></td>
			<td><a href="/shedule/subject/update/${subject.id}">update</a></td>
		</tr>
		<br />
	</c:forEach>
</body>
</html>