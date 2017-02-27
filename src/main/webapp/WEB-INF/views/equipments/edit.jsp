<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<div class="row">
		<div
			class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
			<h3 class="text-center">
				<spring:message code="lbl.roomequipment.editEquipment" />
			</h3>
			<form:form method="post" modelAttribute="equipmentForm">
				<form:hidden path="id" />
				<div class="form-group">
					<spring:message code="lbl.roomequipment.title" var="title" />
					<label for="name">
						<spring:message code="lbl.roomequipment.editMessage" />
					</label>
					<spring:message code="vm.invalidName" var="invalidName" />
					<form:input class="form-control" path="name" placeholder="${title}"
						pattern="[а-яА-ЯёЁіІєЄїЇa-zA-Z0-9№',\.\s\-]{2,254}" required="true"
						oninvalid="this.setCustomValidity('${invalidName}')" oninput="setCustomValidity('')" />
					<form:errors path="name" class="text-danger" />
				</div>
				<div class="form-group text-center">
					<input type="submit" class="btn btn-default" value="<spring:message code="lbl.form.save"/>">
					<a class="btn btn-default"
						href="${pageContext.request.contextPath}/roomequipments/edit/${equipmentForm.id}">
						<spring:message code="lbl.form.reset" />
					</a>
					<a class="btn btn-default" href="${pageContext.request.contextPath}/roomequipments">
						<spring:message code="lbl.form.cancel" />
					</a>
				</div>
			</form:form>
		</div>
	</div>
</div>
