<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page
	import="com.softserve.edu.schedule.controller.UserGroupController"%>

<h3 class="text-center">
	<spring:message code="lbl.group.title" />
</h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th><spring:message code="lbl.group.name" /> <a
				href="usergroups?fieldForSorting=1&sortOrder=0&pageNumber=0"
				title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
			</a> <a href="usergroups?fieldForSorting=1&sortOrder=1&pageNumber=0"
				title="Sort Descending"> <i
					class="fa fa-arrow-circle-o-down fa-lg"></i>
			</a></th>

			<th><spring:message code="lbl.group.curator" /></th>

			<th><spring:message code="lbl.group.level" /> <a
				href="usergroups?fieldForSorting=3&sortOrder=0&pageNumber=0"
				title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
			</a> <a href="usergroups?fieldForSorting=3&sortOrder=1&pageNumber=0"
				title="Sort Descending"> <i
					class="fa fa-arrow-circle-o-down fa-lg"></i>
			</a></th>

			<th><spring:message code="lbl.group.members" /> <a
				href="usergroups?fieldForSorting=4&sortOrder=0&pageNumber=0"
				title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
			</a> <a href="usergroups?fieldForSorting=4&sortOrder=1&pageNumber=0"
				title="Sort Descending"> <i
					class="fa fa-arrow-circle-o-down fa-lg"></i>
			</a></th>

			<th></th>

			<th><sec:authorize
					access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR', 'ROLE_SUPERVISOR')">
					<a href="${pageContext.request.contextPath}/usergroups/create"><i
						class="fa fa-plus fa-lg"></i></a>
				</sec:authorize></th>
		</tr>

		<!-- FILTER -->
		<tr>
			<form:form action="${pageContext.request.contextPath}/usergroups"
				modelAttribute="${UserGroupController.FILTER_MODEL_ATTR}">
				<td><spring:message code="lbl.group.search" var="search" /> <form:input
						class="form-control" path="name" placeholder=" ${search}" /></td>

				<td><form:select class="form-control" path="curatorId">
						<spring:message code="lbl.group.selectCurator" var="curatorLabel" />
						<option value="">${curatorLabel}</option>
						<c:forEach items="${curators}" var="curator">
							<c:choose>
								<c:when test="${usergroupFilter.curatorId eq curator.id}">
									<option value="${curator.id}" selected="selected">${curator.firstName}
										${curator.lastName}</option>
								</c:when>
								<c:otherwise>
									<option value="${curator.id}">${curator.firstName}
										${curator.lastName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>

				<td><form:select class="form-control" path="levelId">
						<spring:message code="lbl.group.selectLevel" var="levelLabel" />
						<option value="">${levelLabel}</option>
						<c:forEach items="${levels}" var="level">
							<c:choose>
								<c:when test="${usergroupFilter.levelId eq level.ordinal()}">
									<option value="${level.ordinal()}" selected="selected"><spring:message
											code="lbl.group.${level.code}" /></option>
								</c:when>
								<c:otherwise>
									<option value="${level.ordinal()}"><spring:message
											code="lbl.group.${level.code}" /></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
				<td></td>

				<td class="text-center v-alighn">
					<button type="submit" class="btn btn-link"
						title="<spring:message code="lbl.room.applyFilter"/>">
						<i class="fa fa-search fa-lg"></i>
					</button>
				</td>
			</form:form>
			<td class="v-alighn"><a
				href="${pageContext.request.contextPath}/usergroups?name=&curatorId=&levelId="
				title="<spring:message code="lbl.room.resetFilter"/>"> <i
					class="fa fa-times fa-lg"></i>
			</a></td>
		</tr>

		<!-- FILTER -->

		<c:forEach var="usergroup" items="${usergroups}">
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/usergroups/${usergroup.id}">${usergroup.name}</a></td>
				<td><a
					href="${pageContext.request.contextPath}/profile${usergroup.curator.id}">${usergroup.curator.lastName}
						${usergroup.curator.firstName}</a></td>
				<td><spring:message code="lbl.group.${usergroup.level.code}" /></td>
				<td>${usergroup.users.size()}</td>

				<td><sec:authorize
						access="hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR', 'ROLE_MODERATOR')">
						<a
							href="${pageContext.request.contextPath}/usergroups/delete/${usergroup.id}"
							onclick="return confirm('<spring:message code="lbl.group.deleteGroupConfirm"/>');"><i
							class="fa fa-trash-o fa-lg"></i></a>
					</sec:authorize></td>

				<td><sec:authorize
						access="hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR', 'ROLE_MODERATOR')">
						<a
							href="${pageContext.request.contextPath}/usergroups/edit/${usergroup.id}"><i
							class="fa fa-pencil-square-o fa-lg"></i></a>
					</sec:authorize></td>
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
			<c:when test="${usergroupPaginator.pageSize eq 5}">
				<a class="btn btn-primary" href="usergroups?pageSize=5&pageNumber=0">5</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default" href="usergroups?pageSize=5&pageNumber=0">5</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${usergroupPaginator.pageSize eq 10}">
				<a class="btn btn-primary"
					href="usergroups?pageSize=10&pageNumber=0">10</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default"
					href="usergroups?pageSize=10&pageNumber=0">10</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${usergroupPaginator.pageSize eq 20}">
				<a class="btn btn-primary"
					href="usergroups?pageSize=20&pageNumber=0">20</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default"
					href="usergroups?pageSize=20&pageNumber=0">20</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="col-md-10 text-center">
		<ul id="paginationList" class="pagination"></ul>
	</div>
</div>
<script>
$('#paginationList').twbsPagination({
    totalPages: ${usergroupPaginator.pagesCount + 1},
    startPage: ${usergroupPaginator.pageNumber + 1},
    visiblePages: 10,
    first: '&lt;&lt;&lt;&lt;',
    last: '&gt;&gt;&gt;&gt;',
    prev: '&lt;&lt;',
    next: '&gt;&gt;',
    initiateStartPageClick: false,        
    onPageClick: function (event, page) {
    	window.location = "usergroups?pageNumber=" + (page-1);        	
    }
});
</script>