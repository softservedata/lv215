<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="com.softserve.edu.schedule.controller.SubjectController"%>

<div class="container">
	<div class="row">
		<div
			class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
			<h3 class="text-center">ADD SUBJECT</h3>
			<form:form method="post"
				modelAttribute="${SubjectController.SUBJECT_FORM_MODEL_ATTR}">
				<div class="form-group">
					<label for="${SubjectController.SUBJECT_PATH_NAME}">Subject</label>
					<form:input class="form-control" path="${SubjectController.SUBJECT_PATH_NAME}"
						placeholder="Subject..." />
				</div>
				<div class="form-group">
					<label for="${SubjectController.SUBJECT_PATH_DESCRIPTION}">Description</label>
					<form:textarea class="form-control" path="${SubjectController.SUBJECT_PATH_DESCRIPTION}"
						placeholder="Description..." />
				</div>
				<div class="form-group">
					<label for="${SubjectController.SUBJECT_PATH_USERS}">Tutor(s)</label>
					<form:select class="form-control" path="${SubjectController.SUBJECT_PATH_USERS}" multiple="multiple">
						<c:forEach items="${users}" var="user">
							<option value="${user.id}">${user.firstName}
								${user.lastName}</option>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group text-center">
					<input type="submit" class="btn btn-default" value="Create" /> <a
						class="btn btn-default"
						href="${pageContext.request.contextPath}${SubjectController.SUBJECTS_MAPPING}">Cancel</a>
				</div>
			</form:form>
		</div>
	</div>
</div>