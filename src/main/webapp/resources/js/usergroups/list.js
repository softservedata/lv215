$('#confirm-delete').on('show.bs.modal', function(e) {
	$(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
});

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
		window.location = "usergroups?pageNumber=" + (page - 1);
	}
});