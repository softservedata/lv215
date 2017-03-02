<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="row">
	<div class="col-lg-8 col-lg-offset-1 col-md-8 col-md-offset-1 col-sm-8 col-sm-offset-1"
		id='calendar'></div>
	<div class="col-lg-2 col-md-2 col-sm-2">
		<div id="myPieChartWeek"></div>
		<div id="myPieChartMonth"></div>
	</div>
</div>

<spring:message code="label.weekChartTitle" var="titleWeek" />
<span id="weekChartTitle" hidden="true">${titleWeek}</span>
<spring:message code="label.monthChartTitle" var="titleMonth" />
<span id="monthChartTitle" hidden="true">${titleMonth}</span>
<span id="userRestURL" hidden="true">${pageContext.request.contextPath}/meetings/restByUser</span>
<span id="userRestChart" hidden="true">${pageContext.request.contextPath}/meetings/restForChart</span>
<span id="userId" hidden="true"><sec:authentication property="principal.id" /></span>
<span id="language" hidden="true"><spring:message code="label.localeCalendar" /></span>
<spring:url value="/resources/js/calendar.js" var="calendarJS" />
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="${calendarJS}"></script>



