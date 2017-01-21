<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1><spring:message code="lbl.room.roomDetails"/></h1>

<div>
	<h4><spring:message code="lbl.room.roomName"/>:</h4>
	<p>${room.name}</p>
	<h4><spring:message code="lbl.room.location"/>:</h4>
	<p>${room.location.name}</p>
	<h4><spring:message code="lbl.room.address"/>:</h4>
	<p>${room.location.address}</p>
	<h4><spring:message code="lbl.room.roomCapacity"/>:</h4>
	<p>${room.capacity}</p>
	<h4><spring:message code="lbl.room.roomEquipments"/>:</h4>
	<ul>
		<c:forEach items="${room.equipments}" var="equipment">
			<li>${equipment.name}</li>
		</c:forEach>
	</ul>
	<a class="btn btn-danger"
		href="${pageContext.request.contextPath}/rooms"><spring:message code="lbl.form.cancel"/></a>
</div>
