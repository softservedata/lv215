$('#confirm-delete').on('show.bs.modal', function(e) {
	$(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
});

$(function() {
	$("select[name=locationId]").chosen({
		width : "160px"
	});
	$("select[name=equipments]").chosen({
		width : "150px"
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
		window.location = "rooms?pageNumber=" + (page - 1);
	}
});