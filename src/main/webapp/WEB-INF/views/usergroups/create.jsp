<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

			<h3 class="text-center">CREATE GROUP</h3>
			<form:form method="post" modelAttribute="userGroupForm">
				<form:hidden path="id" />

				<div class="form-group">
					<label for="Title">Title</label>
					<form:input path="name" class="form-control" placeholder="Title"
						required="true" />
				</div>

				<div class="form-group">
					<label for="description">Description</label>
					<form:input path="description" class="form-control"
						placeholder="Description" required="true" />
				</div>


				<div class="form-group">
					<label for="level">Level</label>
					<form:input path="level" class="form-control" placeholder="Level"
						required="true" />
				</div>


				<label for="curator">Curator</label>
				<form:select class="form-control" path="curator" id="curator">
					<c:forEach items="${curators}" var="curator">
						<c:choose>
							<c:when test="${userGroupForm.curator.id eq curator.id}">
								<option value="${curator.id}" selected="selected">${curator.lastName}</option>
							</c:when>
							<c:otherwise>
								<option value="${curator.id}">${curator.lastName}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>

				<label for="users">Members</label>
				<form:select class="form-control" path="users" id="users"
					multiple="multiple">
					<c:forEach items="${allUsers}" var="user">
						<c:set var="found" value="false" />
						<c:forEach items="${userGroupForm.users}" var="userInGroup">
							<c:if test="${!found}">
								<c:if test="${userInGroup.id eq user.id}">
									<option value="${user.id}" selected="selected">${user.lastName}</option>
									<c:set var="found" value="true" />
								</c:if>
							</c:if>
						</c:forEach>
						<c:if test="${!found}">
							<option value="${user.id}">${user.lastName}</option>
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