<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">
$(function(){
	$("select[name=location]").chosen({width: "50%"});
	$("select[name=equipments]").chosen({width: "50%"});	
})
</script>

<div class="row">
	<div class="col-md-6">
		<h3>Add room</h3>
		<form:form role="form" method="post" modelAttribute="room">
			<form:errors path="*" />
			<div class="form-group">
				<label for="location">Location:</label>
				<form:select class="form-control" path="location" id="location">
					<option value="0"></option>
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
			<a class="btn btn-danger" href="create">Reset form</a>			
			<button class="btn btn-danger" onclick="goBack()">Cancel</button>
		</form:form>
	</div>
</div>
<br>