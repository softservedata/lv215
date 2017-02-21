<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="row chosen-category-row">
	<c:if test="${param.new_account eq true}">
		<div class="row">
			<div
				class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 panel panel-default">
				<h3 class="text-success text-center">
					<spring:message code="lbl.index.welcomeUser" />
				</h3>
			</div>
		</div>
	</c:if>

	<div class="col-lg-2 col-md-2 col-sm-2 col-lg-offset-2 col-md-offset-2 col-sm-offset-2">
		<select class="chosen-category" id="ch1">
			<spring:message code="lbl.nav.rooms" var="roomSelect" />
			<option value="0">${roomSelect}</option>
			<c:forEach var="location" items="${locationI}">
				<c:forEach var="room" items="${location.rooms}">
					<option value="${room.id}">${room.name}(${location.name})</option>
				</c:forEach>
			</c:forEach>
		</select>
	</div>

	<div class="col-lg-2 col-md-2 col-sm-2">
		<select class="chosen-category" id="ch2">
			<spring:message code="lbl.nav.subjects" var="subjectSelect" />
			<option value="0">${subjectSelect}</option>
			<c:forEach var="subject" items="${subjectI}">
				<option value="${subject.id}">${subject.name}</option>
			</c:forEach>
		</select>
	</div>

	<div class="col-lg-2 col-md-2 col-sm-2">
		<select class="chosen-category" id="ch3">
			<spring:message code="lbl.nav.usergroups" var="groupSelect" />
			<option value="0">${groupSelect}</option>
			<c:forEach var="group" items="${groupI}">
				<option value="${group.id}">${group.name}</option>
			</c:forEach>
		</select>
	</div>
</div>

<div class="row">
	<div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2"
		id='calendar'></div>
</div>
 <div id="myPieChart"/>
<spring:message code="label.localeCalendar" var="locale" />
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<span id="language" hidden="true">${locale}</span>
<span id="context" hidden="true">${contextPath}</span>
<spring:url value="/resources/js/index.js" var="indexJS" />
<script type="text/javascript" src="${indexJS}"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
google.charts.load('current', {packages: ['corechart']});
google.charts.setOnLoadCallback(drawChart);

var jsonData = $.ajax({
    url: '${pageContext.request.contextPath}/meetings/restBy/5',
    dataType:'json',
    async: false
  }).responseText;
/* window.alert(Object.keys(JSON.parse(jsonData))); */
/* window.alert(Object.keys(JSON.parse(jsonData)).length); */
function drawChart() {
  var data = new google.visualization.DataTable();
  data.addColumn('string', 'Subject');
  data.addColumn('number', 'Time');
  
 /*  data.addRows(Object.keys(JSON.parse(jsonData)).length);
  var iterator = 0;
$.each(JSON.parse(jsonData), function(key,value) {
	data.setValue(iterator, 0, key);
	data.setValue(iterator, 1, value);
	iterator++;
}); */

data.addRows(jsonData.length);
$.each(JSON.parse(jsonData), function(i,v) {
	data.setValue(i, 0, v.subject);
	data.setValue(i, 1, v.time);
});

var options = {'title':'Schedule',
		'width':400,
		'height':300,
		'legent':'left',
		'is3D':true};
  var chart = new google.visualization.PieChart(document.getElementById('myPieChart'));
  chart.draw(data, options);
}




 
</script>
