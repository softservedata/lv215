<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.softserve.edu.schedule.controller.RoomController"%>

<script type="text/javascript">
	$(function() {
		$("select[name=locationId]").chosen({width : "100%"});
		$("select[name=equipments]").chosen({width : "100%"});
		$("select[name=sortByField]").chosen({width : "75%"});
		$("select[name=sortOrder]").chosen({width : "75%"});
	})
</script>

<h3 class="text-center"><spring:message code="lbl.room.title"/></h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th><spring:message code="lbl.room.location"/></th>
			<th><spring:message code="lbl.room.address"/></th>
			<th><spring:message code="lbl.room.roomName"/></th>
			<th><spring:message code="lbl.room.roomCapacity"/></th>
			<th><spring:message code="lbl.room.roomEquipments"/></th>
			<th class="text-center v-alighn">
				<button class="btn btn-link" data-toggle="collapse" data-target="#showfilter" title="<spring:message code="lbl.room.showFilter"/>">
					<i class="fa fa-filter fa-lg"></i>
				</button>
			</th>
			<th class="text-center v-alighn">
				<a href="${RoomController.ROOM_CREATE_URL}" title="<spring:message code="lbl.room.createRoom"/>">
					<i class="fa fa-plus fa-lg"></i>
				</a>
			</th>
		</tr>
		<c:choose>
			<c:when test="${roomFilter.showFilter eq true}">		
				<tr class="collapse in" id="showfilter">
			</c:when>
			<c:otherwise>
				<tr class="collapse" id="showfilter">
			</c:otherwise>
		</c:choose>
			<form:form role="form" action="rooms" method="get" modelAttribute="${RoomController.FILTER_MODEL_ATTR}">
			<form:input path="showFilter" type="hidden" value="true"/>
			<td colspan="2">
				<div class="form-group">
					<form:select class="form-control" path="locationId" id="locationId">
						<option value="0"></option>
						<c:forEach items="${locations}" var="location">
							<c:choose>
								<c:when test="${roomFilter.locationId eq location.id}">
									<option value="${location.id}" selected="selected">${location.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${location.id}">${location.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</div>				
				<div class="form-group" id="sortfield">
					<label for="sortByField"><spring:message code="lbl.room.sortBy"/>:</label> 
					<form:select class="form-control" path="sortByField" id="sortByField">
						<option value="0"></option>
						<c:choose>
							<c:when test="${roomFilter.sortByField eq 1}">
								<option value="1" selected="selected"><spring:message code="lbl.room.sortByLocation"/></option>
							</c:when>
							<c:otherwise>
								<option value="1"><spring:message code="lbl.room.sortByLocation"/></option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${roomFilter.sortByField eq 2}">
								<option value="2" selected="selected"><spring:message code="lbl.room.sortByRoomName"/></option>
							</c:when>
							<c:otherwise>
								<option value="2"><spring:message code="lbl.room.sortByRoomName"/></option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${roomFilter.sortByField eq 3}">
								<option value="3" selected="selected"><spring:message code="lbl.room.sortByCapacity"/></option>
							</c:when>
							<c:otherwise>
								<option value="3"><spring:message code="lbl.room.sortByCapacity"/></option>
							</c:otherwise>
						</c:choose>					
					</form:select>
				</div>
			</td>
			<td>
				<div class="form-group">
					<spring:message code="lbl.room.roomName" var="nameForPlaceholder"/>
					<form:input class="form-control" type="text" path="name" placeholder="${nameForPlaceholder}" />
				</div>
				<div class="form-group">
					<label for="sortOrder"><spring:message code="lbl.room.sortOrder"/>:</label>
					<form:select class="form-control" path="sortOrder" id="sortOrder">
						<option value="0"></option>
						<c:choose>
							<c:when test="${roomFilter.sortOrder eq 1}">
								<option value="1" selected="selected"><spring:message code="lbl.room.sortAsc"/></option>
							</c:when>
							<c:otherwise>
								<option value="1"><spring:message code="lbl.room.sortAsc"/></option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${roomFilter.sortOrder eq 2}">
								<option value="2" selected="selected"><spring:message code="lbl.room.sortDesc"/></option>
							</c:when>
							<c:otherwise>
								<option value="2"><spring:message code="lbl.room.sortDesc"/></option>
							</c:otherwise>
						</c:choose>							
					</form:select>
				</div>
			</td>
			<td>
				<div class="form-group">
					<spring:message code="lbl.room.filterMinCapacity" var="filterMinCapacity"/>
					<form:input type="number" class="form-control" path="minCapacity" placeholder="${filterMinCapacity}" step="1" />
				</div> 
				<div class="form-group">
					<spring:message code="lbl.room.filterMaxCapacity" var="filterMaxCapacity"/>
					<form:input	type="number" class="form-control" path="maxCapacity" placeholder="${filterMaxCapacity}" step="1" />
				</div>
			</td>
			<td>
				<div class="form-group">
					<form:select class="form-control" path="equipments" id="equipments"	multiple="multiple">
						<c:forEach items="${equipments}" var="equipment">
							<c:set var="found" value="false"/>
							<c:forEach items="${roomFilter.equipments}" var="equipmentInFilter">
								<c:if test="${!found}">							
									<c:if test="${equipmentInFilter.id eq equipment.id}">
										<option value="${equipment.id}" selected="selected">${equipment.name}</option>
										<c:set var="found" value="true"/>
									</c:if>
								</c:if>						
							</c:forEach>
							<c:if test="${!found}">
								<option value="${equipment.id}">${equipment.name}</option>
							</c:if>
						</c:forEach>					
					</form:select>
				</div>
			</td>
			<td class="text-center v-alighn">				
				<button type="submit" class="btn btn-link" title="<spring:message code="lbl.room.applyFilter"/>">
					<i class="fa fa-check-circle-o fa-lg"></i>
				</button>
			</td>
			</form:form>
			<td class="text-center v-alighn">
				<a href="rooms?showFilter=false&locationId=0&sortByField=0&name=&sortOrder=0&minCapacity=0&maxCapacity=0&_equipments=1" title="<spring:message code="lbl.room.resetFilter"/>">
					<i class="fa fa-ban fa-lg"></i>
				</a>
			</td>
		</tr>		
		<c:forEach items="${rooms}" var="room">
			<tr>
				<td>${room.location.name}</td>
				<td>${room.location.address}</td>
				<td><a href="rooms/${room.id}">${room.name}</a></td>
				<td>${room.capacity}</td>
				<td>
					<ul>
						<c:forEach items="${room.equipments}" var="equipment">
							<li>${equipment.name}</li>
						</c:forEach>
					</ul>
				</td>
				<td class="text-center v-alighn">
					<a href="rooms/delete/${room.id}" title="<spring:message code="lbl.room.deleteRoom"/>" onclick="return confirm('<spring:message code="lbl.room.deleteRoomConfirm"/>');">
						<i class="fa fa-trash-o fa-lg"></i>
					</a>
				</td>
				<td class="text-center v-alighn">
					<a href="rooms/edit/${room.id}" title="<spring:message code="lbl.room.editRoom"/>">
						<i class="fa fa-pencil-square-o fa-lg"></i>
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<br>