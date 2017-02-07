<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<div class="row">
		<div class="col-lg-4 col-lg-offset-1 col-md-5 col-sm-6 panel panel-default">
			<h3 class="text-center">
				<spring:message code="lbl.subject.subjectDetails" />
			</h3>
			<div class="form-group">
				<h4>
					<spring:message code="lbl.subject.name" />
					: ${subject.name}
				</h4>

			</div>
			<div class="form-group">
				<h4>
					<spring:message code="lbl.subject.description" />
					: ${subject.description}
				</h4>
			</div>
			<div class="form-group">
				<h4>
					<spring:message code="lbl.subject.tutor" />
					:
				</h4>
				<ul>
					<c:forEach items="${subject.users}" var="user">
						<li>${user.firstName}${user.lastName}</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="col-lg-6 col-md-7 col-sm-6 panel-map">
			<div id='calendar' style="margin-top: 0px"></div>
		</div>
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
				url : '${pageContext.request.contextPath}/meetings/restBySubject',
				type : 'GET',
				data : {
					subjectId : '${subject.id}'
				}
			}

		})

	});
</script>






