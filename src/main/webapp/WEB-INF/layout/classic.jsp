<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<spring:url value="/resources/css/main.css" var="mainCss" />
<spring:url value="/resources/img/favicon.ico" var="favico" />
<spring:url value="/resources/css/font-awesome.min.css" var="fontCss" />
<spring:url value="/resources/chosen_v1.6.2/chosen.min.css" var="chosenCSS" />
<spring:url value="/resources/chosen_v1.6.2/chosen.jquery.min.js" var="chosenJs" />
<spring:url value="/resources/jQuery_v3.1.1/jquery-3.1.1.min.js" var="jQueryJs" />
<spring:url value="/resources/fullcalendar-3.1.0/fullcalendar.min.css" var="fullCalendarCSS" />
<spring:url value="/resources/bootstrap-3.3.7/css/bootstrap.min.css" var="bootStrapCSS" />
<spring:url value="/resources/bootstrap-3.3.7/js/bootstrap.min.js" var="bootStrapJS" />
<spring:url value="/resources/esimakin-twbs-pagination-1.3.1/jquery.twbsPagination.min.js" var="paginationJS" />
<spring:url value="/resources/fullcalendar-3.1.0/fullcalendar.min.js" var="fullCalendarJS" />
<spring:url value="/resources/fullcalendar-3.1.0/lib/moment.min.js" var="momentJS" />

<link href="${favico}" rel="shortcut icon">
<link rel="stylesheet" href="${bootStrapCSS}">
<link rel="stylesheet" href="${chosenCSS}" />
<link rel="stylesheet" href="${mainCss}">
<link rel="stylesheet" href="${fontCss}">
<link rel='stylesheet' href="${fullCalendarCSS}"/>
<script type="text/javascript" src="${jQueryJs}"></script>
<script type="text/javascript" src="${bootStrapJS}"></script>
<script type="text/javascript" src="${chosenJs}"></script>
<script type="text/javascript" src="${paginationJS}"></script>
<script type="text/javascript" src="${momentJS}"></script>
<script type="text/javascript" src="${fullCalendarJS}"></script>
<title><tiles:getAsString name="title" /></title>
</head>
<body>

	<header>
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="navigation" />
	</header>

	<div class="container">
		<tiles:insertAttribute name="body" />
	</div>

	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>

</body>
</html>