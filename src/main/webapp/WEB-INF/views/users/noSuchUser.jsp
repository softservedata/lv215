<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page
	import="com.softserve.edu.schedule.controller.constants.UserControllerConst"%>

<body>
	<div class="container">
		<div class="row">
			<div
				class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
				<h4 class="text-center">
					<spring:message code="lbl.user.sorry" />
				</h4>
				<div class="form-group text-center">
					<spring:message code="lbl.user.noSuchUser" />
				</div>
				<div class="form-group text-center">
					<a class="btn btn-default"
						href="${pageContext.request.contextPath}/${UserControllerConst.RESTORE_PASSWORD_MAPPING}"><spring:message
							code="lbl.user.tryAgain" /></a> <a class="btn btn-default"
						href="${pageContext.request.contextPath}/"><spring:message
							code="lbl.form.cancel" /></a>
				</div>
			</div>
		</div>
	</div>
</body>