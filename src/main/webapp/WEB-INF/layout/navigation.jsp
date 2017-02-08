<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}">
				<span class="glyphicon glyphicon-calendar"></span> UniSchedule
			</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<sec:authorize access="isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/calendar">
							<spring:message code="lbl.nav.calendar" />
					</a></li>
				</sec:authorize>
				<li><a href="${pageContext.request.contextPath}/meetings">
						<spring:message code="lbl.nav.meetings" />
				</a></li>
				<li><a href="${pageContext.request.contextPath}/locations">
						<spring:message code="lbl.nav.locations" />
				</a></li>
				<li><a href="${pageContext.request.contextPath}/rooms"> <spring:message
							code="lbl.nav.rooms" />
				</a></li>
				<li><a href="${pageContext.request.contextPath}/subjects">
						<spring:message code="lbl.nav.subjects" />
				</a></li>
				<li><a href="${pageContext.request.contextPath}/usergroups">
						<spring:message code="lbl.nav.usergroups" />
				</a></li>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR', 'ROLE_USER')">
					<li><a href="${pageContext.request.contextPath}/users"> <spring:message
								code="lbl.nav.users" />
					</a></li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
					<li><a
						href="${pageContext.request.contextPath}/roomequipments"> <spring:message
								code="lbl.nav.roomequipments" />
					</a></li>
				</sec:authorize>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/userDetails">
							<sec:authentication property="principal.firstName" /> <sec:authentication
								property="principal.lastName" />
					</a></li>
					<li><c:url var="logoutUrl" value="/logout" /> <spring:message
							code="lbl.nav.logout" var="logoutText" />
						<form id="logoutForm" action="${logoutUrl}" method="post">
							<input class="btn btn-link" type="submit" value="${logoutText}" />
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form></li>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/registration">
							<spring:message code="lbl.nav.signUp" />
					</a></li>
					<li><a href="${pageContext.request.contextPath}/login"> <span
							class="glyphicon glyphicon-log-in"></span> <spring:message
								code="lbl.nav.signIn" />
					</a></li>
				</sec:authorize>
				<li><a href="?lang=en">En</a></li>
				<li><a href="?lang=ua">Ua</a></li>
				<li><a href="?lang=ru">Ru</a></li>
			</ul>
		</div>
	</div>
</nav>
