$(document).ready(function() {
	$('#calendar').fullCalendar({
		defaultView : 'agendaWeek',
		locale : $("#language").text(),
		header : {
			left : 'prev,next,today',
			center : 'title',
			right : 'month,agendaWeek,agendaDay,listWeek'
		},
		nowIndicator : true,
		navLinks : true,
		events : {
			url : $("#userRestURL").text(),
			type : 'GET',
			data : {
				userId : $("#userId").text()
			}
		}

	})

});