<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<div class="row">
		<div class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
			<h3 class="text-center"><spring:message code="lbl.roomequipment.editEquipment" /></h3>
			<form:form method="post" modelAttribute="equipmentForm">
				<form:hidden path="id" />
				<div class="form-group">
				<spring:message code="lbl.roomequipment.title" var = "title"/> 
					<label for="name"><spring:message code="lbl.roomequipment.editMessage" /></label>
					<form:input class="form-control" path="name" placeholder="${title}" />
				</div>
				<div class="form-group text-center">
					<input type="submit" class="btn btn-default"
						value="<spring:message code="lbl.form.save"/>"> <a
						class="btn btn-default"
						href="${pageContext.request.contextPath}/roomequipments"><spring:message
							code="lbl.form.cancel" /></a>
				</div>
			</form:form>
		</div>
	</div>
</div>
