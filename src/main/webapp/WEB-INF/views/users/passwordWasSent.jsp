<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<body>
	<div class="container">
		<div class="row">
			<div
				class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
				<br><h4 class="text-center">
					<spring:message code="lbl.user.dear" />
				</h4>
				<br><div class="form-group text-center">${user.lastName} ${user.firstName}</div>
				<div class="form-group text-center">
					<h5>
						<spring:message code="lbl.user.passwordSent" />
					</h5>
				</div>
				<div class="form-group text-center">
					<a class="btn btn-default"
						href="${pageContext.request.contextPath}/login"><spring:message
							code="lbl.nav.signIn" /></a>
				</div>
			</div>
		</div>
	</div>
</body>