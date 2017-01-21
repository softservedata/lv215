<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}"><span
				class="glyphicon glyphicon-calendar"></span> UniSchedule</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/calendar">Calendar</a></li>
				<li><a href="${pageContext.request.contextPath}/meetings">Meetings</a></li>
				<li><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
				<li><a href="${pageContext.request.contextPath}/rooms">Rooms</a></li>
				<li><a href="${pageContext.request.contextPath}/subjects">Subjects</a></li>
				<li><a href="${pageContext.request.contextPath}/usergroups">Groups</a></li>
				<li><a href="${pageContext.request.contextPath}/users">Users</a></li>
				<li><a href="${pageContext.request.contextPath}/roomequipments">Equipments</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/registration">Sign
						up</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
						Sign in</a></li>
			</ul>
		</div>
	</div>
</nav>
