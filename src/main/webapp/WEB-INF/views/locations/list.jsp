<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page
	import="com.softserve.edu.schedule.controller.LocationController"%>

<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel"><spring:message code="lbl.form.deleteTitle" /></h4>
			</div>
			<div class="modal-body">
				<p>
					<spring:message code="lbl.location.deleteConfirm" />
				</p>
			</div>
			<div class="modal-footer">
				<a class="btn btn-primary btn-ok"><spring:message code="lbl.form.delete" /></a>
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="lbl.form.cancel" /></button>
			</div>
		</div>
	</div>
</div>

<h3 class="text-center">
	<spring:message code="lbl.location.title" />
</h3>
<div class="table-responsive">
	<table class="table table-hover">

		<tr>
			<th>
				<spring:message code="lbl.location.name" />
				<a
					href="${pageContext.request.contextPath}${LocationController.LOCATIONS_MAPPING}?sortByField=1&sortOrder=1&pageNumber=0"
					title="<spring:message code="lbl.location.sortAsc" />">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a
					href="${pageContext.request.contextPath}${LocationController.LOCATIONS_MAPPING}?sortByField=1&sortOrder=2&pageNumber=0"
					title="<spring:message code="lbl.location.sortDesc" />">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th>
				<spring:message code="lbl.location.address" />
				<a
					href="${pageContext.request.contextPath}${LocationController.LOCATIONS_MAPPING}?sortByField=2&sortOrder=1&pageNumber=0"
					title="<spring:message code="lbl.location.sortAsc" />">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a
					href="${pageContext.request.contextPath}${LocationController.LOCATIONS_MAPPING}?sortByField=2&sortOrder=2&pageNumber=0"
					title="<spring:message code="lbl.location.sortDesc" />">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th>
				<spring:message code="lbl.location.coordinates" />
			</th>
			<th>
				<spring:message code="lbl.location.rooms" />
				<a
					href="${pageContext.request.contextPath}${LocationController.LOCATIONS_SORT_BY_COUNT_ROOM_ASC_MAPPING}"
					title="<spring:message code="lbl.location.sortAsc" />">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a
					href="${pageContext.request.contextPath}${LocationController.LOCATIONS_SORT_BY_COUNT_ROOM_DESC_MAPPING}"
					title="<spring:message code="lbl.location.sortDesc" />">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th></th>
			<th>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
					<a href="${pageContext.request.contextPath}${LocationController.LOCATION_CREATE_MAPPING}"
						title="<spring:message code="lbl.location.add" />">
						<i class="fa fa-plus fa-lg"></i>
					</a>
				</sec:authorize>
			</th>
		</tr>

		<tr>
			<form:form action="${pageContext.request.contextPath}${LocationController.LOCATIONS_MAPPING}"
				modelAttribute="${LocationController.FILTER_MODEL_ATTR}">
				<td>
					<spring:message code="lbl.location.search" var="search" />
					<form:input class="form-control" path="name" placeholder=" ${search}" />
				</td>
				<td>
					<spring:message code="lbl.location.search" var="search" />
					<form:input class="form-control" path="address" placeholder=" ${search}" />
				</td>
				<td></td>
				<td></td>
				<td class="text-center v-alighn">
					<button type="submit" class="btn btn-link"
						title="<spring:message code="lbl.room.applyFilter"/>">
						<i class="fa fa-search fa-lg"></i>
					</button>
				</td>
			</form:form>
			<td class="v-alighn">
				<a
					href="${pageContext.request.contextPath}${LocationController.LOCATIONS_MAPPING}?name=&address="
					title="<spring:message code="lbl.room.resetFilter"/>">
					<i class="fa fa-times fa-lg"></i>
				</a>
			</td>
		</tr>


		<c:forEach var="location" items="${locations}">
			<tr>
				<td>${location.name}</td>
				<td>${location.address}</td>
				<td>
					<a
						href="${pageContext.request.contextPath}${LocationController.LOCATION_MAP_MAPPING}${location.id}">
						<spring:message code="lbl.location.map" />
						<i class="fa fa-map-o"></i>
					</a>
				</td>
				<td>
					<div class="tooltip3">${location.rooms.size()}
						<c:if test="${location.rooms.size() != 0}">
							<span class="tooltiptext3"> <c:forEach items="${location.rooms}" var="room">
									<a href="${pageContext.request.contextPath}/rooms/${room.id}">${room.name}</a>
								</c:forEach>
							</span>
						</c:if>
					</div>
				</td>
				<td class="text-center v-alighn">
					<c:if test="${location.rooms.size() == 0}">
						<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
							<a
								data-href="${pageContext.request.contextPath}${LocationController.LOCATION_DELETE_MAPPING}${location.id}"
								data-toggle="modal" data-target="#confirm-delete"
								title="<spring:message code="lbl.location.delete" />"><i
								class="fa fa-trash-o fa-lg"></i></a>
						</sec:authorize>
					</c:if>
				</td>
				<td>
					<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
						<a
							href="${pageContext.request.contextPath}${LocationController.LOCATION_EDIT_MAPPING}${location.id}"
							title="<spring:message code="lbl.location.edit" />">
							<i class="fa fa-pencil-square-o fa-lg"></i>
						</a>
					</sec:authorize>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<div class="row">
	<div class="col-md-2">
		<p>
			<spring:message code="lbl.form.resPerPage" />
		</p>
		<c:choose>
			<c:when test="${locationPaginator.pageSize eq 5}">
				<a class="btn btn-primary" href="#">5</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default"
					href="${pageContext.request.contextPath}${LocationController.LOCATIONS_MAPPING}?pageSize=5&pageNumber=0">5</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${locationPaginator.pageSize eq 10}">
				<a class="btn btn-primary" href="#">10</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default"
					href="${pageContext.request.contextPath}${LocationController.LOCATIONS_MAPPING}?pageSize=10&pageNumber=0">10</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${locationPaginator.pageSize eq 20}">
				<a class="btn btn-primary" href="#">20</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default"
					href="${pageContext.request.contextPath}${LocationController.LOCATIONS_MAPPING}?pageSize=20&pageNumber=0">20</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="col-md-10 text-center">
		<ul id="paginationList" class="pagination"></ul>
	</div>
</div>
<script>
$('#confirm-delete').on('show.bs.modal', function(e) {
    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
});

 $('#paginationList').twbsPagination({
        totalPages: ${locationPaginator.pagesCount + 1},
        startPage: ${locationPaginator.pageNumber + 1},
        visiblePages: 10,
        first: '<spring:message code="lbl.pager.first"/>',
        last: '<spring:message code="lbl.pager.last"/>',
        prev: '<spring:message code="lbl.pager.previous"/>',
        next: '<spring:message code="lbl.pager.next"/>',
        initiateStartPageClick: false,        
        onPageClick: function (event, page) {
        	window.location = "locations?pageNumber=" + (page-1);        	
        }
    });
</script>