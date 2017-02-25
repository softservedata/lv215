$('#confirm-delete').on('show.bs.modal', function(e) {
	$(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
});

$('#paginationList').twbsPagination({
	totalPages : totalPages,
	startPage : startPage,
	visiblePages : 10,
	first : $("#firstLabel").text(),
	last : $("#lastLabel").text(),
	prev : $("#previousLabel").text(),
	next : $("#nextLabel").text(),
	initiateStartPageClick : false,
	onPageClick : function(event, page) {
		window.location = "roomequipments?pageNumber=" + (page - 1);
	}
});