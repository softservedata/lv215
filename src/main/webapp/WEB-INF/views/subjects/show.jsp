<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page
	import="com.softserve.edu.schedule.controller.SubjectController"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="lbl.form.deleteTitle" />
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<spring:message code="lbl.subject.deleteConfirm" />
				</p>
			</div>
			<div class="modal-footer">
				<a class="btn btn-primary btn-ok"><spring:message
						code="lbl.form.delete" /></a>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="lbl.form.cancel" />
				</button>
			</div>
		</div>
	</div>
</div>

<div class="container">
	<div class="row">
		<div
			class="col-lg-1 col-lg-offset-1 col-md-1 col-sm-1 col-xs-1 panel-exit text-center">
			<h3>
				<a href="#" onclick="window.history.back()"
					title="<spring:message code="lbl.form.back" />"> <i
					class="fa fa-arrow-left"></i>
				</a>
			</h3>
		</div>
		<div
			class="col-lg-1 col-lg-offset-8 col-md-1 col-sm-1 col-xs-1 panel-exit text-center">
			<h3>
				<a
					href="${pageContext.request.contextPath}${SubjectController.SUBJECTS_MAPPING}"
					title="<spring:message code="lbl.subject.title" />"> <i
					class="fa fa-table fa-lg"></i>
				</a>
			</h3>
		</div>
	</div>
	<div class="row">
		<div
			class="col-lg-4 col-lg-offset-1 col-md-5 col-sm-6 panel panel-default zero-margin-top">
			<h3 class="text-center">
				<spring:message code="lbl.subject.subjectDetails" />
			</h3>
			<div class="form-group">
				<h4>
					<spring:message code="lbl.subject.name" />
					: ${subject.name}
				</h4>

			</div>
			<div class="form-group" style="word-wrap: break-word;">
				<h4>
					<spring:message code="lbl.subject.description" />
					: ${subject.description}
				</h4>
			</div>
			<div class="form-group">
				<form:form modelAttribute="fileForSubjectForm"
					action="${pageContext.request.contextPath}${SubjectController.SUBJECTS_MAPPING_SHOW}${subject.id}?${_csrf.parameterName}=${_csrf.token}"
					method="POST" enctype="multipart/form-data">
					<input type="file" name="file"
						accept="image/jpeg, image/gif, application/msword, application/vnd.openxmlformats-officedocument.wordprocessingml.document, 
						application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-powerpoint, 
						application/vnd.openxmlformats-officedocument.presentationml.presentation, application/pdf" />
					<input type="submit" value="Submit" />
				</form:form>
			</div>
			<div class="form-group">
				<h4>Subject files :</h4>
				<ul>
					<c:forEach items="${subjectFiles}" var="fileName">
						<li style="list-style-type: none"><i class="fa fa-file-o"
							aria-hidden="true"></i> ${fileName} <a
							data-href="/schedule/subjects/deleteFile/${fileName}/${subject.id}"
							title="<spring:message code="lbl.subject.delete"/>"
							data-toggle="modal" data-target="#confirm-delete"> <i
								class="fa fa-trash-o fa-lg"></i>
						</a> <a
							href="/schedule/subjects/downloadFile/${fileName}/${subject.id}"><i
								class="fa fa-download" aria-hidden="true"></i> </a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="form-group">
				<h4>
					<spring:message code="lbl.subject.tutor" />
					:
				</h4>
				<ul>
					<c:forEach items="${subject.users}" var="user">
						<sec:authorize
							access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR', 'ROLE_USER')">
							<li><a
								href="${pageContext.request.contextPath}/${UserController.USER_MEETINGS_MAPPING}${user.id}">${user.firstName}
									${user.lastName}</a></li>
						</sec:authorize>
						<sec:authorize
							access="!hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR', 'ROLE_USER')">
							<li>${user.firstName} ${user.lastName}</li>
						</sec:authorize>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="col-lg-6 col-md-7 col-sm-6 panel-map zero-margin-top">
			<div id='calendar'></div>
		</div>
	</div>
</div>

<span id="subjectRestURL" hidden="true">${pageContext.request.contextPath}/meetings/restBySubject</span>
<span id="subjectId" hidden="true">${subject.id}</span>
<span id="language" hidden="true"><spring:message
		code="label.localeCalendar" /></span>
<spring:url value="/resources/js/subjects/show.js" var="subjectsShowJS" />
<script type="text/javascript" src="${subjectsShowJS}">
	
</script>


