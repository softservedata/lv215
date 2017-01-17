<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th>ID</th>
			<th>Name <a
				href="${pageContext.request.contextPath}/locations/sortbynameasc"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a
				href="${pageContext.request.contextPath}/locations/sortbynamedesc"><i
					class="fa fa-arrow-circle-o-down"></i></a></th>
			<th>Address <a
				href="${pageContext.request.contextPath}/locations/sortbyaddressasc"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a
				href="${pageContext.request.contextPath}/locations/sortbyaddressdesc"><i
					class="fa fa-arrow-circle-o-down"></i></a></th>
			<th>Coordinates</th>
			<th>Rooms <a
				href="${pageContext.request.contextPath}/locations/sortbycountroomsasc"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a
				href="${pageContext.request.contextPath}/locations/sortbycountroomsdesc"><i
					class="fa fa-arrow-circle-o-down"></i></a></th>
			<th></th>
			<th><a
				href="${pageContext.request.contextPath}/locations/create"><i
					class="fa fa-plus"></i></a></th>
		</tr>
		<c:forEach var="location" items="${locations}">
			<tr>
				<td>${location.id}</td>
				<td>${location.name}</td>
				<td>${location.address}</td>
				<td>Go to map <i class="fa fa-map-o"></i></td>
				<td>${location.rooms.size()}</td>
				<td><c:if test="${location.rooms.size() == 0}">
						<a
							href="${pageContext.request.contextPath}/locations/delete/${location.id}"><i
							class="fa fa-trash-o"></i></a>
					</c:if></td>
				<td><a
					href="${pageContext.request.contextPath}/locations/edit/${location.id}"><i
						class="fa fa-pencil-square-o"></i></a></td>
			</tr>
		</c:forEach>
	</table>
</div>