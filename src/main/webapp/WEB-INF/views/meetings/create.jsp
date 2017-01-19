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
<title>Meeting create form</title>
</head>
<body>

	<div>
		GREAT NEW MEETING
		<form:form method="post" modelAttribute="meetingForm">
			<form:hidden path="id" />
			<form:input path="description" placeholder="Description" />
			<form:input path="subject" placeholder="Subject" />
			<form:input path="owner" placeholder="Owner" />
			<form:input path="room" placeholder="Room" />
			<form:input path="level" placeholder="Level" />
			<form:select path="groups" id="groups" placeholder="Groups"
				multiple="multiple">
				<c:forEach items="${groups}" var="group">
					<option value="${group.id}">${group.name}</option>
				</c:forEach>
			</form:select>

			<form:input path="status" placeholder="Status" />





			<%--		<form:input path="level" placeholder="Address"/>
		<form:input path="status" placeholder="Coordinates"/> --%>
			<input type="submit" value="Register" />
		</form:form>
	</div>
</body>
</html>