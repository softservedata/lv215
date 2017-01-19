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
<title>Meeting edit form</title>
</head>
<body>
	<div>
		EDIT MEETING
		<form:form method="post" modelAttribute="meetingForm">
			<form:hidden path="id" />
			<form:input path="description" placeholder="Meeting description" />
<%-- 			<form:input path="subject" placeholder="Subject" />
			<form:input path="owner" placeholder="Meeting subject" />
			<form:input path="room" placeholder="Meeting room" />
			<form:select path="groups" id="groups" multiple="multiple">
				<c:forEach items="${groups}" var="group">
					<c:forEach items="${meeting.group}" var="groupInMeeting">
						<c:choose>
							<c:when test="${groupInMeeting.id eq group.id}">
								<option value="${group.id}" selected="selected">${group.name}</option>
							</c:when>
						</c:choose>
					</c:forEach>
				</c:forEach>
				<c:forEach items="${groups}" var="group">
					<option value="${group.id}">${group.name}</option>
				</c:forEach>
			</form:select> --%>
			<input type="submit" value="Register" />
		</form:form>
	</div>
</body>
</html>