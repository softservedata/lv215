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
<title>Update: ${userFormUpdate.lastName},
	${userFormUpdate.firstName}</title>
</head>
<body>
	<div>
		<h2>Update</h2>
		<form:form action="saveUpdatedUser/${userFormUpdate.id}" method="post"
			commandName="userFormUpdate">
			<form:hidden path="id" />
			<form:input path="firstName" value="${userFormUpdate.firstName}" />
			<br>
			<form:input path="lastName" value="${userFormUpdate.lastName}" />
			<br>
			<form:input path="mail" value="${userFormUpdate.mail}" />
			<br>
			<form:input path="phone" value="${userFormUpdate.phone}" />
			<br>
			<form:input path="position" value="${userFormUpdate.position}" />
			<br>
			<form:input path="password" type="password"
				value="${userFormUpdate.password}" />
			<br>
			<input type="submit" value="Save" />
		</form:form>
	</div>
</body>
</html>