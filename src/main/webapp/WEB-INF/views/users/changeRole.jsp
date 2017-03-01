<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.softserve.edu.schedule.controller.constants.UserControllerConst"%>

<body>
	<div class="container">
		<div class="row">
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
					<spring:message code="lbl.user.update" />
				</h3>
				<form:form modelAttribute="${UserControllerConst.USER_MODEL_ATTR}"
					action="${pageContext.request.contextPath}${UserControllerConst.SAVE_CHANGED_ROLE_MAPPING}${user.id}"
					method="post">
					<div class="form-group">
						<form:select class="form-control" path="role">
							<c:forEach var="role" items="${roles}">
								<option value="${role}"><spring:message
										code="lbl.user.${role.getRole()}" /></option>
							</c:forEach>
						</form:select>
					</div>
					<div class="form-group text-center">
						<input type="submit" class="btn btn-default"
							value="<spring:message code="lbl.form.save"/>"> <a
							class="btn btn-default"
							href="${pageContext.request.contextPath}${UserControllerConst.USERS_MAPPING_FROM_HEADER}"><spring:message
								code="lbl.form.cancel" /></a>
					</div>
				</form:form>
			</div>
			<div
				class="col-lg-1 col-lg-offset-1 col-md-1 col-sm-1 col-xs-1 panel-exit text-center">
				<h3>
					<a
						href="${pageContext.request.contextPath}${UserControllerConst.USERS_MAPPING_FROM_HEADER}">
						<i class="fa fa-table fa-lg"></i>
					</a>
				</h3>
			</div>
		</div>
	</div>
</body>