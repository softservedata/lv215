<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="com.softserve.edu.schedule.controller.SubjectController"%>

<h3 class="text-center">
	<spring:message code="lbl.subject.title" />
</h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th>ID</th>
			<th><spring:message code="lbl.subject.name" /> <a
				href="${pageContext.request.contextPath}${SubjectController.SUBJECTS_SORT_BY_NAME_ASC_MAPPING}"
				title="<spring:message code="lbl.subject.sortAsc"/>"><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="${pageContext.request.contextPath}${SubjectController.SUBJECTS_SORT_BY_NAME_DESC_MAPPING}"
				title="<spring:message code="lbl.subject.sortDesc"/>"><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<th><spring:message code="lbl.subject.description" /> <a
				href="${pageContext.request.contextPath}${SubjectController.SUBJECTS_SORT_BY_DESCRIPTION_ASC_MAPPING}"
				title="<spring:message code="lbl.subject.sortAsc"/>"><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="${pageContext.request.contextPath}${SubjectController.SUBJECTS_SORT_BY_DESCRIPTION_DESC_MAPPING}"
				title="<spring:message code="lbl.subject.sortDesc"/>"><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<th><spring:message code="lbl.subject.tutor" /> </th>
			<th></th>
			<th><a
				href="${pageContext.request.contextPath}${SubjectController.SUBJECT_CREATE_MAPPING}"
				title="<spring:message code="lbl.subject.add"/>"><i
					class="fa fa-plus fa-lg"></i></a></th>
		</tr>
		<tr>
			<td></td>
			<td><form:form method="post"
					action="${pageContext.request.contextPath}${SubjectController.SUBJECTS_SEARCH_BY_NAME_MAPPING}"
					modelAttribute="${SubjectController.SEARCH_MODEL_ATTR}">
					<spring:message code="lbl.subject.search" var="search" />
					<form:input path="${SubjectController.SUBJECT_PATH_NAME}"
						placeholder=" ${search}"/>
					<button type="submit" title="<spring:message code="lbl.subject.searchByName"/>">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>
			<td><form:form method="post"
					action="${pageContext.request.contextPath}${SubjectController.SUBJECTS_SEARCH_BY_DESCRIPTION_MAPPING}"
					modelAttribute="${SubjectController.SEARCH_MODEL_ATTR}">
					<spring:message code="lbl.subject.search" var="search" />
					<form:input path="${SubjectController.SUBJECT_PATH_DESCRIPTION}"
						placeholder=" ${search}" />
					<button type="submit" title="<spring:message code="lbl.subject.searchByDescription"/>">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>
			<td><form:form method="post"
					action="${pageContext.request.contextPath}${SubjectController.SUBJECTS_SEARCH_BY_TUTOR_MAPPING}"
					modelAttribute="${SubjectController.SEARCH_BY_TUTOR_MODEL_ATTR}">
					<spring:message code="lbl.subject.search" var="search" />
					<form:input path="${SubjectController.SUBJECT_PATH_LASTNAME}"
						placeholder=" ${search}" />
					<button type="submit" title="<spring:message code="lbl.subject.searchByTutor"/>">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>
			<td></td>
			<td></td>
		</tr>
		<c:forEach var="subject" items="${subjects}">
			<tr>
				<td>${subject.id}</td>
				<td>${subject.name}</td>
				<td>${subject.description}</td>
				<td><c:forEach items="${subject.users}" var="user">
						<p>${user.firstName}${user.lastName}</p>
					</c:forEach></td>
				<td><a
					href="${pageContext.request.contextPath}${SubjectController.SUBJECT_DELETE_MAPPING}${subject.id}"
					title="<spring:message code="lbl.subject.delete"/>"
					onclick="return confirm('<spring:message code="lbl.subject.deleteConfirm"/>')"><i
						class="fa fa-trash-o fa-lg"></i></a></td>
				<td><a
					href="${pageContext.request.contextPath}${SubjectController.SUBJECT_EDIT_MAPPING}${subject.id}"
					title="<spring:message code="lbl.subject.edit"/>"><i
						class="fa fa-pencil-square-o fa-lg"></i></a></td>
			</tr>
		</c:forEach>
	</table>
</div>