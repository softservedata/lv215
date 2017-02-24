<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>
<body>
	<div class="container">
		<div class="row ">
			<div
				class="col-lg-1 col-lg-offset-2 col-md-1 col-sm-1 col-xs-1 panel-exit text-center">
				<h3>
					<a href="#" onclick="window.history.back()"
						title="<spring:message code="lbl.form.back" />"> <i
						class="fa fa-arrow-left"></i>
					</a>
				</h3>
			</div>
			<div
				class="col-lg-4 col-lg-offset-1 col-md-10 col-sm-10 col-xs-10 panel panel-default">
				<h3 class="text-center">
					<spring:message code="lbl.user.profile" />
				</h3>
				<div class="form-group">
					<ul>
						<c:forEach items="${userFiles}" var="fileName">
							<li style="list-style-type: none"><a
								style="display: inline-block;"
								href="downloadFile/${fileName}/${user.id}"> <img
									src="downloadFile/${fileName}/${user.id}" height="200"
									class="img-fluid" alt="${fileName}">
							</a></li>
						</c:forEach>
					</ul>
				</div>
				<div class="form-group">
					<form
						action="${pageContext.request.contextPath}${UserController.SAVE_IMAGES}?${_csrf.parameterName}=${_csrf.token}"
						method="POST" enctype="multipart/form-data">
						<div class="form-group">
							<input type="file" name="image" /> <input type="submit"
								class="form-control"
								value="<spring:message code="lbl.form.save"/>" />
						</div>
					</form>
				</div>

				<%-- 	<form:form commandName="${UserController.USER_MODEL_ATTR}"
					action="${pageContext.request.contextPath}${UserController.SAVE_IMAGES}?${_csrf.parameterName}=${_csrf.token}"
					method="post" enctype="multipart/form-data">
					<br>
					<input type="file" name="image">
					<br>
				</form:form> --%>
				<%-- <div class="form-group">
					<input type="submit" class="form-control"
						value="<spring:message code="lbl.form.save"/>">
				</div> --%>
				<div class="form-group">
					<label for="firstName"><spring:message
							code="lbl.user.firstName" />:</label> <b>${user.firstName}</b>
				</div>
				<div class="form-group">
					<label for="lastName"><spring:message
							code="lbl.user.lastName" />:</label> <b>${user.lastName}</b>
				</div>
				<div class="form-group">
					<label for="mail"><spring:message code="lbl.user.mail" />:</label>
					<b>${user.mail}</b>
				</div>
				<div class="form-group">
					<label for="phone"><spring:message code="lbl.user.phone" />:</label>
					<b>${user.phone}</b>
				</div>
				<div class="form-group">
					<label for="position"><spring:message
							code="lbl.user.position" />:</label> <b>${user.position}</b>
				</div>
				<div class="form-group">
					<a class="form-control"
						href="${pageContext.request.contextPath}/${UserController.UPDATE_USER_MAPPING}${user.id}">
						<spring:message code="lbl.user.update" />
					</a>
				</div>
				<div class="form-group">
					<a class="form-control"
						href="${pageContext.request.contextPath}/${UserController.CHANGE_PASSWORD_MAPPING}${user.id}">
						<spring:message code="lbl.user.changePassword" />
					</a>
				</div>
			</div>
			<div
				class="col-lg-1 col-lg-offset-1 col-md-1 col-sm-1 col-xs-1 panel-exit text-center">
				<h3>
					<a
						href="${pageContext.request.contextPath}${UserController.USERS_MAPPING_FROM_HEADER}">
						<i class="fa fa-table fa-lg"></i>
					</a>
				</h3>
			</div>
		</div>
	</div>
</body>
