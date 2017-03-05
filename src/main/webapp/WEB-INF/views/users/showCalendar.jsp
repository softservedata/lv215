<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page
	import="com.softserve.edu.schedule.controller.constants.UserControllerConst"%>

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
					href="${pageContext.request.contextPath}${UserControllerConst.USERS_MAPPING_FROM_HEADER}">
					<i class="fa fa-table fa-lg"></i>
				</a>
			</h3>
		</div>
	</div>
</div>

<span id="usersRestURL" hidden="true">${pageContext.request.contextPath}/meetings/restByUser</span>
<span id="userId" hidden="true">${user.id}</span>
<span id="language" hidden="true"><spring:message code="label.localeCalendar" /></span>
<spring:url value="/resources/js/users/show.js" var="userShowJS" />
<script type="text/javascript" src="${userShowJS}">
	
</script>
