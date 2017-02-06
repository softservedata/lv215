<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<div class="row">
		<div class="col-md-1">
			<br>
			<button class="btn btn-default" onclick="window.history.back()">
				<spring:message code="lbl.form.back" />
			</button>
		</div>
		<div class="col-md-11">
			<h1 class="text-center">
				<spring:message code="lbl.room.roomDetails" />
			</h1>
		</div>
	</div>
	<div class="row panel panel-default">
		<div class="col-md-3">
			<h4>
				<spring:message code="lbl.room.roomName" />
				:
			</h4>
			<p>${room.name}</p>
		</div>
		<div class="col-md-3">
			<h4>
				<spring:message code="lbl.room.location" />
				:
			</h4>
			<p>
				<b>${room.location.name}</b>
			</p>
			<p>${room.location.address}</p>
		</div>
		<div class="col-md-3">
			<h4>
				<spring:message code="lbl.room.roomCapacity" />
				:
			</h4>
			<p>${room.capacity}</p>
		</div>
		<div class="col-md-3">
			<h4>
				<spring:message code="lbl.room.roomEquipments" />
				:
			</h4>
			<ul>
				<c:forEach items="${room.equipments}" var="equipment">
					<li>${equipment.name}</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="row">
		<h1 class="text-center">
			<spring:message code="lbl.room.plannedMeetings" />
		</h1>
		<div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2"
			id='calendar'></div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#calendar').fullCalendar({
			defaultView : 'agendaWeek',
			locale : '<spring:message code="label.localeCalendar"/>',
			header : {
				left : 'prev,next,today',
				center : 'title',
				right : 'month,agendaWeek,agendaDay,listWeek'
			},
			nowIndicator : true,
			navLinks : true,
			events : {
				url : '${pageContext.request.contextPath}/meetings/restByRoom',
				type : 'GET',
				data : {
					roomId : '${room.id}'
				}
			}

		})

	});
</script>
