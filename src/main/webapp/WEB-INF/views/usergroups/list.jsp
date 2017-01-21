<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h3>UserGroups</h3>
<div class="table-responsive">
<table class="table table-hover">
	<tr>
		<th>Name <a
			href="${pageContext.request.contextPath}/usergroups/sortbynameasc"><i
				class="fa fa-arrow-circle-o-up"></i></a> <a
			href="${pageContext.request.contextPath}/usergroups/sortbynamedesc"><i
				class="fa fa-arrow-circle-o-down"></i></a></th>
		<th>Level <a
			href="${pageContext.request.contextPath}/usergroups/sortbylevelasc"><i
				class="fa fa-arrow-circle-o-up"></i></a> <a
			href="${pageContext.request.contextPath}/usergroups/sortbyleveldesc"><i
				class="fa fa-arrow-circle-o-down"></i></a></th>
		<th>Curator</th>
		<th>Members<a
			href="${pageContext.request.contextPath}/usergroups/sortbymembersasc"><i
				class="fa fa-arrow-circle-o-up"></i></a> <a
			href="${pageContext.request.contextPath}/usergroups/sortbymembersdesc"><i
				class="fa fa-arrow-circle-o-down"></i></a></th>
		<th></th>
		<th><a
			href="${pageContext.request.contextPath}/usergroups/create"><i
				class="fa fa-plus"></i></a></th>
	</tr>
	<c:forEach var="usergroup" items="${usergroups}">
		<tr>
			<td>${usergroup.name}</td>
			<td><c:choose>
					<c:when test="${usergroup.level == 0}">
						Students</c:when>
					<c:when test="${usergroup.level == 1}">
						Teachers</c:when>
					<c:otherwise>
						Unknown</c:otherwise>
				</c:choose></td>
			<td>${usergroup.curator.firstName}${usergroup.curator.lastName}</td>
			<td>${usergroup.users.size()}</td>

			<td><c:if test="${usergroup.users.size() == 0}">
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