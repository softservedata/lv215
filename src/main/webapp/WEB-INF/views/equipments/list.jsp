<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th>ID</th>
			<th>Name
			<a
				href="${pageContext.request.contextPath}/roomequipments/sortbynameasc"
				title="Sort by name (ascending)"><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="${pageContext.request.contextPath}/roomequipments/sortbynamedesc"
				title="Sort by name (descending)"><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<th>Edit</th>
			<th>Delete</th>
			<th><a
				href="${pageContext.request.contextPath}/roomequipments/create"
				title="Add new equipment"><i class="fa fa-plus fa-lg"></i></a></th>
		</tr>
		<c:forEach var="equipment" items="${equipments}">
			<tr>
				<td>${equipment.id}</td>
				<td>${equipment.name}</td>
				<td><a
					href="${pageContext.request.contextPath}/roomequipments/edit/${equipment.id}"
					title="Edit Equipment"><i class="fa fa-pencil-square-o fa-lg"></i></a></td>
				<td><a
					href="${pageContext.request.contextPath}/roomequipments/delete/${equipment.id}"
					onclick="return confirm('Are you sure you want to delete this equipment?')"
					title="Delete equipment"><i class="fa fa-trash-o fa-lg"></i></a></td>
					<td></td>
			</tr>
		</c:forEach>
	</table>
</div>