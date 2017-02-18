<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page
	import="com.softserve.edu.schedule.controller.MeetingController"%>

<div class="container">
	<div class="row">
		<div class=" col-md-4 col-md-offset-4  panel-default">
			<h3 class="text-center">
				<spring:message code="lbl.meeting.create" />
			</h3>
			<form:form role="form" method="post"
				modelAttribute="${MeetingController.MEETING_MODEL_ATTR}"
				onsubmit="return isValidForm()">
				<form:input path="id" type="hidden" />
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
					<div class="form-group">
						<label for="subject"><spring:message
								code="lbl.meeting.subject" /></label>
						<form:select class="form-control" path="subject" id="subject">
							<c:forEach items="${subjects}" var="subject">
								<option value="${subject.id}">${subject.name}</option>
							</c:forEach>
						</form:select>
						<form:errors path="subject" class="text-danger" />
					</div>
				</sec:authorize>

				<sec:authorize access="hasAnyRole('ROLE_MODERATOR')">
					<div class="form-group">
						<label for="subject"><spring:message
								code="lbl.meeting.subject" /></label>
						<sec:authentication property="principal.id" var="principarid" />
						<form:select class="form-control" path="subject" id="subject">
							<c:forEach items="${subjects}" var="subject">
								<c:forEach items="${subject.users}" var="subjecttutor">
									<c:choose>
										<c:when test="${subjecttutor.id eq principarid}">
											<option value="${subject.id}">${subject.name}</option>
										</c:when>
									</c:choose>
								</c:forEach>
							</c:forEach>

						</form:select>
						<form:errors path="subject" class="text-danger" />
					</div>
				</sec:authorize>

				<div class="form-group">
					<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
						<label for="owner"><spring:message
								code="lbl.meeting.owner" /></label>
						<form:select class="form-control" path="owner" id="ownerid">
							<c:forEach items="${owners}" var="owner">
								<option value="${owner.id}">${owner.lastName}
									${owner.firstName}</option>
							</c:forEach>
						</form:select>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_MODERATOR')">
						<label for="owner"><spring:message
								code="lbl.meeting.owner" /></label>
						<sec:authentication property="principal.id" var="principarid" />

						<form:select class="form-control" path="owner" id="owner"
							readonly="true">
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
							<option value="${room.id}">${room.name}
								(${room.getLocation().name})</option>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<label for="date"><spring:message code="lbl.meeting.date" /></label>
					<spring:message code="lbl.meeting.dates" var="datetemp" />
					<form:input type="date" path="date" id="date"
						placeholder="YYYY-MM-DD" required="true" 
						 min="2017-01-01"  max="2050-01-01"
						/>
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
					<label for="endTime"><spring:message
							code="lbl.meeting.endtime" /></label>
					<form:input type="time" path="endTime" id="endTime"
						placeholder="HH:MM" required="true" />
					<br>
					<form:errors path="endTime" class="text-danger" />
					<p id="timevalidator"></p>
				</div>
				<div class="form-group">
					<label for="groups"><spring:message
							code="lbl.meeting.groups" /></label>
					<form:select class="form-control" path="groups" id="groups"
						required="true" multiple="multiple">
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
					<form:errors path="groups" class="text-danger" />
				</div>
				<div class="form-group">
					<label for="level"><spring:message code="lbl.meeting.level" /></label>
					<spring:message code="lbl.meeting.levelinput" var="templevel" />
					<form:input type="number" min="1" max="5" step="1" required="true"
						class="form-control" path="level" id="level"
						placeholder="${templevel}" />
					<form:errors path="level" class="text-danger" />
				</div>
				<div class="form-group ">
					<label for="description"><spring:message
							code="lbl.meeting.description" /></label>
					<spring:message code="lbl.meeting.createDesc" var="descPH" />
					<form:input class="form-control" path="description"
						id="description" placeholder="${descPH}"
						pattern="[а-яА-ЯёЁіІєЄїЇa-zA-Z0-9№'@#$%^&+=,\.\s\-]{1,254}" />
					<form:errors path="description" class="text-danger" />
				</div>

				<div class="form-group text-center">
					<input type="submit" class="btn btn-default"
						value="<spring:message code="lbl.form.save"/>"> <a
						class="btn btn-default"
						href="${pageContext.request.contextPath}/
						${MeetingController.MEETING_CREATE_URL}"><spring:message
							code="lbl.form.reset" /></a> <a class="btn btn-default"
						href="${pageContext.request.contextPath}/
						${MeetingController.MEETINGS_MODEL_ATTR}"><spring:message
							code="lbl.form.cancel" /></a>
				</div>
			</form:form>
		</div>
	</div>
</div>

<script>
function checkform() {
	var dateString = document.purchase.date.value;
	var myDate = new Date(dateString);
	var today = new Date();
	if (document.purchase.txndt.value == "") {
		//something is wrong
		alert('REQUIRED FIELD ERROR: Please enter date in field!')
		return false;
	} else if (myDate < today) {
		//something else is wrong
		alert('You cannot enter a date in the past!');
		return false;
	}
	// if script gets this far through all of your fields
	// without problems, it's ok and you can submit the form
	alert('Alles gut!');
	return true;
}

	//Expect input as d/m/y
	function isValidDate(s) {
		var bits = s.split('/');
		var d = new Date(bits[2], bits[1] - 1, bits[0]);
		return d && (d.getMonth() + 1) == bits[1];
	}

	function isValidForm() {
		var startTimeMeeting = document.getElementById("startTime").value;
		var endTimeMeeting = document.getElementById("endTime").value;
		if (startTimeMeeting > endTimeMeeting) {
			document.getElementById("timevalidator").innerHTML = "Invalid time. The end of the meeting should be after the start meeting.";
			return false;
		}
		
		return true;
	}

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

	//new
	$("#subject").change(function() {
		subjectid = $(this).val();

	
	/* 	$("#ownerid").val('2').hide().trigger('chosen:updated');
		 */
		/* $("#ownerid").val('2').trigger('chosen:updated'); */
	});

	
</script>