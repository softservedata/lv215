<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Update Position: ${userFormUpdatePosition.lastName}, ${userFormUpdatePosition.firstName}</title>
</head>
<body>

	<form:form
		action="/shedule/users/updateUserPosition/saveUpdatedUserPosition/${userFormUpdatePosition.id}"
		method="post" commandName="userFormUpdatePosition">
		<h3>Position</h3>
		<form:input type="text" value="${userFormUpdatePosition.position}"
			path="position" id="name" />
		<br>
		<button>Update</button>
	</form:form>

</body>
</html>