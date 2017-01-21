<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New group</title>
</head>
<body>
	<div>
		CREATE NEW GROUP
		<form:form method="post" modelAttribute="userGroupForm">
			<form:hidden path="id" />
			<form:input path="name" placeholder="Title" />
			<form:input path="description" placeholder="Description" />
			<form:input path="level" placeholder="Level" />
			<input type="submit" value="Create group" />
		</form:form>
	</div>
</body>
</html>