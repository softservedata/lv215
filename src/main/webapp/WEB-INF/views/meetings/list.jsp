<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page import="com.softserve.edu.schedule.controller.MeetingController"%>

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
					<spring:message code="lbl.room.deleteMeetingConfirm" />
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
	<spring:message code="lbl.meeting.all" />
</h3>


<div align="center">
	<h3>
		<a href="meetings/downloadExcel">Download Meeting History Excel file</a>
	</h3>
</div>


<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th>
				<spring:message code="lbl.meeting.id" />
				<br>
			</th>
			<th style="width: 120px">
				<spring:message code="lbl.meeting.subject" />
				<br>
				<a href="meetings?fieldForSorting=1&sortOrder=1&pageNumber=0" title="Sort Ascending">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a href="meetings?fieldForSorting=1&sortOrder=2&pageNumber=0" title="Sort Descending">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th style="width: 120px">
				<spring:message code="lbl.meeting.owner" />
				<br>
				<a href="meetings?fieldForSorting=2&sortOrder=1&pageNumber=0" title="Sort Ascending">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a href="meetings?fieldForSorting=2&sortOrder=2&pageNumber=0" title="Sort Descending">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>

			<th>
				<spring:message code="lbl.meeting.room" />
				<br>
				<a href="meetings?fieldForSorting=3&sortOrder=1&pageNumber=0" title="Sort Ascending">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a href="meetings?fieldForSorting=3&sortOrder=2&pageNumber=0" title="Sort Descending">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th>
				<spring:message code="lbl.meeting.date" />
				<br>
				<a href="meetings?fieldForSorting=4&sortOrder=1&pageNumber=0" title="Sort Ascending">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a href="meetings?fieldForSorting=4&sortOrder=2&pageNumber=0" title="Sort Descending">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th>
				<spring:message code="lbl.meeting.starttime" />
				<br>
				<a href="meetings?fieldForSorting=5&sortOrder=1&pageNumber=0" title="Sort Ascending">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a href="meetings?fieldForSorting=5&sortOrder=2&pageNumber=0" title="Sort Descending">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th>
				<spring:message code="lbl.meeting.endtime" />
				<br>
				<a href="meetings?fieldForSorting=6&sortOrder=1&pageNumber=0" title="Sort Ascending">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a href="meetings?fieldForSorting=6&sortOrder=2&pageNumber=0" title="Sort Descending">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th>
				<spring:message code="lbl.meeting.groups" />
			</th>
			<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR', 'ROLE_USER', 'ROLE_SUPERVISOR')">
				<th class="levelclass">
					<spring:message code="lbl.meeting.level" />
					<br>
					<a href="meetings?fieldForSorting=7&sortOrder=1&pageNumber=0" title="Sort Ascending">
						<i class="fa fa-arrow-circle-o-up fa-lg"></i>
					</a>
					<a href="meetings?fieldForSorting=7&sortOrder=2&pageNumber=0" title="Sort Descending">
						<i class="fa fa-arrow-circle-o-down fa-lg"></i>
					</a>
				</th>
			</sec:authorize>
			<th>
				<spring:message code="lbl.meeting.status" />
				<br>
				<a href="meetings?fieldForSorting=8&sortOrder=1&pageNumber=0" title="Sort Ascending">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a href="meetings?fieldForSorting=8&sortOrder=2&pageNumber=0" title="Sort Descending">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th></th>
			<th class="text-center v-alighn">
				<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR', 'ROLE_SUPERVISOR')">
					<a href="${pageContext.request.contextPath}/${MeetingController.MEETING_CREATE_URL}"
						title="<spring:message code="lbl.meeting.create" />">
						<i class="fa fa-plus fa-lg"></i>
					</a>
				</sec:authorize>
			</th>
		</tr>
		<tr>
			<!-- Filter form -->
			<form:form role="form" action="meetings" method="get" modelAttribute="meetingFilter">
				<spring:message code="lbl.meeting.id" var="meetingid" />
				<td>
					<form:input type="number" path="id" placeholder="${meetingid}" class="classid" step="1" min="1" />
				</td>
				<td>
					<form:select path="subjectId" id="subjectId" class="form-control">
						<option value="0"></option>
						<c:forEach items="${subjects}" var="subject">
							<c:choose>
								<c:when test="${meetingFilter.subjectId eq subject.id}">
									<option value="${subject.id}" selected="selected">${subject.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${subject.id}">${subject.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</td>
				<td>
					<form:select class="form-control" path="ownerId" id="ownerId">
						<option value="0"></option>
						<c:forEach items="${users}" var="owner">
							<c:choose>
								<c:when test="${meetingFilter.ownerId eq owner.id}">
									<option value="${owner.id}" selected="selected">${owner.lastName}
										${owner.firstName}</option>
								</c:when>
								<c:otherwise>
									<option value="${owner.id}">${owner.lastName}${owner.firstName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</td>
				<td>
					<form:select path="roomId" id="roomId">
						<option value="0"></option>
						<c:forEach items="${rooms}" var="room">
							<c:choose>
								<c:when test="${meetingFilter.roomId eq room.id}">
									<option value="${room.id}" selected="selected">${room.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${room.id}">${room.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</td>
				<td>
					<form:input class=" input-sm class-date" type="date" path="date" placeholder="YYYY-MM-DD" />
				</td>
				<td>
					<form:input class="classtime" type="time" path="startTime" id="startTime" placeholder="HH:MM" />
				</td>
				<td>
					<form:input class="classtime" type="time" path="endTime" id="endTime" placeholder="HH:MM" />
				</td>
				<td>
					<form:select path="groups" id="groups" multiple="multiple">
						<c:forEach items="${userGroups}" var="userGroup">
							<c:set var="found" value="false" />
							<c:forEach items="${meetingFilter.groups}" var="groupsInFilter">
								<c:if test="${!found}">
									<c:if test="${groupsInFilter.id eq userGroup.id}">
										<option value="${userGroup.id}" selected="selected">${userGroup.name}</option>
										<c:set var="found" value="true" />
									</c:if>
								</c:if>
							</c:forEach>
							<c:if test="${!found}">
								<option value="${userGroup.id}">${userGroup.name}</option>
							</c:if>
						</c:forEach>
					</form:select>
				</td>
				<sec:authorize
					access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR', 'ROLE_USER', 'ROLE_SUPERVISOR')">
					<td class="levelclass">
						<form:input type="number" class="input-number levelclass" path="level" min="1" max="5"
							step="1" />
					</td>
				</sec:authorize>
				<td>
					<form:select class="form-control" path="status" id="status">
						<option value="-1"></option>
						<c:forEach items="${meetingStatuses}" var="status">
							<c:choose>
								<c:when test="${meetingFilter.status eq status.ordinal()}">
									<option value="${status.ordinal()}" selected="selected">
										<spring:message code="${status.getMessageCode()}" /></option>
								</c:when>
								<c:otherwise>
									<option value="${status.ordinal()}"><spring:message
											code="${status.getMessageCode()}" /></option>
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
					href="meetings?sortOrder=0&id=&subjectId=0&ownerId=0&roomId=0&date=&startTime=&endTime=&_groups=1&level=&status=-1"
					title="<spring:message code="lbl.room.resetFilter"/>">
					<i class="fa fa-times fa-lg"></i>
				</a>
			</td>
		</tr>
		<c:forEach var="meeting" items="${meetings}">
			<tr>
				<td>
					<a href="${MeetingController.MEETINGS_MODEL_ATTR}/${meeting.id}"
						title="<spring:message code="lbl.meeting.details" />">${meeting.id}</a>
				</td>
				<td>
					<a
						href="${pageContext.request.contextPath}/${MeetingController.SUBJECTS_MODEL_ATTR}/
					${meeting.subject.id}">${meeting.subject.name}</a>
				</td>
				<td>
					<sec:authorize access="isAuthenticated()">
						<a
							href="${pageContext.request.contextPath}/
							${MeetingController.PROFILE_MAPPING}/${meeting.owner.id}">${meeting.owner.lastName}
							${meeting.owner.firstName}</a>
					</sec:authorize>
					<sec:authorize access="!isAuthenticated()">
						${meeting.owner.lastName}
						${meeting.owner.firstName}
						</sec:authorize>
				</td>
				<td>
					<a
						href="${pageContext.request.contextPath}/
					${MeetingController.ROOMS_MODEL_ATTR}/${meeting.room.id}">
						${meeting.room.name} (${meeting.room.location.name}) </a>
				</td>
				<td>${meeting.date}</td>
				<td>${meeting.startTime}</td>
				<td>${meeting.endTime}</td>
				<td>
					<c:forEach items="${meeting.groups}" var="group">
						<p>
							<a
								href="${pageContext.request.contextPath}/
								${MeetingController.USERGROUPS_MAPPING}/${group.id}">${group.name}</a>
						</p>
					</c:forEach>
				</td>
				<sec:authorize
					access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR', 'ROLE_USER', 'ROLE_SUPERVISOR')">
					<td>${meeting.level}</td>
				</sec:authorize>
				<td>
					<spring:message code="${meeting.status.getMessageCode()}" />
				</td>
				<td>
					<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR')">
						<c:if test="${ meeting.status.ordinal() != 2 && meeting.status.ordinal() != 4}">
							<a
								data-href="${pageContext.request.contextPath}/
							${MeetingController.DELETE_MAPPING}/${meeting.id}"
								data-toggle="modal" data-target="#confirm-delete"
								title="<spring:message code="lbl.meeting.delete" />">
								<i class="fa fa-trash-o fa-lg"></i>
							</a>
						</c:if>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_MODERATOR')">
						<sec:authentication property="principal.id" var="principarid" />
						<c:if
							test="${principarid eq  meeting.owner.id && meeting.status.ordinal() != 2 && meeting.status.ordinal() != 4}">
							<a
								data-href="${pageContext.request.contextPath}/
								${MeetingController.DELETE_MAPPING}/${meeting.id}"
								data-toggle="modal" data-target="#confirm-delete"
								title="<spring:message code="lbl.meeting.delete" />">
								<i class="fa fa-trash-o fa-lg"></i>
							</a>
						</c:if>
					</sec:authorize>
				</td>
				<td>
					<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR')">
						<c:if test="${ meeting.status.ordinal() != 2 && meeting.status.ordinal() != 4}">
							<a
								href="${pageContext.request.contextPath}/
							${MeetingController.MEETING_EDIT_URL}/${meeting.id}"
								title="<spring:message code="lbl.meeting.edit" />">
								<i class="fa fa-pencil-square-o fa-lg"></i>
							</a>
						</c:if>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_MODERATOR')">
						<sec:authentication property="principal.id" var="principarid" />
						<c:if
							test="${principarid eq  meeting.owner.id && meeting.status.ordinal() != 2 && meeting.status.ordinal() != 4 }">
							<a
								href="${pageContext.request.contextPath}/
								${MeetingController.MEETING_EDIT_URL}/${meeting.id}"
								title="<spring:message code="lbl.meeting.edit" />">
								<i class="fa fa-pencil-square-o fa-lg"></i>
							</a>
						</c:if>
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
			<c:when test="${meetingPaginator.pageSize eq 5}">
				<a class="btn btn-primary" href="meetings?pageSize=5&pageNumber=0">5</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default" href="meetings?pageSize=5&pageNumber=0">5</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${meetingPaginator.pageSize eq 10}">
				<a class="btn btn-primary" href="meetings?pageSize=10&pageNumber=0">10</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default" href="meetings?pageSize=10&pageNumber=0">10</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${meetingPaginator.pageSize eq 20}">
				<a class="btn btn-primary" href="meetings?pageSize=20&pageNumber=0">20</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default" href="meetings?pageSize=20&pageNumber=0">20</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="col-md-10 text-center">
		<ul id="paginationList" class="pagination"></ul>
	</div>
</div>


<c:set var="first">
	<spring:message code="lbl.pager.first" />
</c:set>
<input id="firstLabel" type="hidden" value="${first}" />
<c:set var="last">
	<spring:message code="lbl.pager.last" />
</c:set>
<input id="lastLabel" type="hidden" value="${last}" />
<c:set var="previous">
	<spring:message code="lbl.pager.previous" />
</c:set>
<input id="previousLabel" type="hidden" value="${previous}" />
<c:set var="next">
	<spring:message code="lbl.pager.next" />
</c:set>
<input id="nextLabel" type="hidden" value="${next}" />

<spring:url value="/resources/js/meetings/list.js" var="meetingsListJS" />
<script>
	var totalPages = ${meetingPaginator.pagesCount + 1}
	var startPage = ${meetingPaginator.pageNumber + 1}
</script>
<script type="text/javascript" src="${meetingsListJS}">
	
</script>

