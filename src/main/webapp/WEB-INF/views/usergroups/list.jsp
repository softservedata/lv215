<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="com.softserve.edu.schedule.controller.UserGroupController"%>

<h3 class="text-center">
	<spring:message code="lbl.group.title" />
</h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th><spring:message code="lbl.group.name" /><a
				href="${pageContext.request.contextPath}/usergroups/sortbynameasc"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a
				href="${pageContext.request.contextPath}/usergroups/sortbynamedesc"><i
					class="fa fa-arrow-circle-o-down"></i></a></th>
			<th><spring:message code="lbl.group.curator" /></th>
			<th><spring:message code="lbl.group.level" /><a
				href="${pageContext.request.contextPath}/usergroups/sortbylevelasc"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a
				href="${pageContext.request.contextPath}/usergroups/sortbyleveldesc"><i
					class="fa fa-arrow-circle-o-down"></i></a></th>
			<th><spring:message code="lbl.group.members" /><a
				href="${pageContext.request.contextPath}/usergroups/sortbymembersasc"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a
				href="${pageContext.request.contextPath}/usergroups/sortbymembersdesc"><i
					class="fa fa-arrow-circle-o-down"></i></a></th>
			<th></th>
			<th><a
				href="${pageContext.request.contextPath}/usergroups/create"><i
					class="fa fa-plus"></i></a></th>
		</tr>

		<tr>
			<td><form:form method="post"
					action="${pageContext.request.contextPath}${UserGroupController.USERGROUPS_SEARCH_BY_NAME_MAPPING}"
					modelAttribute="${UserGroupController.SEARCH_MODEL_ATTR}">
					<form:input path="${UserGroupController.USERGROUP_PATH_NAME}"
						placeholder="Search..." />
					<button type="submit" title="Search by name">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>

			<td><form:form method="post"
					action="${pageContext.request.contextPath}${UserGroupController.USERGROUPS_SEARCH_BY_CURATOR_MAPPING}"
					modelAttribute="${UserGroupController.SEARCH_MODEL_ATTR}">
					<form:input
						path="${UserGroupController.USERGROUP_PATH_CURATOR_LASTNAME}"
						placeholder="Search..." />
					<button type="submit" title="Search by curator">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>

		<c:forEach var="usergroup" items="${usergroups}">
			<tr>
				<td>${usergroup.name}</td>
				<td>${usergroup.curator.lastName}
					${usergroup.curator.firstName}</td>
				<td>${usergroup.level}</td>
				<td>${usergroup.users.size()}</td>

				<td><c:if test="${usergroup.users.size() <= 1}">
						<a
							href="${pageContext.request.contextPath}/usergroups/delete/${usergroup.id}"><i
							class="fa fa-trash-o"></i></a>
					</c:if></td>
				<td><a
					href="${pageContext.request.contextPath}/usergroups/edit/${usergroup.id}"><i
						class="fa fa-pencil-square-o"></i></a></td>
			</tr>
		</c:forEach>
	</table>
</div>