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
			url : $("#subjectRestURL").text(),
			type : 'GET',
			data : {
				subjectId : $("#subjectId").text()
			}
		}

	})
});
$('#confirm-delete').on('show.bs.modal', function(e) {
    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
});
$(function() {
	$(document).on(
			'change',
			':file',
			function() {
				var input = $(this), numFiles = input.get(0).files ? input
						.get(0).files.length : 1, label = input.val().replace(
						/\\/g, '/').replace(/.*\//, '');
				input.trigger('fileselect', [ numFiles, label ]);
			});
	$(document).ready(
			function() {
				$(':file').on(
						'fileselect',
						function(event, numFiles, label) {
							var input = $(this).parents('.input-group').find(
									':text'), log = numFiles > 1 ? numFiles
									+ ' files selected' : label;
							if (input.length) {
								input.val(log);
							} else {
								if (log)
									alert(log);
							}

						});
			});
});