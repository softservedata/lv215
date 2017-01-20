<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">
	$(function() {
		$("select[name=locationId]").chosen({width : "100%"});
		$("select[name=equipments]").chosen({width : "100%"});
		$("select[name=sortByField]").chosen({width : "75%"});
		$("select[name=sortOrder]").chosen({width : "75%"});
	})
</script>

<h3 class="text-center">Rooms</h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th>Location</th>
			<th>Address</th>
			<th>Room name</th>
			<th>Room capacity</th>
			<th>Room equipment</th>
			<th class="text-center v-alighn">
				<button class="btn btn-link fa fa-filter fa-2x" data-toggle="collapse" data-target="#showfilter" title="Show filter"></button>
			</th>
			<th class="text-center v-alighn">
				<a class="fa fa-plus fa-2x" href="rooms/create" title="Create room"></a>
			</th>
		</tr>
		<c:choose>
			<c:when test="${roomFilter.showFilter eq true}">		
				<tr class="collapse in" id="showfilter">
			</c:when>
			<c:otherwise>
				<tr class="collapse" id="showfilter">
			</c:otherwise>
		</c:choose>
			<form:form role="form" action="rooms" method="get" modelAttribute="roomFilter">
			<form:input path="showFilter" type="hidden" value="true"/>
			<td colspan="2">
				<div class="form-group">
					<form:select class="form-control" path="locationId" id="locationId">
						<option value="0"></option>
						<c:forEach items="${locations}" var="location">
							<c:choose>
								<c:when test="${roomFilter.locationId eq location.id}">
									<option value="${location.id}" selected="selected">${location.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${location.id}">${location.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</div>				
				<div class="form-group" id="sortfield">
					<label for="sortByField">Sort by:</label> 
					<form:select class="form-control" path="sortByField" id="sortByField">
						<option value="0"></option>
						<c:choose>
							<c:when test="${roomFilter.sortByField eq 1}">
								<option value="1" selected="selected">Location</option>
							</c:when>
							<c:otherwise>
								<option value="1">Location</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${roomFilter.sortByField eq 2}">
								<option value="2" selected="selected">Room name</option>
							</c:when>
							<c:otherwise>
								<option value="2">Room name</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${roomFilter.sortByField eq 3}">
								<option value="3" selected="selected">Capacity</option>
							</c:when>
							<c:otherwise>
								<option value="3">Capacity</option>
							</c:otherwise>
						</c:choose>					
					</form:select>
				</div>
			</td>
			<td>
				<div class="form-group">
					<form:input class="form-control" type="text" path="name" placeholder="name" />
				</div>
				<div class="form-group">
					<label for="sortOrder">Order:</label>
					<form:select class="form-control" path="sortOrder" id="sortOrder">
						<option value="0"></option>
						<c:choose>
							<c:when test="${roomFilter.sortOrder eq 1}">
								<option value="1" selected="selected">Ascending</option>
							</c:when>
							<c:otherwise>
								<option value="1">Ascending</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${roomFilter.sortOrder eq 2}">
								<option value="2" selected="selected">Descending</option>
							</c:when>
							<c:otherwise>
								<option value="2">Descending</option>
							</c:otherwise>
						</c:choose>							
					</form:select>
				</div>
			</td>
			<td>
				<div class="form-group">
					<form:input type="number" class="form-control" path="minCapacity" placeholder="minimal" step="1" />
				</div> 
				<div class="form-group">
					<form:input	type="number" class="form-control" path="maxCapacity" placeholder="maximal" step="1" />
				</div>
			</td>
			<td>
				<div class="form-group">
					<form:select class="form-control" path="equipments" id="equipments"	multiple="multiple">
						<c:forEach items="${equipments}" var="equipment">
							<c:set var="found" value="false"/>
							<c:forEach items="${roomFilter.equipments}" var="equipmentInFilter">
								<c:if test="${!found}">							
									<c:if test="${equipmentInFilter.id eq equipment.id}">
										<option value="${equipment.id}" selected="selected">${equipment.name}</option>
										<c:set var="found" value="true"/>
									</c:if>
								</c:if>						
							</c:forEach>
							<c:if test="${!found}">
								<option value="${equipment.id}">${equipment.name}</option>
							</c:if>
						</c:forEach>					
					</form:select>
				</div>
			</td>
			<td class="text-center v-alighn">				
				<button type="submit" class="btn btn-link fa fa-check-circle-o fa-2x" title="Apply filter"></button>
			</td>
			</form:form>
			<td class="text-center v-alighn">
				<a class="fa fa-ban fa-2x" href="rooms?showFilter=false&locationId=0&sortByField=0&name=&sortOrder=0&minCapacity=0&maxCapacity=0&_equipments=1" title="Reset filter"></a>
			</td>
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
				<td class="text-center v-alighn"><a class="fa fa-trash-o fa-2x" href="rooms/delete/${room.id}" title="Delete room"
					onclick="return confirm('Are you sure you want to delete this room?');"></a></td>
				<td class="text-center v-alighn"><a class="fa fa-pencil-square-o fa-2x" href="rooms/edit/${room.id}" title="Edit room"></a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<br>