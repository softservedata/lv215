<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="com.softserve.edu.schedule.controller.UserGroupController"%>

<script type="text/javascript">
	$(function() {
		$("select[name=users]").chosen({
			width : "100%"
		});
		$("select[name=curators]").chosen({
			width : "100%"
		});
	})
	
	function validateForm() {
		document.getElementById("nameErrorMsg").innerHTML = "";
		document.getElementById("descriptionErrorMsg").innerHTML = "";

		var isValid = true;
		
		var description = document.getElementById("description");
		var name = document.getElementById("name");
		
		if(name.value.length < "${UserGroupController.MIN_GROUP_NAME_LENGTH}") {
			document.getElementById("nameErrorMsg").innerHTML = '<spring:message code="lbl.group.shortName"/>';
			isValid = false;
		}
		if(name.value.length > "${UserGroupController.MAX_GROUP_NAME_LENGTH}") {
			document.getElementById("nameErrorMsg").innerHTML = '<spring:message code="lbl.group.longName"/>';
			isValid = false;
		}
		if (description.value.length < "${UserGroupController.MIN_GROUP_DESCRIPTION_LENGTH}") {
			document.getElementById("descriptionErrorMsg").innerHTML = '<spring:message code="lbl.group.shortDescription"/>';
			isValid = false;
		} else if (description.value.length > "${UserGroupController.MAX_GROUP_DESCRIPTION_LENGTH}") {
			document.getElementById("descriptionErrorMsg").innerHTML = '<spring:message code="lbl.group.longDescription"/>';
			isValid = false;
		}
		return isValid;
	}
</script>
<div class="container">
	<div class="row">
		<div
			class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">

			<h3 class="text-center">
				<spring:message code="lbl.group.create" />
			</h3>
			<form:form name="creationForm" method="post"
				modelAttribute="userGroupForm" onsubmit="return validateForm(this);">
				<form:hidden path="id" />

				<spring:message code="lbl.group.title" var="title" />
				<div class="form-group">
					<label for="Title">${title}</label>
					<form:input path="name" class="form-control" placeholder="${title}" id="name"/>
					<form:errors path="name" class="text-danger" />
					<p class="text-danger" id="nameErrorMsg"></p>
				</div>

				<spring:message code="lbl.group.description" var="description" />
				<div class="form-group">
					<label for="description">${description}</label>
					<form:textarea path="description" name="description"
						id="description" class="form-control" placeholder="${description}"
						min="15" max="100" />
					<form:errors path="description" class="text-danger"
						id="descriptionError" />
					<p class="text-danger" id="descriptionErrorMsg"></p>
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
