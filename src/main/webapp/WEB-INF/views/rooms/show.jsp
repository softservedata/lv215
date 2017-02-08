<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<div class="row">
		<h3 class="col-lg-1 col-lg-offset-1 col-md-1 col-md-offset-1 col-sm-1 col-sm-offset-1">
			<button class="btn btn-default" onclick="window.history.back()">
				<spring:message code="lbl.form.back" />
			</button>
		</h3>
	</div>
	<div class="row">
		<div class="col-lg-4 col-lg-offset-1 col-md-5 col-sm-6 panel panel-default">
			<h3 class="text-center">
				<spring:message code="lbl.room.roomDetails" />
			</h3>
			<h4>
				<spring:message code="lbl.room.roomName" />
				: ${room.name}
			</h4>
			<h4>
				<spring:message code="lbl.room.location" />
				: ${room.location.name}
			</h4>
			<p>${room.location.address}</p>
			<h4>
				<spring:message code="lbl.room.roomCapacity" />
				: ${room.capacity}
			</h4>
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
		<div class="col-lg-6 col-md-7 col-sm-6 panel-map">
			<div id='calendar'></div>
		</div>
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
