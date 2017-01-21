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
<spring:url value="/resources/chosen_v1.6.2/chosen.min.css"
	var="chosenCSS" />
<spring:url value="/resources/chosen_v1.6.2/chosen.jquery.min.js"
	var="chosenJs" />
<link href="${favico}" rel="shortcut icon">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<link rel="stylesheet" href="${chosenCSS}" />
<link rel="stylesheet" href="${mainCss}">
<link rel="stylesheet" href="${fontCss}">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="${chosenJs}"></script>
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