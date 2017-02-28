<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>
<%@ page
	import="com.softserve.edu.schedule.controller.RegistrationController"%>

<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="lbl.form.deleteTitle" />
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<spring:message code="lbl.user.deleteConfirm" />
				</p>
			</div>
			<div class="modal-footer">
				<a class="btn btn-primary btn-ok"><spring:message
						code="lbl.form.delete" /></a>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="lbl.form.cancel" />
				</button>
			</div>
		</div>
	</div>
</div>

<h3 class="text-center">
	<spring:message code="lbl.user.title" />
</h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th></th>
			<th><spring:message code="lbl.user.name" /> <a
				href="${pageContext.request.contextPath}/users?sortByField=1&sortOrder=1&pageNumber=0"
				title="<spring:message code="lbl.user.sortAsc" />"><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="${pageContext.request.contextPath}/users?sortByField=1&sortOrder=2&pageNumber=0"
				title="<spring:message code="lbl.user.sortDesc" />"><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<sec:authorize
				access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR')">
				<th><spring:message code="lbl.user.mail" /></th>
			</sec:authorize>
			<sec:authorize
				access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR')">
				<th><spring:message code="lbl.user.phone" /></th>
			</sec:authorize>
			<th><spring:message code="lbl.user.position" /> <a
				href="${pageContext.request.contextPath}/users?sortByField=2&sortOrder=1&pageNumber=0"
				title="<spring:message code="lbl.user.sortAsc" />"><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="${pageContext.request.contextPath}/users?sortByField=2&sortOrder=2&pageNumber=0"
				title="<spring:message code="lbl.user.sortDesc" />"><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<sec:authorize
				access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR')">
				<th><spring:message code="lbl.user.role" /></th>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('ROLE_USER')">
				<th></th>
			</sec:authorize>
			<th><spring:message code="lbl.user.group" /></th>
			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
				<th></th>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
				<th></th>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
				<th></th>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
				<th><sec:authorize access="hasAnyRole('ROLE_ADMIN')">
						<a
							href="${pageContext.request.contextPath}/${RegistrationController.USER_REGIST_MAPPING_FOR_ADMIN}"><i
							class="fa fa-plus"></i></a>
					</sec:authorize></th>
			</sec:authorize>
		</tr>
		<tr>
			<td></td>
			<form:form action="${pageContext.request.contextPath}/users"
				modelAttribute="${UserController.FILTER_MODEL_ATTR}">
				<td><spring:message code="lbl.user.search" var="search" /> <form:input
						class="form-control" path="lastName" placeholder=" ${search}" />
				</td>
				<sec:authorize
					access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR')">
					<td></td>
				</sec:authorize>
				<sec:authorize
					access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR')">
					<td></td>
				</sec:authorize>
				<td><spring:message code="lbl.user.search" var="search" /> <form:input
						class="form-control" path="position" placeholder=" ${search}" />
				</td>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
					<td></td>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
					<td></td>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
					<td></td>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
					<td></td>
				</sec:authorize>
				<td class="text-center v-alighn">
					<button type="submit" class="btn btn-link"
						title="<spring:message code="lbl.room.applyFilter"/>">
						<i class="fa fa-search fa-lg"></i>
					</button>
				</td>
			</form:form>
			<td class="text-center v-alighn"><a
				href="${pageContext.request.contextPath}/users?lastName=&position="
				title="<spring:message code="lbl.room.resetFilter"/>"> <i
					class="fa fa-times fa-lg"></i>
			</a></td>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td></td>
				<td><a
					href="${pageContext.request.contextPath}/${UserController.USER_PROFILE_MAPPING}${user.id}">${user.lastName}
						${user.firstName}</a></td>
				<sec:authorize
					access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR')">
					<td>${user.mail}</td>
				</sec:authorize>
				<sec:authorize
					access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR')">
					<td>${user.phone}</td>
				</sec:authorize>
				<td>${user.position}</td>
				<sec:authorize
					access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR')">
					<td><spring:message code="lbl.user.${user.role.getRole()}" />
					</td>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_USER')">
					<td></td>
				</sec:authorize>
				<td><c:forEach items="${user.groups}" var="group">
						<p>${group.name}</p>
					</c:forEach></td>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
					<td class="text-center v-alighn"><a
						data-href="${pageContext.request.contextPath}${UserController.DELETE_USER_MAPPING}${user.id}"
						data-toggle="modal" data-target="#confirm-delete"
						title="<spring:message code="lbl.user.delete" />"><i
							class="fa fa-trash-o"></i></a></td>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
					<td class="text-center v-alighn"><a
						href="${pageContext.request.contextPath}${UserController.CHANGE_ROLE_MAPPING}${user.id}">
							<i class="fa fa-pencil-square-o"></i>
					</a></td>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
					<td class="text-center v-alighn"><c:if
							test="${user.status.ordinal() == 1}">
							<a
								href="${pageContext.request.contextPath}${UserController.BAN_USER_MAPPING}${user.id}">
								<i class="fa fa-ban"></i>
							</a>
						</c:if></td>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
					<td class="text-center v-alighn"><c:if
							test="${user.status.ordinal() == 2 or user.status.ordinal() == 0}">
							<a
								href="${pageContext.request.contextPath}${UserController.UNBAN_USER_MAPPING}${user.id}">
								<i class="fa fa-check-circle-o"></i>
							</a>
						</c:if></td>
				</sec:authorize>
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
			<c:when test="${userPaginator.pageSize eq 5}">
				<a class="btn btn-primary" href="#">5</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default"
					href="${pageContext.request.contextPath}/users?pageSize=5&pageNumber=0">5</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${userPaginator.pageSize eq 10}">
				<a class="btn btn-primary" href="#">10</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default"
					href="${pageContext.request.contextPath}/users?pageSize=10&pageNumber=0">10</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${userPaginator.pageSize eq 20}">
				<a class="btn btn-primary" href="#">20</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default"
					href="${pageContext.request.contextPath}/users?pageSize=20&pageNumber=0">20</a>
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
        totalPages: ${userPaginator.pagesCount + 1},
        startPage: ${userPaginator.pageNumber + 1},
        visiblePages: 10,
        first : '&lt;&lt;',
    	last : '&gt;&gt;',
    	prev : '&lt;',
    	next : '&gt;',
        initiateStartPageClick: false,        
        onPageClick: function (event, page) {
        	window.location = "users?pageNumber=" + (page-1);        	
        }
    });
</script>