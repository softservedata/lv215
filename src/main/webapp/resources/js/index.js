$(function() {
	$(".chosen-category").chosen({
		width : "100%"
	});
})
$(document).ready(function() {
	$(".chosen-category").val('0').trigger('chosen:updated');
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
	})
});

$("#ch1").change(function() {
	$(".chosen-category").not(this).val('0').trigger('chosen:updated');
	$('#calendar').fullCalendar('destroy');
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
			url : $("#context").text() + '/meetings/restByRoom',
			type : 'GET',
			data : {
				roomId : $(this).val()
			}
		}
	})
});

$("#ch2").change(function() {
	$(".chosen-category").not(this).val('0').trigger('chosen:updated');
	$('#calendar').fullCalendar('destroy');
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
			url : $("#context").text() + '/meetings/restBySubject',
			type : 'GET',
			data : {
				subjectId : $(this).val()
			}
		}
	})
});

$("#ch3").change(function() {
	$(".chosen-category").not(this).val('0').trigger('chosen:updated');
	$('#calendar').fullCalendar('destroy');
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
			url : $("#context").text() + '/meetings/restByGroup',
			type : 'GET',
			data : {
				groupId : $(this).val()
			}
		}
	})
});