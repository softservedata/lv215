<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h3>Present rooms:</h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th>Location</th>
			<th>Address</th>
			<th>Room name</th>
			<th>Room capacity</th>
			<th>Room equipment</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach items="${rooms}" var="room">
			<tr>
				<td>${room.location.name}</td>
				<td>${room.location.address}</td>
				<td><a href="rooms/${room.id}">${room.name}</a></td>
				<td>${room.capacity}</td>
				<td>
					<ul>
						<c:forEach items="${room.equipments}" var="equipment">
							<li>${equipment.name}</li>
						</c:forEach>
					</ul>
				</td>
				<td><a class="btn btn-danger" href="rooms/delete/${room.id}">Delete</a></td>
				<td><a class="btn btn-success" href="rooms/edit/${room.id}">Edit</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<br>
<p><a class="btn btn-success" href="rooms/create">Add room</a></p>