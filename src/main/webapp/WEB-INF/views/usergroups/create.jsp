<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New group</title>
</head>
<body>
	<div>
		CREATE NEW GROUP
		<form:form method="post" modelAttribute="userGroupForm">
			<fieldset class="form-fieldset">
				<form:hidden path="id" />

				<label for="name">Name</label>
				<form:input path="name" placeholder="Title" />
				<br> 
				
				<label for="description">Description</label>
				<form:input path="description" placeholder="Description" />
				<br> 
				
				<label for="level">Level</label>
				<form:input path="level" placeholder="Level" />
				<br> 
				
				<label for="curator">Curator</label>
				<form:select class="form-control" path="curator" id="curator">
					<c:forEach items="${users}" var="curator">
						<option value="${curator.id}">${curator.lastName}
							${curator.firstName}</option>
					</c:forEach>
				</form:select>
				<br> <input type="submit" value="Create group" />
			</fieldset>
		</form:form>
	</div>
</body>
</html>