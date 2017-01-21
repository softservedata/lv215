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

		Edit Meeting
		<form:form method="post" modelAttribute="meetingForm">

			<label for="description">Description</label>

			<form:input path="description" placeholder="Description" />
			<br>

			<label for="subject">Subject</label>
			<form:select class="form-control" path="subject" id="subject">
				<c:forEach items="${subjects}" var="subject">
					<option value="${subject.id}">${subject.name}</option>
				</c:forEach>
			</form:select>

			<label for="owner">Owner</label>
			<form:select class="form-control" path="owner" id="owner">
				<c:forEach items="${owners}" var="owner">
					<option value="${owner.id}">${owner.lastName}
						${owner.firstName}</option>
				</c:forEach>
			</form:select>


			<br>
			<label for="room">Room</label>
			<form:select class="form-control" path="room" id="room">
				<c:forEach items="${rooms}" var="room">
					<option value="${room.id}">${room.name}</option>
				</c:forEach>
			</form:select>
			<label for="startTime">Start time</label>
			<br>
			<form:input type="datetime-local" path="startTime" id="startTime"
				placeholder="YYYY-MM-DD" />
			<br>
			<label for="endTime">End time</label>
			<br>
			<form:input type="datetime-local" path="endTime" id="endTime"
				placeholder="YYYY-MM-DD" />

			<br>
			<label for="groups">Groups</label>
			<br>
			<form:select path="groups" id="groups" multiple="multiple">
				<c:forEach items="${groups}" var="group">
					<option value="${group.id}">${group.name}</option>
				</c:forEach>
			</form:select>
			<br>
			<label for="level">Level</label>
			<form:input path="level" placeholder="Level" />
			<br>
			<label for="status">Status</label>
			<form:input path="status" placeholder="status" />
			<br>
						
			<input type="submit" class="btn btn-primary"
				value="<spring:message code="lbl.form.save"/>">
			<a class="btn btn-danger"
				href="/schedule/meetings/edit/${meeting.id}"><spring:message
					code="lbl.form.reset" /></a>
			<a class="btn btn-danger"
				href="${pageContext.request.contextPath}/meetings"><spring:message
					code="lbl.form.cancel" /></a>
		</form:form>
	</div>
</body>
</html>