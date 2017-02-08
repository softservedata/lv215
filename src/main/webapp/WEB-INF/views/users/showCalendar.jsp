<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>

<div class="container">
	<div class="row">
		<h3 class="text-center">
			<spring:message code="lbl.user.plannedMeetings" />
			${user.lastName} ${user.firstName}
		</h3>
		<div
			class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2"
			id='calendar'></div>
	</div>
	<div  class="text-center v-alighn"
		class="col-lg-1 col-lg-offset-1 col-md-1 col-sm-1 col-xs-1 panel-exit">
		<h3>
			<a class="align-right"
				onclick="window.history.back()"> <i
				class="fa fa-table fa-lg"></i>
			</a>
		</h3>
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
												url : '${pageContext.request.contextPath}/meetings/restByAnyUser',
												type : 'GET',
												data : {
													userId : '${user.id}'
												}
											}

										})

					});
</script>
