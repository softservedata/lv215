$('#confirm-delete').on('show.bs.modal', function(e) {
    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
});

$('#paginationList').twbsPagination({
    totalPages: totalPages,
    startPage: startPage,
    visiblePages: 10,
    first: document.getElementById("firstLabel").value,
    last: document.getElementById("lastLabel").value,
    prev: document.getElementById("previousLabel").value,
    next: document.getElementById("nextLabel").value,
    initiateStartPageClick: false,        
    onPageClick: function (event, page) {
    	window.location = "usergroups?pageNumber=" + (page-1);        	
    }
});