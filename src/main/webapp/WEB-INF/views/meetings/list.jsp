<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Subject</title>
</head>
<body>
	<h2>Subject</h2>
	<table>
		<tr>
			<th>ID</th>
			<th>Meeting Description <%--<a href="${pageContext.request.contextPath}/meetings/sortbynameasc"><i
					class="fa fa-arrow-circle-o-up"></i></a> <a href="${pageContext.request.contextPath}/subjects/sortbynamedesc"><i
					class="fa fa-arrow-circle-o-down"></i> 
					</a>
					--%>
			</th>
			<th>Meeting Subject <%--<a href="${pageContext.request.contextPath}/subjects/sortbydescriptionasc">
			 <i
					class="fa fa-arrow-circle-o-up"></i></a> <a href="${pageContext.request.contextPath}/subjects/sortbydescriptiondesc"><i
					class="fa fa-arrow-circle-o-down"></i> 
					</a>--%>
			</th>
			<th>Owner</th>
<%-- 			<th>
			<a href="${pageContext.request.contextPath}/subjects/create"><i
					class="fa fa-plus"></i></a>
					</th> --%>
				<th>Room</th>	
				<th>Groups</th>
				<th>Level</th>
				<th>Status</th>
		</tr>
		<c:forEach var="meeting" items="${meetings}">
			<tr>
				<td>${meeting.id}</td>
				<td>${meeting.description}</td>
				<td>${meeting.subject.name}</td>
				<td>${meeting.owner.lastName}${meting.owner.firstName}</td>
				<td>${meeting.room.name}</td>
				<td><c:forEach items="${meeting.groups}" var="group">
						<p>${group.name}</p>
					</c:forEach></td>
				<td>${meeting.level}</td>
				<td>${meeting.status}</td>	
				<%-- <td><a
					href="${pageContext.request.contextPath}/subjects/delete/${subject.id}"><i
						class="fa fa-trash-o"></i></a></td>
				<td><a
					href="${pageContext.request.contextPath}/subjects/edit/${subject.id}"><i
						class="fa fa-pencil-square-o"></i></a></td> --%>
			</tr>
		</c:forEach>
	</table>
</body>
</html>