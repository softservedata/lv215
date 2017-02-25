<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.softserve.edu.schedule.controller.RoomController"%>

<div class="container">
	<div class="row">
		<div
			class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
			<h3 class="text-center">
				<spring:message code="lbl.room.editRoom" />
			</h3>
			<form:form role="form" method="post" modelAttribute="${RoomController.ROOM_MODEL_ATTR}">
				<form:input path="id" type="hidden" />
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
					<spring:message code="vm.ivalidRoomCapacity" var="invalidCapacity" />
					<spring:message code="lbl.room.roomCapacity" var="capacityForPlaceholder" />
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
					<a class="btn btn-default" href="${pageContext.request.contextPath}/rooms/edit/${room.id}">
						<spring:message code="lbl.form.reset" />
					</a>
					<a class="btn btn-default" href="${pageContext.request.contextPath}/rooms">
						<spring:message code="lbl.form.cancel" />
					</a>
				</div>
			</form:form>
		</div>
		<!-- room images -->
		<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<spring:message code="lbl.form.deleteTitle" />
						</h4>
					</div>
					<div class="modal-body">
						<p>
							<spring:message code="lbl.room.deleteRoomImageConfirm" />
						</p>
					</div>
					<div class="modal-footer">
						<a class="btn btn-primary btn-ok">
							<spring:message code="lbl.form.delete" />
						</a>
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<spring:message code="lbl.form.cancel" />
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div
				class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
				<h4>
					<spring:message code="lbl.room.uploadRoomPhoto" />
					:
				</h4>
				<form class="form-inline"
					action="${pageContext.request.contextPath}/rooms/edit/${room.id}/uploadfile?${_csrf.parameterName}=${_csrf.token}"
					method="POST" enctype="multipart/form-data">
					<div class="form-group">
						<div class="input-group">
							<label class="input-group-btn">
								<span class="btn btn-primary"><spring:message code="lbl.filePicker.browse" />&hellip;
									<input type="file" style="display: none;" name="file" accept="image/*"> </span>
							</label>
							<input type="text" class="form-control" readonly>
						</div>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-default" value="<spring:message code="lbl.form.save"/>" />
					</div>
				</form>
				<h4>
					<spring:message code="lbl.room.roomPhotos" />
					:
				</h4>
				<ul class="image_list">
					<c:forEach items="${roomFiles}" var="fileName">
						<li>
							<a class="room_image"
								href="${pageContext.request.contextPath}/rooms/downloadFile/${fileName}/${room.id}"
								title="<spring:message code="lbl.room.clickToDownload"/>">
								<img src="${pageContext.request.contextPath}/rooms/downloadFile/${fileName}/${room.id}"
									height="200" class="img-fluid" alt="${fileName}">
							</a>
							<a class="room_image_delete" data-toggle="modal" data-target="#confirm-delete"
								data-href="${pageContext.request.contextPath}/rooms/deleteFile/${fileName}/${room.id}"
								title="<spring:message code="lbl.room.deletePhoto"/>">
								<i class="fa fa-lg fa-times" aria-hidden="true"></i>
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!-- room images -->
	</div>
</div>

<spring:url value="/resources/js/rooms/create.js" var="roomsCreateJS" />
<script type="text/javascript" src="${roomsCreateJS}">
	
</script>