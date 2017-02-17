<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="row">
	<div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2"
		id='calendar'></div>
</div>

<span id="userRestURL" hidden="true">${pageContext.request.contextPath}/meetings/restByUser</span>
<span id="userId" hidden="true"><sec:authentication property="principal.id" /></span>
<span id="language" hidden="true"><spring:message code="label.localeCalendar" /></span>
<spring:url value="/resources/js/calendar.js" var="calendarJS" />
<script type="text/javascript" src="${calendarJS}">
	
</script>