<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<meta charset="UTF-8">
<title>Meeting status edit form</title>
</head>
<body>
	<div>
		Edit Meeting Status
		<form:form method="post" modelAttribute="meetingForm">
			<label for="status">Meeting status:</label>
			<form:input path="status" placeholder="status" />



			<br>
			<input type="submit" class="btn btn-primary" value="<spring:message code="lbl.form.save"/>">
			<a class="btn btn-danger" href="${pageContext.request.contextPath}/meetings/editStatus/${meeting.id}"><spring:message code="lbl.form.reset"/></a>
			<a class="btn btn-danger"
				href="${pageContext.request.contextPath}/meetings"><spring:message
					code="lbl.form.cancel" /></a>
		</form:form>


	</div>
</body>
</html>