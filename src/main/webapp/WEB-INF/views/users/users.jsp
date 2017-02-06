<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>
<%@ page import="com.softserve.edu.schedule.controller.RegistrationController"%>


<h3 class="text-center">
	<spring:message code="lbl.user.title" />
</h3>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th></th>
			<th>
				<spring:message code="lbl.user.name" />
				<%-- <a
				href="${pageContext.request.contextPath}${UserController.SORT_BY_LASTNAME_ASC_MAPPING}"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a
				href="${pageContext.request.contextPath}${UserController.SORT_BY_LASTNAME_DESC_MAPPING}"><i
					class="fa fa-arrow-circle-o-down"></i></a> --%>
				<a href="${pageContext.request.contextPath}/users?sortByField=1&sortOrder=1&pageNumber=0"
					title="<spring:message code="lbl.user.sortAsc" />">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a href="${pageContext.request.contextPath}/users?sortByField=1&sortOrder=2&pageNumber=0"
					title="<spring:message code="lbl.user.sortDesc" />">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th>
				<spring:message code="lbl.user.mail" />
			</th>
			<th>
				<spring:message code="lbl.user.phone" />
			</th>
			<th>
				<spring:message code="lbl.user.position" />
				<%-- <a
				href="${pageContext.request.contextPath}${UserController.SORT_BY_POSITION_ASC_MAPPING}"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a
				href="${pageContext.request.contextPath}${UserController.SORT_BY_POSITION_DESC_MAPPING}"><i
					class="fa fa-arrow-circle-o-down"></i></a> --%>
				<a href="${pageContext.request.contextPath}/users?sortByField=1&sortOrder=1&pageNumber=0"
					title="<spring:message code="lbl.user.sortAsc" />">
					<i class="fa fa-arrow-circle-o-up fa-lg"></i>
				</a>
				<a href="${pageContext.request.contextPath}/users?sortByField=1&sortOrder=2&pageNumber=0"
					title="<spring:message code="lbl.user.sortDesc" />">
					<i class="fa fa-arrow-circle-o-down fa-lg"></i>
				</a>
			</th>
			<th>
				<spring:message code="lbl.user.role" />
			</th>
			<th>
				<spring:message code="lbl.user.group" />
			</th>
			<th></th>
			<th></th>
			<th></th>
			<th>
				<a
					href="${pageContext.request.contextPath}/${RegistrationController.USER_REGIST_MAPPING_FOR_ADMIN}">
					<i class="fa fa-plus"></i>
				</a>
			</th>
		</tr>




		<tr>
			<td></td>
			<form:form action="${pageContext.request.contextPath}/users"
				modelAttribute="${UserController.FILTER_MODEL_ATTR}">
				<td>
					<spring:message code="lbl.user.search" var="search" />
					<form:input class="form-control" path="lastName" placeholder=" ${search}" />
				</td>
				<td></td>
				<td></td>
				<td>
					<spring:message code="lbl.user.search" var="search" />
					<form:input class="form-control" path="position" placeholder=" ${search}" />
				</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td class="text-center v-alighn">
					<button type="submit" class="btn btn-link"
						title="<spring:message code="lbl.room.applyFilter"/>">
						<i class="fa fa-search fa-lg"></i>
					</button>
				</td>
			</form:form>
			<td class="v-alighn">
				<a href="${pageContext.request.contextPath}/users?lastName=&position="
					title="<spring:message code="lbl.room.resetFilter"/>">
					<i class="fa fa-times fa-lg"></i>
				</a>
			</td>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td></td>
				<td>
					<a href="${pageContext.request.contextPath}/${UserController.USER_PROFILE_MAPPING}${user.id}">${user.lastName}
						${user.firstName}</a>
				</td>
				<td>${user.mail}</td>
				<td>${user.phone}</td>
				<td>${user.position}</td>
				<td>
					<spring:message code="lbl.user.${user.role.getRole()}" />
				</td>
				<td>
					<c:forEach items="${user.groups}" var="group">
						<p>${group.name}</p>
					</c:forEach>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}${UserController.DELETE_USER_MAPPING}${user.id}"
						onclick="return confirm('The user can not be deleted if he is curated group. Are you sure you want to delete this user?');">
						<i class="fa fa-trash-o"></i>
					</a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}${UserController.CHANGE_ROLE_MAPPING}${user.id}">
						<i class="fa fa-pencil-square-o"></i>
					</a>
				</td>
				<td>
					<c:if test="${user.status.ordinal() == 1}">
						<a href="${pageContext.request.contextPath}${UserController.BAN_USER_MAPPING}${user.id}">
							<i class="fa fa-ban"></i>
						</a>
					</c:if>
				</td>
				<td>
					<c:if test="${user.status.ordinal() == 2 or user.status.ordinal() == 0}">
						<a href="${pageContext.request.contextPath}${UserController.UNBAN_USER_MAPPING}${user.id}">
							<i class="fa fa-check-circle-o"></i>
						</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<div class="row">
	<div class="col-md-2">
		<p>
			<spring:message code="lbl.form.resPerPage" />
		</p>
		<c:choose>
			<c:when test="${userPaginator.pageSize eq 5}">
				<a class="btn btn-primary" href="#">5</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default"
					href="${pageContext.request.contextPath}/users?pageSize=5&pageNumber=0">5</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${userPaginator.pageSize eq 10}">
				<a class="btn btn-primary" href="#">10</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default"
					href="${pageContext.request.contextPath}/users?pageSize=10&pageNumber=0">10</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${userPaginator.pageSize eq 20}">
				<a class="btn btn-primary" href="#">20</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-default"
					href="${pageContext.request.contextPath}/users?pageSize=20&pageNumber=0">20</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="col-md-10 text-center">
		<ul id="paginationList" class="pagination"></ul>
	</div>
</div>
<script>
 $('#paginationList').twbsPagination({
        totalPages: ${userPaginator.pagesCount + 1},
        startPage: ${userPaginator.pageNumber + 1},
        visiblePages: 10,
        first: '<spring:message code="lbl.pager.first"/>',
        last: '<spring:message code="lbl.pager.last"/>',
        prev: '<spring:message code="lbl.pager.previous"/>',
        next: '<spring:message code="lbl.pager.next"/>',
        initiateStartPageClick: false,        
        onPageClick: function (event, page) {
        	window.location = "users?pageNumber=" + (page-1);        	
        }
    });
</script>