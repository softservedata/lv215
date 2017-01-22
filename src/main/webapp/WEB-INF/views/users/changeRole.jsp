<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-lg-offset-4 panel panel-default">
			<h3 class="text-center">Update</h3>
				<form:form modelAttribute="${UserController.USER_MODEL_ATTR}"
					action="${pageContext.request.contextPath}${UserController.SAVE_CHANGED_ROLE_MAPPING}${user.id}"
					method="post">
					<select name="chooseRole">
						<c:forEach var="role" items="${roles}">
							<option value="${role}">${role.name()}</option>
						</c:forEach>
					</select>
					<button>Save</button>
				</form:form>
			</div>
		</div>
	</div>
</body>
