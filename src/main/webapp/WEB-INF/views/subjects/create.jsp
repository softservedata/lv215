<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div>
	ADD SUBJECT
	<form:form method="post" modelAttribute="subjectForm">
		<form:input path="name" placeholder="Subject name" />
		<form:input type="textarea" path="description"
			placeholder="Subject description" />
		<form:select path="users" multiple="multiple">
			<c:forEach items="${users}" var="user">
				<option value="${user.id}">${user.firstName}
					${user.lastName}</option>
			</c:forEach>
		</form:select>
		<input type="submit" value="Register" />
	</form:form>
</div>
<script type="text/javascript">
</script>