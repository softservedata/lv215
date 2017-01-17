<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-6">
		<h3>Add room</h3>
		<form:form role="form" method="post" modelAttribute="room">
			<form:errors path="*" />
			<div class="form-group">
				<label for="location">Location:</label>
				<form:select class="form-control" path="location" id="location">
					<c:forEach items="${locations}" var="location">
						<option value="${location.id}">${location.name}</option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group">
				<label for="name">Room name:</label>
				<form:input type="text" class="form-control" path="name" id="name"
					placeholder="room name" required="true" />
			</div>
			<div class="form-group">
				<label for="capacity">Room capacity:</label>
				<form:input class="form-control" type="number" path="capacity"
					id="capacity" min="1" step="1" required="true" />
			</div>
			<div class="form-group">
				<label for="equipments">Room equipments:</label>
				<form:select class="form-control" path="equipments" id="equipments"
					multiple="multiple">
					<c:forEach items="${equipments}" var="equipment">
						<option value="${equipment.id}">${equipment.name}</option>
					</c:forEach>
				</form:select>
			</div>
			<input type="submit" class="btn btn-primary" value="Save room">
			<input type="reset" class="btn btn-danger" value="Reset form">
		</form:form>
	</div>
</div>
<br>