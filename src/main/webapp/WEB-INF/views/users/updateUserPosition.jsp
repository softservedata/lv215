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
<title>Update Position: ${userFormUpdatePosition.lastName}, ${userFormUpdatePosition.firstName}</title>
</head>
<body>
	Update
	<form:form
		action="saveUpdatedUserPosition/${userFormUpdatePosition.id}"
		method="post" commandName="userFormUpdatePosition">
		<form:input path="position" value="${userFormUpdatePosition.position}"/>
		<input type="submit" value="Update" />
	</form:form>
</body>
</html>