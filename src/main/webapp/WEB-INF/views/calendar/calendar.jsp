<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="row">
	<div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2"
		id='calendar'></div>
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
					userId : '<sec:authentication property="principal.id" />'
				}
			}

		})

	});
</script>