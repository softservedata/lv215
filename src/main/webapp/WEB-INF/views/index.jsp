<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%-- <div class="row">
	<div
		class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 panel panel-default">
		<c:if test="${param.accessDenied eq true}">
			<h3 class="text-danger text-center">
				<spring:message code="lbl.index.securityMessage" />
			</h3>
		</c:if>
		<h3>
			<spring:message code="lbl.index.greetings1" />
			<spring:message code="lbl.index.greetings2" />
			<spring:message code="lbl.index.greetings3" />
		</h3>
	</div>
</div> --%>

<script type="text/javascript">
	$(function() {
		$(".chosen-category").chosen({
			width : "100%"
		});
	})
</script>
<div class="row chosen-category-row">
<div class="col-lg-4 col-md-4 col-sm-4">
	<select class="chosen-category" id="ch1">
		<option value="0"></option>
		<c:forEach var="location" items="${local}">
			<c:forEach var="room" items="${location.rooms}">
			<option value="${room.id}">${room.name} (${location.name}) ${room.id}</option>
			</c:forEach>
		</c:forEach>
	</select>
</div>

<div class="col-lg-4 col-md-4 col-sm-4">
	<select class="chosen-category" id="ch2">
		<option value="0"></option>
		<c:forEach var="subject" items="${sub}">
			<option value="${subject.id}">${subject.name}</option>
		</c:forEach>
	</select>
</div>

<div class="col-lg-4 col-md-4 col-sm-4">
	<select class="chosen-category">
		<option value="0"></option>
		<c:forEach var="group" items="${gro}">
			<option value="${group.id}">${group.name}</option>
		</c:forEach>
	</select>
</div>
</div>

<div class="row">
		<div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2"
			id='calendar'></div>
	</div>
	
	<script>
	
	$("#ch1").change(function() {
		$('#calendar').fullCalendar('refetchEventSources', {
			url : '${pageContext.request.contextPath}/meetings/restByRoom',
			type : 'GET',
			data : {
				roomId : $("#ch1").val()
			}
		});
		$('#calendar').fullCalendar('refresh');
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
				url : '${pageContext.request.contextPath}/meetings/restByRoom',
				type : 'GET',
				data : {
					roomId : $(this).val()
				}
			}

		})		
	});
	
	
	
	
</script>
	