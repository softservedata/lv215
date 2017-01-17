<%-- <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Update</title>
</head>
<body>
	<form:form
		action="/shedule/users/updateUser/saveUpdatedUser/${userFormUpdate.id}"
		method="post" commandName="userFormUpdate">

		<h3>First Name</h3>
		<form:input type="text" value="${userFormUpdate.firstName}"
			path="firstName" id="name" />
		<br>
		<h3>Last Name</h3>
		<form:input type="text" value="${userFormUpdate.lastName}"
			path="lastName" id="name" />
		<br>
		<h3>Email</h3>
		<form:input type="email" value="${userFormUpdate.mail}" path="mail"
			id="name" />
		<br>
		<h3>Phone number</h3>
		<form:input type="phone" value="${userFormUpdate.phone}" path="phone"
			id="name" />
		<br>
		<h3>Position</h3>
		<form:input type="text" value="${userFormUpdate.position}"
			path="position" id="name" />
		<br>
		<h3>Password</h3>
		<form:input type="password" value="${userFormUpdate.password}"
			path="password" id="name" />
		<br>
		<button>Update</button>

	</form:form>

</body>
</html> --%>



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
<title>Update</title>
</head>
<body>
	Update
	<form:form
		action="/saveUpdatedUser/${userFormUpdate.id}"
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