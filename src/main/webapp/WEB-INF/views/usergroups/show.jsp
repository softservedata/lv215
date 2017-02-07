<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">
	<div class="row">
		<div
			class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
			<h1 class="text-center">
				<spring:message code="lbl.group.showgroup" />
			</h1>

			<form:input path="id" type="hidden" />

			<div class="form-group">
				<h3>${userGroupForm.name}</h3>
			</div>

			<div class="form-group">
				<b><spring:message code="lbl.group.description" /></b> :
				${userGroupForm.description}
			</div>

			<div class="form-group">
				<b><spring:message code="lbl.group.curator" /></b> : <a
					href="${pageContext.request.contextPath}/profile${userGroupForm.curator.id}">
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
									href="${pageContext.request.contextPath}/profile${member.id}">${member.lastName}
										${member.firstName}</a></li>
							</c:if>
						</c:forEach>
					</c:otherwise>
				</c:choose>

			</div>

		</div>
	</div>

	<div class="row">
		<h1 class="text-center">
			<spring:message code="lbl.group.plannedMeetings" />
		</h1>
		<div
			class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2"
			id='calendar'></div>
	</div>
</div>

<script>
$(document).ready(function() {
	$('#calendar').fullCalendar({
		defaultView : 'agendaWeek',
		locale : '<spring:message code="label.localeCalendar"/>',
		header : {
			left : 'prev,next,today',
			center : 'title',
			right : 'month,agendaWeek,agendaDay,listWeek'
		},
		nowIndicator : true,
		navLinks : true,
		events : {
			url : '${pageContext.request.contextPath}/meetings/restByGroup',
			type : 'GET',
			data : {
				groupId : '${userGroupForm.id}'
			}
		}

	})

});
</script>