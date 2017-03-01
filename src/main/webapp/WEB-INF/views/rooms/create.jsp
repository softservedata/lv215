<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">
	<div class="row">
		<div
			class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
			<h3 class="text-center">
				<spring:message code="lbl.room.createRoom" />
			</h3>
			<form:form role="form" method="post" modelAttribute="room">
				<div class="form-group">
					<label for="location">
						<spring:message code="lbl.room.location" />
						:
					</label>
					<form:select class="form-control" path="location" id="location">
						<c:forEach items="${locations}" var="location">
							<c:choose>
								<c:when test="${room.location.id eq location.id}">
									<option value="${location.id}" selected="selected">${location.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${location.id}">${location.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
					<form:errors path="location" class="text-danger" />
				</div>
				<div class="form-group">
					<label for="name">
						<spring:message code="lbl.room.roomName" />
						:
					</label>
					<spring:message code="lbl.room.roomName" var="nameForPlaceholder" />
					<spring:message code="vm.invalidName" var="invalidName" />
					<form:input type="text" class="form-control" path="name" id="name"
						placeholder="${nameForPlaceholder}" pattern="[а-яА-ЯёЁіІєЄїЇa-zA-Z0-9№',\.\s\-]{2,254}"
						required="true" oninvalid="this.setCustomValidity('${invalidName}')"
						oninput="setCustomValidity('')" />
					<form:errors path="name" class="text-danger" />
				</div>
				<div class="form-group">
					<label for="capacity">
						<spring:message code="lbl.room.roomCapacity" />
						:
					</label>
					<spring:message code="lbl.room.roomCapacity" var="capacityForPlaceholder" />
					<spring:message code="vm.ivalidRoomCapacity" var="invalidCapacity" />
					<form:input class="form-control" type="number" path="capacity" id="capacity" min="1"
						max="50000" step="1" placeholder="${capacityForPlaceholder}" required="true"
						oninvalid="this.setCustomValidity('${invalidCapacity}')" oninput="setCustomValidity('')" />
					<form:errors path="capacity" class="text-danger" />
				</div>
				<div class="form-group">
					<label for="equipments">
						<spring:message code="lbl.room.roomEquipments" />
						:
					</label>
					<form:select class="form-control" path="equipments" id="equipments" multiple="multiple">
						<c:forEach items="${equipments}" var="equipment">
							<c:set var="found" value="false" />
							<c:forEach items="${room.equipments}" var="equipmentInRoom">
								<c:if test="${!found}">
									<c:if test="${equipmentInRoom.id eq equipment.id}">
										<option value="${equipment.id}" selected="selected">${equipment.name}</option>
										<c:set var="found" value="true" />
									</c:if>
								</c:if>
							</c:forEach>
							<c:if test="${!found}">
								<option value="${equipment.id}">${equipment.name}</option>
							</c:if>
						</c:forEach>
					</form:select>
				</div>
				<div class="form-group text-center">
					<input type="submit" class="btn btn-default" value="<spring:message code="lbl.form.save"/>">
					<a class="btn btn-default" href="create">
						<spring:message code="lbl.form.reset" />
					</a>
					<a class="btn btn-default" href="${pageContext.request.contextPath}/rooms">
						<spring:message code="lbl.form.cancel" />
					</a>
				</div>
			</form:form>
		</div>
	</div>
</div>

<spring:url value="/resources/js/rooms/create.js" var="roomsCreateJS" />
<script type="text/javascript" src="${roomsCreateJS}">
	
</script>