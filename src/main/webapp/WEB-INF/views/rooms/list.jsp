<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page import="com.softserve.edu.schedule.controller.RoomController"%>

<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="lbl.form.deleteTitle" />
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<spring:message code="lbl.room.deleteRoomConfirm" />
				</p>
			</div>
			<div class="modal-footer">
				<a class="btn btn-primary btn-ok">
					<spring:message code="lbl.form.delete" />
				</a>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="lbl.form.cancel" />
				</button>
			</div>
		</div>
	</div>
</div>

<h3 class="text-center">
	<spring:message code="lbl.room.title" />
</h3>

<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th class="v-alighn">
				<spring:message code="lbl.room.roomName" />
				<a href="rooms?sortByField=2&sortOrder=1&pageNumber=0"
					title="<spring:message code="lbl.room.sortAsc"/>">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a href="rooms?sortByField=2&sortOrder=2&pageNumber=0"
					title="<spring:message code="lbl.room.sortDesc"/>">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th class="v-alighn">
				<spring:message code="lbl.room.location" />
				<a href="rooms?sortByField=1&sortOrder=1&pageNumber=0"
					title="<spring:message code="lbl.room.sortAsc"/>">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a href="rooms?sortByField=1&sortOrder=2&pageNumber=0"
					title="<spring:message code="lbl.room.sortDesc"/>">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th class="v-alighn">
				<spring:message code="lbl.room.roomCapacity" />
				<a href="rooms?sortByField=3&sortOrder=1&pageNumber=0"
					title="<spring:message code="lbl.room.sortAsc"/>">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a href="rooms?sortByField=3&sortOrder=2&pageNumber=0"
					title="<spring:message code="lbl.room.sortDesc"/>">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th class="v-alighn">
				<spring:message code="lbl.room.roomEquipments" />
			</th>
			<th class="text-center v-alighn">
				<button class="btn btn-link" data-toggle="collapse" data-target="#showfilter"
					title="<spring:message code="lbl.room.showFilter"/>">
					<i class="fa fa-filter fa-lg"></i>
				</button>
			</th>
			<th class="text-center v-alighn">
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
					<a href="${RoomController.ROOM_CREATE_URL}"
						title="<spring:message code="lbl.room.createRoom"/>">
						<i class="fa fa-plus fa-lg"></i>
					</a>
				</sec:authorize>
			</th>
		</tr>
		<tr>
			<c:choose>
				<c:when test="${roomFilter.showFilter eq true}">
					<tr class="collapse in" id="showfilter">
				</c:when>
				<c:otherwise>
					<tr class="collapse" id="showfilter">
				</c:otherwise>
			</c:choose>
			<form:form role="form" id="filterForm" action="rooms" method="get"
				modelAttribute="${RoomController.FILTER_MODEL_ATTR}">
				<form:input path="showFilter" type="hidden" value="true" />
				<td>
					<div class="form-group">
						<spring:message code="lbl.room.roomName" var="nameForPlaceholder" />
						<form:input class="form-control input-sm input-name" type="text" path="name"
							placeholder="${nameForPlaceholder}" />
					</div>
				</td>
				<td>
					<div class="form-group">
						<form:select class="form-control" path="locationId" id="locationId">
							<option value="0"></option>
							<c:forEach items="${locations}" var="location">
								<c:choose>
									<c:when test="${roomFilter.locationId eq location.id}">
										<option value="${location.id}" selected="selected">${location.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${location.id}">${location.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
					</div>
				</td>
				<td>
					<div class="form-group">
						<spring:message code="lbl.room.filterMinCapacity" var="filterMinCapacity" />
						<form:input type="number" class="input-sm input-number" path="minCapacity" step="1"
							required="true" title="${filterMinCapacity}" />
						<spring:message code="lbl.room.filterMaxCapacity" var="filterMaxCapacity" />
						<form:input type="number" class="input-sm input-number" path="maxCapacity" step="1"
							required="true" title="${filterMaxCapacity}" />
					</div>
				</td>
				<td>
					<div class="form-group">
						<form:select class="form-control" path="equipments" id="equipments" multiple="multiple">
							<c:forEach items="${equipments}" var="equipment">
								<c:set var="found" value="false" />
								<c:forEach items="${roomFilter.equipments}" var="equipmentInFilter">
									<c:if test="${!found}">
										<c:if test="${equipmentInFilter.id eq equipment.id}">
											<option value="${equipment.id}" selected="selected">${equipment.name}</option>
											<c:set var="found" value="true" />
										</c:if>
									</c:if>
								</c:forEach>
								<c:if test="${!found}">
									<option value="${equipment.id}">${equipment.name}</option>
								</c:if>
							</c:forEach>
						</form:select>
					</div>
				</td>
				<td class="text-center v-alighn">
					<button type="submit" class="btn btn-link"
						title="<spring:message code="lbl.room.applyFilter"/>">
						<i class="fa fa-check-circle-o fa-lg"></i>
					</button>
				</td>
			</form:form>
			<td class="text-center v-alighn">
				<a
					href="rooms?showFilter=false&locationId=0&name=&minCapacity=0&maxCapacity=0&_equipments=1&pageNumber=0&sortOrder=0"
					title="<spring:message code="lbl.room.resetFilter"/>">
					<i class="fa fa-ban fa-lg"></i>
				</a>
			</td>
		</tr>
		<c:forEach items="${rooms}" var="room">
			<tr>
				<td>
					<a href="rooms/${room.id}">${room.name}</a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/locations/map/${room.location.id}">
						${room.location.name}</a>
				</td>
				<td>${room.capacity}</td>
				<td>
					<ul>
						<c:forEach items="${room.equipments}" var="equipment">
							<li>${equipment.name}</li>
						</c:forEach>
					</ul>
				</td>
				<td class="text-center v-alighn">
					<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
						<a data-href="rooms/delete/${room.id}" data-toggle="modal" data-target="#confirm-delete"
							title="<spring:message code="lbl.room.deleteRoom"/>">
							<i class="fa fa-trash-o fa-lg"></i>
						</a>
					</sec:authorize>
				</td>
				<td class="text-center v-alighn">
					<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
						<a href="rooms/edit/${room.id}" title="<spring:message code="lbl.room.editRoom"/>">
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
			<c:when test="${roomPaginator.pageSize eq 5}">
				<a class="btn btn-primary" href="#">5</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default" href="rooms?pageSize=5&pageNumber=0">5</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${roomPaginator.pageSize eq 10}">
				<a class="btn btn-primary" href="#">10</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default" href="rooms?pageSize=10&pageNumber=0">10</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${roomPaginator.pageSize eq 20}">
				<a class="btn btn-primary" href="#">20</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default" href="rooms?pageSize=20&pageNumber=0">20</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="col-md-10 text-center">
		<ul id="paginationList" class="pagination"></ul>
	</div>
</div>

<span id="firstLabel" hidden="true"><spring:message code="lbl.pager.first" /></span>
<span id="lastLabel" hidden="true"><spring:message code="lbl.pager.last" /></span>
<span id="previousLabel" hidden="true"><spring:message code="lbl.pager.previous" /></span>
<span id="nextLabel" hidden="true"><spring:message code="lbl.pager.next" /></span>
<spring:url value="/resources/js/rooms/list.js" var="roomsListJS" />
<script>
	var totalPages = ${roomPaginator.pagesCount + 1}
	var startPage = ${roomPaginator.pageNumber + 1}
</script>
<script type="text/javascript" src="${roomsListJS}">
	
</script>