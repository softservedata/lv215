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
<title>Meeting post form</title>
</head>
<body>

	<div>
		ADD MEETING
		<form:form method="post" modelAttribute="meetingForm">
			<form:input path="name" placeholder="Meeting name" />
			<form:input type="textarea" path="description" placeholder="Address" />
			<form:select path="users"
				multiple="multiple">
				<c:forEach items="${users}" var="user">
					<option value="${user.id}">${user.firstName} ${user.lastName}</option>
				</c:forEach>
			</form:select>
			<input type="submit" value="Register" />
		</form:form>
	</div>
	<script type="text/javascript">
/* $(function(){
	$("select[name=users]").chosen({width: "50%"});	
})  */
</script>
</body>
</html>