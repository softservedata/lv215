<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<div class="row">
		<div class="col-md-1">
			<br>
			<a class="btn btn-default"	href="${pageContext.request.contextPath}/rooms"><spring:message code="lbl.form.cancel"/></a>			
		</div>
		<div class="col-md-11">
			<h1 class="text-center"><spring:message code="lbl.room.roomDetails"/></h1>
		</div>
	</div>
	<div class="row panel panel-default">
		<div class="col-md-3">
			<h4><spring:message code="lbl.room.roomName"/>:</h4>
			<p>${room.name}</p>
		</div>
		<div class="col-md-3">
			<h4><spring:message code="lbl.room.location"/>:</h4>
			<p><b>${room.location.name}</b></p>	
			<p>${room.location.address}</p>
		</div>
		<div class="col-md-3">
			<h4><spring:message code="lbl.room.roomCapacity"/>:</h4>
			<p>${room.capacity}</p>
		</div>
		<div class="col-md-3">
			<h4><spring:message code="lbl.room.roomEquipments"/>:</h4>
			<ul>
				<c:forEach items="${room.equipments}" var="equipment">
					<li>${equipment.name}</li>
				</c:forEach>
			</ul>
		</div>			
	</div>
	<div class="row">
		<div class="col-md-1">
			<form:form role="form" action="${room.id}" method="get" modelAttribute="dateFilter">
				<div class="form-group">
					<label for="date">Select meetings date</label>
					<form:input type="date" path="date" id="date" placeholder="YYYY-MM-DD" required="true" />
				</div>
				<div class="form-group text-center">
					<input type="submit" class="btn btn-default" value="Get meetings">					
				</div>
			</form:form>		
		</div>
		<div class="col-md-11">
			<h1 class="text-center">Planned meetings</h1>
		</div>
	</div>
		<div class="table-responsive">
			<table class="table table-hover">
				<tr>
					<th>Date</th>
					<th>Start time</th>
					<th>End time</th>
					<th>Subject</th>
					<th>Owner</th>
					<th>Groups</th>
					<th>Status</th>
				</tr>
				<c:forEach items="${meetings}" var="meeting">
					<tr>
						<td>${meeting.date}</td>
						<td>${meeting.startTime}</td>
						<td>${meeting.endTime}</td>
						<td>${meeting.subjectName}</td>
						<td>${meeting.ownerFullName}</td>
						<td>
							<ul>
								<c:forEach items="${meeting.groupsNames}" var="groupName">
									<li>${groupName}</li>
								</c:forEach>
							</ul>							
						</td>
						<td>${meeting.status}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
</div>