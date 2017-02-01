<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="com.softserve.edu.schedule.controller.SubjectController"%>

<h3 class="text-center">
	<spring:message code="lbl.subject.title" />
</h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th>ID</th>
			<th><spring:message code="lbl.subject.name" /> <a
				href="subjects?sortByField=1&sortOrder=1&pageNumber=0"
				title="<spring:message code="lbl.subject.sortAsc"/>"><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="subjects?sortByField=1&sortOrder=2&pageNumber=0"
				title="<spring:message code="lbl.subject.sortDesc"/>"><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<th><spring:message code="lbl.subject.description" /> <a
				href="subjects?sortByField=2&sortOrder=1&pageNumber=0"
				title="<spring:message code="lbl.subject.sortAsc"/>"><i
					class="fa fa-arrow-circle-o-up fa-lg"></i></a> <a
				href="subjects?sortByField=2&sortOrder=2&pageNumber=0"
				title="<spring:message code="lbl.subject.sortDesc"/>"><i
					class="fa fa-arrow-circle-o-down fa-lg"></i></a></th>
			<th><spring:message code="lbl.subject.tutor" /> </th>
			<th></th>
			<th class="text-center v-alighn"><a
				href="${pageContext.request.contextPath}${SubjectController.SUBJECT_CREATE_MAPPING}"
				title="<spring:message code="lbl.subject.add"/>"><i
					class="fa fa-plus fa-lg"></i></a></th>
		</tr>
		<tr>
		<form:form action="subjects" 
			modelAttribute="${SubjectController.FILTER_MODEL_ATTR}">
			<td></td>
			<td>
					<spring:message code="lbl.subject.search" var="search" />
					<form:input  class="form-control" path="name"
						placeholder=" ${search}" />
			</td>
			<td>
					<spring:message code="lbl.subject.search" var="search" />
					<form:input class="form-control" path="description"
						placeholder="${search}" />
			</td>
			<td>
					<form:select  class="form-control" path="userId">
						<option value="0"></option>
						<c:forEach items="${users}" var="user">
							<c:choose>
								<c:when test="${subjectFilter.userId eq user.id}">
									<option value="${user.id}" selected="selected">${user.firstName} ${user.lastName}</option>
								</c:when>
								<c:otherwise>
									<option value="${user.id}">${user.firstName} ${user.lastName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
			</td>
			<td class="text-center v-alighn">
				<button type="submit" class="btn btn-link"
					title="<spring:message code="lbl.room.applyFilter"/>">
					<i class="fa fa-search"></i>
				</button>
			</td>
		</form:form>
		<td class="text-center v-alighn"><a 
			href="subjects?name=&description=&userId=0"
			title="<spring:message code="lbl.room.resetFilter"/>"> <i
				class="fa fa-times fa-lg"></i>
		</a></td>
		</tr>
		<c:forEach var="subject" items="${subjects}">
			<tr>
				<td>${subject.id}</td>
				<td>${subject.name}</td>
				<td>${subject.description}</td>
				<td><c:forEach items="${subject.users}" var="user">
						<p>${user.firstName}${user.lastName}</p>
					</c:forEach></td>
				<td class="text-center v-alighn"><a
					href="${pageContext.request.contextPath}${SubjectController.SUBJECT_DELETE_MAPPING}${subject.id}"
					title="<spring:message code="lbl.subject.delete"/>"
					onclick="return confirm('<spring:message code="lbl.subject.deleteConfirm"/>')"><i
						class="fa fa-trash-o fa-lg"></i></a></td>
				<td class="text-center v-alighn"><a
					href="${pageContext.request.contextPath}${SubjectController.SUBJECT_EDIT_MAPPING}${subject.id}"
					title="<spring:message code="lbl.subject.edit"/>"><i
						class="fa fa-pencil-square-o fa-lg"></i></a></td>
			</tr>
		</c:forEach>
	</table>

</div>
<div class="row">
	<div class="col-md-2">
		<p><spring:message code="lbl.form.resPerPage"/></p>
		<c:choose>
			<c:when test="${subjectPaginator.pageSize eq 5}">
				<a class="btn btn-primary" href="#">5</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default" href="subjects?pageSize=5&pageNumber=0">5</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${subjectPaginator.pageSize eq 10}">
				<a class="btn btn-primary" href="#">10</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default" href="subjects?pageSize=10&pageNumber=0">10</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${subjectPaginator.pageSize eq 20}">
				<a class="btn btn-primary" href="#">20</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default" href="subjects?pageSize=20&pageNumber=0">20</a>
			</c:otherwise>
		</c:choose>	
	</div>
	<div class="col-md-10 text-center">	
		<ul id="paginationList" class="pagination"></ul>	
	</div>
</div>
<script>
 $('#paginationList').twbsPagination({
        totalPages: ${subjectPaginator.pagesCount + 1},
        startPage: ${subjectPaginator.pageNumber + 1},
        visiblePages: 10,
        initiateStartPageClick: false,        
        onPageClick: function (event, page) {
        	window.location = "subjects?pageNumber=" + (page-1);        	
        }
    });
</script>