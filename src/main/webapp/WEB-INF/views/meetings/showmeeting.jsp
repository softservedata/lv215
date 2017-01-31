<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<div class="row">
		<div class="col-md-1">
			<br> <a class="btn btn-default"
				href="${pageContext.request.contextPath}/meetings"><spring:message
					code="lbl.form.back" /></a>
		</div>
		<div class="col-md-11">
			<h1 class="text-center">
				<spring:message code="lbl.meeting.showmeeting" />
			</h1>
		</div>
	</div>
	<div class="row panel panel-default">
		<div class="col-md-3">
			<h4>
				<spring:message code="lbl.meeting.description" />
				:
			</h4>
			<p>${meeting.description}</p>
		</div>
		<div class="col-md-3">
			<h4>
				<spring:message code="lbl.meeting.subject" />
				:
			</h4>
			<p>
				<b>${meeting.subject.name}</b>
			</p>
		</div>

		<div class="col-md-3">
			<h4>
				<spring:message code="lbl.meeting.groups" />
				:
			</h4>
			<ul>
				<c:forEach items="${meeting.groups}" var="group">
					<li>${group.name}</li>
				</c:forEach>
			</ul>
		</div>
	</div>

</div>