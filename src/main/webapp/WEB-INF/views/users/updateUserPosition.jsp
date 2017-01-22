<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>

<title>Update Position: ${userFormUpdatePosition.lastName},
	${userFormUpdatePosition.firstName}</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-lg-offset-4 panel panel-default">
				<h3 class="text-center">Update</h3>
				<form:form
					action="${pageContext.request.contextPath}${UserController.SAVE_UPDATED_POSITION_MAPPING}${userFormUpdatePosition.id}"
					method="post"
					commandName="${UserController.USER_UPDATE_POSITION_ATTR}">
					<form:hidden path="id" />
					<form:input path="position"
						value="${userFormUpdatePosition.position}" />
					<br>
					<input type="submit" value="Save" />
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>