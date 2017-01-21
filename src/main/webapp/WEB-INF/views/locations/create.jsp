<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="com.softserve.edu.schedule.controller.LocationController"%>

<div class="container">
	<div class="row">
		<div class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
			<h3 class="text-center">ADD LOCATION</h3>
			<form:form method="post"
				modelAttribute="${LocationController.LOCATION_FORM_MODEL_ATTR}">
				<form:hidden path="id" />
				<div class="form-group">
					<label for="name">TITLE</label>
					<form:input class="form-control" path="name" placeholder="Title" />
				</div>
				<div class="form-group">
					<label for="address">ADDRESS</label>
					<form:input class="form-control" path="address"
						placeholder="Address" />
				</div>
				<div class="form-group">
					<label for="coordinates">COORDINATES</label>
					<form:input class="form-control" path="coordinates"
						placeholder="Coordinates" />
				</div>
				<div class="form-group text-center">
					<input type="submit" class="btn btn-default"
						value="<spring:message code="lbl.form.save"/>"> <a
						class="btn btn-default"
						href="${pageContext.request.contextPath}${LocationController.LOCATIONS_MAPPING}"><spring:message
							code="lbl.form.cancel" /></a>
				</div>
			</form:form>
		</div>
	</div>
</div>