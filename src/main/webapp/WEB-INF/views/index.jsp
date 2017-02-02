<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="row">
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
</div>