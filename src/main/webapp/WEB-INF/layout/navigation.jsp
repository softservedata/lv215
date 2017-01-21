<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
				<li><a href="${pageContext.request.contextPath}/calendar"><spring:message code="lbl.nav.calendar"/></a></li>
				<li><a href="${pageContext.request.contextPath}/meetings"><spring:message code="lbl.nav.meetings"/></a></li>
				<li><a href="${pageContext.request.contextPath}/locations"><spring:message code="lbl.nav.locations"/></a></li>
				<li><a href="${pageContext.request.contextPath}/rooms"><spring:message code="lbl.nav.rooms"/></a></li>
				<li><a href="${pageContext.request.contextPath}/subjects"><spring:message code="lbl.nav.subjects"/></a></li>
				<li><a href="${pageContext.request.contextPath}/usergroups"><spring:message code="lbl.nav.usergroups"/></a></li>
				<li><a href="${pageContext.request.contextPath}/users"><spring:message code="lbl.nav.users"/></a></li>
				<li><a href="${pageContext.request.contextPath}/roomequipments"><spring:message code="lbl.nav.roomequipments"/></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/registration"><spring:message code="lbl.nav.signUp"/></a></li>
				<li><a href="#"><span class="glyphicon glyphicon-log-in"></span><spring:message code="lbl.nav.signIn"/></a></li>
				<li><a href="?lang=en">En</a></li>
				<li><a href="?lang=ua">Ua</a></li>
				<li><a href="?lang=ru">Ru</a></li>
			</ul>
		</div>
	</div>
</nav>
