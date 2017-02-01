<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
	$(function() {
		$("select[name=subject]").chosen({
			width : "100%"
		});
		$("select[name=owner]").chosen({
			width : "100%"
		});
		$("select[name=room]").chosen({
			width : "100%"
		});
		$("select[name=groups]").chosen({
			width : "100%"
		});
		$("select[name=status]").chosen({
			width : "100%"
		});
	})
</script>
<div class="container">
	<div class="row">
		<div class="col-md-1">
			<br> <a class="btn btn-default"
				href="${pageContext.request.contextPath}/meetings"><spring:message
					code="lbl.form.back" /></a>
		</div>
		<div class="col-md-11">
			<h1 class="text-center">
				<spring:message code="lbl.meeting.showmeeting" />
			</h1>
		</div>
	</div>
	<div class="row">
		<div
			class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
			<form:form role="form" method="post" modelAttribute="meetingForm">
				<form:input path="id" type="hidden" />
				<div class="form-group">
					<spring:message code="lbl.meeting.id" />
					: ${meetingForm.id}

				</div>
				<div class="form-group">
					<b><spring:message code="lbl.meeting.description" /></b>
					: ${meetingForm.description}

				</div>


				<div class="form-group">
					<b><spring:message code="lbl.meeting.subject" /></b>
					: ${meetingForm.subject.name}

				</div>

				<div class="form-group">
					<b><spring:message code="lbl.meeting.owner" /></b>
					: ${meetingForm.owner.lastName} ${meetingForm.owner.firstName}

				</div>

				<div class="form-group">
					<b><spring:message code="lbl.meeting.room" /></b>
					: ${meetingForm.room.name}
				</div>

				<div class="form-group">
					<b><spring:message code="lbl.meeting.date" /></b>
					: ${meetingForm.date}
				</div>
				<div class="form-group">
					<b><spring:message code="lbl.meeting.starttime" /></b>
					: ${meetingForm.startTime}
				</div>
				<div class="form-group">
					<b><spring:message code="lbl.meeting.endtime" /></b>
					: ${meetingForm.endTime}
				</div>
				<div class="form-group">
					<b><spring:message code="lbl.meeting.groups" /></b>
					:
					<c:forEach items="${meetingForm.groups}" var="group">
						<li>${group.name}</li>
					</c:forEach>
				</div>
				<div class="form-group">
					<b><spring:message code="lbl.meeting.level" /></b>
					: ${meetingForm.level}
				</div>
				<div class="form-group">
					<b><spring:message code="lbl.meeting.status" /></b>
					: ${meetingForm.status}
				</div>
			</form:form>
		</div>
	</div>
</div>