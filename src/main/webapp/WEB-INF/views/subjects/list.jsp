<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page import="com.softserve.edu.schedule.controller.constants.SubjectControllerConst"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
					<spring:message code="lbl.subject.deleteConfirm" />
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
	<spring:message code="lbl.subject.title" />
</h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th>ID</th>
			<th>
				<spring:message code="lbl.subject.name" />
				<a
					href="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECTS_MAPPING}?sortByField=1&sortOrder=1&pageNumber=0"
					title="<spring:message code="lbl.subject.sortAsc"/>">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a
					href="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECTS_MAPPING}?sortByField=1&sortOrder=2&pageNumber=0"
					title="<spring:message code="lbl.subject.sortDesc"/>">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th>
				<spring:message code="lbl.subject.description" />
				<a
					href="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECTS_MAPPING}?sortByField=2&sortOrder=1&pageNumber=0"
					title="<spring:message code="lbl.subject.sortAsc"/>">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a
					href="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECTS_MAPPING}?sortByField=2&sortOrder=2&pageNumber=0"
					title="<spring:message code="lbl.subject.sortDesc"/>">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th>
				<spring:message code="lbl.subject.tutor" />
			</th>
			<th></th>
			<th class="text-center v-alighn">
				<sec:authorize access="${SubjectControllerConst.HAS_ANY_ROLE_EXEPT_USER}">
					<a href="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECT_CREATE_MAPPING}"
						title="<spring:message code="lbl.subject.add"/>">
						<i class="fa fa-plus fa-lg"></i>
					</a>
				</sec:authorize>
			</th>
		</tr>
		<tr>
			<form:form action="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECTS_MAPPING}"
				modelAttribute="${SubjectControllerConst.FILTER_MODEL_ATTR}">
				<td></td>
				<td>
					<spring:message code="lbl.subject.search" var="search" />
					<form:input class="form-control" path="${SubjectControllerConst.SUBJECT_PATH_NAME}"
						placeholder=" ${search}" />
				</td>
				<td>
					<spring:message code="lbl.subject.search" var="search" />
					<form:input class="form-control" path="${SubjectControllerConst.SUBJECT_PATH_DESCRIPTION}"
						placeholder="${search}" />
				</td>
				<td>
					<form:select class="form-control" path="${SubjectControllerConst.SUBJECT_PATH_USER_ID}" id="${SubjectControllerConst.SUBJECT_PATH_USER_ID}">
					<spring:message code="lbl.subject.selectTutor" var="tutor" />
						<option value="0">${tutor}</option>
						<c:forEach items="${users}" var="user">
							<c:choose>
								<c:when test="${subjectFilter.userId eq user.id}">
									<option value="${user.id}" selected="selected">${user.firstName} ${user.lastName}</option>
								</c:when>
								<c:otherwise>
									<option value="${user.id}">${user.firstName} ${user.lastName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</td>
				<td class="text-center v-alighn">
					<button type="submit" class="btn btn-link"
						title="<spring:message code="lbl.room.applyFilter"/>">
						<i class="fa fa-search"></i>
					</button>
				</td>
			</form:form>
			<td class="text-center v-alighn">
				<a
					href="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECTS_MAPPING}?name=&description=&userId=0"
					title="<spring:message code="lbl.room.resetFilter"/>">
					<i class="fa fa-times fa-lg"></i>
				</a>
			</td>
		</tr>
		<c:forEach var="subject" items="${subjects}">
			<tr>
				<td>${subject.id}</td>
				<td><a href="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECTS_MAPPING_SHOW}${subject.id}">${subject.name}</a></td>
				<c:set var="string" value="${subject.description}"/>
				<c:set var="string2" value="${fn:substring(string, 0, 25)}..." />
				<c:if test="${string.length() < 20 }">
				<td>${string}</td>
				</c:if>
				<c:if test="${string.length() > 20 }">
				<td>${string2}</td>
				</c:if>
				<td>
					<c:forEach items="${subject.users}" var="user">
						<p>${user.firstName} ${user.lastName}</p>
					</c:forEach>
				</td>
				<td class="text-center v-alighn">
					<sec:authorize access="${SubjectControllerConst.HAS_ANY_ROLE_EXEPT_USER}">
						<a
							data-href="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECT_DELETE_MAPPING}${subject.id}"
							title="<spring:message code="lbl.subject.delete"/>"
							data-toggle="modal" data-target="#confirm-delete">
							<i class="fa fa-trash-o fa-lg"></i>
						</a>
					</sec:authorize>
				</td>
				<td class="text-center v-alighn">
					<sec:authorize access="${SubjectControllerConst.HAS_ANY_ROLE_EXEPT_USER}">
						<a
							href="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECT_EDIT_MAPPING}${subject.id}"
							title="<spring:message code="lbl.subject.edit"/>">
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
			<c:when test="${subjectPaginator.pageSize eq 5}">
				<a class="btn btn-primary" href="#">5</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default"
					href="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECTS_MAPPING}?pageSize=5&pageNumber=0">5</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${subjectPaginator.pageSize eq 10}">
				<a class="btn btn-primary" href="#">10</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default"
					href="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECTS_MAPPING}?pageSize=10&pageNumber=0">10</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${subjectPaginator.pageSize eq 20}">
				<a class="btn btn-primary" href="#">20</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default"
					href="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECTS_MAPPING}?pageSize=20&pageNumber=0">20</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="col-md-10 text-center">
		<ul id="paginationList" class="pagination"></ul>
	</div>
</div>
<spring:url value="/resources/js/subjects/list.js" var="subjectsListJS" />
<script>
var totalPages = ${subjectPaginator.pagesCount + 1}
var startPage = ${subjectPaginator.pageNumber + 1}
</script>
<script type="text/javascript" src="${subjectsListJS}">	
</script>