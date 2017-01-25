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
	<h3 class="text-center">Meetings</h3>
	<table class="table table-hover">
		<tr>
			
			<th>Description <a
				href="${pageContext.request.contextPath}/meetings/sortbydescriptionasc"><i
					class="fa fa-arrow-circle-o-up"></i></a><a
				href="${pageContext.request.contextPath}/meetings/sortbydescriptiondesc"><i
					class="fa fa-arrow-circle-o-down"></i> </a>

			</th>
			<th>Subject <%-- <a
				href="${pageContext.request.contextPath}/meetings/sortbysubjectasc">
					<i class="fa fa-arrow-circle-o-up"></i>
			</a> <a
				href="${pageContext.request.contextPath}/meetings/sortbysubjectdesc"><i
					class="fa fa-arrow-circle-o-down"></i> </a>--%>
			</th>
			<th>Owner<%-- <a
				href="${pageContext.request.contextPath}/meetings/sortbyownerasc">
					<i class="fa fa-arrow-circle-o-up"></i>
			</a> <a
				href="${pageContext.request.contextPath}/meetings/sortbyownerdesc"><i
					class="fa fa-arrow-circle-o-down"></i> </a> --%>
			</th>
			<th>Room<%-- <a
				href="${pageContext.request.contextPath}/meetings/sortbyroomasc">
					<i class="fa fa-arrow-circle-o-up"></i>
			</a> <a href="${pageContext.request.contextPath}/meetings/sortbyroomdesc"><i
					class="fa fa-arrow-circle-o-down"></i> </a> --%>
			</th>

			<th>Date</th>
			<th>StartTime</th>

			<th>EndTime</th>
			<th>Groups</th>
			<th class="searchMeetingBig">Level<a
				href="${pageContext.request.contextPath}/meetings/sortbylevelasc">
					<i class="fa fa-arrow-circle-o-up"></i>
			</a><a
				href="${pageContext.request.contextPath}/meetings/sortbyleveldesc"><i
					class="fa fa-arrow-circle-o-down"></i></a>
			</th>
			<th>Status<a
				href="${pageContext.request.contextPath}/meetings/sortbystatusasc">
					<i class="fa fa-arrow-circle-o-up"></i>
			</a><a
				href="${pageContext.request.contextPath}/meetings/sortbystatusdesc"><i
					class="fa fa-arrow-circle-o-down"></i></a>
			</th>
			<th></th>
			<th><a href="${pageContext.request.contextPath}/meetings/create"><i
					class="fa fa-plus fa-lg"></i></a></th>
		</tr>

		<tr>
		
			<td><form:form method="post"
					action="${pageContext.request.contextPath}/meetings/searchmeetingsbydescription"
					modelAttribute="searchmeetingsbydescription">
					<form:input path="description" placeholder=" Search ..."
						class="searchMeetingBig" />
					<button type="submit" title="Search by name">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>
				
			<td><form:form method="post"
					action="${pageContext.request.contextPath}/meetings/searchmeetingsbysubject"
					modelAttribute="searchmeetingsbysubject">
					<form:input path="name" placeholder=" Search ..."
						class="searchMeeting" />
					<button type="submit" title="name">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>
				
			<td><form:form method="post"
					action="${pageContext.request.contextPath}/meetings/searchmeetingsbyowner"
					modelAttribute="searchmeetingsbyowner">
					<form:input path="lastName" placeholder=" Search ..."
						class="searchMeeting" />
					<button type="submit" title="name">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>

			<td><form:form method="post"
					action="${pageContext.request.contextPath}/meetings/searchmeetingsbyroom"
					modelAttribute="searchmeetingsbyroom">
					<form:input path="name" placeholder=" Search ..."
						class="searchMeeting" />
					<button type="submit" title="name">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>

			<td><form:form method="post"
					action="${pageContext.request.contextPath}/meetings/searchmeetingsbydate"
					modelAttribute="searchmeetingsbydate">

					<form:input type="date" path="date" id="date"  
						placeholder="YYYY-MM-DD" required="true" />

					<button type="submit" >
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>
			<td></td>
			<td></td>

			<td><form:form method="post"
					action="${pageContext.request.contextPath}/meetings/searchmeetingsbygroup"
					modelAttribute="searchmeetingsbygroup">
					<form:input path="name" placeholder=" Search ..."
						class="searchMeeting" />
					<button type="submit" title="name">
						<i class="fa fa-search"></i>
					</button>
				</form:form></td>

			<td><%-- <form:form method="post"
					action="${pageContext.request.contextPath}/meetings/searchmeetingsbylevel"
					modelAttribute="searchmeetingsbylevel">
					<form:input path="level" placeholder=" Search ..."
						class="searchMeeting" />
					<button type="submit" title="name">
						<i class="fa fa-search"></i>
					</button>
				</form:form> --%></td>

			<td><%-- <form:form method="post"
					action="${pageContext.request.contextPath}/meetings/searchmeetingsbystatus"
					modelAttribute="searchmeetingsbystatus">
					<form:input path="description" placeholder=" Search ..."
						class="searchMeetingBig" />
					<button type="submit" title="name">
						<i class="fa fa-search"></i>
					</button>
				</form:form> --%></td>
			<td></td>
			<td></td>
		</tr>

		<c:forEach var="meeting" items="${meetings}">
			<tr>
				
				<td>${meeting.description}</td>
				<td>${meeting.subject.name}</td>
				<td>${meeting.owner.lastName} ${meeting.owner.firstName}</td>
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
						class="fa fa-trash-o fa-lg"></i></a></td>
				<td><a
					href="${pageContext.request.contextPath}/meetings/edit/${meeting.id}"><i
						class="fa fa-pencil-square-o fa-lg"></i></a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>