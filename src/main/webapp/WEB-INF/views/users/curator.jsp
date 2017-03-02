<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
	<div class="container">
		<div class="row">
			<div
				class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
				<h3 class="text-center"><spring:message code="lbl.user.user"/> ${user.lastName} ${user.firstName} <spring:message code="lbl.user.noDel"/></h3>
				<h3 class="text-center"><spring:message code="lbl.user.becouseCurator"/></h3>
			</div>
		</div>
	</div>
</body>