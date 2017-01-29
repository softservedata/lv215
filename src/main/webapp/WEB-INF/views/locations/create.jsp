<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="com.softserve.edu.schedule.controller.LocationController"%>

<div class="container">
	<div class="row">
		<div class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
			<h3 class="text-center"><spring:message code="lbl.location.add" /></h3>
			<form:form method="post"
				modelAttribute="${LocationController.LOCATION_FORM_MODEL_ATTR}">
				<form:hidden path="id" />
				<div class="form-group">
					<label for="name"><spring:message code="lbl.location.name" /></label>
					<spring:message code="lbl.location.namePlaceholder" var="namePlaceholder" />
					<spring:message code="vm.invalidName" var="invalidName" />
					<form:input class="form-control" path="name" placeholder="${namePlaceholder}" pattern = "[а-яА-ЯёЁіІєЄїЇa-zA-Z0-9№',\.\s\-]{2,254}" title="${invalidName}" required = "true"/>
					<form:errors path="name" style="color: red"/>
				</div>
				<div class="form-group">
					<label for="address"><spring:message code="lbl.location.address" /></label>
					<spring:message code="lbl.location.addressPlaceholder" var="addressPlaceholder" />
					<spring:message code="vm.invalidAddress" var="invalidAddress" />
					<form:input class="form-control" path="address"
						placeholder="${addressPlaceholder}" pattern = "[а-яА-ЯёЁіІєЄїЇa-zA-Z0-9№',\.\s\-]{10,254}" title="${invalidAddress}" required = "true"/>
						<form:errors path="address" style="color: red"/>
				</div>
				<div class="form-group">
					<label for="coordinates"><spring:message code="lbl.location.coordinates" /></label>
					<form:input class="form-control" path="coordinates"
						placeholder="Coordinates"/>
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