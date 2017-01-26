<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Meetings</title>
</head>
<body>
	<h3 class="text-center">Meetings</h3>
	<table class="table table-hover">
		<tr>

			<th>Description <%-- <a
				href="${pageContext.request.contextPath}/meetings/sortbydescriptionasc"><i
					class="fa fa-arrow-circle-o-up"></i></a><a
				href="${pageContext.request.contextPath}/meetings/sortbydescriptiondesc"><i
					class="fa fa-arrow-circle-o-down"></i> </a> --%>

			</th>
			<th>Subject <%-- <a
				href="${pageContext.request.contextPath}/meetings/sortbysubjectasc">
					<i class="fa fa-arrow-circle-o-up"></i>
			</a> <a
				href="${pageContext.request.contextPath}/meetings/sortbysubjectdesc"><i
					class="fa fa-arrow-circle-o-down"></i> </a>--%>
			</th>
			<th>Owner<%-- <a
				href="${pageContext.request.contextPath}/meetings/sortbyownerasc">
					<i class="fa fa-arrow-circle-o-up"></i>
			</a> <a
				href="${pageContext.request.contextPath}/meetings/sortbyownerdesc"><i
					class="fa fa-arrow-circle-o-down"></i> </a> --%>
			</th>
			<th>Room<%-- <a
				href="${pageContext.request.contextPath}/meetings/sortbyroomasc">
					<i class="fa fa-arrow-circle-o-up"></i>
			</a> <a href="${pageContext.request.contextPath}/meetings/sortbyroomdesc"><i
					class="fa fa-arrow-circle-o-down"></i> </a> --%>
			</th>

			<th>Date</th>
			<th>StartTime</th>

			<th>EndTime</th>
			<th>Groups</th>
			<th class="searchMeetingBig">Level<%-- <a
				href="${pageContext.request.contextPath}/meetings/sortbylevelasc">
					<i class="fa fa-arrow-circle-o-up"></i>
			</a><a
				href="${pageContext.request.contextPath}/meetings/sortbyleveldesc"><i
					class="fa fa-arrow-circle-o-down"></i></a> --%>
			</th>
			<th>Status<%-- <a
				href="${pageContext.request.contextPath}/meetings/sortbystatusasc">
					<i class="fa fa-arrow-circle-o-up"></i>
			</a><a
				href="${pageContext.request.contextPath}/meetings/sortbystatusdesc"><i
					class="fa fa-arrow-circle-o-down"></i></a> --%>
			</th>
			<!-- Filter buttom -->
			<th class="text-center v-alighn">
				<button class="btn btn-link" data-toggle="collapse"
					data-target="#showfilter" title="showFilter">
					<i class="fa fa-filter fa-lg"></i>
				</button>
			</th>
			<th><a href="${pageContext.request.contextPath}/meetings/create"><i
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
				<!-- description -->
				<td>
					<div class="form-group col-xs-10">

						<form:input class="form-control input-sm" type="text"
							path="description" placeholder="meetingDescription" />
					</div>
				</td>
				<!-- subject -->
				<td>
					<div class="form-group">
						<form:select class="form-control" path="subjectId"
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
						</form:select>
					</div>
				</td>
				<!-- owner -->
				<td>
					<div class="form-group">
						<form:select class="form-control" path="ownerId"
							id="ownerId">
							<option value="0"></option>
							<c:forEach items="${users}" var="owner">
								<c:choose>
									<c:when test="${meetingFilter.ownerId eq owner.id}">
										<option value="${owner.id}" selected="selected">${owner.lastName} ${owner.firstName}</option>
									</c:when>
									<c:otherwise>
										<option value="${owner.id}">${owner.lastName} ${owner.firstName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
					</div>
				</td>
				<!-- room -->
				<td>
					<div class="form-group">
						<form:select class="form-control" path="roomId"
							id="roomId">
							<option value="0"></option>
							<c:forEach items="${rooms}" var="room">
								<c:choose>
									<c:when test="${meetingFilter.roomId eq room.id}">
										<option value="${room.id}" selected="selected">${room.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${room.id}">${owner.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
					</div>
				</td>
				<!-- date -->
				<td>
					<div class="form-group col-xs-10">

						<form:input class="form-control input-sm" type="date"
							path="date" placeholder="YYYY-MM-DD" />
					</div>
				</td>
				<!-- Start Time -->
				<td>
					<div class="form-group col-xs-10">

						<form:input class="form-control input-sm" type="time" 
						path="startTime" id="startTime"
						placeholder="HH:MM" />
					</div>
				</td>
		<!-- End Time -->
				<td>
					<div class="form-group col-xs-10">

						<form:input class="form-control input-sm" type="time" 
						path="endTime" id="endTime"
						placeholder="HH:MM" />
					</div>
				</td>	
				<!-- UserGroups -->
				<td>
					<div class="form-group">
						<form:select class="form-control" path="groups"
							id="groups" multiple="multiple">
							<c:forEach items="${userGroups}" var="userGroup">
								<c:set var="found" value="false" />
								<c:forEach items="${meetingFilter.groups}"
									var="groupsInFilter">
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
				<td>
					<div class="form-group">
						<form:input type="number" class="input-number" path="minLevel"
							step="1"  title="MIN" />
						<form:input type="number" class="input-number" path="maxLevel"
							step="1"  title="MAX" />
					</div>
				</td>
				<!-- Status -->
				<td>
					<div class="form-group">
						<form:select class="form-control" path="status"
							id="status">
							 <option value="-1"></option>
							<c:forEach items="${meetingStatuses}" var="status">
								<c:choose>
									<c:when test="${meetingFilter.status eq status}">
										<option value="${status}" selected="selected">${status}</option>
									</c:when>
									<c:otherwise>
										<option value="${status}">${status}</option>
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
			<td class="text-center v-alighn">
				 <a href="#" title="resetFilter"> <i class="fa fa-ban fa-lg"></i></a>
			</td>
		</tr>
		<!-- End Filter form -->

		<c:forEach var="meeting" items="${meetings}">
			<tr>

				<td>${meeting.description}</td>
				<td>${meeting.subject.name}</td>
				<td>${meeting.owner.lastName}${meeting.owner.firstName}</td>
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
</body>
</html>