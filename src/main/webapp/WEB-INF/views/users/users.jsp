<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Users</title>
</head>
<body>
	<c:forEach items="${users}" var="user">
		<tr>
			<td>${user.firstName}</td>
			<td>${user.lastName}</td>
			<td>${user.mail}</td>
			<td>${user.position}</td>
			<td>${subject.role}</td>
			<td><c:forEach items="${subject.groups}" var="group">
					<td>${group.name}</td>
				</c:forEach></td>
			<td><a href="/shedule/users/delete/${user.id}"> delete </a></td>
			<td><a href="/shedule/users/updateUser/${user.id}"> update </a></td>
			<td><a href="/shedule/users/updateUserPosition/${user.id}">
					change position </a></td>
			<td><a href="/shedule/users/changeRole/${user.id}"> change
					role </a></td>
			<td><a href="/shedule/users/addToGroup/${user.id}"> add to
					group </a></td>
			<td><a href="/shedule/users/banUser/${user.id}"> ban </a></td>
			<td><a href="/shedule/users/unBanUser/${user.id}"> unban </a></td>
		</tr>
		<br />
	</c:forEach>
</body>
</html>