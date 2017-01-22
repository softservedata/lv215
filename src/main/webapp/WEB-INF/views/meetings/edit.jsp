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
		<div
			class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
			<h3 class="text-center">Edit Meeting</h3>
			<form:form role="form" method="post" modelAttribute="meetingForm">
				<form:input path="id" type="hidden" />
				<form:input path="status" type="hidden"  />
				<div class="form-group">
					<label for="description">Description</label>
					<form:input type="text" class="form-control" path="description"
						id="description" placeholder="Description" required="true" />
				</div>

				<div class="form-group">
					<label for="subject">Subject</label>
					<form:select class="form-control" path="subject" id="subject">
						<c:forEach items="${subjects}" var="subject">
							<c:choose>
								<c:when test="${meetingForm.subject.id eq subject.id}">
									<option value="${subject.id}" selected="selected">${subject.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${subject.id}">${subject.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<label for="owner">Owner</label>
					<form:select class="form-control" path="owner" id="owner">
						<c:forEach items="${owners}" var="owner">
							<c:choose>
								<c:when test="${meetingForm.owner.id eq owner.id}">
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
				<div class="form-group">
					<label for="room">Room</label>
					<form:select class="form-control" path="room" id="room">
						<c:forEach items="${rooms}" var="room">
							<c:choose>
								<c:when test="${meetingForm.room.id eq room.id}">
									<option value="${room.id}" selected="selected">${room.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${room.id}">${room.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<label for="date">Date</label>
					<form:input type="date" path="date" id="date"
						placeholder="YYYY-MM-DD" required="true" />
				</div>
				<div class="form-group">
					<label for="startTime">Start time</label>
					<form:input type="time" path="startTime" id="startTime"
						placeholder="HH:MM" required="true" />
				</div>
				<div class="form-group">
					<label for="endTime">End time</label>
					<form:input type="time" path="endTime" id="endTime"
						placeholder="HH:MM" required="true" />
				</div>
				<div class="form-group">
					<label for="groups">Groups</label>
					<form:select class="form-control" path="groups" id="groups"
						multiple="multiple">
						<c:forEach items="${groups}" var="group">
							<c:set var="found" value="false" />
							<c:forEach items="${meetingForm.groups}" var="groupsInMeeting">
								<c:if test="${!found}">
									<c:if test="${groupsInMeeting.id eq group.id}">
										<option value="${group.id}" selected="selected">${group.name}</option>
										<c:set var="found" value="true" />
									</c:if>
								</c:if>
							</c:forEach>
							<c:if test="${!found}">
								<option value="${group.id}">${group.name}</option>
							</c:if>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group">
					<label for="level">Level</label>
					<form:input type="number" min="1" max="5" step="1"
						class="form-control" path="level" id="level" placeholder="Level"
						required="true" />
				</div>
				<div class="form-group text-center">
					<input type="submit" class="btn btn-default"
						value="<spring:message code="lbl.form.save"/>"> <a
						class="btn btn-default"
						href="/schedule/meetings/edit/${meetingForm.id}"><spring:message
							code="lbl.form.reset" /></a> <a class="btn btn-default"
						href="${pageContext.request.contextPath}/meetings"><spring:message
							code="lbl.form.cancel" /></a>
				</div>


			</form:form>
		</div>
	</div>
</div>