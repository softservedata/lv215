<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="com.softserve.edu.schedule.controller.UserGroupController"%>

<div class="container">
	<div class="row padding-calendar-details">
		<div class="float-calendar-details">
			<h3>
				<a class="align-left" href="#" onclick="window.history.back()"
					title="<spring:message code="lbl.form.back" />"> <i
					class="fa fa-arrow-left fa-lg"></i>
				</a>
			</h3>
		</div>
		<div class="col-lg-1 col-lg-offset-9 col-md-1 col-sm-1 col-xs-1 panel-exit zero-margin-top">
			<h3>
				<a class="align-right"
					href="${pageContext.request.contextPath}${UserGroupController.USERGROUP_MAPPING}"
					title="<spring:message code="lbl.group.title" />"> <i
					class="fa fa-table fa-lg"></i>
				</a>
			</h3>
		</div>
	</div>

	<div class="row">
		<div
			class="col-lg-4 col-lg-offset-1 col-md-5 col-sm-6 panel panel-default zero-margin-top">
			<h3 class="text-center">
				<spring:message code="lbl.group.showgroup" />
			</h3>

			<form:input path="id" type="hidden" />

			<div class="form-group">
				<h3>${userGroupForm.name}</h3>
			</div>

			<div class="form-group" style="word-wrap: break-word;">
				<b><spring:message code="lbl.group.description" /></b> :
				${userGroupForm.description}
			</div>

			<div class="form-group">
				<b><spring:message code="lbl.group.curator" /></b> : <a
					href="${pageContext.request.contextPath}${UserGroupController.USERGROUP_USER_PROFILE_MAPPING}${userGroupForm.curator.id}">
					${userGroupForm.curator.lastName}
					${userGroupForm.curator.firstName}</a>
			</div>

			<div class="form-group">
				<b><spring:message code="lbl.group.level" /></b> :
				<spring:message code="lbl.group.${userGroupForm.level.code}" />
			</div>

			<div class="form-group">
				<h3>
					<spring:message code="lbl.group.groupmembers" />
				</h3>

				<c:choose>
					<c:when test="${userGroupForm.users.size() eq 1}">
						<p>
							<spring:message code="lbl.group.emptygroup" />
						</p>
					</c:when>
					<c:otherwise>
						<c:forEach items="${userGroupForm.users}" var="member">
							<c:if test="${userGroupForm.curator.id ne member.id}">
								<li><a
									href="${pageContext.request.contextPath}${UserGroupController.USERGROUP_USER_PROFILE_MAPPING}${member.id}">${member.lastName}
										${member.firstName}</a></li>
							</c:if>
						</c:forEach>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
		<div class="col-lg-6 col-md-7 col-sm-6 panel-map zero-margin-top">
			<div id='calendar' style="margin-top: 0px"></div>
		</div>
	</div>
</div>

<span id="userGroupsRestURL" hidden="true">${pageContext.request.contextPath}/meetings/restByGroup</span>
<span id="groupId" hidden="true">${userGroupForm.id}</span>
<span id="language" hidden="true"><spring:message code="label.localeCalendar" /></span>

<spring:url value="/resources/js/usergroups/calendar.js" var="userGroupsCalendarJS" />
<script type="text/javascript" src="${userGroupsCalendarJS}">

</script>