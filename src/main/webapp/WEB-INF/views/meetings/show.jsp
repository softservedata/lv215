<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Meeting info:</h1>

<div>
	<h4>Description name</h4>
	<p>${meeting.description}</p>
	<h4>Meeting subject</h4>
	<p>${meeting.subject.name}</p>
	<h4>Meeting owner</h4>
	<p>${meeting.owner.lastName}</p>
	<h4>Meeting room</h4>
	<p>${meeting.room.name}</p>
<%-- 	<h4>Meeting groups</h4>
	<ul>
		<c:forEach items="${room.equipments}" var="equipment">
			<li>${equipment.name}</li>
		</c:forEach>
	</ul> --%>
</div>
