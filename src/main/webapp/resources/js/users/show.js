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