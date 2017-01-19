<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>User: ${user.lastName} ${user.firstName}</h3>
<div class="table-responsive">
	<table class="table table-hover">

			<tr><td><a href="updateUser/${user.id}"> update </a></td></tr>
			<tr><td><a href="updateUserPosition/${user.id}">
					change position </a></td></tr>
			<tr><td><a href="changeRole/${user.id}"> change role</a></td></tr>
			<tr><td><a href="addToGroup/${user.id}"> add to group</a></td></tr>

	</table>
</div>
