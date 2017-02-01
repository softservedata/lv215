<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	$(function() {
		$("select[name=subjectId]").chosen({width : "100%"});
		$("select[name=ownerId]").chosen({width : "100%"});
		$("select[name=roomId]").chosen({width : "100%"});
		$("select[name=groups]").chosen({width : "100%"});
		$("select[name=status]").chosen({width : "100%"});
	})
</script>
<h3 class="text-center">
	<spring:message code="lbl.meeting.all" />
</h3>
<table class="table table-hover meetingsTable">
	<tr>
		<th><spring:message code="lbl.meeting.id" /> <br></th>

		<th class="allInnerForms1"><spring:message
				code="lbl.meeting.subject" /> <br> <a
			href="meetings?fieldForSorting=1&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=1&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a></th>
		<th class="allInnerForms1"><spring:message
				code="lbl.meeting.owner" /> <br> <a
			href="meetings?fieldForSorting=2&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=2&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a></th>

		<th><spring:message
				code="lbl.meeting.room" /> <br> <a
			href="meetings?fieldForSorting=3&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=3&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a></th>
		<th class="allInnerForms2"><spring:message
				code="lbl.meeting.date" /> <br> <a
			href="meetings?fieldForSorting=4&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=4&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a></th>
		<th><spring:message code="lbl.meeting.starttime" /><br> <a
			href="meetings?fieldForSorting=5&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=5&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a></th>
		<th><spring:message code="lbl.meeting.endtime" /><br> <a
			href="meetings?fieldForSorting=6&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=6&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a></th>
		<th class="allInnerForms1"><spring:message
				code="lbl.meeting.groups" /></th>
		<th><spring:message code="lbl.meeting.level" /> <br> <a
			href="meetings?fieldForSorting=7&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=7&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a></th>
		<th><spring:message code="lbl.meeting.status" /> <br> <a
			href="meetings?fieldForSorting=8&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=8&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a></th>
		<th></th>
		<th class="text-center v-alighn"><a
			href="${pageContext.request.contextPath}/meetings/create"><i
				class="fa fa-plus fa-lg"></i></a></th>
	</tr>

	<tr>
		<!-- Filter form -->

		<form:form role="form" action="meetings" method="get"
			modelAttribute="meetingFilter">
			<form:input path="showFilter" type="hidden" value="true" />

			<!-- ID -->
			<spring:message code="lbl.meeting.id" var="meetingid" />
			<td><form:input type="number" path="id" placeholder="${meetingid}" class="classid"
					step="1" /></td>


			<!-- subject -->
			<td><form:select class="form-control" path="subjectId"
					id="subjectId">
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
				</form:select></td>
			<!-- owner -->
			<td><form:select class="form-control allInnerForms"
					path="ownerId" id="ownerId">
					<option value="0"></option>
					<c:forEach items="${users}" var="owner">
						<c:choose>
							<c:when test="${meetingFilter.ownerId eq owner.id}">
								<option value="${owner.id}" selected="selected">${owner.lastName}
									${owner.firstName}</option>
							</c:when>
							<c:otherwise>
								<option value="${owner.id}">${owner.lastName}
									${owner.firstName}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select></td>
			<!-- room -->
			<td class="meetingroomclass"><form:select class="meetingroomclass"
					path="roomId" id="roomId">
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
				</form:select></td>
			<!-- date -->
			<td><form:input class=" input-sm allInnerForms2"
					type="date" path="date" placeholder="YYYY-MM-DD" /></td>
			<!-- Start Time -->
			<td><form:input class="classtime"
					type="time" path="startTime" id="startTime" placeholder="HH:MM" />
			</td>
			<!-- End Time -->
			<td><form:input class="classtime"
					type="time" path="endTime" id="endTime" placeholder="HH:MM" /></td>
			<!-- UserGroups -->
			<td><form:select class="meetinggroup" path="groups" id="groups"
					multiple="multiple">
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
				</form:select></td>
			<!-- Level -->
			<td class="levelclass"><form:input type="number"
					class="input-number levelclass" path="level" step="1"
					title="MIN" /></td>
			<!-- Status -->
			<td><form:select path="status" id="status">
					<option value="-1"></option>
					<c:forEach items="${meetingStatuses}" var="status">
						<c:choose>
							<c:when test="${meetingFilter.status eq status.ordinal()}">
								<option value="${status.ordinal()}" selected="selected"
									>${status}</option>
							</c:when>
							<c:otherwise>
								<option value="${status.ordinal()}">${status}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select></td>

			<td class="text-center v-alighn">
				<button type="submit" class="btn btn-link"
					title="<spring:message code="lbl.room.applyFilter"/>">
					<i class="fa fa-search"></i>
				</button>
			</td>
		</form:form>
		<td class="text-center v-alighn"><a
			href="meetings?showFilter=false&id=&subjectId=0&ownerId=0&roomId=0&date=&startTime=&endTime=&_groups=1&level=&status=-1"
			title="resetFilter"> <i class="fa fa-times fa-lg"></i>
		</a></td>
	</tr>
	<!-- End Filter form -->

	<c:forEach var="meeting" items="${meetings}">
		<tr>
			<td><a href="meetings/${meeting.id}">${meeting.id}</a></td>

			<td>${meeting.subject.name}</td>
			<td><a
				href="${pageContext.request.contextPath}/profile${meeting.owner.id}">${meeting.owner.lastName}
					${meeting.owner.firstName}</a></td>
			<td><a
				href="${pageContext.request.contextPath}/rooms/${meeting.room.id}">${meeting.room.name}</a></td>
			<td>${meeting.date}</td>
			<td>${meeting.startTime}</td>
			<td>${meeting.endTime}</td>
			<td><c:forEach items="${meeting.groups}" var="group">
					<p>${group.name}</p>
				</c:forEach></td>
			<td>${meeting.level}</td>
			<td>${meeting.status}</td>
			<td><a
				href="${pageContext.request.contextPath}/meetings/delete/${meeting.id}"
				onclick="return confirm('<spring:message code="lbl.room.deleteMeetingConfirm"/>');">
					<i class="fa fa-trash-o fa-lg"></i>
			</a></td>
			<td><a
				href="${pageContext.request.contextPath}/meetings/edit/${meeting.id}"><i
					class="fa fa-pencil-square-o fa-lg"></i></a></td>
		</tr>
	</c:forEach>
</table>
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
<script>
 $('#paginationList').twbsPagination({
        totalPages: ${meetingPaginator.pagesCount + 1},
        startPage: ${meetingPaginator.pageNumber + 1},
        visiblePages: 10,
        initiateStartPageClick: false,        
        onPageClick: function (event, page) {
        	window.location = "meetings?pageNumber=" + (page-1);        	
        }
    });
</script>