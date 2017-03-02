<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page
	import="com.softserve.edu.schedule.controller.constants.UserGroupControllerConst"%>

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
					<spring:message code="lbl.group.deleteConfirm" />
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
				modelAttribute="${UserGroupControllerConst.FILTER_MODEL_ATTR}">
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
					href="${pageContext.request.contextPath}${UserGroupControllerConst.USERGROUP_USER_PROFILE_MAPPING}${usergroup.curator.id}">${usergroup.curator.lastName}
						${usergroup.curator.firstName}</a></td>
				<td><spring:message code="lbl.group.${usergroup.level.code}" /></td>
				<td>${usergroup.users.size()}</td>

				<td><sec:authorize
						access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
						<a
							data-href="${pageContext.request.contextPath}${UserGroupControllerConst.USERGROUPS_DELETE_MAPPING}${usergroup.id}"
							data-toggle="modal" data-target="#confirm-delete"
							title="<spring:message code="lbl.group.delete" />"><i
							class="fa fa-trash-o fa-lg"></i></a>
					</sec:authorize> <sec:authorize access="hasAnyRole('ROLE_MODERATOR')">
						<sec:authentication property="principal.id" var="principarid" />
						<c:if test="${principalid eq  usergroup.curator.id}">
							<a
								data-href="${pageContext.request.contextPath}${UserGroupControllerConst.USERGROUPS_DELETE_MAPPING}${usergroup.id}"
								data-toggle="modal" data-target="#confirm-delete"
								title="<spring:message code="lbl.group.delete" />"> <i
								class="fa fa-trash-o fa-lg"></i>
							</a>
						</c:if>
					</sec:authorize></td>

				<td><sec:authorize
						access="hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR')">
						<a
							href="${pageContext.request.contextPath}/usergroups/edit/${usergroup.id}"><i
							class="fa fa-pencil-square-o fa-lg"></i></a>
					</sec:authorize> <sec:authorize access="hasAnyRole('ROLE_MODERATOR')">
						<sec:authentication property="principal.id" var="principalid" />
						<c:if test="${principalid eq usergroup.curator.id}">
							<a
								href="${pageContext.request.contextPath}/usergroups/edit/${usergroup.id}"><i
								class="fa fa-pencil-square-o fa-lg"></i></a>
						</c:if>
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

<spring:url value="/resources/js/usergroups/list.js" var="userGroupsListJS" />
<script>
	var totalPages = ${usergroupPaginator.pagesCount + 1}
	var startPage = ${usergroupPaginator.pageNumber + 1}
	
</script>
<script type="text/javascript" src="${userGroupsListJS}">	
</script>