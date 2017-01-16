<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="/resources/img/favicon.ico" rel="shortcut icon">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" href="/resources/chosen_v1.6.2/chosen.min.css" />
<link rel="stylesheet" href="/resources/css/main.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="/resources/chosen_v1.6.2/chosen.jquery.min.js"></script>
<title><tiles:getAsString name="title" /></title>
</head>
<body>

	<header>
		<tiles:insertAttribute name="header" />
	</header>
	<!-- Основной контент сторінки -->
	<div class="container">
		<tiles:insertAttribute name="body" />
	</div>
	<footer>
		<!-- футер сторінки -->
		<tiles:insertAttribute name="footer" />
	</footer>

</body>
</html>