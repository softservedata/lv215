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
<title>Subject post form</title>
</head>
<body>
	<div>
		Edit Subject
		<form:form method="post" modelAttribute="subjectForm">
			<form:hidden path="id" />
			<form:input path="name" placeholder="Subject name" />
			<form:input type="textarea" path="description"
				placeholder="Subject description" />
			<form:select path="users" multiple="multiple">
				<c:set var="found" value="false"/>
				<c:forEach items="${users}" var="user">
					<c:forEach items="${subjectForm.users}" var="userInSubject">
						<c:choose>
							<c:when test="${userInSubject.id eq user.id}">
								<option value="${user.id}" selected="selected">${user.firstName}
									${user.lastName}</option>
							</c:when>
						</c:choose>
					</c:forEach>
				</c:forEach>
				<c:forEach items="${users}" var="user">
					<option value="${user.id}">${user.firstName}
						${user.lastName}</option>
				</c:forEach>
			</form:select>
			<input type="submit" value="Register" />
		</form:form>
	</div>
	<script type="text/javascript">
		$(function() {
			$("select[name=users]").chosen({
				width : "50%"
			});
		})
	</script>
</body>
</html>