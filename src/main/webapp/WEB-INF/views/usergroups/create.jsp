<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
	$(function() {
		$("select[name=users]").chosen({
			width : "100%"
		});
		$("select[name=curators]").chosen({
			width : "100%"
		});
	})
</script>
<div class="container">
	<div class="row">
		<div
			class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">

			<h3 class="text-center">
				<spring:message code="lbl.group.create" />
			</h3>
			<form:form method="post" modelAttribute="userGroupForm">
				<form:hidden path="id" />

				<spring:message code="lbl.group.title" var="title"/>
				<div class="form-group">
					<label for="Title">${title}</label>
					<form:input path="name" class="form-control" placeholder="${title}" />
					<form:errors path="name" class="text-danger" />
				</div>

				<spring:message code="lbl.group.description" var="description"/>
				<div class="form-group">
					<label for="description">${description}</label>
					<form:input path="description" class="form-control"
						placeholder="${description}" />
					<form:errors path="description" class="text-danger" />
				</div>


				<div class="form-group">
					<label for="level"><spring:message code="lbl.group.level" /></label>
					<form:select class="form-control" path="level" id="level">
						<c:forEach items="${levels}" var="level">
							<option value="${level}"><spring:message
									code="lbl.group.${level.code}" /></option>
						</c:forEach>
					</form:select>
				</div>

				<label for="curator"><spring:message
						code="lbl.group.curator" /></label>
				<form:select class="form-control" path="curator" id="curator">
					<c:forEach items="${curators}" var="curator">
						<c:choose>
							<c:when test="${userGroupForm.curator.id eq curator.id}">
								<option value="${curator.id}" selected="selected">${curator.lastName}
									${user.firstName}</option>
							</c:when>
							<c:otherwise>
								<option value="${curator.id}">${curator.lastName}
									${curator.firstName}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>

				<label for="users"><spring:message code="lbl.group.members" /></label>
				<form:select class="form-control" path="users" id="users"
					multiple="multiple">
					<c:forEach items="${allUsers}" var="user">
						<c:set var="found" value="false" />
						<c:forEach items="${userGroupForm.users}" var="userInGroup">
							<c:if test="${!found}">
								<c:if test="${userInGroup.id eq user.id}">
									<option value="${user.id}" selected="selected">${user.lastName}
										${user.firstName.charAt(0)}.</option>
									<c:set var="found" value="true" />
								</c:if>
							</c:if>
						</c:forEach>
						<c:if test="${!found}">
							<option value="${user.id}">${user.lastName}
								${user.firstName.charAt(0)}.</option>
						</c:if>
					</c:forEach>
				</form:select>

				<div class="form-group text-center">
					<input type="submit" class="btn btn-default"
						value="<spring:message code="lbl.form.save"/>"> <a
						class="btn btn-default"
						href="${pageContext.request.contextPath}/usergroups"><spring:message
							code="lbl.form.cancel" /></a>
				</div>
			</form:form>
		</div>
	</div>
</div>