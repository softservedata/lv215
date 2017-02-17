<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<div class="row">
		<div
			class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
			<!-- class="col-lg-3 col-lg-offset-3 col-md-3 col-md-offset-3 col-sm-3 col-sm-offset-3 panel panel-default"> -->
			<h3 class="text-center">
				<spring:message code="lbl.user.login" />
			</h3>
			<c:if test="${param.fail eq true}">
				<h4 class="text-danger">
					<spring:message code="lbl.auth.errorMessadge" />
				</h4>
			</c:if>
			<form:form role="form"
				action="${pageContext.request.contextPath}/login" method="post">
				<div class="form-group">
					<label for="username"> <spring:message code="lbl.user.mail" />
					</label>
					<spring:message code="lbl.user.mail" var="nameForPlaceholder" />
					<input class="form-control" type="email" name="username"
						id="username" placeholder="${nameForPlaceholder}" required>
				</div>
				<div class="form-group">
					<label for="password"> <spring:message
							code="lbl.user.password" />
					</label>
					<spring:message code="lbl.user.password" var="nameForPlaceholder" />
					<input class="form-control" name="password" id="password"
						type="password" placeholder="${nameForPlaceholder}" required>
				</div>
				<div class="form-group">
					<label for="remember-me"> <spring:message
							code="lbl.auth.rememberMe" />
					</label> <input type="checkbox" name="remember-me" id="remember-me"
						checked="checked">
				</div>
				<div class="form-group text-center">
					<input type="submit" class="btn btn-default"
						value="<spring:message code="lbl.auth.enter"/>" /> <a
						class="btn btn-default" href="${pageContext.request.contextPath}/">
						<spring:message code="lbl.form.cancel" />
					</a>
				</div>
			</form:form>
		</div>
	</div>
</div>