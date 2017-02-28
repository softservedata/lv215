/* Notification when deleting meeting.  */
$('#confirm-delete').on('show.bs.modal', function(e) {
	$(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
});

$(function() {
	$("select[name=subjectId]").chosen({
		width : "130px"
	});
	$("select[name=ownerId]").chosen({
		width : "150px"
	});
	$("select[name=roomId]").chosen({
		width : "130px"
	});
	$("select[name=groups]").chosen({
		width : "90px"
	});
	$("select[name=status]").chosen({
		width : "120px"
	});
})

$('#paginationList').twbsPagination({
	totalPages : totalPages,
	startPage : startPage,
	visiblePages : 10,
	first : '&lt;&lt;',
	last : '&gt;&gt;',
	prev : '&lt;',
	next : '&gt;',
	initiateStartPageClick : false,
	onPageClick : function(event, page) {
		window.location = "meetings?pageNumber=" + (page - 1);
	}
});
