<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="com.softserve.edu.schedule.controller.LocationController"%>
<spring:url value="/resources/js/maps.js" var="mapsJS" />
<div class="container">
	<div class="row ">
		<div
			class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-11 col-xs-11 panel-exit">
			<h3 class="text-center v-alighn">
				<i class="fa fa-building-o"></i> ${map.name} <i
					class="fa fa-map-marker"></i> ${map.address}
			</h3>
			<input type="hidden" id="coordinates" value="${map.coordinates}" />
		</div>
		<div
			class="col-lg-1 col-lg-offset-1 col-md-1 col-sm-1 col-xs-1 panel-exit">
			<h3>
				<a class="align-right"
					href="${pageContext.request.contextPath}${LocationController.LOCATIONS_MAPPING}"
					title="<spring:message code="lbl.location.title" />"> <i
					class="fa fa-table fa-lg"></i>
				</a>
			</h3>
		</div>
	</div>
	<div class="row ">
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div id="map"></div>
		</div>
	</div>
</div>

<script type="text/javascript" src="${mapsJS}"></script>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDxvtL6fscPTAt98KLMFSqXooLpfABVUes&callback=initMap">
	
</script>