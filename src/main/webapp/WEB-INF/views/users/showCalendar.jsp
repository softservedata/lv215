<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>

<div class="container">
	<div class="row">
		<div
			class="col-lg-1 col-lg-offset-0 col-md-1 col-sm-1 col-xs-1 panel-exit text-center">
			<h3>
				<a href="#" onclick="window.history.back()"
					title="<spring:message code="lbl.form.back" />"> <i
					class="fa fa-arrow-left"></i>
				</a>
			</h3>
		</div>
		<div
			class="col-lg-10 col-lg-offset-0 col-md-10 col-sm-10 col-xs-10">
			<h3 class="text-center">
				<spring:message code="lbl.user.plannedMeetings" />
				${user.lastName} ${user.firstName}
			</h3>
			<div
				class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2"
				id='calendar'></div>
		</div>
		<div
			class="col-lg-1 col-lg-offset-0 col-md-1 col-sm-1 col-xs-1 panel-exit text-center">
			<h3>
				<a
					href="${pageContext.request.contextPath}${UserController.USERS_MAPPING_FROM_HEADER}">
					<i class="fa fa-table fa-lg"></i>
				</a>
			</h3>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#calendar').fullCalendar({
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
				url : '${pageContext.request.contextPath}/meetings/restByUser',
				type : 'GET',
				data : {
					userId : '${user.id}'
				}
			}

		})

	});
</script>
