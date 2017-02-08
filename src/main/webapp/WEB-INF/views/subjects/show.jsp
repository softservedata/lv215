<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.softserve.edu.schedule.controller.SubjectController"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="container">
	<div class="row " style="padding-left: 160px;">
		<div style="float: left;">
			<h3>
				<a class="align-left" href="#" onclick="window.history.back()"
					title="<spring:message code="lbl.form.back" />">
					<i class="fa fa-arrow-left fa-lg"></i>
				</a>
			</h3>
		</div>
		<div class="col-lg-1 col-lg-offset-9 col-md-1 col-sm-1 col-xs-1 panel-exit" style="margin-top: 0;">
			<h3>
				<a class="align-right"
					href="${pageContext.request.contextPath}${SubjectController.SUBJECTS_MAPPING}"
					title="<spring:message code="lbl.subject.title" />">
					<i class="fa fa-table fa-lg"></i>
				</a>
			</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-4 col-lg-offset-1 col-md-5 col-sm-6 panel panel-default" style="margin-top: 0;">
			<h3 class="text-center">
				<spring:message code="lbl.subject.subjectDetails" />
			</h3>
			<div class="form-group">
				<h4>
					<spring:message code="lbl.subject.name" />
					: ${subject.name}
				</h4>

			</div>
			<div class="form-group" style="word-wrap: break-word;">
				<h4>
					<spring:message code="lbl.subject.description" />
					: ${subject.description}
				</h4>
			</div>
			<div class="form-group">
				<h4>
					<spring:message code="lbl.subject.tutor" />
					:
				</h4>
				<ul>
					<c:forEach items="${subject.users}" var="user">
						<sec:authorize
							access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR', 'ROLE_USER')">
							<li>
								<a
									href="${pageContext.request.contextPath}/${UserController.USER_MEETINGS_MAPPING}${user.id}">${user.firstName}
									${user.lastName}</a>
							</li>
						</sec:authorize>
						<sec:authorize
							access="!hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR', 'ROLE_USER')">
							<li>${user.firstName}${user.lastName}</li>
						</sec:authorize>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="col-lg-6 col-md-7 col-sm-6 panel-map" style="margin-top: 0;">
			<div id='calendar' style="margin-top: 0px"></div>
		</div>
	</div>

</div>

<script>
	$(document)
			.ready(
					function() {
						$('#calendar')
								.fullCalendar(
										{
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
												url : '${pageContext.request.contextPath}/meetings/restBySubject',
												type : 'GET',
												data : {
													subjectId : '${subject.id}'
												}
											}
										})
					});
</script>
