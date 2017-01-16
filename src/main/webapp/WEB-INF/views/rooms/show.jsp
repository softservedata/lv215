<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Room info:</h1>

<div>
	<h4>Room name</h4>
	<p>${room.name}</p>
	<h4>Room location</h4>
	<p>${room.location.name}</p>
	<h4>Room address</h4>
	<p>${room.location.address}</p>
	<h4>Room capacity</h4>
	<p>${room.capacity}</p>
	<h4>Room equipment</h4>
	<ul>
		<c:forEach items="${room.equipments}" var="equipment">
			<li>${equipment.name}</li>
		</c:forEach>
	</ul>
</div>
