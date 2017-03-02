<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page
	import="com.softserve.edu.schedule.controller.constants.SubjectControllerConst"%>
<%@ page import="com.softserve.edu.schedule.controller.constants.UserControllerConst"%>
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
					href="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECTS_MAPPING}"
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
			<sec:authorize
				access="${SubjectControllerConst.HAS_ANY_ROLE_EXEPT_USER}">
				<form:form class="form-inline"
					modelAttribute="${SubjectControllerConst.SUBJECT_FILE_FORM}"
					action="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECTS_MAPPING_SHOW}${subject.id}?${_csrf.parameterName}=${_csrf.token}"
					method="POST" enctype="multipart/form-data">
					<div class="form-group">
						<div class="input-group">
							<label class="input-group-btn"> <span
								class="btn btn-primary"><spring:message
										code="lbl.filePicker.browse" />&hellip; <input
									style="display: none;" type="file" name="file"
									accept="${SubjectControllerConst.ACCES_FILES}"> </span>
							</label> <input type="text" class="form-control" readonly>
						</div>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-default"
							value="<spring:message code="lbl.form.save"/>" />
					</div>
					<div class="input-group">
						<form:errors path="file" style="color: red"/>
					</div>
				</form:form>
			</sec:authorize>
			<sec:authorize
				access="${SubjectControllerConst.HAS_ANY_ROLE}">
				<div class="form-group">
					<h4>
						<spring:message code="lbl.subject.files" />
					</h4>
					<ul>
						<c:forEach items="${subjectFiles}" var="fileName">
							<li style="list-style-type: none"><i class="fa fa-file-o"
								aria-hidden="true"></i> ${fileName} <sec:authorize
									access="${SubjectControllerConst.HAS_ANY_ROLE_EXEPT_USER}">
									<a
										data-href="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECT_DELETE_FILE_MAPPING}${fileName}/${subject.id}"
										title="<spring:message code="lbl.subject.delete"/>"
										data-toggle="modal" data-target="#confirm-delete"> <i
										class="fa fa-trash-o fa-lg"></i>
									</a>
								</sec:authorize> <sec:authorize
									access="${SubjectControllerConst.HAS_ANY_ROLE}">
									<a
										href="${pageContext.request.contextPath}${SubjectControllerConst.SUBJECT_DOWNLOAD_FILE_MAPPING}${fileName}/${subject.id}"><i
										class="fa fa-download" aria-hidden="true"></i> </a>
								</sec:authorize></li>
						</c:forEach>
					</ul>
				</div>
			</sec:authorize>
			<div class="form-group">
				<h4>
					<spring:message code="lbl.subject.tutor" />
					:
				</h4>
				<ul>
					<c:forEach items="${subject.users}" var="user">
						<sec:authorize
							access="${SubjectControllerConst.HAS_ANY_ROLE}">
							<li><a
								href="${pageContext.request.contextPath}/${UserControllerConst.USER_MEETINGS_MAPPING}${user.id}">${user.firstName}
									${user.lastName}</a></li>
						</sec:authorize>
						<sec:authorize
							access="!${SubjectControllerConst.HAS_ANY_ROLE}">
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


