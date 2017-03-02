<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@ page import="com.softserve.edu.schedule.controller.constants.MeetingControllerConst"%>

<div class="container">
	<div class="row padding-calendar-details">
		<div class="float-calendar-details">
			<h3>
				<a id="back" class="align-left" href="#" onclick="window.history.back()"
					title="<spring:message code="lbl.form.back" />">
					<i class="fa fa-arrow-left fa-lg"></i>
				</a>
			</h3>
		</div>
		<div class="col-lg-1 col-lg-offset-9 col-md-1 col-sm-1 col-xs-1 panel-exit zero-margin-top">
			<h3>
				<a class="align-right"
					href="${pageContext.request.contextPath}/${MeetingControllerConst.MEETINGS_MODEL_ATTR}"
					title="<spring:message code="lbl.subject.title" />">
					<i class="fa fa-table fa-lg"></i>
				</a>
			</h3>
		</div>
	</div>
	<div class="row">
		<div
			class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel ">
			<h2><spring:message code="lbl.meeting.showmeeting" /></h2>
			<form:form role="form" method="post" modelAttribute="meetingForm">
				<form:input path="id" type="hidden" />
				<div class="form-group">
					<spring:message code="lbl.meeting.id" />
					: ${meetingForm.id}
				</div>
				<div class="form-group">
					<b><spring:message code="lbl.meeting.description" /></b> :
					${meetingForm.description}
				</div>
				<div class="form-group">
					<b><spring:message code="lbl.meeting.subject" /></b> : <a
						href="${pageContext.request.contextPath}/${MeetingControllerConst.SUBJECTS_MODEL_ATTR}/
						${meetingForm.subject.id}">${meetingForm.subject.name}</a>
				</div>
				<div class="form-group">
					<b><spring:message code="lbl.meeting.owner" /></b> :
					<sec:authorize access="isAuthenticated()">
						<a
							href="${pageContext.request.contextPath}/${MeetingControllerConst.PROFILE_MAPPING}/
							${meetingForm.owner.id}">${meetingForm.owner.lastName}
							${meetingForm.owner.firstName}</a>
					</sec:authorize>
					<sec:authorize access="!isAuthenticated()">
						
						${meetingForm.owner.lastName}
						${meetingForm.owner.firstName}
						</sec:authorize>
				</div>
				<div class="form-group">
					<b><spring:message code="lbl.meeting.room" /></b> : <a
						href="${pageContext.request.contextPath}/${MeetingControllerConst.ROOMS_MODEL_ATTR}/
						${meetingForm.room.id}">${meetingForm.room.name} (${meetingForm.room.location.name})</a>
				</div>

				<div class="form-group">
					<b><spring:message code="lbl.meeting.date" /></b> :
					${meetingForm.date}
				</div>
				<div class="form-group">
					<b><spring:message code="lbl.meeting.starttime" /></b> :
					${meetingForm.startTime}
				</div>
				<div class="form-group">
					<b><spring:message code="lbl.meeting.endtime" /></b> :
					${meetingForm.endTime}
				</div>
				<div class="form-group">
					<b><spring:message code="lbl.meeting.groups" /></b> :
					<c:forEach items="${meetingForm.groups}" var="group">
						<li><a
							href="${pageContext.request.contextPath}/
							${MeetingControllerConst.USERGROUPS_MAPPING}/${group.id}">
							${group.name}</a>
						</li>
					</c:forEach>
				</div>
				<div class="form-group">
					<b><spring:message code="lbl.meeting.level" /></b> :
					${meetingForm.level}
				</div>
				<div class="form-group">
					<b><spring:message code="lbl.meeting.status" /></b> :
					<spring:message code="${meetingForm.status.getMessageCode()}" />
				</div>
			</form:form>
		</div>
	</div>
</div>

<spring:url value="/resources/js/meetings/showmeeting.js" var="meetingsShowJS" />
<script type="text/javascript" src="${meetingsShowJS}">
	
</script>