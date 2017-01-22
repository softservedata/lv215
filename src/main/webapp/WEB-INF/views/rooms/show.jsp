<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
	<div class="row">	
		<h1 class="text-center"><spring:message code="lbl.room.roomDetails"/></h1>
		<div class="panel panel-default">
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
		<a class="btn btn-danger"
				href="${pageContext.request.contextPath}/rooms"><spring:message code="lbl.form.cancel"/></a>
	</div>
</div>