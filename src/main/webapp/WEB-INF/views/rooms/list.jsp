<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">
$(function(){
	$("select[name=locationId]").chosen({width: "100%"});
	$("select[name=equipments]").chosen({width: "75%"});
	$("select[name=sortByField]").chosen({width: "70%"});
	$("select[name=sortOrder]").chosen({width: "75%"});
})
</script>

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
			<th><a class="btn btn-success" href="rooms/create">Add room</a></th>
		</tr>
		<c:if test="${filter ne null}">
		<tr>
			<form:form role="form" action="rooms" method="get" modelAttribute="filter">
				<td colspan="2">
					<form:select path="locationId" id="locationId">
						<option value="0"> </option>
						<c:forEach items="${locations}" var="location">
							<option value="${location.id}">${location.name}</option>	
						</c:forEach>			
					</form:select>
					<br><br>
					<label for="sortByField">Sort by:</label>
					<form:select path="sortByField" id="sortByField">
						<option value="0"> </option>
						<option value="1">Location</option>
						<option value="2">Room name</option>
						<option value="3">Capacity</option>
					</form:select>
				</td>				
				<td>
					<form:input class="form-control" type="text" path="name" placeholder="name"/>
					<br>
					<label for="sortOrder">Order:</label>
					<form:select path="sortOrder" id="sortOrder">
						<option value="0"> </option>
						<option value="1">Ascending</option>
						<option value="2">Descending</option>						
					</form:select>
				</td>
				<td>										
					<form:input type="number" class="form-control" path="minCapacity" placeholder="minimal" step="1"/>
					<br>						
					<form:input type="number" class="form-control" path="maxCapacity" placeholder="maximal" step="1"/>
				</td>
				<td>
					<form:select path="equipments" id="equipments" multiple="multiple">						
						<c:forEach items="${equipments}" var="equipment">
							<option value="${equipment.id}">${equipment.name}</option>	
						</c:forEach>			
					</form:select>
				</td>
				<td>
					<input type="submit" class="btn btn-primary" value="Apply filter">
				</td>
			</form:form>
				<td>
					<a class="btn btn-primary" href="rooms">Reset filter</a>
				</td>
		</tr>
		</c:if>		
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