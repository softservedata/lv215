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
<h3 class="text-center">Meetings</h3>
<table class="table table-hover meetingsTable">
	<tr>
		<th style="width: 3%">ID <br></th>
		<!-- <th style="width: 15%">Description <br> <a
			href="meetings?fieldForSorting=0&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=0&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a>
		</th> -->
		<th class="allInnerForms1">Subject <br> <a
			href="meetings?fieldForSorting=1&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=1&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a>
		</th>
		<th class="allInnerForms1">Owner <br> <a
			href="meetings?fieldForSorting=2&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=2&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a>
		</th>

		<th class="allInnerForms1">Room <br> <a
			href="meetings?fieldForSorting=3&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=3&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a>
		</th>
		<th class="allInnerForms2">Date <br> <a
			href="meetings?fieldForSorting=4&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=4&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a>
		</th>
		<th>StartTime <br> <!--  <a
			href="meetings?fieldForSorting=5&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=5&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a> -->
		</th>
		<th>EndTime <br> <!--  <a
			href="meetings?fieldForSorting=6&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=6&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a> -->
		</th>
		<th class="allInnerForms1">Groups</th>
		<th>Level <br> <a
			href="meetings?fieldForSorting=7&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=7&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a>
		</th>
		<th>Status <br> <a
			href="meetings?fieldForSorting=8&sortOrder=0&pageNumber=0"
			title="Sort Ascending"> <i class="fa fa-arrow-circle-o-up fa-lg"></i>
		</a> <a href="meetings?fieldForSorting=8&sortOrder=1&pageNumber=0"
			title="Sort Descending"> <i
				class="fa fa-arrow-circle-o-down fa-lg"></i>
		</a>
		</th>
		<!-- Filter button -->
		<th class="text-center v-alighn">
			<button class="btn btn-link" data-toggle="collapse"
				data-target="#showfilter" title="showFilter">
				<i class="fa fa-filter fa-lg"></i>
			</button>
		</th>
		<th class="text-center v-alighn"><a
			href="${pageContext.request.contextPath}/meetings/create"><i
				class="fa fa-plus fa-lg"></i></a></th>
	</tr>

	<tr>
		<!-- Filter form -->
		<c:choose>
			<c:when test="${meetingFilter.showFilter eq true}">
				<tr class="collapse in" id="showfilter">
			</c:when>
			<c:otherwise>
				<tr class="collapse" id="showfilter">
			</c:otherwise>
		</c:choose>
		<form:form role="form" action="meetings" method="get"
			modelAttribute="meetingFilter">
			<form:input path="showFilter" type="hidden" value="true" />

			<!-- ID -->
			<td style="width: 100%">
				<div class="form-group">
					<form:input class="form-control input-sm allInnerForms" type="text"
						path="description" placeholder="ID" />
				</div>
			</td>
			<%-- <!-- description -->
			<td style="width: 15%">
				<div class="form-group">
					<form:input class="form-control input-sm allInnerForms" type="text"
						path="description" placeholder="meetingDescription" />
				</div>
			</td> --%>
			<!-- subject -->
			<td>
				<div class="form-group allInnerForms1">
					<form:select class="form-control" path="subjectId" id="subjectId">
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
				</div>
			</td>
			<!-- owner -->
			<td>
				<div class="form-group allInnerForms1">
					<form:select class="form-control allInnerForms" path="ownerId"
						id="ownerId">
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
					</form:select>
				</div>
			</td>
			<!-- room -->
			<td>
				<div class="form-group allInnerForms1">
					<form:select class="form-control allInnerForms" path="roomId"
						id="roomId">
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
				</div>
			</td>
			<!-- date -->
			<td>

				<div class="form-group allInnerforms2 ">
					<form:input class="form-control input-sm allInnerForms2"
						type="date" path="date" placeholder="YYYY-MM-DD" />
				</div>
			</td>
			<!-- Start Time -->
			<td>
				<div class="form-group">


					<form:input class="form-control input-sm allInnerForms" type="time"
						path="startTime" id="startTime" placeholder="HH:MM" />
				</div>
			</td>
			<!-- End Time -->
			<td>
				<div class="form-group">
					<form:input class="form-control input-sm allInnerForms" type="time"
						path="endTime" id="endTime" placeholder="HH:MM" />
				</div>
			</td>
			<!-- UserGroups -->
			<td>

				<div class="form-group allInnerForms1">
					<form:select class="form-control " path="groups" id="groups"
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
					</form:select>
				</div>
			</td>
			<!-- Level -->
			<td class="levelclass">
				<div class="form-group levelclass">
					<form:input type="number" class="input-number levelclass"
						path="minLevel" step="1" title="MIN" />
					<form:input type="number" class="input-number levelclass"
						path="maxLevel" step="1" title="MAX" />
				</div>
			</td>
			<!-- Status -->
			<td>
				<div class="form-group ">
					<form:select class="form-control" path="status" id="status">
						<option value="-1"></option>
						<c:forEach items="${meetingStatuses}" var="status">
							<c:choose>
								<c:when test="${meetingFilter.status eq status.ordinal()}">
									<option value="${status.ordinal()}" selected="selected">${status}</option>
								</c:when>
								<c:otherwise>
									<option value="${status.ordinal()}">${status}</option>
								</c:otherwise>
							</c:choose>
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
		<td class="text-center v-alighn"><a
			href="meetings?showFilter=false&description=&subjectId=0&ownerId=0&roomId=0&date=&startTime=&endTime=&_groups=1&minLevel=&maxLevel=&status=-1"
			title="resetFilter"> <i class="fa fa-ban fa-lg"></i>
		</a></td>
	</tr>
	<!-- End Filter form -->

	<c:forEach var="meeting" items="${meetings}">
		<tr>
			<td>${meeting.id}</td>
			<%-- <td>${meeting.description}</td> --%>
			<td>${meeting.subject.name}</td>
			<td>${meeting.owner.lastName} ${meeting.owner.firstName}</td>
			<td>${meeting.room.name}</td>
			<td>${meeting.date}</td>
			<td>${meeting.startTime}</td>
			<td>${meeting.endTime}</td>
			<td><c:forEach items="${meeting.groups}" var="group">
					<p>${group.name}</p>
				</c:forEach></td>
			<td>${meeting.level}</td>
			<td>${meeting.status}<a
				href="${pageContext.request.contextPath}/meetings/editStatus/${meeting.id}"><i
					class="fa fa-pencil-square-o"></i></a>
			</td>
			<td><a
				href="${pageContext.request.contextPath}/meetings/delete/${meeting.id}"><i
					class="fa fa-trash-o fa-lg"></i></a></td>
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