<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

	<%@ page import="com.softserve.edu.schedule.controller.constants.MeetingControllerConst"%>

<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4  panel-default">
			<h3 class="text-center">
				<spring:message code="lbl.meeting.edit" />
			</h3>
			<form:form role="form" method="post" modelAttribute="meetingForm"
				onsubmit="return isValidForm()">
				<form:input path="id" type="hidden" />
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
					<div class="form-group">
						<label for="subject"><spring:message
								code="lbl.meeting.subject" /></label>
						<form:select class="form-control" path="subject" id="subject">
							<c:forEach items="${subjects}" var="subject">
								<c:choose>
									<c:when test="${meetingForm.subject.id eq subject.id}">
										<option value="${subject.id}" selected="selected">
											${subject.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${subject.id}">${subject.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
						<br>
						<form:errors path="subject" class="text-danger" />
					</div>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_MODERATOR')">
					<sec:authentication property="principal.id" var="principarid" />
					<div class="form-group">
						<label for="subject"><spring:message
								code="lbl.meeting.subject" /></label>
						<form:select class="form-control" path="subject" id="subject">
							<c:forEach items="${subjects}" var="subject">
								<c:choose>
									<c:when test="${meetingForm.subject.id eq subject.id}">
										<option value="${subject.id}" selected="selected">
											${subject.name}</option>
									</c:when>
									<c:otherwise>
										<c:forEach items="${subject.users}" var="subjecttutor">
											<c:choose>
												<c:when test="${subjecttutor.id eq principarid}">
													<option value="${subject.id}">${subject.name}</option>
												</c:when>
											</c:choose>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
						<br>
						<form:errors path="subject" class="text-danger" />
					</div>
				</sec:authorize>

				<div class="form-group">
					<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
						<label for="owner"><spring:message
								code="lbl.meeting.owner" /></label>
						<form:select class="form-control" path="owner" id="owner">
							<c:forEach items="${owners}" var="owner">
								<c:choose>
									<c:when test="${meetingForm.owner.id eq owner.id}">
										<option value="${owner.id}" selected="selected">${owner.lastName} ${owner.firstName}</option>
									</c:when>
									<c:otherwise>
										<option value="${owner.id}">${owner.lastName} ${owner.firstName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_MODERATOR')">
						<label for="owner"><spring:message
								code="lbl.meeting.owner" /></label>
						<sec:authentication property="principal.id" var="principarid" />
						<form:select class="form-control" path="owner" id="owner">
							<c:forEach items="${owners}" var="owner">
								<c:choose>
									<c:when test="${owner.id eq principarid}">
										<option value="${owner.id}" selected="selected">${owner.lastName}
											${owner.firstName}</option>
									</c:when>
								</c:choose>
							</c:forEach>
						</form:select>
					</sec:authorize>
				</div>
				<div class="form-group">
					<label for="room"><spring:message code="lbl.meeting.room" /></label>
					<form:select class="form-control" path="room" id="room">
						<c:forEach items="${rooms}" var="room">
							<c:choose>
								<c:when test="${meetingForm.room.id eq room.id}">
									<option value="${room.id}" selected="selected">${room.name} (${room.location.name})</option>
								</c:when>
								<c:otherwise>
									<option value="${room.id}">${room.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<label for="date"><spring:message code="lbl.meeting.date" /></label>
					<form:input type="date" path="date" id="date"
						placeholder="YYYY-MM-DD" required="true"
						min="2017-01-01"  max="2050-01-01" />
					<br>
					<form:errors path="date" class="text-danger" />
					<p id="datevalidator"></p>
				</div>
				<div class="form-group">
					<label for="startTime"><spring:message
							code="lbl.meeting.starttime" /></label>
					<form:input type="time" path="startTime" id="startTime"
						placeholder="HH:MM" required="true" />
					<br>
					<form:errors path="startTime" class="text-danger" />
				</div>
				<div class="form-group">
					<label for="endTime"> <spring:message
							code="lbl.meeting.endtime" />
					</label>
					<form:input type="time" path="endTime" id="endTime"
						placeholder="HH:MM" required="true" />
					<br>
					<form:errors path="endTime" class="text-danger" />
					<p id="timevalidator" title="invalidName"></p>
				</div>
				<div class="form-group">
					<label for="groups"><spring:message
							code="lbl.meeting.groups" /></label>
					<form:select class="form-control" path="groups" id="groups"
						required="true"
						oninvalid="this.setCustomValidity('${invalidName}')"
						oninput="setCustomValidity('')" multiple="multiple">
						<c:forEach items="${groups}" var="group">
							<c:set var="found" value="false" />
							<c:forEach items="${meetingForm.groups}" var="groupsInMeeting">
								<c:if test="${!found}">
									<c:if test="${groupsInMeeting.id eq group.id}">
										<option value="${group.id}" selected="selected">
											${group.name}</option>
										<c:set var="found" value="true" />
									</c:if>
								</c:if>
							</c:forEach>
							<c:if test="${!found}">
								<option value="${group.id}">${group.name}</option>
							</c:if>
						</c:forEach>
					</form:select>
					<br>
					<form:errors path="groups" class="text-danger" />
				</div>
				<div class="form-group">
					<label for="level"><spring:message code="lbl.meeting.level" /></label>
					<spring:message code="lbl.meeting.levelinput" var="templevel" />
					<form:input type="number" min="1" max="5" step="1"
						class="form-control" path="level" id="level"
						placeholder="${templevel}" required="true" />
					<br>
					<form:errors path="level" class="text-danger" />
				</div>
				<div class="form-group">
					<label for="description"><spring:message
							code="lbl.meeting.description" /></label>
					<spring:message code="lbl.meeting.createDesc" var="descPH" />
					<form:input class="form-control" path="description"
						id="description" placeholder="${descPH}"
						pattern="[а-яА-ЯёЁіІєЄїЇa-zA-Z0-9№'@#$%^&+=,\.\s\-]{1,254}" />
					<form:errors path="description" class="text-danger" />
				</div>
				<div class="form-group">
					<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
						<label for="status"><spring:message
								code="lbl.meeting.changestatus" /></label>
						<form:select class="form-control" path="status" id="status">
							<c:forEach items="${meetingStatuses}" var="status">
								<c:choose>
									<c:when
										test="${meetingForm.status.ordinal() eq status.ordinal()}">
										<option value="${status}" selected="selected"><spring:message
												code="${status.getMessageCode()}" /></option>
									</c:when>
									<c:otherwise>
										<c:if test="${status.ordinal() ne 2 && status.ordinal() ne 4 }">
											<option value="${status}"><spring:message
													code="${status.getMessageCode()}" /></option>
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
						<form:errors path="status" class="text-danger" />
					</sec:authorize>
					<sec:authorize access="!hasAnyRole('ROLE_ADMIN')">
						<label for="status"><spring:message
								code="lbl.meeting.status" /></label>
						<form:select class="form-control" path="status" id="status">
							<c:forEach items="${meetingStatuses}" var="status">
								<c:choose>
									<c:when
										test="${meetingForm.status.ordinal() eq status.ordinal()}">
										<option value="${status}" selected="selected"><spring:message
												code="${status.getMessageCode()}" /></option>
									</c:when>
								</c:choose>
							</c:forEach>
						</form:select>
						<form:errors path="status" class="text-danger" />
					</sec:authorize>
				</div>
				<div class="form-group text-center">
					<input type="submit" class="btn btn-default" id="timeerror"
						value="<spring:message code="lbl.form.save"/>"> <a
						class="btn btn-default"
						href="${pageContext.request.contextPath}/${MeetingControllerConst.MEETING_EDIT_URL}/
						${meetingForm.id}"><spring:message
							code="lbl.form.reset" /></a> <a class="btn btn-default"
						href="${pageContext.request.contextPath}/
						${MeetingControllerConst.MEETINGS_MODEL_ATTR}"><spring:message
							code="lbl.form.cancel" /></a>
				</div>
			</form:form>
		</div>
	</div>
</div>

<spring:url value="/resources/js/meetings/edit.js" var="meetingsEditJS" />
<script type="text/javascript" src="${meetingsEditJS}">
	
</script>