<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h3 class="text-center"><spring:message code="lbl.roomequipment.title" /></h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th><spring:message code="lbl.roomequipment.id" /></th>
			<th><spring:message code="lbl.roomequipment.name" /><a
				href="${pageContext.request.contextPath}/roomequipments/sortbynameasc"
				title=<spring:message code="lbl.roomequipment.sortASC" />><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="${pageContext.request.contextPath}/roomequipments/sortbynamedesc"
				title=<spring:message code="lbl.roomequipment.sortDESC" />><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<th><spring:message code="lbl.roomequipment.edit" /></th>
			<th><spring:message code="lbl.roomequipment.delete"/></th>
			<th><a
				href="${pageContext.request.contextPath}/roomequipments/create"
				title=<spring:message code="lbl.roomequipment.addEquipment" />><i class="fa fa-plus fa-lg"></i></a></th>
		</tr>
		<c:forEach var="equipment" items="${equipments}">
			<tr>
				<td>${equipment.id}</td>
				<td>${equipment.name}</td>
				<td><a
					href="${pageContext.request.contextPath}/roomequipments/edit/${equipment.id}"
					title=<spring:message code="lbl.roomequipment.editEquipment"/>><i class="fa fa-pencil-square-o fa-lg"></i></a></td>
				<td><a
					href="${pageContext.request.contextPath}/roomequipments/delete/${equipment.id}"
					onclick="return confirm('<spring:message code="lbl.roomequipment.deleteConfirm"/>');"
					title=<spring:message code="lbl.roomequipment.deleteEquipment"/>><i class="fa fa-trash-o fa-lg"></i></a></td>
				<td></td>
			</tr>
		</c:forEach>
	</table>
</div>