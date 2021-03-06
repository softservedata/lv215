<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page
	import="com.softserve.edu.schedule.controller.RegistrationController"%>
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
<body>
	<div>
		<h2>Registration</h2>
		<form:form method="post"
			modelAttribute="${RegistrationController.USER_REGIST_MODEL_ATTR}">
			<form:hidden path="id" />
			<form:input path="firstName" placeholder="First Name" />
			<br>
			<form:input path="lastName" placeholder="Last Name" />
			<br>
			<form:input path="mail" placeholder="email" />
			<br>
			<form:input path="phone" placeholder="phone number" />
			<br>
			<form:input path="position" placeholder="position" />
			<br>
			<form:input path="password" type="password" placeholder="password" />
			<br>
			<input type="submit" value="Register" />
		</form:form>
	</div>
</body>
</html>