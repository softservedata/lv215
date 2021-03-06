<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3 class="text-center">Locations</h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th>ID</th>
			<th>Name <a
				href="${pageContext.request.contextPath}/locations/sortbynameasc"
				title="Sort by name (ascending)"><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="${pageContext.request.contextPath}/locations/sortbynamedesc"
				title="Sort by name (descending)"><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<th>Address <a
				href="${pageContext.request.contextPath}/locations/sortbyaddressasc"
				title="Sort by address (ascending)"><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="${pageContext.request.contextPath}/locations/sortbyaddressdesc"
				title="Sort by address (descending)"><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<th>Coordinates</th>
			<th>Rooms <a
				href="${pageContext.request.contextPath}/locations/sortbycountroomsasc"
				title="Sort by count rooms (ascending)"><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="${pageContext.request.contextPath}/locations/sortbycountroomsdesc"
				title="Sort by count rooms (descending)"><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<th></th>
			<th><a
				href="${pageContext.request.contextPath}/locations/create"
				title="Add new location"><i class="fa fa-plus fa-lg"></i></a></th>
		</tr>
		<tr>
			<td></td>
			<td><form:form method="post"
					action="${pageContext.request.contextPath}/locations/searchByName"
					modelAttribute="search">
					<form:input path="name" placeholder=" Search..." />
					<button type="submit" title="Search by name">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>
			<td><form:form method="post"
					action="${pageContext.request.contextPath}/locations/searchByAddress"
					modelAttribute="search">
					<form:input path="address" placeholder=" Search..." />
					<button type="submit" title="Search by address">
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
				<td>${location.id}</td>
				<td>${location.name}</td>
				<td>${location.address}</td>
				<td>Go to map <i class="fa fa-map-o"></i></td>
				<td><div class="tooltip3">${location.rooms.size()}
						<c:if test="${location.rooms.size() != 0}">
							<span class="tooltiptext3"> <c:forEach
									items="${location.rooms}" var="room">
							${room.name}
						</c:forEach>
							</span>
						</c:if>
					</div></td>
				<td><c:if test="${location.rooms.size() == 0}">
						<a
							href="${pageContext.request.contextPath}/locations/delete/${location.id}"
							onclick="return confirm('Are you sure you want to delete this location?')"
							title="Delete location"><i class="fa fa-trash-o fa-lg"></i></a>
					</c:if></td>
				<td><a
					href="${pageContext.request.contextPath}/locations/edit/${location.id}"
					title="Edit location"><i class="fa fa-pencil-square-o fa-lg"></i></a></td>
			</tr>
		</c:forEach>
	</table>
</div>