<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Change Role: ${user.lastName}, ${user.firstName}</title>
</head>
<body>

	<sf:form modelAttribute="user" action="saveChangedRole/${user.id}" 
		method="post">
		<select name="chooseRole">
			<c:forEach var="role" items="${roles}">
				<option value="${role}">${role.name()}</option>
			</c:forEach>
		</select>
		<button>Save</button>
	</sf:form>

</body>
</html>