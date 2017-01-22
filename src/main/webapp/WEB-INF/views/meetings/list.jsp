<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Meetings</title>
</head>
<body>
	<h2>Meetings</h2>
	<table class="table table-hover">
		<tr>
			<th>ID</th>
			<th>Description <a
				href="${pageContext.request.contextPath}/meetings/sortbydescriptionasc"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a
				href="${pageContext.request.contextPath}/meetings/sortbydescriptiondesc"><i
					class="fa fa-arrow-circle-o-down"></i> </a>

			</th>
			<th>Subject<a
				href="${pageContext.request.contextPath}/meetings/sortbysubjectasc">
					<i class="fa fa-arrow-circle-o-up"></i>
			</a> <a
				href="${pageContext.request.contextPath}/meetings/sortbysubjectdesc"><i
					class="fa fa-arrow-circle-o-down"></i> </a>
			</th>
			<th>Owner<a
				href="${pageContext.request.contextPath}/meetings/sortbyownerasc">
					<i class="fa fa-arrow-circle-o-up"></i>
			</a> <a
				href="${pageContext.request.contextPath}/meetings/sortbyownerdesc"><i
					class="fa fa-arrow-circle-o-down"></i> </a>
			</th>
			<th>Room<a
				href="${pageContext.request.contextPath}/meetings/sortbyroomasc">
					<i class="fa fa-arrow-circle-o-up"></i>
			</a> <a href="${pageContext.request.contextPath}/meetings/sortbyroomdesc"><i
					class="fa fa-arrow-circle-o-down"></i> </a>
			</th>

			<th>Date</th>
			<th>Start time</th>

			<th>End time</th>
			<th>Groups</th>
			<th>Level<a
				href="${pageContext.request.contextPath}/meetings/sortbylevelasc">
					<i class="fa fa-arrow-circle-o-up"></i>
			</a> <a
				href="${pageContext.request.contextPath}/meetings/sortbyleveldesc"><i
					class="fa fa-arrow-circle-o-down"></i> </a>
			</th>
			<th>Status<a
				href="${pageContext.request.contextPath}/meetings/sortbystatusasc">
					<i class="fa fa-arrow-circle-o-up"></i>
			</a> <a
				href="${pageContext.request.contextPath}/meetings/sortbystatusdesc"><i
					class="fa fa-arrow-circle-o-down"></i> </a>
			</th>
			<th></th>
			<th><a href="${pageContext.request.contextPath}/meetings/create"><i
					class="fa fa-plus"></i></a></th>
		</tr>


		<c:forEach var="meeting" items="${meetings}">
			<tr>
				<td>${meeting.id}</td>
				<td>${meeting.description}</td>
				<td>${meeting.subject.name}</td>
				<td>${meeting.owner.lastName}${meeting.owner.firstName}</td>
				<td>${meeting.room.name}</td>
				<td>${meeting.date}</td>
				<td>${meeting.startTime}</td>
				<td>${meeting.endTime}</td>
				<td><c:forEach items="${meeting.groups}" var="group">
						<p>${group.name}</p>
					</c:forEach></td>
				<td>${meeting.level}</td>
				<td>${meeting.status}<a
					href="${pageContext.request.contextPath}/meetings/editStatus/${meeting.id}"><i
						class="fa fa-pencil-square-o"></i></a>
				</td>
				<td><a
					href="${pageContext.request.contextPath}/meetings/delete/${meeting.id}"><i
						class="fa fa-trash-o"></i></a></td>
				<td><a
					href="${pageContext.request.contextPath}/meetings/edit/${meeting.id}"><i
						class="fa fa-pencil-square-o"></i></a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>