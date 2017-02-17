<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="row">
	<div
		class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 panel panel-default">
		<h3>
			<spring:message code="label.error.message" />
		</h3>
		<p>
			<b><spring:message code="label.error.errorType" />: </b>${exception.getClass()}</p>
		<p>
			<b><spring:message code="label.error.errorMessage" />: </b>${exception.getMessage()}</p>
		<p>
			<b><spring:message code="label.error.causedBy" />: </b>${exception.getCause()}</p>
		<p>
			<b><spring:message code="label.error.errorFullName" />: </b>${exception.toString()}</p>
		<p class="text-center">
			<a class="btn btn-default" href="${pageContext.request.contextPath}/">
				<spring:message code="lbl.form.mainPage" />
			</a>
		</p>
		<br> <br> <br>
	</div>
</div>