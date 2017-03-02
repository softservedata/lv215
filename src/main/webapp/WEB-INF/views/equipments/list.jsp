<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
					<spring:message code="lbl.roomequipment.deleteConfirm" />
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
<div class="row">
	<h3 class="text-center">
		<spring:message code="lbl.roomequipment.title" />
	</h3>
	<div
		class="table-responsive col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">
		<table class="table table-hover">
			<tr>
				<th class="text-center v-alighn">
					<spring:message code="lbl.roomequipment.id" />
				</th>
				<th class="text-center v-alighn">
					<spring:message code="lbl.roomequipment.name" />
					<a href="roomequipments?sortByField=1&sortOrder=1&pageNumber=0"
						title="<spring:message code="lbl.room.sortAsc"/>">
						<i class="fa fa-arrow-circle-o-up fa-lg"></i>
					</a>
					<a href="roomequipments?sortByField=1&sortOrder=2&pageNumber=0"
						title="<spring:message code="lbl.room.sortDesc"/>">
						<i class="fa fa-arrow-circle-o-down fa-lg"></i>
					</a>
				</th>
				<th class="text-center v-alighn">
					<button class="btn btn-link" data-toggle="collapse" data-target="#showfilter"
						title="<spring:message code="lbl.room.showFilter"/>">
						<i class="fa fa-filter fa-lg"></i>
					</button>
				</th>
				<th class="text-center v-alighn">
					<a href="${pageContext.request.contextPath}/roomequipments/create"
						title="<spring:message code="lbl.roomequipment.addEquipment" />">
						<i class="fa fa-plus fa-lg"></i>
					</a>
				</th>
			</tr>
			<tr>
				<c:choose>
					<c:when test="${roomEquipmentFilter.showFilter eq true}">
						<tr class="collapse in" id="showfilter">
					</c:when>
					<c:otherwise>
						<tr class="collapse" id="showfilter">
					</c:otherwise>
				</c:choose>
				<form:form role="form" id="filterForm" action="roomequipments" method="get"
					modelAttribute="roomEquipmentFilter">
					<form:input path="showFilter" type="hidden" value="true" />
					<td></td>
					<td>
						<div class="form-group">
							<spring:message code="lbl.roomequipment.name" var="nameForPlaceholder" />
							<form:input class="form-control input-sm input-name" type="text" path="name"
								placeholder="${nameForPlaceholder}" />
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
					<a href="roomequipments?showFilter=false&name=&pageNumber=0&sortOrder=0"
						title="<spring:message code="lbl.room.resetFilter"/>">
						<i class="fa fa-ban fa-lg"></i>
					</a>
				</td>
			</tr>
			<c:forEach var="equipment" items="${equipments}">
				<tr>
					<td>${equipment.id}</td>
					<td>${equipment.name}</td>
					<td class="text-center v-alighn">
						<a data-href="${pageContext.request.contextPath}/roomequipments/delete/${equipment.id}"
							data-toggle="modal" data-target="#confirm-delete"
							title="<spring:message code="lbl.roomequipment.deleteEquipment"/>">
							<i class="fa fa-trash-o fa-lg"></i>
						</a>
					</td>
					<td class="text-center v-alighn">
						<a href="${pageContext.request.contextPath}/roomequipments/edit/${equipment.id}"
							title="<spring:message code="lbl.roomequipment.editEquipment"/>">
							<i class="fa fa-pencil-square-o fa-lg"></i>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<div class="row">
	<div class="col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">
		<div class="col-md-4">
			<p>
				<spring:message code="lbl.form.resPerPage" />
			</p>
			<c:choose>
				<c:when test="${roomEquipmentPaginator.pageSize eq 5}">
					<a class="btn btn-primary" href="#">5</a>
				</c:when>
				<c:otherwise>
					<a class="btn btn-default" href="roomequipments?pageSize=5&pageNumber=0">5</a>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${roomEquipmentPaginator.pageSize eq 10}">
					<a class="btn btn-primary" href="#">10</a>
				</c:when>
				<c:otherwise>
					<a class="btn btn-default" href="roomequipments?pageSize=10&pageNumber=0">10</a>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${roomEquipmentPaginator.pageSize eq 20}">
					<a class="btn btn-primary" href="#">20</a>
				</c:when>
				<c:otherwise>
					<a class="btn btn-default" href="roomequipments?pageSize=20&pageNumber=0">20</a>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="col-md-8 text-center">
			<ul id="paginationList" class="pagination"></ul>
		</div>
	</div>
</div>

<spring:url value="/resources/js/equipments/list.js" var="equipmentsListJS" />
<script>
	var totalPages = ${roomEquipmentPaginator.pagesCount + 1}
	var startPage = ${roomEquipmentPaginator.pageNumber + 1}
</script>
<script type="text/javascript" src="${equipmentsListJS}">
	
</script>