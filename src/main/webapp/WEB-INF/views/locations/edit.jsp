<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div>
	EDIT LOCATION
	<form:form method="post" modelAttribute="locationForm">
		<form:hidden path="id" />
		<form:input path="name" placeholder="Title" />
		<form:input path="address" placeholder="Address" />
		<form:input path="coordinates" placeholder="Coordinates" />
		<input type="submit" value="Register" />
	</form:form>
</div>
