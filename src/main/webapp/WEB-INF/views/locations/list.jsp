<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="com.softserve.edu.schedule.controller.LocationController"%>
<h3 class="text-center"><spring:message code="lbl.location.title" /></h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th><spring:message code="lbl.location.name" /> <a
				href="${pageContext.request.contextPath}${LocationController.LOCATIONS_SORT_BY_NAME_ASC_MAPPING}"
				title="<spring:message code="lbl.location.sortAsc" />"><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="${pageContext.request.contextPath}${LocationController.LOCATIONS_SORT_BY_NAME_DESC_MAPPING}"
				title="<spring:message code="lbl.location.sortDesc" />"><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<th><spring:message code="lbl.location.address" /> <a
				href="${pageContext.request.contextPath}${LocationController.LOCATIONS_SORT_BY_ADDRESS_ASC_MAPPING}"
				title="<spring:message code="lbl.location.sortAsc" />"><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="${pageContext.request.contextPath}${LocationController.LOCATIONS_SORT_BY_ADDRESS_DESC_MAPPING}"
				title="<spring:message code="lbl.location.sortDesc" />"><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<th><spring:message code="lbl.location.coordinates" /></th>
			<th><spring:message code="lbl.location.rooms" /> <a
				href="${pageContext.request.contextPath}${LocationController.LOCATIONS_SORT_BY_COUNT_ROOM_ASC_MAPPING}"
				title="<spring:message code="lbl.location.sortAsc" />"><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="${pageContext.request.contextPath}${LocationController.LOCATIONS_SORT_BY_COUNT_ROOM_DESC_MAPPING}"
				title="<spring:message code="lbl.location.sortDesc" />"><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<th></th>
			<th><a
				href="${pageContext.request.contextPath}${LocationController.LOCATION_CREATE_MAPPING}"
				title="<spring:message code="lbl.location.add" />"><i class="fa fa-plus fa-lg"></i></a></th>
		</tr>
		<tr>
			<td><form:form method="post"
					action="${pageContext.request.contextPath}${LocationController.LOCATIONS_SEARCH_BY_NAME_MAPPING}"
					modelAttribute="${LocationController.SEARCH_MODEL_ATTR}">
					<spring:message code="lbl.location.search" var="search" />
					<form:input path="name" placeholder=" ${search}" />
					<button type="submit" title="<spring:message code="lbl.location.searchByName" />">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>
			<td><form:form method="post"
					action="${pageContext.request.contextPath}${LocationController.LOCATIONS_SEARCH_BY_ADDRESS_MAPPING}"
					modelAttribute="${LocationController.SEARCH_MODEL_ATTR}">
					<spring:message code="lbl.location.search" var="search" />
					<form:input path="address" placeholder=" ${search}" />
					<button type="submit" title="<spring:message code="lbl.location.searchByAddress" />">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<c:forEach var="location" items="${locations}">
			<tr>
				<td>${location.name}</td>
				<td>${location.address}</td>
				<td><spring:message code="lbl.location.map" /> <i class="fa fa-map-o"></i></td>
				<td><div class="tooltip3">${location.rooms.size()}
						<c:if test="${location.rooms.size() != 0}">
							<span class="tooltiptext3"> <c:forEach
									items="${location.rooms}" var="room">
							<a href="${pageContext.request.contextPath}/rooms/${room.id}">${room.name}</a>
						</c:forEach>
							</span>
						</c:if>
					</div></td>
				<td><c:if test="${location.rooms.size() == 0}">
						<a
							href="${pageContext.request.contextPath}${LocationController.LOCATION_DELETE_MAPPING}${location.id}"
							onclick="return confirm('<spring:message code="lbl.location.deleteConfirm" />')"
							title="<spring:message code="lbl.location.delete" />"><i class="fa fa-trash-o fa-lg"></i></a>
					</c:if></td>
				<td><a
					href="${pageContext.request.contextPath}${LocationController.LOCATION_EDIT_MAPPING}${location.id}"
					title="<spring:message code="lbl.location.edit" />"><i class="fa fa-pencil-square-o fa-lg"></i></a></td>
			</tr>
		</c:forEach>
	</table>
</div>