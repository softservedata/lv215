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
<title>Update: ${userFormUpdate.lastName}, ${userFormUpdate.firstName}</title>
</head>
<body>
	Update
	<form:form
		action="saveUpdatedUser/${userFormUpdate.id}"
		method="post" commandName="userFormUpdate">
		<form:input path="firstName" value="${userFormUpdate.firstName}" />
		<form:input path="lastName" value="${userFormUpdate.lastName}" />
		<form:input path="mail" value="${userFormUpdate.mail}" />
		<form:input path="phone" value="${userFormUpdate.phone}" />
		<form:input path="position" value="${userFormUpdate.position}" />
		<form:input path="password" 
				type="password" value="${userFormUpdate.password}" />
		<input type="submit" value="Update" />
	</form:form>
</body>
</html>