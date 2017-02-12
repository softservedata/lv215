<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<div class="row">
		<div class="col-lg-1 col-lg-offset-1 col-md-1 col-sm-1 col-xs-1 panel-exit text-center">
			<h3>
				<a href="#" onclick="window.history.back()" title="<spring:message code="lbl.form.back" />">
					<i class="fa fa-arrow-left"></i>
				</a>
			</h3>
		</div>
		<div class="col-lg-1 col-lg-offset-8 col-md-1 col-sm-1 col-xs-1 panel-exit text-center">
			<h3>
				<a href="${pageContext.request.contextPath}/rooms"
					title="<spring:message code="lbl.room.title" />">
					<i class="fa fa-table fa-lg"></i>
				</a>
			</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-4 col-lg-offset-1 col-md-5 col-sm-6 panel panel-default zero-margin-top">
			<h3 class="text-center">
				<spring:message code="lbl.room.roomDetails" />
			</h3>
			<h4>
				<spring:message code="lbl.room.roomName" />
				: ${room.name}
			</h4>
			<h4>
				<a href="${pageContext.request.contextPath}/locations/map/${room.location.id}">
					<spring:message code="lbl.room.location" />
					: ${room.location.name}
				</a>
			</h4>
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
		<div class="col-lg-6 col-md-7 col-sm-6 panel-map zero-margin-top">
			<div id='calendar'></div>
		</div>
	</div>
</div>

<span id="roomsRestURL" hidden="true">${pageContext.request.contextPath}/meetings/restByRoom</span>
<span id="roomId" hidden="true">${room.id}</span>
<span id="language" hidden="true"><spring:message code="label.localeCalendar" /></span>
<spring:url value="/resources/js/rooms/show.js" var="roomsShowJS" />
<script type="text/javascript" src="${roomsShowJS}">
	
</script>