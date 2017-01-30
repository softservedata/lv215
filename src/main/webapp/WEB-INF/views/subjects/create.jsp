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
			<h3 class="text-center"><spring:message code="lbl.subject.add"/></h3>
			<form:form method="post"
				modelAttribute="${SubjectController.SUBJECT_FORM_MODEL_ATTR}">
				<div class="form-group">
					<label for="${SubjectController.SUBJECT_PATH_NAME}"><spring:message code="lbl.subject.name" /></label>
					<spring:message code="vm.invalidCharactersOrEmptyField" var="name" />
					<spring:message code="lbl.subject.createName" var="namePH" />
					<form:input class="form-control" path="${SubjectController.SUBJECT_PATH_NAME}"
						placeholder="${namePH}" required="true" pattern = "[а-яА-ЯёЁіІєЄїЇa-zA-Z0-9№'@#$%^&+=,\.\s\-]{1,254}" title = "${name}"/>
					<form:errors path="${SubjectController.SUBJECT_PATH_NAME}"/>	
				</div>
				<div class="form-group">
					<label for="${SubjectController.SUBJECT_PATH_DESCRIPTION}"><spring:message code="lbl.subject.description" /></label>
					<spring:message code="vm.invalidCharactersOrEmptyField" var="description" />
					<spring:message code="lbl.subject.createDesc" var="descPH" />
					<form:textarea class="form-control" path="${SubjectController.SUBJECT_PATH_DESCRIPTION}"
						placeholder="${descPH}" required="true" pattern = "[а-яА-ЯёЁіІєЄїЇa-zA-Z0-9№'@#$%^&+=,\.\s\-]{1,1000}" title = "${description}"/>
						<form:errors path="${SubjectController.SUBJECT_PATH_DESCRIPTION}"/>
				</div>
				<div class="form-group">
					<label for="${SubjectController.SUBJECT_PATH_USERS}"><spring:message code="lbl.subject.tutor" /></label>
					<form:select class="form-control"
						path="${SubjectController.SUBJECT_PATH_USERS}" multiple="multiple" required="true">
						<c:forEach items="${users}" var="user">
							<c:set var="found" value="false" />
							<c:forEach items="${subjectForm.users}" var="userInSubject">
								<c:if test="${!found}">
									<c:if test="${userInSubject.id eq user.id}">
										<option value="${user.id}" selected="selected">${user.firstName}
											${user.lastName}</option>
										<c:set var="found" value="true" />
									</c:if>
								</c:if>
							</c:forEach>
							<c:if test="${!found}">
								<option value="${user.id}">${user.firstName}
									${user.lastName}</option>
							</c:if>
						</c:forEach>
					</form:select>
					<form:errors path="${SubjectController.SUBJECT_PATH_USERS}"/>
				</div>
				<div class="form-group text-center">
					<input type="submit" class="btn btn-default" value="<spring:message code="lbl.form.save"/>" /> <a
						class="btn btn-default"
						href="${pageContext.request.contextPath}${SubjectController.SUBJECTS_MAPPING}"><spring:message code="lbl.form.cancel"/></a>
				</div>
			</form:form>
		</div>
	</div>
</div>